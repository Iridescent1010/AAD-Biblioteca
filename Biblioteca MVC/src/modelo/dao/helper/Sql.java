package modelo.dao.helper;

import singleton.ConexionMySQL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.*;

/**
 * Clase auxiliar con distintas funcionalidades a la hora de trabajar con SQL
 * @author AGE
 * @version 2
 */
public class Sql {
    /**
     * Prepara la parte where de una cláusula para concatenar condiciones usando el OR
     * @param where parte where actual antes de su ejecución
     * @param opcion condición a incluir en el futuro where
     * @return devuelve la parte where junto con la condición enviada y concatenada según una OR
     */
    public static String rellenaWhereOR(String where, String opcion) {
        if (!opcion.equals("")){
            if (where.equals(""))
                where=opcion;
            else where+=" OR "+opcion;
        }
        return where;
    }
    /**
     * Prepara la parte where de una cláusula para concatenar condiciones usando el AND
     * @param where parte where actual antes de su ejecución
     * @param opcion condición a incluir en el futuro where
     * @return devuelve la parte where junto con la condición enviada y concatenada según una AND
     */
    public static String rellenaWhereAND(String where, String opcion) {
        if (!opcion.equals("")){
            if (where.equals(""))
                where=opcion;
            else where+=" AND "+opcion;
        }
        return where;
    }
    /**
     * Permitirá grabar el contenido de una tabla en un fichero csv
     * dentro del path
     * @param path fichero donde se grabará el contenido de la tabla
     * @param tabla nombre de la tabla a importar
     * @param delimiter caracter delimitador
     */
    public static void importCSV(Path path, String tabla,char delimiter) throws Exception {
        String sql = "SELECT * FROM "+tabla;
        Connection con = ConexionMySQL.getInstance().getConexion();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            LogFile.saveLOG(sql);
            // escribimos la linea de la cabecera
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            String cabecera="";
            int numColumn=resultSetMetaData.getColumnCount();
            for (int i=1;i<=numColumn;i++) {
                cabecera+=resultSetMetaData.getColumnName(i);
                if (i!=numColumn)
                    cabecera+=delimiter;
                else cabecera+='\n';
            }
            Files.writeString(path,cabecera, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            while (rs.next()){
                String fila="";
                for (int i=1; i<=numColumn;i++) {
                    fila+=rs.getObject(i).toString();
                    if (i!=numColumn)
                        fila+=delimiter;
                    else fila+='\n';
                }
                Files.writeString(path,fila, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            }
        }
    }
}
