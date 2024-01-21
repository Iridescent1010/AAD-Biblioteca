package vista.secret;
import vista.secret.game.Game;
import vista.secret.game.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecretFrame extends JDialog {

    public SecretFrame(JFrame frame) {
        super(frame, "(｡･ω･｡)ﾉ♡", true);

        ImageIcon myIcon = null;
        try {
            BufferedImage img = ImageIO.read(new File("src/resources/biblioteca.jpeg"));
            myIcon = new ImageIcon(img);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel main = new JPanel(new BorderLayout());

        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel centerLabel = new JLabel(myIcon);
        JLabel name = new JLabel("{Proyecto Biblioteca ORM}");
        JLabel expandedName = new JLabel("<html>Proyecto realizado con muchísimo amor y cariño infinito por<br>" +
                "Ridoan, Tinan e Irodoscint wiiiii o((&gt;ω&lt; ))o <br><sub>y por el profe que nos dio el proyecto medio hecho</sub>");
        JLabel copyright = new JLabel("Esperamos que os haya super encantado :)");

        name.setFont(new Font(name.getFont().getFamily(), Font.BOLD, 18));

        copyright.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        int eb = 20;
        centerLabel.setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.add(name);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(expandedName);
        leftPanel.add(copyright);
        leftPanel.add(Box.createVerticalGlue());

        main.add(centerLabel, BorderLayout.CENTER);
        main.add(leftPanel, BorderLayout.LINE_START);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                long time = System.currentTimeMillis();
                if (time - lastPressed > 1000) { // reinicia secuencia si pasa un segundo
                    currentSequence.clear();
                }
                lastPressed = time;
                currentSequence.add(e.getKeyCode());
                if (currentSequence.equals(konamiCode)) {
                    close();
                    Game.launch();
                }
            }
        });

        add(main);

        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void close() {
        this.dispose();
    }

    long lastPressed = System.currentTimeMillis();
    List<Integer> currentSequence = new ArrayList<>();
    List<Integer> konamiCode = Arrays.asList(38, 38, 40, 40, 37, 39, 37, 39, 66, 65, 10);
}
