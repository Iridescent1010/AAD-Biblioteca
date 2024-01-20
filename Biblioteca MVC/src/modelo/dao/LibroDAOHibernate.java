package modelo.dao;

import modelo.Categoria;
import modelo.Libro;
import modelo.dao.helper.HibernateUtilJPA;
import modelo.dao.helper.LogFile;
import org.hibernate.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class LibroDAOHibernate implements LibroDAO{
    private final EntityManagerFactory managerFactory;

    public LibroDAOHibernate() {
        managerFactory = HibernateUtilJPA.getEntityManagerFactory();
    }

    /*
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {

        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();
            throw new Exception("Error: Imposible actualizar el empleado", e)
        } finally {
            man.close()
        }
     */
    @Override
    public boolean insertar(Libro libro) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {
            trans = man.getTransaction();
            trans.begin();
            man.persist(libro);
            man.flush();
            trans.commit();
            LogFile.saveLOG("[Hibernate] Libro insertado");
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();
            throw new Exception(("Error: Imposible insertar el libro"), e);
        } finally {
            man.close();
        }
    }

    @Override
    public boolean modificar(Libro libro) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {
            trans = man.getTransaction();
            trans.begin();
            man.merge(libro);
            man.flush();
            trans.commit();
            LogFile.saveLOG("[Hibernate] Libro actualizado");
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();
            throw new Exception("Error: Imposible actualizar el libro", e);
        } finally {
            man.close();
        }
    }

    @Override
    public boolean borrar(int id) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {
            Libro libro = getLibro(id);
            if (libro == null) return false;
            trans = man.getTransaction();
            trans.begin();
            libro = man.merge(libro);
            man.remove(libro);
            trans.commit();
            LogFile.saveLOG("[Hibernate] Libro eliminado");
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();
            throw new Exception("Error: Imposible borrar el libro", e);
        } finally {
            man.close();
        }
    }

    @Override
    public List<Libro> leerAllLibros() throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        try {
            LogFile.saveLOG("[Hibernate] Todos los libros seleccionados");
            String query = "SELECT l FROM Libro l";
            return man.createQuery(query, Libro.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Error: Imposible seleccionar los libros", e);
        } finally {
            man.close();
        }
    }

    /**
     * Métodos llamados en la parte de búsqueda de libro, antes de generar un nuevo préstamo
     * @param original
     * @return
     */
    private String insertWildcards(String original) {
        original = original.trim();
        if (original.isEmpty())
            return original;
        return "%" + original + "%";
    }
    @Override
    public List<Libro> leerLibrosOR(int id, String titulo, String autor, String editorial, int categoria) throws Exception {
        EntityManager man = managerFactory.createEntityManager();

        // Insertar wildcards en strings no vacios
        titulo = insertWildcards(titulo);
        autor = insertWildcards(autor);
        editorial = insertWildcards(editorial);

        try {
            String query = """
                SELECT l FROM Libro l
                WHERE l.id = :id OR l.nombre like :titulo OR l.autor like :autor
                OR l.editorial like :editorial OR l.categoria.id = :idcategoria
            """;
            TypedQuery<Libro> q = man.createQuery(query, Libro.class);
            q.setParameter("id", id);
            q.setParameter("titulo", titulo);
            q.setParameter("autor", autor);
            q.setParameter("editorial", editorial);
            q.setParameter("idcategoria", categoria);

            LogFile.saveLOG("[Hibernate] Múltiples libros seleccionados (OR)");
            return q.getResultList();
        } catch (Exception e) {
            throw new Exception("Error: Imposible seleccionar los libros", e);
        } finally {
            man.close();
        }
    }

    @Override
    public Libro getLibro(int id) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        try {
            LogFile.saveLOG("[Hibernate] Libro seleccionado " + id);
            return man.find(Libro.class, id);
        } catch (Exception e) {
            throw new Exception("Error: Imposible seleccionar el libro", e);
        } finally {
            man.close();
        }
    }
}
