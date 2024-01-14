package vista;

import excepciones.CampoVacioExcepcion;
import modelo.old.Usuario;
import presentador.PresentadorUsuario;
import presentador.VistaUsuario;
import vista.helper.SwgAuxiliar;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.*;


/**
 * Formulario que permite interactuar (insertar, modificar o borrar)
 * con la información de la tabla usuario
 * @author AGE
 * @version 2
 */
public class FichaUsuario extends JInternalFrame implements VistaUsuario, ActionListener, InternalFrameListener, FocusListener , KeyListener{
    private static final int WIDTH = 450;
    private static final int HEIGHT = 150;
    private Usuario usuario;
    private PresentadorUsuario presentador;
    private JTextField eNombre;
    private JTextField eApellidos;
    private JPanel pNombre;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pNombre = new JPanel(flLeft);
        JLabel lNombre = new JLabel("Nombre:");
        eNombre = new JTextField();
        eNombre.setColumns(15);
        eNombre.addFocusListener(this);
        eNombre.addKeyListener(this);
        pNombre.add(lNombre);
        pNombre.add(eNombre);
    }
    private JPanel pApellidos;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pApellidos = new JPanel(flLeft);
        JLabel lApellidos = new JLabel("Apellidos:");
        eApellidos = new JTextField();
        eApellidos.setColumns(30);
        eApellidos.addFocusListener(this);
        eApellidos.addKeyListener(this);
        pApellidos.add(lApellidos);
        pApellidos.add(eApellidos);
    }

    private JPanel pCentro;{
        pCentro = new JPanel(new GridLayout(2, 0));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Datos personales");
        pCentro.setBorder(titledBorder);
        pCentro.add(pNombre);
        pCentro.add(pApellidos);
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
    public Usuario getUsuario(){
        return usuario;
    }
    @Override
    public void lanzar() {
        setVisible(true);
    }
    @Override
    public void setPresentador(PresentadorUsuario presentador) {
        this.presentador=presentador;
    }
    public FichaUsuario(Usuario usuario) {
        this.usuario=usuario;
        setVentana();
        setContenedores();
        actualizaformulario();
        addInternalFrameListener(this);
    }

    private void setContenedores() {
        setLayout(new BorderLayout());
        add(pCentro,BorderLayout.CENTER);
        add(pSur, BorderLayout.SOUTH);
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
    private void actualizaformulario() {
        eNombre.setText(getUsuario().getNombre());
        eApellidos.setText(getUsuario().getApellidos());
        bBorrar.setVisible(getUsuario().getId()!=0);
        this.setTitle(String.format("Ficha usuario: [%d]", getUsuario().getId()));
    }

    private void salir(){
        if (JOptionPane.showConfirmDialog(this,
                "¿Desea SALIR de la ficha usuario?","Atención:",
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
            getUsuario().setNombre(eNombre.getText());
            getUsuario().setApellidos(eApellidos.getText());
            if (getUsuario().getId()==0) {
                presentador.inserta();
                actualizaformulario();
            }
            else presentador.modifica();
            FormMain.actualizaListaUsuarios();
            JOptionPane.showMessageDialog(this,"Grabado correctamente!!");
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
    }

    private void borrar() {
        if (JOptionPane.showConfirmDialog(this,
                String.format("¿Desea BORRAR el usuario: %s %s?",getUsuario().getNombre(),getUsuario().getApellidos()),
                "Atención:",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
            try {
                presentador.borra();
                FormMain.actualizaListaUsuarios();
                JOptionPane.showMessageDialog(this,"Usuario borrado con éxito!!");
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
        if (e.getComponent().equals(eNombre))
            FormMain.barraEstado("Indique el nombre del usuario, recuerde que este no puede ser vacío");
        else if (e.getComponent().equals(eApellidos))
            FormMain.barraEstado("Indique el apellido del usuario, recuerde que este no puede ser vacío");
        else if (e.getComponent().equals(bGuardar))
            FormMain.barraEstado("La información que se esta mostrando será almacenada en la BD");
        else if (e.getComponent().equals(bSalir))
            FormMain.barraEstado("Pulse esta opción para salir de la ficha del usuario");
        else if (e.getComponent().equals(bBorrar))
            FormMain.barraEstado("Antención si pulsa este botón el usuario será eliminado de la BD, siempre y cuando no tenga préstamos de libros");
    }

    @Override
    public void focusLost(FocusEvent e) {
            if (e.getComponent().equals(eNombre))
                try {
                    getUsuario().setNombre(eNombre.getText());
                } catch (CampoVacioExcepcion campoVacioExcepcion) {
                    eNombre.requestFocus();
                    JOptionPane.showMessageDialog(this,campoVacioExcepcion.getMessage(),"Error: ",JOptionPane.ERROR_MESSAGE);
                }
            else if (e.getComponent().equals(eApellidos))
                try {
                    getUsuario().setApellidos(eApellidos.getText());
                } catch (CampoVacioExcepcion campoVacioExcepcion) {
                    eApellidos.requestFocus();
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
