package vista;

import modelo.Categoria;
import modelo.Libro;
import vista.componentes.MiModeloDatosSoloLectura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;

public class ConsultaLibro extends JDialog implements KeyListener {

    private static final int WIDTH = 625;
    private static final int HEIGHT = 200;
    private JTable tablaLibros;
    private JScrollPane scrollPane;


    Collection<Libro> libros;

    public ConsultaLibro(Frame owner, String title, boolean modal, Categoria categoria) {
        super(owner, title, modal);
        this.libros = categoria.getLibros();
        initComponents();

        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setTitle(title);
        Dimension dime = new Dimension(WIDTH, HEIGHT);
        setBounds(FormMain.posInterna(),FormMain.posInterna(), WIDTH, HEIGHT);
        setMinimumSize(dime);
        setSize(dime);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        tablaLibros = new JTable();
        tablaLibros.setFillsViewportHeight(true);
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaLibros.addKeyListener(this);

        scrollPane = new JScrollPane(tablaLibros);
        add(scrollPane);
        muestraTabla(libros);
    }

    public void muestraTabla(Collection<Libro> libros) {
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
        tablaLibros.setModel(new MiModeloDatosSoloLectura(datos, nombreColumnas));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
            dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
