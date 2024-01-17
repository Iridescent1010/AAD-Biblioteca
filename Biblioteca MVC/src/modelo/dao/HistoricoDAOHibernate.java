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

public class HistoricoDAOHibernate implements HistoricoDAO {

    @Override
    public Historico getHistorico() {
        EntityManager entityManager = HibernateUtilJPA.getEntityManagerFactory().createEntityManager();

        try {
            Query query = entityManager.createQuery("SELECT h FROM Historico h ORDER BY h.idHistorico DESC", Historico.class);
            query.setMaxResults(1);
            return (Historico) query.getSingleResult();
        } catch (NoResultException e) {
            System.err.println("No hay historicos en la base de datos.");
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean insertar() throws SQLException, IOException {
        EntityManager entityManager = HibernateUtilJPA.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();


            Historico historico = new Historico();
            historico.setUser(Configuracion.getInstance().getUser());
            historico.setFecha(LocalDateTime.now());
            historico.setInfo("Mensaje de prueba");


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
}
