package modelo.dao;

import modelo.Categoria;
import modelo.dao.helper.HibernateUtilJPA;
import modelo.dao.helper.LogFile;
import presentador.PresentadorUsuario;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class CategoriaDAOHibernate implements CategoriaDAO {

    private final EntityManagerFactory managerFactory;

    public CategoriaDAOHibernate() {
        this.managerFactory = HibernateUtilJPA.getEntityManagerFactory();
    }

    @Override
    public boolean inserta(Categoria categoria) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {
            trans = man.getTransaction();
            trans.begin();
            man.persist(categoria);
            man.flush();
            trans.commit();
            LogFile.saveLOG("[Hibernate] Categoria insertada" + categoria.getId());
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();
            throw new Exception("Error: Imposible insertar categoría", e);
        } finally {
            man.close();
        }
    }

    @Override
    public boolean modificar(Categoria categoria) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {
            trans = man.getTransaction();
            trans.begin();
            man.merge(categoria);
            man.flush();
            trans.commit();
            LogFile.saveLOG("[Hibernate] Categoría actualizada" + categoria.getId());
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();
            throw new Exception("Error: Imposible actualizar la categoría", e);
        } finally {
            man.close();
        }
    }

    @Override
    public boolean borrar(int id) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {
            Categoria categoria = categoria(id); // Primero buscamos el objeto
            if (categoria == null) return false; // Si no existe no se puede borrar
            trans = man.getTransaction();
            trans.begin();
            categoria = man.merge(categoria);
            man.remove(categoria);
            trans.commit();
            LogFile.saveLOG("[Hibernate] Categoria eliminada " + categoria.getId());
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();

            throw new Exception("Error: Imposible eliminar la categoría con id " + id, e);
        } finally {
            man.close();
        }

    }

    @Override
    public Categoria categoria(int id) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        try {
            LogFile.saveLOG("[Hibernate] Categoria seleccionada " + id);
            return man.find(Categoria.class, id);
        } catch (Exception e) {
            throw new Exception("Error: Imposible seleccionar la categoría ", e);
        } finally {
            man.close();
        }
    }

    @Override
    public List<Categoria> leerAllCategorias() throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        try {
            LogFile.saveLOG("[Hibernate] Todas las categorías seleccionadas");
            String query = "SELECT d FROM Categoria d";
            return man.createQuery(query, Categoria.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Error: Imposible seleccionar las categorías", e);
        } finally {
            man.close();
        }
    }
}
