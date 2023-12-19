package singleton;

import modelo.dao.helper.LogFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase solo permitira la instanciación de un único objeto
 * para definir y mantener la conexión con una BD MySQL
 * para su correcto funcionamiento requerirar de otro objeto configuración
 * @author AGE
 * @version 2
 */
public class ConexionMySQL {
    private static ConexionMySQL conexionMySQL = null;
    private Connection conexion;
    private ConexionMySQL(Connection conexion) {
        this.conexion=conexion;
    }

    /**
     * Conexión JDBC a nuestra BD
     * @return un objeto con la conexión actual a la BD usando JDBC
     */
    public Connection getConexion(){
        return conexion;
    }

    /**
     * implentación del patrón de diseño Singleton para esta clase
     * @return instancia única del objeto de esta clase para la aplicación actual
     * @throws ClassNotFoundException en el caso de no existir la clase com.mysql.cj.jdbc.Driver
     * @throws SQLException errores asociados a sentencias SQL
     * @throws IOException errores de entrada y salida cuando trabajamos con el fichero de configuración de la aplicación
     */
    public static ConexionMySQL getInstance() throws Exception {
        if ( conexionMySQL == null ) {
            Configuracion myConf=Configuracion.getInstance();
            Class.forName(myConf.getDriver()) ; // cargamos la clase que tiene el driver para MySQL
            Connection conexion = DriverManager.getConnection(myConf.getUrl(),myConf.getUser(),myConf.getPassword());
            conexion.setAutoCommit(true); // por defecto se hace autocommit;
            conexionMySQL=new ConexionMySQL(conexion);
            String msg=String.format("Usuario conectado: %s a %s (%s)",myConf.getUser(),myConf.getUrl(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            LogFile.saveLOG(msg);
            Runtime.getRuntime().addShutdownHook(new ConexionMySQL.MiApagado());
        }
        return conexionMySQL ;
    }
    private void desconectar() throws SQLException {
        if ( conexion != null )
            conexion.close() ;
    }
    private static class MiApagado extends Thread {
        @Override
        public void run() {
            try {
                if (conexionMySQL!=null) {
                    String msg=String.format("Usuario actual desconectado a las %s",
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    LogFile.saveLOG(msg);
                    conexionMySQL.desconectar();
                    conexionMySQL = null;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
