package vista.helper;


import modelo.BusquedaUsuario;
import modelo.Usuario;
import modelo.dao.UsuarioDAO;
import modelo.dao.UsuarioDAOHibernate;
import presentador.PresentadorUsuario;
import vista.FichaUsuario;
import vista.ListaUsuarios;
import vista.SeleccionaUsuario;
import java.awt.*;

public class Usuarios {
    public static ListaUsuarios listaUsuarios() throws Exception {
        UsuarioDAO usuarioDAO=new UsuarioDAOHibernate();
        ListaUsuarios listaUsuarios=new ListaUsuarios();
        PresentadorUsuario presentadorUsuario=new PresentadorUsuario(usuarioDAO,listaUsuarios);
        listaUsuarios.setPresentador(presentadorUsuario);
        listaUsuarios.lanzar();
        return listaUsuarios;
    }

    public static SeleccionaUsuario seleccionaUsuario(Frame owner, String title, boolean modal, BusquedaUsuario busquedaUsuario) throws Exception {
        UsuarioDAO usuarioDAO=new UsuarioDAOHibernate();
        SeleccionaUsuario seleccionaUsuario=new SeleccionaUsuario(owner, title, modal,busquedaUsuario);
        PresentadorUsuario presentadorUsuario=new PresentadorUsuario(usuarioDAO,seleccionaUsuario);
        seleccionaUsuario.setPresentador(presentadorUsuario);
        seleccionaUsuario.lanzar();
        return seleccionaUsuario;
    }

    public static FichaUsuario fichaUsuario(Usuario usuario) throws Exception {
        UsuarioDAO usuarioDAO=new UsuarioDAOHibernate();
        FichaUsuario fichaUsuario=new FichaUsuario(usuario);
        PresentadorUsuario presentadorUsuario=new PresentadorUsuario(usuarioDAO,fichaUsuario);
        fichaUsuario.setPresentador(presentadorUsuario);
        fichaUsuario.lanzar();
        return fichaUsuario;

    }
}
