package vista;

import modelo.*;
import modelo.Categoria;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Usuario;
import presentador.PresentadorPrestamo;
import presentador.VistaPrestamo;
import vista.helper.Libros;
import vista.helper.SwgAuxiliar;
import vista.helper.Usuarios;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
/**
 * Formulario que permite interactuar (insertar, modificar o borrar)
 * con la información de la tabla Prestamo
 * @author AGE
 * @version 2
 */
public class FichaPrestamo extends JInternalFrame implements VistaPrestamo, ActionListener, InternalFrameListener, FocusListener, KeyListener {

    private static final int WIDTH = 350;
    private static final int HEIGHT = 405;

    private Prestamo prestamo;
    private PresentadorPrestamo presentador;
    private JTextField eFindLibro;
    private JButton bFindLibro;
    private JCheckBox ckIdLibro =new JCheckBox("Código");
    private JCheckBox ckTitulo =new JCheckBox("Título");
    private JCheckBox ckAutor =new JCheckBox("Autor");
    private JCheckBox ckEditorial =new JCheckBox("Editorial");

    private JPanel pCheckLibro;{
        pCheckLibro=new JPanel(new GridLayout(0,4));
        ckTitulo.setSelected(true);
        pCheckLibro.add(ckIdLibro);
        pCheckLibro.add(ckTitulo);
        pCheckLibro.add(ckAutor);
        pCheckLibro.add(ckEditorial);
    }
    private JComboBox cbCategoria=new JComboBox();{
        cbCategoria.addFocusListener(this);
        cbCategoria.addKeyListener(this);
    };
    private JPanel pCategoria;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pCategoria = new JPanel(flLeft);
        JLabel lCategoria = new JLabel("Categoria:");
        pCategoria.add(lCategoria);
        pCategoria.add(cbCategoria);
    }

    private JPanel pFindLibro;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pFindLibro = new JPanel(flLeft);
        eFindLibro = new JTextField();
        eFindLibro.setColumns(20);
        eFindLibro.addFocusListener(this);
        bFindLibro = new JButton("Libro");
        bFindLibro.setMnemonic('L');
        bFindLibro.addFocusListener(this);
        bFindLibro.addActionListener(this);
        pFindLibro.add(eFindLibro);
        pFindLibro.add(bFindLibro);
    }
    private JPanel pNorteLibro;{
        pNorteLibro=new JPanel(new GridLayout(3,0));
        pNorteLibro.add(pCheckLibro);
        pNorteLibro.add(pCategoria);
        pNorteLibro.add(pFindLibro);
    }

    private JTextField eFindUsuario;
    private JButton bFindUsuario;
    private JCheckBox ckIdUsuario =new JCheckBox("Código");
    private JCheckBox ckNombre =new JCheckBox("Nombre");
    private JCheckBox ckApellidos =new JCheckBox("Apellidos");
    private JPanel pCheckUsuario;{
        pCheckUsuario=new JPanel(new GridLayout(0,3));
        ckApellidos.setSelected(true);
        pCheckUsuario.add(ckIdUsuario);
        pCheckUsuario.add(ckNombre);
        pCheckUsuario.add(ckApellidos);
    }
    private JPanel pFindUsuario;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pFindUsuario = new JPanel(flLeft);
        eFindUsuario = new JTextField();
        eFindUsuario.setColumns(20);
        eFindUsuario.addFocusListener(this);
        bFindUsuario = new JButton("Usuario");
        bFindLibro.setMnemonic('U');
        bFindUsuario.addFocusListener(this);
        bFindUsuario.addActionListener(this);
        pFindUsuario.add(eFindUsuario);
        pFindUsuario.add(bFindUsuario);
    }
    private JPanel pNorteUsuario;{
        pNorteUsuario=new JPanel(new GridLayout(2,0));
        pNorteUsuario.add(pCheckUsuario);
        pNorteUsuario.add(pFindUsuario);
    }
    private JPanel pNorte;{
        pNorte = new JPanel(new GridLayout(2, 0));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Búsqueda de libros y usuarios");
        pNorte.setBorder(titledBorder);
        pNorte.add(pNorteLibro);
        pNorte.add(pNorteUsuario);
        SwgAuxiliar.AsignaTeclaEnterTab(pNorte);
    }
    private JTextField eLibro;
    private JPanel pLibro;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pLibro = new JPanel(flLeft);
        JLabel lLibroSel = new JLabel("Libro:");
        eLibro = new JTextField();
        eLibro.setColumns(20);
        eLibro.setEnabled(false);
        pLibro.add(lLibroSel);
        pLibro.add(eLibro);
    }

    private JTextField eUsuario;
    private JPanel pUsuario;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pUsuario = new JPanel(flLeft);
        JLabel lUsuarioSel = new JLabel("Usuario:");
        eUsuario = new JTextField();
        eUsuario.setColumns(20);
        eUsuario.setEnabled(false);
        pUsuario.add(lUsuarioSel);
        pUsuario.add(eUsuario);
    }
    private JTextField eFechaPrestamo;
    private JPanel pFechaPrestamo;{
        FlowLayout flLeft = new FlowLayout();
        flLeft.setAlignment(FlowLayout.LEFT);
        pFechaPrestamo = new JPanel(flLeft);
        JLabel lFechaPrestamo = new JLabel("Fecha:");
        eFechaPrestamo = new JTextField();
        eFechaPrestamo.setColumns(20);
        eFechaPrestamo.setEnabled(false);
        eFechaPrestamo.addFocusListener(this);
        eFechaPrestamo.addKeyListener(this);
        pFechaPrestamo.add(lFechaPrestamo);
        pFechaPrestamo.add(eFechaPrestamo);
    }

    private JPanel pCentro;{
        pCentro = new JPanel(new GridLayout(3,0));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Datos del prestamo");
        pCentro.setBorder(titledBorder);
        pCentro.add(pLibro);
        pCentro.add(pUsuario);
        pCentro.add(pFechaPrestamo);
        SwgAuxiliar.AsignaTeclaEnterTab(pNorte);
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
    public void setPresentador(PresentadorPrestamo presentador){
        this.presentador=presentador;
        presentador.listaAllCategorias();
    }

    @Override
    public Prestamo getPrestamo() {
        return prestamo;
    }
    @Override
    public void setCategorias(List<Categoria> categorias) {
        cbCategoria.removeAllItems();
        Categoria catTodas=new Categoria();
        catTodas.setId(0);
        try {
            catTodas.setCategoria("Todas");
        } catch (Exception e){}
        cbCategoria.addItem(catTodas);
        if (categorias!=null){
            for (Categoria categoria:categorias) {
                cbCategoria.addItem(categoria);
            }
        }
    }
    public FichaPrestamo(Prestamo prestamo)  {
        this.prestamo=prestamo;
        setVentana();
        setContenedores();
        actualizaformulario();
        addInternalFrameListener(this);

    }

    private void setContenedores() {
        setLayout(new BorderLayout());
        add(pNorte,BorderLayout.NORTH);
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
        Libro libro=prestamo.getObjLibro();
        if (libro!=null)
            eLibro.setText(libro.toString());

        Usuario usuario = prestamo.getObjUsuario();
        if (usuario!=null)
            eUsuario.setText(usuario.toString());

        eFechaPrestamo.setText(getPrestamo().getFecha());
        bBorrar.setVisible(getPrestamo().getIdPrestamo()!=0);
        this.setTitle(String.format("Ficha prestamo: [%d]", getPrestamo().getIdPrestamo()));
    }

    private void salir(){
        if (JOptionPane.showConfirmDialog(this,
                "¿Desea SALIR de la ficha prestamo?","Atención:",
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
        } else if (e.getSource().equals(bFindLibro)){
            buscaLibro();
        } else if (e.getSource().equals(bFindUsuario)){
            buscaUsuario();
        }
    }

    private void buscaUsuario() {
        BusquedaUsuario busquedaUsuario = new BusquedaUsuario();
        busquedaUsuario.id=0;
        busquedaUsuario.nombre="";
        busquedaUsuario.apellidos="";
        try {
            if (!eFindUsuario.getText().trim().equals("")) {
                busquedaUsuario.tipo = TipoBusqueda.OR;
                if (ckIdUsuario.isSelected()) {
                    busquedaUsuario.id = Integer.parseInt(eFindUsuario.getText());
                }
                else {
                    if (ckNombre.isSelected())
                        busquedaUsuario.nombre=eFindUsuario.getText().trim();
                    if (ckApellidos.isSelected())
                        busquedaUsuario.apellidos=eFindUsuario.getText().trim();
                }
            } else busquedaUsuario.tipo=TipoBusqueda.TODOS;
            Usuarios.seleccionaUsuario(null,"Seleccione un usuario:",true,busquedaUsuario);
            getPrestamo().setUsuarioId(busquedaUsuario.idSel);
            actualizaformulario();
        } catch (NumberFormatException e) {
            SwgAuxiliar.msgError("Introduzca un valor númerico o desactive la busqueda por código de usuario");
        } catch (Exception e) {
            SwgAuxiliar.msgError(e.getMessage());
        }
    }

    private void buscaLibro() {
        BusquedaLibro busquedaLibro=new BusquedaLibro();
        try {
            Categoria categoria= (Categoria) cbCategoria.getSelectedItem();
            busquedaLibro.id=0;
            busquedaLibro.autor="";
            busquedaLibro.titulo="";
            busquedaLibro.editorial="";
            busquedaLibro.categoria=categoria.getId();
            if (!eFindLibro.getText().trim().equals("") || categoria.getId()!=0) {
                busquedaLibro.tipo = TipoBusqueda.OR;
                if (ckIdLibro.isSelected())
                    busquedaLibro.id=Integer.parseInt(eFindLibro.getText());
                else {
                    if (ckAutor.isSelected())
                        busquedaLibro.autor=eFindLibro.getText().trim();
                    if (ckTitulo.isSelected())
                        busquedaLibro.titulo=eFindLibro.getText().trim();
                    if (ckEditorial.isSelected())
                        busquedaLibro.editorial=eFindLibro.getText().trim();
                }
            } else {
                busquedaLibro.tipo = TipoBusqueda.TODOS;
            }
            Libros.seleccionaLibro(null,"Seleccione un libro:",true,busquedaLibro);
            getPrestamo().setLibroId(busquedaLibro.idSel);
            actualizaformulario();
        } catch (NumberFormatException e) {
            SwgAuxiliar.msgError("Introduzca un valor númerico o desactive la busqueda por código de libro");
        } catch (Exception e) {
            SwgAuxiliar.msgError(e.getMessage());
        }
    }

    private void grabar() {
        try {
            if (getPrestamo().getIdPrestamo()==0) {
                presentador.inserta();
                actualizaformulario();
            }
            else presentador.modifica();
            FormMain.actualizaListaPrestamos();
            JOptionPane.showMessageDialog(this,"Grabado correctamente!!");
            dispose();
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }
    }

    private void borrar() {
        if (JOptionPane.showConfirmDialog(this,
                String.format("¿Desea BORRAR el préstamo:\n%s\n%s?",eLibro.getText(),eUsuario.getText()),
                "Atención:",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
            try {
                presentador.borra();
                FormMain.actualizaListaPrestamos();
                JOptionPane.showMessageDialog(this, "Prestamo borrado con éxito!!");
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
        if (e.getComponent().equals(bFindLibro))
            FormMain.barraEstado("Pulse para localizar un libro");
        else if (e.getComponent().equals(bFindUsuario))
            FormMain.barraEstado("Pulse para localizar un usuario");
        else if (e.getComponent().equals(eFindLibro))
            FormMain.barraEstado("Indique información sobre el libro a buscar, se permite el comodin %");
        else if (e.getComponent().equals(eFindUsuario))
            FormMain.barraEstado("Indique información sobre el usuario a buscar, se permite el comodin %");
        else if (e.getComponent().equals(bGuardar))
            FormMain.barraEstado("La información que se esta mostrando será almacenada en la BD");
        else if (e.getComponent().equals(bSalir))
            FormMain.barraEstado("Pulse esta opción para salir de la ficha del prestamo");
        else if (e.getComponent().equals(bBorrar))
            FormMain.barraEstado("Antención si pulsa este botón el prestamo será eliminado de la BD, siempre y cuando no tenga préstamos de prestamos");
    }

    @Override
    public void focusLost(FocusEvent e) {
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

