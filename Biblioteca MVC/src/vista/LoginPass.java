package vista;

import singleton.ConexionMySQL;
import singleton.Configuracion;
import vista.helper.SwgAuxiliar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Formulario que permite logearnos en la aplicación de gestión de biblioteca
 * @author AGE
 * @version 2
 */
public class LoginPass extends JDialog implements ActionListener, WindowListener, KeyListener, FocusListener {
    private static final int WIDTH = 350;
    private static final int HEIGHT = 226;
    private Configuracion myConf;{
        try {
            myConf=Configuracion.getInstance();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }
    private JPanel pNorte;{
        pNorte=new JPanel(new GridLayout(2,0));
        TitledBorder titledBorderDriverUrl = BorderFactory.createTitledBorder("Driver y URL:");
        pNorte.setBorder(titledBorderDriverUrl);
        JLabel driver=new JLabel(myConf.getDriver());
        JLabel url=new JLabel(myConf.getUrl());
        pNorte.add(driver);
        pNorte.add(url);
    }
    private JPanel pOeste;{
        pOeste = new JPanel();
        Border loweredbevel = BorderFactory.createLineBorder(Color.darkGray);
        pOeste.setBorder(loweredbevel);
        JLabel label = new JLabel(new ImageIcon("imagenes/login.png"));
        pOeste.add(label);
    }
    private JPanel pUser; {
        pUser = new JPanel();
        TitledBorder titledBorderUser = BorderFactory.createTitledBorder("Usuario: ");
        pUser.setBorder(titledBorderUser);
    }
    private JTextField eUser;{
        eUser=new JTextField(myConf.getUser());
        eUser.addFocusListener(this);
        eUser.setFont(new Font("Arial",Font.BOLD,16));
        eUser.addKeyListener(this);
        pUser.add(eUser);
    }
    private JPanel pPass;{
        pPass = new JPanel();
        TitledBorder titledBorderPass = BorderFactory.createTitledBorder("Contraseña: ");
        pPass.setBorder(titledBorderPass);
    }
    private JPasswordField ePass;{
        ePass = new JPasswordField();
        ePass.addFocusListener(this);
        ePass.setFont(new Font("Arial",Font.BOLD,16));
        ePass.addKeyListener(this);
        pPass.add(ePass);
    }
    private JPanel pCentral;{
        pCentral = new JPanel(new GridLayout(2,0));
        Border loweredbevel = BorderFactory.createLineBorder(Color.darkGray);
        pCentral.setBorder(loweredbevel);
        pCentral.add(eUser);
        pCentral.add(ePass);
        pCentral.addKeyListener(this);
        SwgAuxiliar.AsignaTeclaEnterTab(pCentral);
    }
    private JButton bAceptar;{
        bAceptar= new JButton("Aceptar");
        bAceptar.setMnemonic('A');
        bAceptar.addActionListener(this);
        bAceptar.addKeyListener(this);
        getRootPane().setDefaultButton(bAceptar); //lo hacemos el botón por defecto (el enter permite pulsarlo)
    }
    private JButton bSalir;{
        bSalir = new JButton("Salir");
        bSalir.setMnemonic('S');
        bSalir.addActionListener(this);
        bSalir.addKeyListener(this);
    }
    private JPanel pSur;{
        pSur = new JPanel();
        FlowLayout flRight = new FlowLayout();
        flRight.setAlignment(FlowLayout.RIGHT);
        pSur.setLayout(flRight);
        pSur.add(bAceptar);
        pSur.add(bSalir);
    }

    public LoginPass(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        initComponents();
    }

    public LoginPass(Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
        initComponents();
    }

    private void initComponents(){
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Dimension dimension=new Dimension(WIDTH,HEIGHT);
        setResizable(false);
        setSize(dimension);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(pNorte,BorderLayout.NORTH);
        add(pOeste,BorderLayout.WEST);
        add(pCentral,BorderLayout.CENTER);
        add(pSur,BorderLayout.SOUTH);
        addWindowListener(this);
        addKeyListener(this);
    }
    private void salir() {
        if (JOptionPane.showConfirmDialog(FormMain.getInstance(),
                "¿Seguro que desea SALIR?",
                "Atención:",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            System.exit(0);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bAceptar)) {
            if (conectar()) {
                this.dispose();
                FormMain.getInstance().actualizaFormulario(true);
            }
            else JOptionPane.showMessageDialog(this,"Revise la conexión a la BD, el usuario o la contraseña","Atención: ",JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource().equals(bSalir))
            salir();
    }

    private boolean conectar() {
        boolean bSalir=false;
        myConf.setUser(eUser.getText());
        try{
            myConf.setPassword(String.valueOf(ePass.getPassword()));
            ConexionMySQL.getInstance().getConexion();
            bSalir=true;
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
        return bSalir;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        salir();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

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

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent().equals(eUser))
            FormMain.barraEstado("Introduzca nombre de usuario");
        else if (e.getComponent().equals(ePass))
            FormMain.barraEstado(String.format("Introduzca contraseña asociada al usuario %s",eUser.getText()));
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
