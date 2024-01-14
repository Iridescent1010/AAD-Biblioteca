package modelo.dao;

import modelo.old.Historico;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Esta será la intefaz para manejar los tabla de log 
 * Historico de la BD BIBLIOTECA
 * @author AGE
 * @version 2
 */
public interface HistoricoDAO {
    /**
     * Método para obtener el objeto que contiene la parte de la información asociada al modelo.
     * @return la instancía del objeto Historico (pojo)
     */
    Historico getHistorico();
    /**
     * Implementaremos las instrucciones necesarias para poder
     * insertar un registro asociado a la tabla historico para algún
     * sistema gestor de BD o de ficheros
     * @return verdad en el caso de que la inserción ser realice con éxito
     * @throws SQLException
     * @throws IOException
     */
    boolean insertar() throws SQLException, IOException;

    /**
     * Implementaremos las instrucciones necesarias para poder
     * modificar un registro asociado a la tabla historico para algún
     * sistema gestor de BD o de ficheros
     * @return verdad en el caso de que la modificación concluya éxito
     * @throws SQLException
     * @throws IOException
     */
}
