package singleton;

import helper.Aux;
import helper.EncriptacionDesencriptacion;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
/**
 * Esta clase solo permitira la instanciación de un único objeto
 * para definir y mantener la configuración de una aplicación,
 * teniendo esta implementada en un fichero de propiedades
 * ubicado en la carpeta ficheros y con nombre de archivo biblioteca.config
 * @author AGE
 * @version 2
 */
public class Configuracion {
    public static final String FILE_CONF="ficheros/biblioteca.config";
    private static final String claveSecreta="asdf234fsdva%l9asdnklfa@f4f_adfafaAAaad;";
    private static Configuracion conf=null;
    private static final String DRIVER_DEFAULT="com.mysql.cj.jdbc.Driver";

    private String driver;
    private String url;
    private String user;
    private String password;

    private Configuracion() throws Exception {
        Properties p = new Properties();
        p.load(new FileReader(FILE_CONF));
        driver = p.getProperty("driver");
        url = p.getProperty("url");
        user = p.getProperty("user");
        password = p.getProperty("password");
    }

    /**
     * para obtener el driver que necesita el contralador JDBC para la conexión
     * @return la clase del driver JDBC
     */
    public String getDriver() {
        if (driver.equals(""))
            return DRIVER_DEFAULT;
        else return driver;
    }

    /**
     * actualiza el driver en la configuración
     * @param driver la clase del driver JDBC
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }
    /**
     * para obtener la configuracíón del servidor/puerto/esquema de la BD
     * @return cadena de conexión a la bd
     */
    public String getUrl() {
        if (url.equals(""))
            return "Indica la url en el fichero de configuración";
        return url;
    }
    /**
     * actualiza la cadena de conexión
     * @param url cadena de conexión a la bd
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * para obtener el usuario de la bd
     * @return usuario de la bd
     */
    public String getUser() {
        return user;
    }
    /**
     * actualiza el usuario de conexión
     * @param user usuario de conexión a la bd
     */
    public void setUser(String user) {
        this.user = user;
    }
    /**
     * para obtener la contraseña de la bd asociada a un usario
     * @return contraseña de la bd
     */
    public String getPassword() throws Exception {
        return EncriptacionDesencriptacion.desencriptar(password,claveSecreta);
    }
    /**
     * actualiza la contraseña de la bd asociada a un usario
     * @param password contraseña de la bd
     */
    public void setPassword(String password) throws Exception {
        this.password = EncriptacionDesencriptacion.encriptar(password,claveSecreta);
    }
    /**
     * implentación del patrón de diseño Singleton para esta clase
     * @return instancia única del objeto de esta clase para la aplicación actual
     * @throws IOException errores de entrada y salida cuando trabajamos con el fichero de configuración de la aplicación
     */
    public static Configuracion getInstance() throws Exception {
        if (conf==null){
            conf=new Configuracion();
            Runtime.getRuntime().addShutdownHook(new MiApagado());
        }
        return conf;
    }
    private static class MiApagado extends Thread {
        @Override
        public void run() {
            super.run();
            if (conf != null) {
                Properties p = new Properties();
                p.setProperty("driver", conf.driver);
                p.setProperty("url", conf.url);
                p.setProperty("user", conf.user);
                try {
                    p.setProperty("password", EncriptacionDesencriptacion.encriptar(conf.password, claveSecreta));
                    p.store(new FileWriter(FILE_CONF), String.format("Actualizado el %s", Aux.dameFechaActual()));
                    conf = null;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
