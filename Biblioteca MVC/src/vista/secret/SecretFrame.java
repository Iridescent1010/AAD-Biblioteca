package vista.secret;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SecretFrame extends JDialog {
    public static final String IMAGE_PATH = "http://upload.wikimedia.org/wikipedia/"
            + "commons/thumb/3/39/European_Common_Frog_Rana_temporaria.jpg/"
            + "800px-European_Common_Frog_Rana_temporaria.jpg";

    public SecretFrame(JFrame frame) {
        super(frame, "About [PROGRAM]", true);

        ImageIcon myIcon = null;
        try {
            URL imgUrl = new URL(IMAGE_PATH);
            BufferedImage img = ImageIO.read(imgUrl);
            myIcon = new ImageIcon(img);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        JPanel main = new JPanel(new BorderLayout());

        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel centerLabel = new JLabel(myIcon);
        JLabel name = new JLabel("[PROGRAM]");
        JLabel expandedName = new JLabel("[PROGRAM DESCRIPTION]");
        JLabel copyright = new JLabel("[COPYRIGHT JUNK]");
        JLabel credits = new JLabel("[CREDITS]");

        name.setFont(new Font(name.getFont().getFamily(), Font.BOLD, 18));

        copyright.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        int eb = 20;
        centerLabel.setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.add(name);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(expandedName);
        leftPanel.add(copyright);
        leftPanel.add(credits);
        leftPanel.add(Box.createVerticalGlue());

        main.add(centerLabel, BorderLayout.CENTER);
        main.add(leftPanel, BorderLayout.LINE_START);

        add(main);

        pack();
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("GUI");
        JPanel panel = new JPanel();
        panel.add(new JButton(new AbstractAction("About") {

            @Override
            public void actionPerformed(ActionEvent e) {
                SecretFrame about = new SecretFrame(frame);
                about.setLocationRelativeTo(frame);
                about.setVisible(true);
            }
        }));
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
