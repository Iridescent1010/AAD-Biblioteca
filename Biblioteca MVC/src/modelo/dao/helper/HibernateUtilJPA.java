package modelo.dao.helper;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Es la clase proporcionada en el proyecto "proyectoJPA" pero he añadido dos
 * métodos (clearDatabase() y suppressWarnings())
 */
public class HibernateUtilJPA {
    // Reemplaza con el nombre de tu unidad de persistencia ver en archivo persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "aad_biblioteca";

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = createEntityManagerFactory();
        }
        return entityManagerFactory;
    }

    private static EntityManagerFactory createEntityManagerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // Reemplaza con el dialecto de tu base de datos
        // Puedes agregar más propiedades según tus necesidades

        return new HibernatePersistenceProvider()
                .createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
    }

    public static void suppressWarnings() {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    public static void printException(Exception e){
        System.err.println("---- Pila de excepciones INICIO ----");
        System.err.println("[superior]" + e.getClass().toString());
        Throwable th = e;
        int i=1;
        while ((th != null) && !(th instanceof ConstraintViolationException)) {
            th = th.getCause();
            if (th != null)
                System.err.println("[-" + (i++) + "]" + th.getClass().toString());
        }
        System.err.println("---- Pila de excepciones: FIN   ----");
        if (th !=null && th instanceof ConstraintViolationException ) {
            ConstraintViolationException cve = (ConstraintViolationException) th;
            System.err.println("===================================");
            System.err.println("Excepción de Hibernate de tipo " + cve.getClass().getName() + ": [" + cve.getMessage() + "]");
            System.err.println("Sentencia SQL: " + cve.getSQL());
            System.err.println("Restricción violada: " + cve.getConstraintName());
            System.err.print("Error de la excepción SQLException: ");
            System.err.println(cve.getSQLException().getMessage());
            System.err.println("===================================");
        } else {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Elimina todos los datos de la base de datos
     * @throws Exception
     */
    public static void clearDatabase() throws Exception {
        EntityManager manager = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = manager.getTransaction();
            transaction.begin();
            String[] queries = new String[]{
                    "USE proyecto_orm;",
                    "DELETE FROM empleado;",
                    "DELETE FROM empleado_datos_prof;",
                    "DELETE FROM proyecto;",
                    "DELETE FROM proyecto_sede;",
                    "DELETE FROM departamento;",
                    "DELETE FROM sede;"
            };

            for (String sql : queries) {
                Query q = manager.createNativeQuery(sql);
                q.executeUpdate();
            }
            transaction.commit();
        } catch(Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();

            throw new Exception("Error: Imposible eliminar la base de datos",e);
        } finally {
            manager.close();
        }
    }
}
