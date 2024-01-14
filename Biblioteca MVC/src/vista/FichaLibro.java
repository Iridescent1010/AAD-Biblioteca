package vista;

import excepciones.CampoVacioExcepcion;
import modelo.old.Categoria;
import modelo.old.Libro;
import presentador.PresentadorLibro;
import presentador.VistaLibro;
import vista.helper.SwgAuxiliar;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Formulario que permite interactuar (insertar, modificar o borrar)
 * con la información de la tabla Libro
 * @author AGE
 * @version 2
 */
public class FichaLibro extends JInternalFrame implements VistaLibro, ActionListener, InternalFrameListener, FocusListener, KeyListener {
    private static final int WIDTH = 450;
    private static final int HEIGHT = 250;
    private Libro libro;
    private PresentadorLibro presentador;

    private JTextField eTitulo;
    private JTextField eAutor;
    private JPanel pTitulo;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pTitulo = new JPanel(flLeft);
        JLabel lTitulo = new JLabel("Título:");
        eTitulo = new JTextField();
        eTitulo.setColumns(30);
        eTitulo.addFocusListener(this);
        eTitulo.addKeyListener(this);
        pTitulo.add(lTitulo);
        pTitulo.add(eTitulo);
    }
    private JPanel pAutor;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pAutor = new JPanel(flLeft);
        JLabel lAutor = new JLabel("Autor:");
        eAutor = new JTextField();
        eAutor.setColumns(30);
        eAutor.addFocusListener(this);
        eAutor.addKeyListener(this);
        pAutor.add(lAutor);
        pAutor.add(eAutor);
    }
    private JTextField eEditorial;
    private JPanel pEditorial;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pEditorial = new JPanel(flLeft);
        JLabel lEditorial = new JLabel("Editorial:");
        eEditorial = new JTextField();
        eEditorial.setColumns(30);
        eEditorial.addFocusListener(this);
        eEditorial.addKeyListener(this);
        pEditorial.add(lEditorial);
        pEditorial.add(eEditorial);
    }
    private JComboBox cbCategoria= new JComboBox();{
        cbCategoria.addFocusListener(this);
        cbCategoria.addKeyListener(this);
    }
    private JPanel pCategoria;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pCategoria = new JPanel(flLeft);
        JLabel lCategoria = new JLabel("Categoria:");
        pCategoria.add(lCategoria);
        pCategoria.add(cbCategoria);
    }

    private JPanel pCentro;{
        pCentro = new JPanel(new GridLayout(4,0));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Datos del libro");
        pCentro.setBorder(titledBorder);
        pCentro.add(pTitulo);
        pCentro.add(pAutor);
        pCentro.add(pEditorial);
        pCentro.add(pCategoria);
        SwgAuxiliar.AsignaTeclaEnterTab(pCentro);
    }
    private JButton bBorrar;
    private JPanel pSurBLeft;{
        pSurBLeft = new JPanel();
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pSurBLeft.setLayout(flLeft);
        bBorrar= new JButton("Borrar");
        bBorrar.setMnemonic('B');
        bBorrar.addKeyListener(this);
        bBorrar.addActionListener(this);
        bBorrar.addFocusListener(this);
        pSurBLeft.add(bBorrar);
    }
    private JButton bGuardar;
    private JButton bSalir;
    private JPanel pSurBRight;{
        pSurBRight = new JPanel();
        FlowLayout flRight = new FlowLayout();
        flRight.setAlignment(FlowLayout.RIGHT);
        pSurBRight.setLayout(flRight);
        bGuardar= new JButton("Guardar");
        bGuardar.setMnemonic('G');
        bGuardar.addFocusListener(this);
        bGuardar.addActionListener(this);
        bGuardar.addKeyListener(this);
        bSalir= new JButton("Salir");
        bSalir.addFocusListener(this);
        bSalir.addActionListener(this);
        bSalir.addKeyListener(this);
        bSalir.setMnemonic('S');
        pSurBRight.add(bGuardar);
        pSurBRight.add(bSalir);
    }
    private JPanel pSur;{
        pSur = new JPanel(new GridLayout(0,2));
        pSur.add(pSurBLeft);
        pSur.add(pSurBRight);
    }
    @Override
    public Libro getLibro(){
        return libro;
    }
    @Override
    public void lanzar() {
        setVisible(true);
    }

    @Override
    public void setPresentador(PresentadorLibro presentador) {
        this.presentador=presentador;
        presentador.listaAllCategorias();
    }

    @Override
    public void setCategorias(List<Categoria> categorias) {
        cbCategoria.removeAllItems();
        if (categorias!=null){
            for (Categoria categoria:categorias) {
                cbCategoria.addItem(categoria);
            }
        }
    }

    public FichaLibro(Libro libro) {
        this.libro=libro;
        setVentana();
        setContenedores();
        actualizaformulario();
        addInternalFrameListener(this);
    }

    private void setVentana() {
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Dimension d = new Dimension(WIDTH, HEIGHT);
        setSize(d);
        setMinimumSize(d);
        setClosable(true);
        setBounds(FormMain.posInterna(),FormMain.posInterna(), WIDTH, HEIGHT);
    }

    private void setContenedores() {
        setLayout(new BorderLayout());
        add(pCentro,BorderLayout.CENTER);
        add(pSur, BorderLayout.SOUTH);
    }

    private void actualizaformulario() {
        eTitulo.setText(getLibro().getNombre());
        eAutor.setText(getLibro().getAutor());
        eEditorial.setText(getLibro().getEditorial());
        bBorrar.setVisible(getLibro().getId()!=0);
        this.setTitle(String.format("Ficha libro: [%d]", getLibro().getId()));
    }

    private void salir(){
        if (JOptionPane.showConfirmDialog(this,
                "¿Desea SALIR de la ficha libro?","Atención:",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bSalir))
            salir();
        else if (e.getSource().equals(bGuardar)){
            grabar();
        } else if (e.getSource().equals(bBorrar)){
            borrar();
        }
    }

    private void grabar() {
        try {
            getLibro().setNombre(eTitulo.getText());
            getLibro().setAutor(eAutor.getText());
            getLibro().setEditorial(eEditorial.getText());
            Categoria categoria=(Categoria) cbCategoria.getSelectedItem();
            if (categoria!=null)
                getLibro().setCategoria(categoria.getId());
            if (getLibro().getId()==0) {
                presentador.inserta();
                actualizaformulario();
            }
            else presentador.modifica();
            FormMain.actualizaListaLibros();
            JOptionPane.showMessageDialog(this,"Grabado correctamente!!");
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
    }

    private void borrar() {
        if (JOptionPane.showConfirmDialog(this,
                String.format("¿Desea BORRAR el libro: %s %s?",getLibro().getNombre(),getLibro().getAutor()),
                "Atención:",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
            try {
                presentador.borra();
                FormMain.actualizaListaLibros();
                JOptionPane.showMessageDialog(this, "Libro borrado con éxito!!");
                dispose();
            } catch (Exception e) {
                SwgAuxiliar.msgExcepcion(e);
            }
        }
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        salir();
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent().equals(eTitulo))
            FormMain.barraEstado("Indique el título del libro, recuerde que este no puede ser vacío");
        else if (e.getComponent().equals(eAutor))
            FormMain.barraEstado("Indique el autor del libro");
        else if (e.getComponent().equals(eEditorial))
            FormMain.barraEstado("Indique la editorial del libro");
        else if (e.getComponent().equals(cbCategoria))
            FormMain.barraEstado("Indique la categoría del libro");
        else if (e.getComponent().equals(bGuardar))
            FormMain.barraEstado("La información que se esta mostrando será almacenada en la BD");
        else if (e.getComponent().equals(bSalir))
            FormMain.barraEstado("Pulse esta opción para salir de la ficha del libro");
        else if (e.getComponent().equals(bBorrar))
            FormMain.barraEstado("Antención si pulsa este botón el libro será eliminado de la BD, siempre y cuando no tenga préstamos de libros");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getComponent().equals(eTitulo)) {
            try {
                getLibro().setNombre(eTitulo.getText());
            } catch (CampoVacioExcepcion campoVacioExcepcion) {
                SwgAuxiliar.msgExcepcion(campoVacioExcepcion);
            }
        }
        else if (e.getComponent().equals(eAutor))
            getLibro().setAutor(eAutor.getText());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
            salir();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

