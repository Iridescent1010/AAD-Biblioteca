package vista;

import excepciones.CampoVacioExcepcion;
import helper.Table;
import modelo.Categoria;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Usuario;
import modelo.dao.helper.Entidades;
import observer.EventType;
import observer.Observer;
import vista.componentes.MiBarraDeEstado;
import vista.helper.*;
import vista.secret.SecretFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Formulario principal de la aplicación, en el se implementarán las opciones
 * de menú necesarias para poder utilizar la aplicación de BIBLIOTECA
 * @author AGE
 * @version 2
 */
public class FormMain extends JFrame implements ActionListener, FocusListener, WindowListener,KeyListener, Observer {
    private static FormMain main=null;
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 756;
    private JDesktopPane desktopPane = new JDesktopPane();

    private ImageIcon backgroundImage = new ImageIcon("imagenes/biblio.jpg");

    private JMenu mArchivo;{
        mArchivo=new JMenu("Archivo");
        mArchivo.setMnemonic('A');
    }

    private JMenuItem miAbrir;{
        miAbrir=new JMenuItem("Abrir");
        miAbrir.setMnemonic('A');
        miAbrir.setFocusable(true);
        miAbrir.addActionListener(this);
        miAbrir.addFocusListener(this);
        //mArchivo.add(miAbrir); TODO pendiente de implementar
    }
    private JMenuItem miGuardarLibro;{
        miGuardarLibro =new JMenuItem("Exportar libros");
        miGuardarLibro.setMnemonic('G');
        miGuardarLibro.setFocusable(true);
        miGuardarLibro.addActionListener(this);
        miGuardarLibro.addFocusListener(this);
        mArchivo.add(miGuardarLibro);
    }
    private JMenuItem miGuardarCategoria;{
        miGuardarCategoria  = new JMenuItem("Exportar categorias");
        miGuardarCategoria.setFocusable(true);
        miGuardarCategoria.addActionListener(this);
        miGuardarCategoria.addFocusListener(this);
        mArchivo.add(miGuardarCategoria);
    }
    private JMenuItem miGuardarUsuarios;{
        miGuardarUsuarios  = new JMenuItem("Exportar usuarios");
        miGuardarUsuarios.setFocusable(true);
        miGuardarUsuarios.addActionListener(this);
        miGuardarUsuarios.addFocusListener(this);
        mArchivo.add(miGuardarUsuarios);
    }
    private JMenuItem miGuardarPrestamos;{
        miGuardarPrestamos  = new JMenuItem("Exportar prestamos");
        miGuardarPrestamos.setFocusable(true);
        miGuardarPrestamos.addActionListener(this);
        miGuardarPrestamos.addFocusListener(this);
        mArchivo.add(miGuardarPrestamos);
    }
    private JMenuItem miConexion;{
        miConexion =new JMenuItem("Conectar");
        miConexion.setMnemonic('C');
        miConexion.addActionListener(this);
        mArchivo.addSeparator();
        mArchivo.add(miConexion);
    }
    private JMenuItem miSalir;{
        miSalir=new JMenuItem("Salir");
        miSalir.setMnemonic('S');
        miSalir.setFocusable(true);
        miSalir.addActionListener(this);
        miSalir.addFocusListener(this);
        mArchivo.addSeparator();
        mArchivo.add(miSalir);
    }
    private JMenu mCategorias;{
        mCategorias =new JMenu("Categorias");
        mCategorias.setMnemonic('U');
        mCategorias.setFocusable(true);
        mCategorias.addFocusListener(this);
    }
    private JMenuItem miListaCategorias;{
        miListaCategorias=new JMenuItem("Lista");
        miListaCategorias.setMnemonic('L');
        miListaCategorias.setFocusable(true);
        miListaCategorias.addActionListener(this);
        miListaCategorias.addFocusListener(this);
        mCategorias.add(miListaCategorias);

    }
    private JMenuItem miNuevaCategoria;{
        miNuevaCategoria =new JMenuItem("Nuevo");
        miNuevaCategoria.setMnemonic('N');
        miNuevaCategoria.setFocusable(true);
        miNuevaCategoria.addActionListener(this);
        miNuevaCategoria.addFocusListener(this);
        mCategorias.add(miNuevaCategoria);
    }
    private JMenu mUsuarios;{
        mUsuarios =new JMenu("Usuarios");
        mUsuarios.setMnemonic('U');
        mUsuarios.setFocusable(true);
        mUsuarios.addFocusListener(this);
    }
    private JMenuItem miListaUsuarios;{
        miListaUsuarios=new JMenuItem("Lista");
        miListaUsuarios.setMnemonic('L');
        miListaUsuarios.setFocusable(true);
        miListaUsuarios.addActionListener(this);
        miListaUsuarios.addFocusListener(this);
        mUsuarios.add(miListaUsuarios);

    }
    private JMenuItem miNuevoUsuario;{
        miNuevoUsuario=new JMenuItem("Nuevo");
        miNuevoUsuario.setMnemonic('N');
        miNuevoUsuario.setFocusable(true);
        miNuevoUsuario.addActionListener(this);
        miNuevoUsuario.addFocusListener(this);
        mUsuarios.add(miNuevoUsuario);

    }
    private JMenu mLibros;{
        mLibros =new JMenu("Libros");
        mLibros.setMnemonic('L');
        mLibros.setFocusable(true);
        mLibros.addFocusListener(this);
    }
    private JMenuItem miListaLibros;{
        miListaLibros=new JMenuItem("Lista");
        miListaLibros.setMnemonic('L');
        miListaLibros.setFocusable(true);
        miListaLibros.addActionListener(this);
        miListaLibros.addFocusListener(this);
        mLibros.add(miListaLibros);

    }
    private JMenuItem miNuevoLibro;{
        miNuevoLibro=new JMenuItem("Nuevo");
        miNuevoLibro.setMnemonic('N');
        miNuevoLibro.setFocusable(true);
        miNuevoLibro.addActionListener(this);
        miNuevoLibro.addFocusListener(this);
        mLibros.add(miNuevoLibro);
    }
    private JMenu mPrestamos;{
        mPrestamos =new JMenu("Préstamos");
        mPrestamos.setMnemonic('P');
        mPrestamos.setFocusable(true);
        mPrestamos.addFocusListener(this);
    }
    private JMenuItem miListaPrestamos;{
        miListaPrestamos=new JMenuItem("Lista");
        miListaPrestamos.setMnemonic('L');
        miListaPrestamos.setFocusable(true);
        miListaPrestamos.addActionListener(this);
        miListaPrestamos.addFocusListener(this);
        mPrestamos.add(miListaPrestamos);
    }
    private JMenuItem miNuevoPrestamo;{
        miNuevoPrestamo = new JMenuItem("Nuevo");
        miNuevoPrestamo.setMnemonic('N');
        miNuevoPrestamo.setFocusable(true);
        miNuevoPrestamo.addActionListener(this);
        miNuevoPrestamo.addFocusListener(this);
        mPrestamos.add(miNuevoPrestamo);
    }
    private JMenu mSecret; {
        mSecret = new JMenu("?????");
        mSecret.setFocusable(true);
        mSecret.addFocusListener(this);
    }
    private JMenuItem miStery; {
        miStery = new JMenuItem("???");
        miStery.setFocusable(true);
        miStery.addActionListener(this);
        miStery.addFocusListener(this);
        mSecret.add(miStery);
    }
    private JMenuBar jMenuBar;{
        jMenuBar = new JMenuBar();
        jMenuBar.add(mArchivo);
        jMenuBar.add(mCategorias);
        jMenuBar.add(mUsuarios);
        jMenuBar.add(mLibros);
        jMenuBar.add(mPrestamos);
        jMenuBar.add(mSecret);
        jMenuBar.addFocusListener(this);
    }
    private MiBarraDeEstado miBarraDeEstado;{
        miBarraDeEstado= MiBarraDeEstado.getInstance();
    }
    private FormMain(){
        setVentana();
        setContenedores();
        actualizaFormulario(false);
        addEventos();
    }

