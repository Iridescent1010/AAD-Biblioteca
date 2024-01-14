package vista.componentes;

import modelo.old.Libro;

import javax.swing.*;
import java.util.List;
/**
 * Esta clase permite crear una tabla donde mostrar la información de los Libros
 * @author AGE
 * @version 2
 */
public class TablaLibros extends JTable  {

    public TablaLibros() {
        setFillsViewportHeight(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


    /**
     * Rellena la tabla con la información con la lista de libros a mostrar
     */
    public void muestraTabla(List<Libro> libros) {
        String[] nombreColumnas = {"Código", "Titulo", "Autor", "Editorial", "Categoria"};
        Object datos[][] = new Object[libros.size()][nombreColumnas.length];
        int i = 0;
        for (Libro libro : libros) {
            datos[i][0] = libro.getId();
            datos[i][1] = libro.getNombre();
            datos[i][2] = libro.getAutor();
            datos[i][3] = libro.getEditorial();
            datos[i][4] = libro.getCategoriaDescr();
            i++;
        }
        setModel(new MiModeloDatosSoloLectura(datos, nombreColumnas));
    }
}