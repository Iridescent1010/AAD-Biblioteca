package modelo.dao;
import modelo.Categoria;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class CategoriaDAOHibernate implements CategoriaDAO {

    private EntityManagerFactory managerFactory;

    public CategoriaDAOHibernate(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
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
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
        return false;
    }

    @Override
    public boolean modificar(Categoria categoria) throws Exception {
        return false;
    }

    @Override
    public boolean borrar(int id) throws Exception {
        return false;
    }

    @Override
    public Categoria categoria(int id) throws Exception {
        return null;
    }

    @Override
    public List<Categoria> leerAllCategorias() throws Exception {
        return null;
    }
}
