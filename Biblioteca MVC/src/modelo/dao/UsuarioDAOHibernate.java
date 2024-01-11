package modelo.dao;

import modelo.Usuario;

import java.util.List;

public class UsuarioDAOHibernate implements UsuarioDAO{
    @Override
    public boolean insertar(Usuario usuario) throws Exception {
        return false;
    }

    @Override
    public boolean modificar(Usuario usuario) throws Exception {
        return false;
    }

    @Override
    public boolean borrar(int id) throws Exception {
        return false;
    }

    @Override
    public List<Usuario> leerAllUsuarios() throws Exception {
        return null;
    }

    @Override
    public List<Usuario> leerUsuariosOR(int id, String nombre, String apellidos) throws Exception {
        return null;
    }

    @Override
    public Usuario getUsuario(int id) throws Exception {
        return null;
    }
}
