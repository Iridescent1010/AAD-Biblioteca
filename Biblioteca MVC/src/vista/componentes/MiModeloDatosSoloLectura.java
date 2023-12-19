package vista.componentes;

import javax.swing.table.AbstractTableModel;
/**
 * Esta clase permite crear modelo para modelar las distintas JTable de la aplicación
 * @author AGE
 * @version 2
 */
public class MiModeloDatosSoloLectura extends AbstractTableModel {
    private Object datos[][];
    private String[] nombreColumnas;

    @Override
    public int getRowCount() {
        return datos.length;
    }

    @Override
    public int getColumnCount() {
        return nombreColumnas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return nombreColumnas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datos[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public MiModeloDatosSoloLectura(Object[][] datos, String[] nombreColumnas) {
        this.datos = datos;
        this.nombreColumnas = nombreColumnas;
    }
}
