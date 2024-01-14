import modelo.Categoria;
import modelo.dao.CategoriaDAOHibernate;
import modelo.dao.helper.HibernateUtilJPA;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Es un método main para probar que la librería de hibernate
 * está funcionando correctamente
 */
public class HibernateTest {
    public static void main(String[] args) {

        try {
            Categoria c = new Categoria();
            c.setCategoria("ciencia culo");
            new CategoriaDAOHibernate(HibernateUtilJPA.getEntityManagerFactory()).inserta(c);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /*
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            if (session != null) {
                System.out.println("Se inicio la sessión");
            } else {
                System.out.println("Error abriendo la sessión");
            }
        }

         */
    }
}
