import vista.FormMain;

import javax.swing.*;

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
