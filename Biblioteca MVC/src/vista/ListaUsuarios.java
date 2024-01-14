package vista;

import modelo.old.Usuario;
import presentador.PresentadorUsuario;
import presentador.VistaUsuarios;
import vista.componentes.TablaUsuarios;
import vista.helper.SwgAuxiliar;
import vista.helper.Usuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
/**
 * Formulario que muestra lista todos los registros asociados a una consulta relacionada con la tabla usuario
 * @author AGE
 * @version 2
 */

public class ListaUsuarios extends JInternalFrame implements VistaUsuarios, MouseListener, FocusListener, KeyListener,ActionListener {
    private static final int WIDTH = 625;
    private static final int HEIGHT = 500;
    private List<Usuario> usuarios;
    PresentadorUsuario presentador;

    private TablaUsuarios jTable;{
        jTable=new TablaUsuarios();
        jTable.addMouseListener(this);
        jTable.addFocusListener(this);
        jTable.addKeyListener(this);
    }
    private JScrollPane scrollPane;{
        scrollPane=new JScrollPane(jTable);
    }
    private JMenuItem miFicha;{
        miFicha=new JMenuItem("Ficha");
        miFicha.setMnemonic('F');
        miFicha.addActionListener(this);
    }
    private JMenuItem miNuevo;{
        miNuevo=new JMenuItem("Nuevo");
        miNuevo.setMnemonic('N');
        miNuevo.addActionListener(this);
    }
    private JMenuItem miBorra;{
        miBorra=new JMenuItem("Borra");
        miBorra.setMnemonic('B');
        miBorra.addActionListener(this);
    }
    private JPopupMenu jPopupMenu;{
        jPopupMenu = new JPopupMenu();
        jPopupMenu.add(miFicha);
        jPopupMenu.add(miNuevo);
        jPopupMenu.add(miBorra);
    }

    @Override
    public void lanzar() {
        setVisible(true);
    }

    @Override
    public void setPresentador(PresentadorUsuario presentador) throws Exception {
        this.presentador=presentador;
        presentador.listaAllUsuarios();
    }

    @Override
    public Usuario getUsuario() {
        return usuarios.get(jTable.getSelectedRow());
    }

    @Override
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios=usuarios;
        jTable.muestraTabla(usuarios);
    }

    public ListaUsuarios() {
        setVentana();
        setContenedores();
        addKeyListener(this);
    }

    private void setVentana() {
        getContentPane().setBackground(Color.WHITE);
        setTitle("Listado de usuarios:");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        Dimension dime = new Dimension(WIDTH, HEIGHT);
        setBounds(FormMain.posInterna(),FormMain.posInterna(), WIDTH, HEIGHT);
        setMinimumSize(dime);
        setSize(dime);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
    }

    private void setContenedores() {
        setLayout(new GridLayout());
        add(scrollPane);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(jTable)){
            if (e.getClickCount()==2)
                muestraFicha(getUsuario());
            else if (e.getButton()==MouseEvent.BUTTON3)
                jPopupMenu.show (jTable, e.getX (), e.getY ());
        }
    }

    private void muestraFicha(Usuario usuario) {
        try {
            FormMain.getInstance().getDesktopPane().add(Usuarios.fichaUsuario(usuario));
            FormMain.getInstance().getDesktopPane().selectFrame(false);
        } catch (Exception e) {
            SwgAuxiliar.msgExcepcion(e);
        }

    }
    private void borrar(Usuario usuario) {
        if (JOptionPane.showConfirmDialog(this,
                String.format("¿Desea BORRAR el usuario: %s %s?",usuario.getNombre(),usuario.getApellidos()),
                "Atención:",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
            try {
                presentador.borra();
                FormMain.actualizaListaUsuarios();
            } catch (Exception e) {
                SwgAuxiliar.msgExcepcion(e);
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
        if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
            dispose();
        else if (e.getKeyCode()==KeyEvent.VK_SPACE)
            muestraFicha(usuarios.get(jTable.getSelectedRow()));
        else if (e.getKeyCode()==KeyEvent.VK_DELETE)
            borrar(getUsuario());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(miFicha))
            muestraFicha(getUsuario());
        else if (e.getSource().equals(miNuevo))
            muestraFicha(new Usuario());
        else if (e.getSource().equals(miBorra))
            borrar(getUsuario());
    }

}
