package presentador;

import modelo.dao.UsuarioDAO;


public class PresentadorUsuario {
    private UsuarioDAO usuarioDAO;
    private VistaUsuario vistaUsuario;

    public PresentadorUsuario(UsuarioDAO usuarioDAO, VistaUsuario vistaUsuario) {
        this.usuarioDAO = usuarioDAO;
        this.vistaUsuario = vistaUsuario;
    }

    public void borra() throws Exception {
        usuarioDAO.borrar(vistaUsuario.getUsuario().getId());
    }

    public void inserta() throws Exception {
        usuarioDAO.insertar(vistaUsuario.getUsuario());
    }

    public void modifica() throws Exception {
        usuarioDAO.modificar(vistaUsuario.getUsuario());
    }

    public void listaAllUsuarios() throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        vistaUsuarios.setUsuarios(usuarioDAO.leerAllUsuarios());
    }

    public void leerUsuariosOR(int id,String nombre,String apellidos) throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        vistaUsuarios.setUsuarios(usuarioDAO.leerUsuariosOR(id,nombre,apellidos));
    }
}
