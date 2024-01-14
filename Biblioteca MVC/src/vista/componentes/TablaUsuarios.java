package vista.componentes;

import modelo.old.Usuario;
import javax.swing.*;
import java.util.List;
/**
 * Esta clase permite crear una tabla donde mostrar la información de los usuarios
 * @author AGE
 * @version 2
 */
public class TablaUsuarios extends JTable {
    public TablaUsuarios() {
        setFillsViewportHeight(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Rellena la tabla con la información con la lista de usuarios a mostrar
     */
    public void muestraTabla(List<Usuario> usuarios) {
        String[] nombreColumnas={"Código","Nombre","Apellidos"};
        Object datos [][]= new Object[usuarios.size()][nombreColumnas.length];
        int i=0;
        for (Usuario usuario:usuarios){
            datos[i][0]=usuario.getId();
            datos[i][1]=usuario.getNombre();
            datos[i][2]=usuario.getApellidos();
            i++;
        }
        setModel(new MiModeloDatosSoloLectura(datos,nombreColumnas));
    }
}
