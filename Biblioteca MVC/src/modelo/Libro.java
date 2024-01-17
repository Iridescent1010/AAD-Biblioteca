package modelo;

import excepciones.CampoVacioExcepcion;
import modelo.dao.helper.Entidades;

import javax.persistence.*;
import java.util.Objects;

/**
 * Esta clase (POJO) será una representación de la tabla libro
 * @author AGE
 * @version 2
 */
@Entity
@Table(name = "libro", schema = "BIBLIOTECA")
public class Libro extends Entidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = true, length = -1)
    private String nombre;
    @Basic
    @Column(name = "autor", nullable = true, length = -1)
    private String autor;
    @Basic
    @Column(name = "editorial", nullable = true, length = -1)
    private String editorial;
    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id")
    private Categoria categoria;

    @Transient
    private int categoriaId;
    /**
     * Getter para atributo id
     * @return el valor del atributo id
     */
    @Override
    public int getId() {
        return id;
    }
    /**
     * Setter para asignar un codigo nuevo;
     * @param id nuevo valor para el atributo id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter para atributo nombre
     * @return el valor del atributo nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Setter para asignar un nombre nuevo;
     * @param nombre nuevo valor para el atributo nombre
     */
    public void setNombre(String nombre) throws CampoVacioExcepcion {
        if (nombre.trim().equals(""))
            throw new CampoVacioExcepcion("NOMBRE");
        else this.nombre = nombre;
    }
    /**
     * Getter para atributo autor
     * @return el valor del atributo autor
     */
    public String getAutor() {
        return autor;
    }
    /**
     * Setter para asignar un autor nuevo;
     * @param autor nuevo valor para el atributo autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }
    /**
     * Getter para atributo editorial
     * @return el valor del atributo editorial
     */
    public String getEditorial() {
        return editorial;
    }
    /**
     * Setter para asignar una editorial nuevo;
     * @param editorial nuevo valor para el atributo editorial
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    /**
     * Getter para atributo categoria
     * @return el valor del atributo categoria
     */
    public int getCategoriaId() {
        return categoriaId;
    }
    /**
     * Setter para asignar una categoria nuevo;
     * @param categoria nuevo valor para el atributo categoria
     */
    public void setCategoriaId(int categoria) {
        categoriaId = categoria;
    }

    @Override
    public String toString(){
        return String.format("%d. %s %s",id,nombre,editorial);
    }

    public Categoria getObjCategoria(){
        return Entidades.categoria(categoriaId);
    }
    public String getCategoriaDescr() {
       Categoria oCategoria = getObjCategoria();
       if (oCategoria!=null)
           return oCategoria.getCategoria();
       else return String.format("Categoria %d desconocida", categoriaId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return id == libro.id && Objects.equals(nombre, libro.nombre) && Objects.equals(autor, libro.autor) && Objects.equals(editorial, libro.editorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, autor, editorial);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

