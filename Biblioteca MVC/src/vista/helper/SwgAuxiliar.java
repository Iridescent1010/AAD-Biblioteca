package vista.helper;

import helper.Table;
import modelo.dao.helper.Sql;
import vista.FormMain;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase Auxiliar pensada para gestionar los mensajes mas comunes en una aplicación implementada en swing
 * @author AGE
 * @version 2
 */
public class SwgAuxiliar {
    /**
     * Muestra el mensaje de una excepción en un panel option
     * @param e excepción que contiene el mensaje
     */
    public static void msgExcepcion(Exception e){
        Component parent = FormMain.getInstance();
        JOptionPane.showMessageDialog(parent,e.getMessage(),"Error: ",JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Muestra el mensaje en un panel option
     * @param msg  contiene el mensaje
     */
    public static void msgError(String msg){
        Component parent = FormMain.getInstance();
        JOptionPane.showMessageDialog(parent,msg,"Error: ",JOptionPane.ERROR_MESSAGE);
    }

    public static void msgInfo(String msg) {
        JOptionPane.showMessageDialog(FormMain.getInstance(), msg, "Información: ", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Para asignar a un panel que los objetos que reciben el foco puedan responder al enter y al tab
     * @param panel objeto panel donde actuara las teclas enter y tab
     */
    public static void AsignaTeclaEnterTab(JPanel panel){
        // Configuro el panel central para que permita pulsar teclas enter
        // y tab para pasar de campo
        Set<AWTKeyStroke> teclas = new HashSet<AWTKeyStroke>();
        teclas.add(AWTKeyStroke.getAWTKeyStroke(
                KeyEvent.VK_ENTER, 0));
        teclas.add(AWTKeyStroke.getAWTKeyStroke(
                KeyEvent.VK_TAB, 0));
        panel.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
                teclas);
    }

    /**
     * Permitirá grabar el contenido de una tabla en un fichero csv
     * dentro de la carpeta ficheros/ListaDeTABLA.csv
     * @param table tabla a grabar todo el contenido
     */
    public static void grabarCSV(Table table) throws Exception {
        Path path = Paths.get("ficheros/csv/"+table.toString().toLowerCase()+".csv");
        Sql.importCsvHibernate(path, table);
    }
}
