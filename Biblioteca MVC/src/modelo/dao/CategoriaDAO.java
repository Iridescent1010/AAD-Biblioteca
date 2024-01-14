package modelo.dao;

import modelo.old.Categoria;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Esta será la intefaz para manejar las categorias de la BD BIBLIOTECA
 * @author AGE
 * @version 2
 */
public interface CategoriaDAO {

    /**
     * Implementaremos las instrucciones necesarias para poder
     * insertar un registro asociado a la tabla categoría para algún
     * sistema gestor de BD o de ficheros
     *
     * @return verdad en el caso de que la inserción ser realice con éxito
     * @throws SQLException
     * @throws IOException
     */
    boolean inserta(Categoria categoria) throws Exception;

    /**
     * Implementaremos las instrucciones necesarias para poder
     * modificar un registro asociado a la tabla categoria para algún
     * sistema gestor de BD o de ficheros
     *
     * @return verdad en el caso de que la modificación concluya éxito
     * @throws SQLException
     * @throws IOException
     */
    boolean modificar(Categoria categoria) throws Exception;

    /**
     * Implementaremos las instrucciones necesarias para poder
     * borrar un registro asociado a la tabla categoria para algún
     * sistema gestor de BD o de ficheros
     *
     * @return verdad en el caso de que el borrado concluya éxito
     * @throws SQLException
     * @throws IOException
     */
    boolean borrar(int id) throws Exception;


    Categoria categoria(int id) throws Exception;

    List<Categoria> leerAllCategorias() throws Exception;


}
