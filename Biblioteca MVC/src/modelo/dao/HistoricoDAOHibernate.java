package modelo.dao;

import modelo.Historico;
import modelo.dao.helper.HibernateUtilJPA;
import modelo.dao.helper.LogFile;
import singleton.Configuracion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoricoDAOHibernate implements HistoricoDAO {

    private Historico historico;


    public HistoricoDAOHibernate(Historico historico) {
        this.historico = historico;
    }

    @Override
    public Historico getHistorico() {
        return historico;
    }

    @Override
    public boolean insertar() throws SQLException, IOException {
        EntityManager entityManager = HibernateUtilJPA.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(historico);

            grabaEnLogIns(historico);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new SQLException("Error al insertar historico", e);
        } finally {
            entityManager.close();
        }
    }

    private void grabaEnLogIns(Historico historico) throws IOException {
        String logMessage = String.format("[Hibernate] Historico insertado - ID: %d, Usuario: %s, Fecha: %s, Info: %s",
                historico.getIdHistorico(), historico.getUser(), historico.getFechaCad(), historico.getInfo());
        LogFile.saveLOGsinBD(logMessage);
    }

    public static void mensaje(String msgLog) throws Exception {
        Historico historico = new Historico();
        historico.setUser(Configuracion.getInstance().getUser());
        historico.setInfo(msgLog);
        new HistoricoDAOImpl(historico).insertar();
    }
}
