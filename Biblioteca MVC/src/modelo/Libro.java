package modelo;

import excepciones.CampoVacioExcepcion;
import modelo.dao.helper.Entidades;

/**
 * Esta clase (POJO) será una representación de la tabla libro
 * @author AGE
 * @version 2
 */
public class Libro extends Entidad{
    private int id;
    private String nombre;
    private String autor;
    private String editorial;
    private int categoria;
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
    public int getCategoria() {
        return categoria;
    }
    /**
     * Setter para asignar una categoria nuevo;
     * @param categoria nuevo valor para el atributo categoria
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    /**
     * realiza una copia de otro objeto libro en esta instancia
     * @param libro objeto desde donde copiar la información
     */
    public void clona(Libro libro){
        this.id=libro.getId();
        this.nombre=libro.getNombre();
        this.autor=libro.getAutor();
        this.editorial=libro.getEditorial();
        this.categoria=libro.getCategoria();
    }
    @Override
    public String toString(){
        return String.format("%d. %s %s",id,nombre,editorial);
    }

    public Categoria getObjCategoria(){
        return Entidades.categoria(categoria);
    }
    public String getCategoriaDescr() {
       Categoria oCategoria = getObjCategoria();
       if (oCategoria!=null)
           return oCategoria.getCategoria();
       else return String.format("Categoria %d desconocida",categoria);
    }
}

