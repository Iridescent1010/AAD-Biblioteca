import excepciones.CampoVacioExcepcion;
import modelo.Categoria;
import modelo.Libro;
import modelo.dao.CategoriaDAO;
import modelo.dao.CategoriaDAOHibernate;
import modelo.dao.LibroDAO;
import modelo.dao.LibroDAOHibernate;
import modelo.dao.helper.HibernateUtilJPA;
import singleton.Configuracion;

/**
 * Es un método main para probar que la librería de hibernate
 * está funcionando correctamente
 */
public class HibernateTest {
    static CategoriaDAO categoriaDAO = new CategoriaDAOHibernate();
    static LibroDAO libroDAO = new LibroDAOHibernate();
    public static void main(String[] args) {
        try {
            // Puede que de error sin esto
            Configuracion.getInstance().setPassword("pwd13");
            HibernateUtilJPA.suppressWarnings();
            testCategoria();
            testLibro();
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

    static void testLibro() throws Exception {
        Libro lib = new Libro();
        lib.setAutor("Migüel do crevantes");
        lib.setNombre("super sayayin");
        lib.setCategoria(categoriaDAO.categoria(1));
        libroDAO.insertar(lib);
        System.out.println(libroDAO.getLibro(lib.getId()));
        System.out.println(lib.getCategoria());
    }
}
