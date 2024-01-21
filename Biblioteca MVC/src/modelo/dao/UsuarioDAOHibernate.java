package modelo.dao;

import modelo.Categoria;
import modelo.Libro;
import modelo.Usuario;
import modelo.dao.helper.HibernateUtilJPA;
import modelo.dao.helper.LogFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class UsuarioDAOHibernate implements UsuarioDAO{

    private final EntityManagerFactory managerFactory;

    public UsuarioDAOHibernate() {
        this.managerFactory = HibernateUtilJPA.getEntityManagerFactory();
    }
    @Override
    public boolean insertar(Usuario usuario) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {
            trans = man.getTransaction();
            trans.begin();
            man.persist(usuario);
            man.flush();
            trans.commit();
            LogFile.saveLOG("[Hibernate] Usuario insertado" + usuario.getId());
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();
            throw new Exception("Error: Imposible insertar usuario", e);
        } finally {
            man.close();
        }
    }

    @Override
    public boolean modificar(Usuario usuario) throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        EntityTransaction trans = null;
        try {
            trans = man.getTransaction();
            trans.begin();
            man.merge(usuario);
            man.flush();
            trans.commit();
            LogFile.saveLOG("[Hibernate] Usuario actualizado" + usuario.getId());
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
            Usuario usuario = getUsuario(id); // Primero buscamos el objeto
            if (usuario == null) return false; // Si no existe no se puede borrar
            trans = man.getTransaction();
            trans.begin();
            usuario = man.merge(usuario);
            man.remove(usuario);
            trans.commit();
            LogFile.saveLOG("[Hibernate] Usuario eliminado" + usuario.getId());
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();

            throw new Exception("Error: Imposible eliminar el usuario con id " + id, e);
        } finally {
            man.close();
        }
    }

    @Override
    public List<Usuario> leerAllUsuarios() throws Exception {
        EntityManager man = managerFactory.createEntityManager();
        try {
            LogFile.saveLOG("[Hibernate] Todos los usuarios seleccionados");
            String query = "SELECT l FROM Usuario l";
            return man.createQuery(query, Usuario.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Error: Imposible seleccionar los usuarios", e);
        } finally {
            man.close();
        }
    }

    private String insertWildcards(String original) {
        original = original.trim();
        if (original.isEmpty())
            return original;
        return "%" + original + "%";
    }
    @Override
    public List<Usuario> leerUsuariosOR(int id, String nombre, String apellidos) throws Exception {
        EntityManager man = managerFactory.createEntityManager();

        // Insertar wildcards en strings no vacíos
        nombre = insertWildcards(nombre);
        apellidos = insertWildcards(apellidos);

        try {
            String query = """
                SELECT l FROM Usuario l
                WHERE l.id = :id OR l.nombre like :nombre OR l.apellidos like :apellidos
            """;
            TypedQuery<Usuario> q = man.createQuery(query, Usuario.class);
            q.setParameter("id", id);
            q.setParameter("nombre", nombre);
            q.setParameter("apellidos", apellidos);

            LogFile.saveLOG("[Hibernate] Todos los usuario seleccionados (OR)");
            return q.getResultList();
        } catch (Exception e) {
            throw new Exception("Error: Imposible seleccionar los usuario", e);
        } finally {
            man.close();
        }
    }

    @Override
    public Usuario getUsuario(int id) throws Exception {
         EntityManager man = managerFactory.createEntityManager();
        try {
            LogFile.saveLOG("[Hibernate] Categoria seleccionada " + id);
            return man.find(Usuario.class, id);
        } catch (Exception e) {
            throw new Exception("Error: Imposible seleccionar la categoría", e);
        } finally {
            man.close();
        }
    }
}
