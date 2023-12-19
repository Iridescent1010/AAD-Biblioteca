package modelo.dao;

import modelo.Historico;
import modelo.dao.helper.LogFile;
import singleton.ConexionMySQL;
import singleton.Configuracion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

/**
 * Aquí implementaremos las reglas de negocio definidas
 * en la interfaz para trabajar con historico y
 * base de datos en MySQL
 * @author AGE
 * @version 2
 */
public class HistoricoDAOImpl implements HistoricoDAO {
    private Historico historico;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Connection con;
    private static final String sqlINSERT="INSERT INTO historico (user,fecha,info) VALUES (?,?,?)";
    public HistoricoDAOImpl(Historico historico) throws Exception {
        this.historico = historico;
        con = ConexionMySQL.getInstance().getConexion();
    }


    @Override
    public Historico getHistorico() {
        return historico;
    }

    @Override
    public boolean insertar() throws SQLException, IOException {
        boolean insertado;
        try (PreparedStatement pstmt = con.prepareStatement(sqlINSERT,PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, historico.getUser());
            pstmt.setString(2, historico.getFecha().format(formatter));
            pstmt.setString(3, historico.getInfo());
            insertado = pstmt.executeUpdate()==1;
            if (insertado) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next())
                    historico.setIdHistorico(rs.getInt(1));
            }
        }
        grabaEnLogIns(sqlINSERT);
        return insertado;
    }

    private void grabaEnLogIns(String sql) throws IOException {
        sql = sql.replaceFirst("\\?", String.valueOf(historico.getUser()));
        sql = sql.replaceFirst("\\?", historico.getFecha().format(formatter));
        sql = sql.replaceFirst("\\?", String.valueOf(historico.getInfo()));
        LogFile.saveLOGsinBD(sql);
    }

    /**
     * Este metodo inserta aquellos mensajes que son enviados al fichero LOG
     * pero que serán almacenados en la tabla Historico
     * @param msgLog mensaje enviado para añadir a la tabla Historico
     * @throws IOException
     * @throws SQLException
     */
    public static void mensaje(String msgLog) throws Exception {
        Historico historico=new Historico();
        historico.setUser(Configuracion.getInstance().getUser());
        historico.setInfo(msgLog);
        new HistoricoDAOImpl(historico).insertar();
    }

}