import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import vista.FormMain;

import javax.swing.*;
import java.util.logging.Level;

public class Biblioteca {
    public static void main(String[] args) {


        // Para eliminar los mensajes de Hibernate hacer cuando este funcionado bien
        // @SuppressWarnings("unused")
       // org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
       // java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            if (session != null) {
                System.out.println("Se inicio la sessión");
            } else {
                System.out.println("Error abriendo la sessión");
            }
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormMain.getInstance().setVisible(true);
            }
        });
    }
}
