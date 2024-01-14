package modelo.dao;

import excepciones.CampoVacioExcepcion;
import modelo.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Esta será la intefaz para manejar los usuarios de la BD BIBLIOTECA
 * @author AGE
 * @version 2
 */
public interface UsuarioDAO {
    /**
     * Implementaremos las instrucciones necesarias para poder
     * insertar un registro asociado a la tabla usuario para algún
     * sistema gestor de BD o de ficheros
     * @return verdad en el caso de que la inserción ser realice con éxito
     * @throws SQLException
     * @throws IOException
     */
    boolean insertar(Usuario usuario) throws Exception;

    /**
     * Implementaremos las instrucciones necesarias para poder
     * modificar un registro asociado a la tabla usuario para algún
     * sistema gestor de BD o de ficheros
     * @return verdad en el caso de que la modificación concluya éxito
     * @throws SQLException
     * @throws IOException
     */
    boolean modificar(Usuario usuario) throws Exception;

    /**
     * Implementaremos las instrucciones necesarias para poder
     * borrar un registro asociado a la tabla usuario para algún
     * sistema gestor de BD o de ficheros
     * @return verdad en el caso de que el borrado concluya éxito
     * @throws SQLException
     * @throws IOException
     */

    boolean borrar(int id) throws Exception;
    /**
     * Este método estático devuelve todos los usuarios de la BD,
     * este método tendremos en un futuro reimplmentarlo por rangos de x,
     * para que el rendimiento no decaiga cuando la tabla crezca
     * @return un arraylist con todos los usuarios de la BD
     * @throws SQLException cualquier error asociado a la consulta sql
     * @throws CampoVacioExcepcion en el caso de que contenga un usuario con nombre o apellidos a null
     */
    List<Usuario> leerAllUsuarios() throws Exception;
    /**
     * Este método estático devuelve todos los usuarios de la BD,
     * que cumplan la condición según los parametros
     * este método tendremos en un futuro reimplmentarlo por rangos de x,
     * para que el rendimiento no decaiga cuando la tabla crezca
     * @param id búsqueda de usuario con dicho código
     * @param nombre búsqueda de usuarios con dicho nombre
     * @param apellidos búsqueda de usuarios con dichos apellidos
     * @return un arraylist con todos los usuarios de la BD
     * @throws SQLException cualquier error asociado a la consulta sql
     * @throws CampoVacioExcepcion en el caso que contenga una categoria con categoria a null
     * */
    List<Usuario> leerUsuariosOR(int id, String nombre, String apellidos) throws Exception;

    /**
     * Para instanciar un objeto usuario a partir de la clave id
     * @param id clave primaria de la tabla usuario
     * @return el objeto usuario asociado a una clave primaria
     * @throws SQLException cualquier error asociado a la consulta sql
     * @throws CampoVacioExcepcion en el caso de que contenga un usuario con usuario a null
     */
    Usuario getUsuario(int id) throws Exception;
}
