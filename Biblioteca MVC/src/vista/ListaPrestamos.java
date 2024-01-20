package vista;

import modelo.Categoria;
import modelo.Prestamo;
import presentador.PresentadorPrestamo;
import presentador.VistaPrestamos;
import vista.componentes.MiModeloDatosSoloLectura;
import vista.helper.Prestamos;
import vista.helper.SwgAuxiliar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.List;
/**
 * Formulario que muestra lista todos los registros asociados a una consulta relacionada con la tabla préstamos
 * @author AGE
 * @version 2
 */

public class ListaPrestamos extends JInternalFrame implements VistaPrestamos, MouseListener, FocusListener, KeyListener, ActionListener {
    private static final int WIDTH = 625;
    private static final int HEIGHT = 500;
    private List<Prestamo> prestamos;
    private PresentadorPrestamo presentador;
    private JMenuItem miFicha; {
        miFicha = new JMenuItem("Ficha");
        miFicha.setMnemonic('F');
        miFicha.addActionListener(this);
    }

    private JMenuItem miNuevo; {
        miNuevo = new JMenuItem("Nuevo");
        miNuevo.setMnemonic('N');
        miNuevo.addActionListener(this);
    }

    private JMenuItem miBorra; {
        miBorra = new JMenuItem("Borra");
        miBorra.setMnemonic('B');
        miBorra.addActionListener(this);
    }

    private JPopupMenu jPopupMenu; {
        jPopupMenu = new JPopupMenu();
        jPopupMenu.add(miFicha);
        jPopupMenu.add(miNuevo);
        jPopupMenu.add(miBorra);
    }

    private JTable jTable; {
        jTable = new JTable();
        jTable.addMouseListener(this);
        jTable.addFocusListener(this);
        jTable.addKeyListener(this);
        jTable.setFillsViewportHeight(true);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private JScrollPane scrollPane= new JScrollPane(jTable);

    @Override
    public void lanzar() {
        setVisible(true);
    }

    @Override
    public void setPresentador(PresentadorPrestamo presentador) throws Exception {
        this.presentador=presentador;
        presentador.listaAllPrestamos();
    }

    @Override
    public Prestamo getPrestamo() {
        if (jTable.getSelectedRow()==-1){
            return null;
        }
        return prestamos.get(jTable.getSelectedRow());
    }

    @Override
    public void setPrestamos(List<Prestamo> listaPrestamos) {
        this.prestamos=listaPrestamos;
        muestraTabla();
    }
    @Override
    public void setCategorias(List<Categoria> categorias) {
        // sin implementación
    }
    public ListaPrestamos()  {
        setVentana();
        setContenedores();
        addKeyListener(this);

    }

    private void setContenedores() {
        setLayout(new GridLayout());
        add(scrollPane);
    }

    private void setVentana() {
        getContentPane().setBackground(Color.WHITE);
        setTitle("Listado de Prestamos:");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        Dimension dime = new Dimension(WIDTH, HEIGHT);
        setBounds(FormMain.posInterna(), FormMain.posInterna(), WIDTH, HEIGHT);
        setMinimumSize(dime);
        setSize(dime);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
    }

    public void muestraTabla() {
        String[] nombreColumnas = {"Código", "Libro", "Usuario", "Fecha"};
        Object datos[][] = new Object[prestamos.size()][nombreColumnas.length];
        int i = 0;
        for (Prestamo prestamo : prestamos) {
            datos[i][0] = prestamo.getIdPrestamo();
            datos[i][1] = prestamo.getObjLibro();
            datos[i][2] = prestamo.getObjUsuario();
            datos[i][3] = prestamo.getFecha();
            i++;
        }
        jTable.setModel(new MiModeloDatosSoloLectura(datos, nombreColumnas));
        //jTable.setModel(new DefaultTableModel(datos,nombreColumnas)); No conseguía que fuese de solo lectura
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(jTable)) {
            if (e.getClickCount() == 2)
                muestraFicha(getPrestamo());
            else if (e.getButton() == MouseEvent.BUTTON3)
                jPopupMenu.show(jTable, e.getX(), e.getY());
        }
    }

    private void muestraFicha(Prestamo prestamo) {
        try {
            FormMain.getInstance().getDesktopPane().add(Prestamos.fichaPrestamo(prestamo));
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
        FormMain.getInstance().getDesktopPane().selectFrame(false);
    }

    private void borrar(Prestamo prestamo) {
        if (prestamo !=null){
            if (JOptionPane.showConfirmDialog(this,
                    String.format("¿Desea BORRAR el préstamo:\n%s\n%s?",
                            prestamo.getObjLibro(), prestamo.getObjUsuario()),
                    "Atención:",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    presentador.borra();
                } catch (Exception e) {
                    SwgAuxiliar.msgExcepcion(e);
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent().equals(jTable))
            FormMain.barraEstado("Realice un doble click o pulse espacio sobre la fila o celda para ver su ficha detalle");
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            dispose();
        else if (e.getKeyCode() == KeyEvent.VK_SPACE)
            muestraFicha(getPrestamo());
        else if (e.getKeyCode()==KeyEvent.VK_DELETE)
            borrar(getPrestamo());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(miFicha))
            muestraFicha(getPrestamo());
        else if (e.getSource().equals(miNuevo))
            muestraFicha(new Prestamo());
        else if (e.getSource().equals(miBorra))
            borrar(getPrestamo());
    }
}