    private void addEventos() {
        addWindowListener(this);
        getContentPane().setFocusable(true);
        getContentPane().addKeyListener(this);
        getContentPane().addFocusListener(this);
    }

    private void setContenedores() {
        setLayout(new BorderLayout());
        add(jMenuBar, BorderLayout.NORTH);

        // Configuración del fondo del JDesktopPane
        desktopPane = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);

            }
        };

        add(desktopPane, BorderLayout.CENTER);
        add(miBarraDeEstado, BorderLayout.SOUTH);
    }


    private void setVentana() {
        setTitle("Aplicación de gestión de una biblioteca: ");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(0,0,WIDTH,HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Configurar imagen de fondo
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, WIDTH, HEIGHT);
        getLayeredPane().add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));

        // Configuración del contenido
        ((JPanel) getContentPane()).setOpaque(false);
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    /**
     * Este método habilitará o desactivará las distintas
     * opciones de menú del programa según corresponda
     */
    public void actualizaFormulario(boolean conectado) {
        miConexion.setEnabled(!conectado);
        miAbrir.setEnabled(conectado);
        mCategorias.setEnabled(conectado);
        mUsuarios.setEnabled(conectado);
        mLibros.setEnabled(conectado);
        mPrestamos.setEnabled(conectado);
    }
    /**
     * Método para la implementación del Singleton del formulario principal
     * @return el objeto global donde se instancia el formulario de la aplicación
     */
    public static FormMain getInstance(){
        if (main==null) {
            main = new FormMain();
            main.loginPassword();
        }
        return main;
    }

    private void muestraCategorias() {
        try {
            desktopPane.add(Categorias.listaCategorias());
            desktopPane.selectFrame(false);
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
    }
    private void nuevaCategoria() {
        try {
            desktopPane.add(Categorias.fichaCategoria(new Categoria()));
            desktopPane.selectFrame(false);
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
    }

    private void muestraUsuarios() {
        try {
            desktopPane.add(Usuarios.listaUsuarios());
            desktopPane.selectFrame(false);
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
    }
    private void nuevoUsuario() {
        try {
            desktopPane.add(Usuarios.fichaUsuario(new Usuario()));
            desktopPane.selectFrame(false);
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
    }
    private void muestraLibros() {
        try {
            desktopPane.add(Libros.listaLibros());
            desktopPane.selectFrame(false);
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
    }
    private void nuevoLibro() {
        try {
            desktopPane.add(Libros.fichaLibro(new Libro()));
            desktopPane.selectFrame(false);
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
    }
    private void muestraPrestamos() {
        try {
            desktopPane.add(Prestamos.listaPrestamos());
            desktopPane.selectFrame(false);
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
    }
    private void nuevoPrestamo(){
        try {
            desktopPane.add(Prestamos.fichaPrestamo(new Prestamo()));
            // desktopPane.add(new FichaPrestamo(new Prestamo()));
            desktopPane.selectFrame(false);
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
    }

    private void loginPassword() {
        new LoginPass(this,"Conectar BD:",true).setVisible(true);
    }

    private void salir() {
        if (JOptionPane.showConfirmDialog(FormMain.getInstance(),
                "¿Seguro que desea SALIR?",
                "Atención:",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                System.exit(0);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent().equals(mArchivo))
            miBarraDeEstado.setInfo("Opciones para archivos");
        else if (e.getComponent().equals(miAbrir))
            miBarraDeEstado.setInfo("Pulsa para cargar una imagen en el visor");
        else if (e.getComponent().equals(miSalir))
            miBarraDeEstado.setInfo("Cierra la aplicación");

        else miBarraDeEstado.setInfo(String.format("Infor: mArchivo: %b, miAbrir: %b, miAbrir: %b",mArchivo.isFocusable(),miAbrir.isFocusable(),miSalir.isFocusable()));
    }

    @Override
    public void focusLost(FocusEvent e) {

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
    public static int posInterna() {
        return FormMain.getInstance().getDesktopPane().getComponentCount()*25; // hasta que no se visualiza no se contabiliza
    }

    /**
     * Implementación de observer
     * @param event Indica que tipo de cambio se ha producido
     * @throws SQLException
     * @throws IOException
     * @throws CampoVacioExcepcion
     */
    @Override
    public void update(EventType event) throws SQLException, IOException, CampoVacioExcepcion {
        switch (event) {// TODO: can be improved by using a map <EventType, Component>
            case UsuarioChanged -> {
                actualizaListaUsuarios();
                actualizaListaPrestamos();
            }
            case CategoriaChanged -> {
                actualizaListaCategorias();
                actualizaListaLibros();
                actualizaFichaLibros();
                actualizaFichaPrestamos();
            }
            case LibroChanged -> {
                actualizaListaLibros();
                actualizaListaPrestamos();
            }
            case PrestamoChanged -> actualizaListaPrestamos();
        }

    }

    public void actualizaListaUsuarios() throws SQLException, CampoVacioExcepcion, IOException {
        List<Usuario> usuarios = Entidades.leerAllUsuarios();
        for (int i=0;i< getDesktopPane().getComponentCount();i++)
            if (getDesktopPane().getComponent(i) instanceof  ListaUsuarios)
                ((ListaUsuarios) getDesktopPane().getComponent(i)).setUsuarios(usuarios);
    }
    public void actualizaListaCategorias() throws SQLException, CampoVacioExcepcion, IOException {
        List<Categoria> categorias = Entidades.leerAllCategorias();
        for (int i=0;i< getDesktopPane().getComponentCount();i++)
            if (getDesktopPane().getComponent(i) instanceof  ListaCategorias)
                ((ListaCategorias) getDesktopPane().getComponent(i)).setCategorias(categorias);
    }
    public void actualizaListaLibros() throws SQLException, CampoVacioExcepcion, IOException {
        List<Libro> libros = Entidades.leerAllLibros();
        for (int i=0;i< getDesktopPane().getComponentCount();i++)
            if (FormMain.getInstance().getDesktopPane().getComponent(i) instanceof  ListaLibros)
                ((ListaLibros) getDesktopPane().getComponent(i)).setLibros(libros);
    }
    public void actualizaListaPrestamos() throws SQLException, CampoVacioExcepcion, IOException {
        List<Prestamo> prestamos = Entidades.leerAllPrestamos();
        for (int i=0;i< getDesktopPane().getComponentCount();i++)
            if (getDesktopPane().getComponent(i) instanceof  ListaPrestamos)
                ((ListaPrestamos) getDesktopPane().getComponent(i)).setPrestamos(prestamos);
    }

    public void actualizaFichaLibros() {
        for (int i=0;i< getDesktopPane().getComponentCount();i++)
            if (getDesktopPane().getComponent(i) instanceof  FichaLibro)
                ((FichaLibro) getDesktopPane().getComponent(i)).updateCategorias();

    }
    public void actualizaFichaPrestamos() {
        for (int i=0;i< getDesktopPane().getComponentCount();i++)
            if (getDesktopPane().getComponent(i) instanceof  FichaPrestamo)
                ((FichaPrestamo) getDesktopPane().getComponent(i)).updateCategorias();

    }


    public static void barraEstado(String mensaje){
        FormMain.getInstance().miBarraDeEstado.setInfo(mensaje);
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
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(miSalir) )
            salir();
        else if (e.getSource().equals(miConexion))
            loginPassword();
        else if (e.getSource()== miListaUsuarios)
            muestraUsuarios();
        else if (e.getSource()== miNuevoUsuario)
            nuevoUsuario();
        else if (e.getSource()== miListaCategorias)
            muestraCategorias();
        else if (e.getSource()== miNuevaCategoria)
            nuevaCategoria();
        else if (e.getSource()== miListaLibros)
            muestraLibros();
        else if (e.getSource()== miNuevoLibro)
            nuevoLibro();
        else if (e.getSource()== miListaPrestamos)
            muestraPrestamos();
        else if (e.getSource()== miNuevoPrestamo)
            nuevoPrestamo();
        else if (e.getSource()==miStery)
            new SecretFrame(this);
        else if (e.getSource()==miGuardarLibro) {
            grabarCsv(Table.LIBROS);
        } else if (e.getSource()==miGuardarCategoria) {
            grabarCsv(Table.CATEGORIAS);
        } else if (e.getSource() == miGuardarUsuarios) {
            grabarCsv(Table.USUARIOS);
        } else if (e.getSource() == miGuardarPrestamos) {
            grabarCsv(Table.PRESTAMOS);
        }
    }

    private static void grabarCsv(Table tabla) {
        try {
            SwgAuxiliar.grabarCSV(tabla);
            SwgAuxiliar.msgInfo("Tabla " + tabla.toString().toLowerCase() + " exportada con éxito");
        } catch (Exception ex) {
            SwgAuxiliar.msgExcepcion(ex);
        }
    }

}
