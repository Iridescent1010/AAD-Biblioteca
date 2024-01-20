package vista;

import presentador.PresentadorCategoria;
import excepciones.CampoVacioExcepcion;
import modelo.Categoria;
import vista.helper.SwgAuxiliar;
import presentador.VistaCategoria;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.*;

/**
 * Formulario que permite interactuar (insertar, modificar o borrar)
 * con la información de la tabla categorias
 * @author AGE
 * @version 2
 */
public class FichaCategoria extends JInternalFrame implements VistaCategoria, KeyListener, ActionListener, FocusListener, InternalFrameListener {
    private static final int WIDTH = 350;
    private static final int HEIGHT = 120;
    private final Categoria categoria;
    private PresentadorCategoria presentador;

    private JTextField eCategoria;{
        eCategoria=new JTextField();
        eCategoria.addFocusListener(this);
        eCategoria.addKeyListener(this);
    }
    private JPanel pCentro;{
        pCentro = new JPanel(new GridLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Categoría:");
        pCentro.setBorder(titledBorder);
        pCentro.add(eCategoria);
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
    public void lanzar() {
        setVisible(true);
    }
    @Override
    public void setPresentador(PresentadorCategoria presentador) {
        this.presentador=presentador;
    }
    @Override
    public Categoria getCategoria(){
        return categoria;
    }
    public FichaCategoria(Categoria categoria) {
        this.categoria=categoria;
        setVetana();
        setContenedores();
        actualizaformulario();
        addInternalFrameListener(this);
    }

    private void setContenedores() {
        setLayout(new BorderLayout());
        add(pCentro,BorderLayout.CENTER);
        add(pSur, BorderLayout.SOUTH);
    }

    private void setVetana() {
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Dimension d = new Dimension(WIDTH, HEIGHT);
        setSize(d);
        setMinimumSize(d);
        setClosable(true);
        setBounds(FormMain.posInterna(),FormMain.posInterna(), WIDTH, HEIGHT);
    }

    private void actualizaformulario() {
        eCategoria.setText(getCategoria().getCategoria());
        bBorrar.setVisible(getCategoria().getId()!=0);
        this.setTitle(String.format("Ficha categoria: [%d]", getCategoria().getId()));
    }

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
            categoria.setCategoria(eCategoria.getText());
            if (categoria.getId()==0) {
                presentador.inserta();
                actualizaformulario();
            }
            else presentador.modifica();
            JOptionPane.showMessageDialog(this,"Grabado correctamente!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,e.getMessage(),"Error: ",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void borrar() {
        if (JOptionPane.showConfirmDialog(this,
                String.format("¿Desea BORRAR la categoría: %s?",getCategoria().getCategoria()),
                "Atención:",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
            try {
                presentador.borra();
                JOptionPane.showMessageDialog(this,"Categoria borrado con éxito!!");
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,e.getMessage(),"Error: ",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void salir(){
        if (JOptionPane.showConfirmDialog(this,
                "¿Desea SALIR de la ficha categoria?","Atención:",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            this.dispose();
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
        if (e.getComponent().equals(eCategoria))
            FormMain.barraEstado("Indique el nombre de la categoría, recuerde que este no puede ser vacío");
        else if (e.getComponent().equals(bGuardar))
            FormMain.barraEstado("La información que se esta mostrando será almacenada en la BD");
        else if (e.getComponent().equals(bSalir))
            FormMain.barraEstado("Pulse esta opción para salir de la ficha de la categoría");
        else if (e.getComponent().equals(bBorrar))
            FormMain.barraEstado("Antención si pulsa este botón la categoría será eliminada de la BD, siempre y cuando no tenga préstamos de libros");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getComponent().equals(eCategoria))
            try {
                getCategoria().setCategoria(eCategoria.getText());
            } catch (CampoVacioExcepcion campoVacioExcepcion) {
                eCategoria.requestFocus();
                JOptionPane.showMessageDialog(this,campoVacioExcepcion.getMessage(),"Error: ",JOptionPane.ERROR_MESSAGE);
            }
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
