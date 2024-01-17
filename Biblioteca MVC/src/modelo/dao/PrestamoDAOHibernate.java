package modelo.dao;

import modelo.Prestamo;
import modelo.Usuario;
import modelo.dao.helper.HibernateUtilJPA;
import modelo.dao.helper.LogFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PrestamoDAOHibernate implements PrestamoDAO{
    private final EntityManagerFactory managerFactory;

    public PrestamoDAOHibernate() {
        this.managerFactory = HibernateUtilJPA.getEntityManagerFactory();
    }
    @Override
    public boolean insertar(Prestamo prestamo) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {
            trans = man.getTransaction();
            trans.begin();
            man.persist(prestamo);
            man.flush();
            trans.commit();
            LogFile.saveLOG("[Hibernate] prestamo insertado" + prestamo.getId());
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();
            throw new Exception("Error: Imposible insertar prestamo", e);
        } finally {
            man.close();
        }
    }

    @Override
    public boolean modificar(Prestamo prestamo) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {
            trans = man.getTransaction();
            trans.begin();
            man.merge(prestamo);
            man.flush();
            trans.commit();
            LogFile.saveLOG("[Hibernate] Usuario actualizado" + prestamo.getId());
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();
            throw new Exception("Error: Imposible actualizar el usuario", e);
        } finally {
            man.close();
        }
    }

    @Override
    public boolean borrar(int id) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {
            Prestamo prestamo = getPrestamo(id); // Primero buscamos el objeto
            if (prestamo == null) return false; // Si no existe no se puede borrar
            trans = man.getTransaction();
            trans.begin();
            prestamo = man.merge(prestamo);
            man.remove(prestamo);
            trans.commit();
            LogFile.saveLOG("[Hibernate] Prestamo eliminado" + prestamo.getId());
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();

            throw new Exception("Error: Imposible eliminar el prestamo con id " + id, e);
        } finally {
            man.close();
        }
    }

    @Override
    public List<Prestamo> leerAllPrestamos() throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        try {
            LogFile.saveLOG("[Hibernate] Todos los prestamos seleccionados");
            String query = "SELECT p FROM Prestamo p";
            return man.createQuery(query, Prestamo.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Error: Imposible seleccionar los prestamos", e);
        } finally {
            man.close();
        }
    }

    @Override
    public Prestamo getPrestamo(int id) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        try {
            LogFile.saveLOG("[Hibernate] Prestamo seleccionado " + id);
            return man.find(Prestamo.class, id);
        } catch (Exception e) {
            throw new Exception("Error: Imposible seleccionar el prestamo", e);
        } finally {
            man.close();
        }
    }
}
