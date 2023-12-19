package modelo;

import excepciones.CampoVacioExcepcion;
/**
 * Esta clase (POJO) será una representación de la tabla categoria
 * @author AGE
 * @version 2
 */
public class Categoria extends Entidad {
    private int id;
    private String categoria;

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
     * Getter para atributo categoria
     * @return el valor del atributo categoria
     */
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
    public String toString() {
        return String.format("%d. %s",id,categoria);
    }
}
