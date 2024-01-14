package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.dao.helper.LogFile;
import modelo.dao.helper.Sql;
import modelo.Usuario;
import singleton.ConexionMySQL;

/**
 * Aquí implementaremos las reglas de negocio definidas
 * en la interfaz para trabajar con usuario y
 * base de datos en MySQL
 * @author AGE
 * @version 2
 */
public class UsuarioDAOImpl implements UsuarioDAO{
    private final Connection con;
    private static final String sqlINSERT="INSERT INTO usuario (nombre,apellidos) VALUES (?,?)";
    private static final String sqlUPDATE="UPDATE usuario SET nombre = ?, apellidos = ? WHERE id = ?";
    private static final String sqlDELETE="DELETE usuario WHERE id = ?";

    public UsuarioDAOImpl() throws Exception {
        con = ConexionMySQL.getInstance().getConexion();
    }
    @Override
    public boolean insertar(Usuario usuario) throws Exception {
        boolean bInsertado;
        try(PreparedStatement ps =con.prepareStatement(sqlINSERT,PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setString(1,usuario.getNombre());
            ps.setString(2,usuario.getApellidos());
            bInsertado = (ps.executeUpdate()==1);
            if (bInsertado) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                    usuario.setId(rs.getInt(1));
            }
        }
        grabaEnLogIns(usuario,sqlINSERT);
        return bInsertado;
    }

    private void grabaEnLogIns(Usuario usuario,String sql) throws Exception {
        sql = sql.replaceFirst("\\?",usuario.getNombre());
        sql = sql.replaceFirst("\\?",usuario.getApellidos());
        LogFile.saveLOG(sql);
    }
    @Override
    public boolean modificar(Usuario usuario) throws Exception {
        boolean modificado;
        try (PreparedStatement ps =con.prepareStatement(sqlUPDATE)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setInt(3, usuario.getId());
            modificado = (ps.executeUpdate() == 1);
        }
        grabaEnLogUpd(usuario,sqlUPDATE);
        return modificado;
    }
    private void grabaEnLogUpd(Usuario usuario,String sql) throws Exception {
        sql = sql.replaceFirst("\\?",usuario.getNombre());
        sql = sql.replaceFirst("\\?",usuario.getApellidos());
        sql = sql.replaceFirst("\\?",String.valueOf(usuario.getId()));
        LogFile.saveLOG(sql);
    }


    @Override
    public boolean borrar(int id) throws Exception {
        boolean borrado;
        try (PreparedStatement ps = con.prepareStatement(sqlDELETE)){
            ps.setInt(1, id);
            borrado = (ps.executeUpdate() == 1);
        }
        grabaEnLogDel(id,sqlDELETE);
        return borrado;
    }
    private void grabaEnLogDel(int id,String sql) throws Exception {
        sql = sql.replaceFirst("\\?",String.valueOf(id));
        LogFile.saveLOG(sql);
    }

    @Override
    public List<Usuario> leerAllUsuarios() throws Exception {
        List<Usuario> lista = null;
        String sql="SELECT id,nombre,apellidos FROM usuario";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            LogFile.saveLOG(sql);
            lista=new ArrayList<>();
            while (rs.next()){
                Usuario usuario=new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                lista.add(usuario);
            }
        }
        return lista;
    }

    @Override
    public List<Usuario> leerUsuariosOR(int id, String nombre, String apellidos) throws Exception {
        String sql="SELECT id,nombre,apellidos FROM usuario";
        String where="";
        String wId="";
        if (id != 0) {
            wId = "id = ?";
            where = Sql.rellenaWhereOR(where, wId);
        }
        String wNombre="";
        if (!nombre.trim().equals("")) {
            wNombre = "nombre LIKE ?";
            where = Sql.rellenaWhereOR(where, wNombre);
        }
        String wApellidos="";
        if (!apellidos.trim().equals("")) {
            wApellidos = "apellidos LIKE ?";
            where = Sql.rellenaWhereOR(where, wApellidos);
        }
        if (where.equals(""))
            return leerAllUsuarios();
        else {
            List<Usuario> lista = null;
            sql = sql + " WHERE "+where;
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                int i = 1;
                if (!wId.equals(""))
                    pstmt.setInt(i++, id);
                if (!wNombre.equals(""))
                    pstmt.setString(i++, nombre);
                if (!wApellidos.equals(""))
                    pstmt.setString(i++, apellidos);
                ResultSet rs = pstmt.executeQuery();
                LogFile.saveLOG(sql);
                lista=new ArrayList<>();
                while (rs.next()){
                    Usuario usuario=new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    lista.add(usuario);
                }
            }
            return lista;
        }
    }



    @Override
    public Usuario getUsuario(int id) throws Exception {
        Usuario usuario=null;
        String sql="SELECT id,nombre,apellidos FROM usuario WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            LogFile.saveLOG(sql.replace("?",String.valueOf(id)));
            if (rs.next()){
                usuario=new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
            }
        }
        return usuario;
    }
}
