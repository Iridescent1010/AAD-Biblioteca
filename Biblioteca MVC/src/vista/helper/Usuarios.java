package vista.helper;


import modelo.BusquedaUsuario;
import modelo.Usuario;
import modelo.dao.UsuarioDAO;
import modelo.dao.UsuarioDAOHibernate;
import presentador.PresentadorUsuario;
import vista.FichaUsuario;
import vista.FormMain;
import vista.ListaUsuarios;
import vista.SeleccionaUsuario;
import java.awt.*;

public class Usuarios {
    static UsuarioDAO usuarioDAO = new UsuarioDAOHibernate();
    public static ListaUsuarios listaUsuarios() throws Exception {
        ListaUsuarios listaUsuarios = new ListaUsuarios();
        PresentadorUsuario presentadorUsuario=new PresentadorUsuario(usuarioDAO,listaUsuarios);
        presentadorUsuario.addObserver(FormMain.getInstance());
        listaUsuarios.setPresentador(presentadorUsuario);
        listaUsuarios.lanzar();
        return listaUsuarios;
    }

    public static SeleccionaUsuario seleccionaUsuario(Frame owner, String title, boolean modal, BusquedaUsuario busquedaUsuario) throws Exception {
        SeleccionaUsuario seleccionaUsuario=new SeleccionaUsuario(owner, title, modal,busquedaUsuario);
        PresentadorUsuario presentadorUsuario=new PresentadorUsuario(usuarioDAO,seleccionaUsuario);
        presentadorUsuario.addObserver(FormMain.getInstance());
        seleccionaUsuario.setPresentador(presentadorUsuario);
        seleccionaUsuario.lanzar();
        return seleccionaUsuario;
    }

    public static FichaUsuario fichaUsuario(Usuario usuario) throws Exception {
        FichaUsuario fichaUsuario=new FichaUsuario(usuario);
        PresentadorUsuario presentadorUsuario=new PresentadorUsuario(usuarioDAO,fichaUsuario);
        presentadorUsuario.addObserver(FormMain.getInstance());
        fichaUsuario.setPresentador(presentadorUsuario);
        fichaUsuario.lanzar();
        return fichaUsuario;

    }
}
