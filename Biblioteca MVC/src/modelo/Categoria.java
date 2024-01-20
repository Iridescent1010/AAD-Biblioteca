package modelo;

import excepciones.CampoVacioExcepcion;
import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * Esta clase (POJO) será una representación de la tabla categoria
 * @author AGE
 * @version 2
 */
@Entity
@Table(name = "categoria", schema = "BIBLIOTECA")
public class Categoria extends Entidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "categoria", nullable = true, length = -1)
    private String categoria;
    @OneToMany(mappedBy = "categoria")
    private Collection<Libro> libros;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    /**
     * Setter para asignar un categoria nuevo;
     * @param categoria nuevo valor para el atributo categoria
     */
    public void setCategoria(String categoria) throws CampoVacioExcepcion {
        if (categoria.trim().equals(""))
            throw new CampoVacioExcepcion("CATEGORIA");
        else this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria that = (Categoria) o;
        return id == that.id && Objects.equals(categoria, that.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoria);
    }

    public Collection<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Collection<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return String.format("%d. %s",id,categoria);
    }
}
