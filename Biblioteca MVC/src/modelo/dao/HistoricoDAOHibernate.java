package modelo.dao;

import modelo.Historico;

import java.io.IOException;
import java.sql.SQLException;

public class HistoricoDAOHibernate implements HistoricoDAO{
    @Override
    public Historico getHistorico() {
        return null;
    }

    @Override
    public boolean insertar() throws SQLException, IOException {
        return false;
    }
}
