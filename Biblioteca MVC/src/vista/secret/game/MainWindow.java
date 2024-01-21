package auto.chincho;

import chincho.merinde.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MainWindow extends JFrame {

    MovementPanel panel;
    Timer timer;
    int xSpeed = 2;
    InputListener input;

    MainWindow() {
        panel = new MovementPanel();
        timer = new Timer(1000 / 120, (ActionEvent ae) -> {
            update();
        });
        timer.start();
        input = new InputListener(this);
        
        this.add(panel);
        this.pack();
        this.setTitle("Me parece que me vas a dar la pelota");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(input);
        this.setVisible(true);
    }

    private void update() {
        checkMovementInputs();
        panel.repaint();
        autoMovement();
    }
    
    void autoMovement(){
        double sizeDif = Math.abs( - panel.getLower().getSize());
        double dif = (panel.getUpper().getxPos() + panel.getUpper().size / 2)
                -(panel.getLower().getxPos() +  panel.getLower().size/2);
        
        if (dif < 0){
            moveUpperRight();
        } else if (dif > 0) {
            moveUpperLeft();
        }
        panel.getUpper().shoot(1);
    }
    
    void checkMovementInputs() {
                
        // Lower player movement
        if (input.pressedKeys.contains(KeyEvent.VK_LEFT)) {
            moveLowerLeft();
        }
        if (input.pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            moveLowerRight();
        }
        if(input.pressedKeys.contains(KeyEvent.VK_UP)) {
            panel.getLower().shoot(-1);
        }
    }

    void moveUpperLeft() {
        if (panel.getUpper().getxPos() > 0) {
            panel.getUpper().moveLeft();
        }
    }

    void moveUpperRight() {
        if (panel.getUpper().getxPos() < panel.getWidth() - panel.getUpper().size) {
            panel.getUpper().moveRight();
        }
    }

    void moveLowerLeft() {
        if (panel.getLower().getxPos() > 0) {
            panel.getLower().moveLeft();
        }
    }

    void moveLowerRight() {
        if (panel.getLower().getxPos() < panel.getWidth() - panel.getLower().size) {
            panel.getLower().moveRight();
        }
    }
}
