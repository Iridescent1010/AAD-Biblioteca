import excepciones.CampoVacioExcepcion;
import modelo.Categoria;
import modelo.Historico;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Usuario;
import modelo.dao.*;
import modelo.dao.helper.HibernateUtilJPA;
import singleton.Configuracion;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Es un método main para probar que la librería de hibernate
 * está funcionando correctamente
 *
 * IMPORTANTE: Antes de ejecutar rellenar la bd con los datos de prueba (datos.sql)
 */
public class HibernateTest {
    private static CategoriaDAO categoriaDAO;
    private static LibroDAO libroDAO;
    private static PrestamoDAO prestamoDAO;
    private static UsuarioDAO usuarioDAO;
    private static HistoricoDAO historicoDAO;
  
    public static void main(String[] args) {
        try {
            // Puede que de error sin esto
            Configuracion.getInstance().setPassword("pwd13");
            HibernateUtilJPA.suppressWarnings();
            categoriaDAO = new CategoriaDAOHibernate();
            libroDAO = new LibroDAOHibernate();
            prestamoDAO = new PrestamoDAOHibernate();
            usuarioDAO = new UsuarioDAOHibernate();
            historicoDAO = new HistoricoDAOHibernate(new Historico());
            System.out.println("== Probando CategoríaDAO ==");
            testCategoria();
            System.out.println("== Probando LibroDAO ==");
            testLibro();
            System.out.println("== Probando UsuarioDAO ==");
            testUsuario();
            System.out.println("== Probando PrestamoDAO ==");
            testPrestamo();
            System.out.println("== Probando Todos ==");
            testAll();
            //testCategoria();
           // testLibro();
            //testInsertarHistorico();
            testObtenerHistorico();
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
    private static void testCategoria() throws Exception {
        Categoria c = new Categoria();
        c.setCategoria("enemies to lovers");
        categoriaDAO.inserta(c);
        Categoria c1 = categoriaDAO.categoria(1);
        c1.setCategoria("Super a tope!!!");
        categoriaDAO.modificar(c1);
        System.out.println(categoriaDAO.leerAllCategorias());
        categoriaDAO.borrar(c.getId());
    }

    private static void testLibro() throws Exception {
        Libro lib = new Libro();
        lib.setAutor("Migüel do crevantes");
        lib.setNombre("super sayayin");
        lib.setCategoria(categoriaDAO.categoria(11234)); // fallará si no existe categoría con id1
        libroDAO.insertar(lib);
        System.out.println(libroDAO.getLibro(lib.getId()));
        System.out.println(lib.getCategoria());
    }

    private static void testUsuario() throws Exception {
        Usuario us = new Usuario();
        us.setNombre("Elian Blackwood");
        us.setApellidos("dies alone");
        usuarioDAO.insertar(us); // 1. Insertar
        System.out.println(usuarioDAO.getUsuario(us.getId())); // 2. Seleccionar insertado
        System.out.println(usuarioDAO.leerAllUsuarios()); // 3. Seleccionar todos
        System.out.println(usuarioDAO.borrar(us.getId())); // 4. Eliminar insertado
        System.out.println(usuarioDAO.getUsuario(us.getId())); // 5. Seleccionar eliminado (devuelve null)
    }

    /**
     * 1. Selecciona el primero de todos los libros
     * 2. Selecciona el primero de todos los usuarios
     *
     */
    private static void testPrestamo() throws Exception {
        Usuario usuario = usuarioDAO.leerAllUsuarios().get(0);
        Libro libro = libroDAO.leerAllLibros().get(0);

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        System.out.println(prestamoDAO.insertar(prestamo)); // 1. Insertar
        System.out.println(prestamoDAO.leerAllPrestamos()); // 2. Seleccionar insertado
        System.out.println(prestamoDAO.leerAllPrestamos()); // 3. Seleccionar todos
        System.out.println(prestamoDAO.borrar(prestamo.getId())); // 4. Eliminar insertado
        System.out.println(prestamoDAO.getPrestamo(prestamo.getId())); // 5. Seleccionar eliminado

    }

    private static void testAll() {
    }

    static void testInsertarHistorico() throws SQLException, IOException {

        Historico historico = new Historico();
        historico.setUser("pepe");
        historico.setFecha(LocalDateTime.now());
        historico.setInfo("Si sas");

        historicoDAO.insertar();
        System.out.println("Historico insertado correctamente.");
    }
    static void testObtenerHistorico() {
        Historico historico = historicoDAO.getHistorico();
        System.out.println(historico);
    }
}