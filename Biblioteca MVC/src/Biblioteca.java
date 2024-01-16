import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import vista.FormMain;

import javax.swing.*;
import java.util.logging.Level;

public class Biblioteca {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormMain.getInstance().setVisible(true);
            }
        });
    }
}
