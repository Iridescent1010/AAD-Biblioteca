package modelo.dao;

import excepciones.CampoVacioExcepcion;
import modelo.dao.helper.LogFile;
import singleton.ConexionMySQL;
import modelo.old.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Aquí implementaremos las reglas de negocio definidas
 * en la interfaz para trabajar con categorías y
 * base de datos en MySQL
 * @author AGE
 * @version 2
 */
public class CategoriaDAOImpl implements CategoriaDAO {

    private final Connection con;
    private static final String sqlINSERT="INSERT INTO categoria (categoria) VALUES (?)";
    private static final String sqlUPDATE="UPDATE categoria SET categoria=? WHERE id = ?";
    private static final String sqlDELETE="DELETE FROM categoria WHERE id = ?";
    public CategoriaDAOImpl() throws Exception {
        con = ConexionMySQL.getInstance().getConexion();
    }

    @Override
    public boolean inserta(Categoria categoria) throws Exception {
        boolean insertado;
        try (PreparedStatement pstmt = con.prepareStatement(sqlINSERT,PreparedStatement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1,categoria.getCategoria());
            insertado=pstmt.executeUpdate()==1;
            if (insertado) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next())
                    categoria.setId(rs.getInt(1));
            }

        }
        LogFile.saveLOG(sqlINSERT.replace("?",categoria.getCategoria()));
        return insertado;
    }

    @Override
    public boolean modificar(Categoria categoria) throws Exception {
        boolean actualizado;
        try (PreparedStatement pstmt = con.prepareStatement(sqlUPDATE)) {
            pstmt.setString(1,categoria.getCategoria());
            pstmt.setInt(2,categoria.getId());
            actualizado=pstmt.executeUpdate()==1;
        }
        String sql=sqlUPDATE.replaceFirst("\\?",
                categoria.getCategoria());
        LogFile.saveLOG(sql.replace("?",String.valueOf(categoria.getId())));
        return actualizado;
    }

    @Override
    public boolean borrar(int id) throws Exception {
        boolean borrado=false;
        try (PreparedStatement pstmt = con.prepareStatement(sqlDELETE)) {
            pstmt.setInt(1,id);
            borrado=pstmt.executeUpdate()==1;
        }
        LogFile.saveLOG(sqlDELETE.replace("?",String.valueOf(id)));
        return borrado;
    }
    /**
     * el valor máximo del campo id de la tabla categorías
     * @return valor máximo del campo id
     * @throws Exception cualquier error asociado a la consulta sql
     */
    public static int maximaId() throws Exception {
        Connection con = ConexionMySQL.getInstance().getConexion();
        int maximo= 0;
        String sql = "SELECT MAX(id) AS max_id FROM categoria";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            LogFile.saveLOG(sql);
            if (rs.next()) {
                maximo= rs.getInt("max_id");
            }
        }
        return maximo;
    }

    /**
     * el valor mínimo del campo id de la tabla categorías
     * @return valor mínimo del campo id
     * @throws Exception cualquier error asociado a la consulta sql
     */
    public static int minimaId() throws Exception {
        Connection con = ConexionMySQL.getInstance().getConexion();
        int minimo= 0;
        String sql = "SELECT MIN(id) AS min_id FROM categoria";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            LogFile.saveLOG(sql);
            if (rs.next()) {
                minimo= rs.getInt("min_id");
            }
        }
        return minimo;
    }

    /**
     * para instanciar un objeto categoria a partir de un id
     * @param id clave primaria de la tabla categoria
     * @return el objeto categoría asociado a una clave primaria
     * @throws Exception cualquier error asociado a la consulta sql
     */
    @Override
    public Categoria categoria(int id) throws Exception {
        Categoria categoria=null;
        String sql="SELECT * FROM categoria WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            LogFile.saveLOG(sql.replace("?",String.valueOf(id)));
            if (rs.next()){
                categoria=new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setCategoria(rs.getString("Categoria"));
            }
        }
        return categoria;
    }


    /**
     * Este método estático devuelve todos las categorías de la BD,
     * este método tendremos en un futuro reimplmentarlo por rangos de x,
     * para que el rendimiento no decaiga cuando la tabla crezca
     * @return un arraylist con todos las categorias de la BD
     * @throws SQLException cualquier error asociado a la consulta sql
     * @throws CampoVacioExcepcion en el caso que contenga una categoria con categoria a null
     */
    public List<Categoria> leerAllCategorias() throws Exception {
        List<Categoria> lista = null;
        String sql = "SELECT id,categoria FROM categoria";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            LogFile.saveLOG(sql);
            lista = new ArrayList<>();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setCategoria(rs.getString("categoria"));
                lista.add(categoria);
            }
        }
        return lista;
    }

}
