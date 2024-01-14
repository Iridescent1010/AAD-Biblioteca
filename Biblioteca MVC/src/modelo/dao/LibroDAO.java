package modelo.dao;

import modelo.old.Libro;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Esta será la intefaz para manejar los libros de la BD BIBLIOTECA
 * @author AGE
 * @version 2
 */
public interface LibroDAO {

    /**
     * Implementaremos las instrucciones necesarias para poder
     * insertar un registro asociado a la tabla libro para algún
     * sistema gestor de BD o de ficheros
     * @return verdad en el caso de que la inserción ser realice con éxito
     * @throws SQLException
     * @throws IOException
     */
    boolean insertar(Libro libro) throws Exception;

    /**
     * Implementaremos las instrucciones necesarias para poder
     * modificar un registro asociado a la tabla libro para algún
     * sistema gestor de BD o de ficheros
     * @return verdad en el caso de que la modificación concluya éxito
     * @throws Exception
     */
    boolean modificar(Libro libro) throws Exception;

    /**
     * Implementaremos las instrucciones necesarias para poder
     * borrar un registro asociado a la tabla libro para algún
     * sistema gestor de BD o de ficheros
     * @return verdad en el caso de que el borrado concluya éxito
     * @throws Exception
     */
    boolean borrar(int id) throws Exception;

    List<Libro> leerAllLibros() throws Exception;

    List<Libro> leerLibrosOR(int id, String titulo, String autor, String editorial, int categoria) throws Exception;

    Libro getLibro(int id) throws Exception;
}
