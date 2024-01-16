import excepciones.CampoVacioExcepcion;
import modelo.Categoria;
import modelo.dao.CategoriaDAO;
import modelo.dao.CategoriaDAOHibernate;

/**
 * Es un método main para probar que la librería de hibernate
 * está funcionando correctamente
 */
public class HibernateTest {
    static CategoriaDAO categoriaDAO = new CategoriaDAOHibernate();
    public static void main(String[] args) {
        try {
            testCategoria();
        } catch (Exception e) {
            e.printStackTrace();
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

    // Funciona!
    static void testCategoria() throws Exception {
        Categoria c = new Categoria();
        c.setCategoria("enemies to lovers");
        categoriaDAO.inserta(c);
        Categoria c1 = categoriaDAO.categoria(1);
        c1.setCategoria("Super a tope!!!");
        categoriaDAO.modificar(c1);
        System.out.println(categoriaDAO.leerAllCategorias());
        categoriaDAO.borrar(c.getId());
    }
}
