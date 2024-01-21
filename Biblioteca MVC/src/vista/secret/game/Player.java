package auto.chincho;

import chincho.merinde.*;
import java.awt.Color;
import java.  awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class Player {

    static double baseVelocity = 2;
    public static final double BASE_SIZE = 100;
    double size;
    private int id;
    static long shootCooldown = 500;

    ImageIcon image;
    Rectangle2D.Double sprite;
    MovementPanel parent;
    private Color color;
    private double xPos;
    private double yPos;
    private long lastFired;
    private int hp;

    public Player(int id, double xPos, double yPos, Color color, ImageIcon image, MovementPanel parent) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
        this.parent = parent;
        this.image = image;
        this.size = BASE_SIZE;
        hp = 3;

        lastFired = 0;
        sprite = new Rectangle2D.Double(xPos, yPos, size, size);
    }

    public void draw(Graphics2D g2d) {
        if (id == 1) {
            this.yPos = parent.getHeight() - size;
        }
        sprite.setRect(xPos, yPos, size, size);
        g2d.setColor(color);
        //g2d.fill(sprite);
        g2d.drawImage(image.getImage(), (int) xPos, (int) yPos, (int) size, (int) size, null);
    }

    public void moveLeft() {
        xPos -= baseVelocity;
    }

    public void moveRight() {
        xPos += baseVelocity;
    }

    public void shoot(int dir) {
        if (System.currentTimeMillis() - lastFired > shootCooldown) {
            lastFired = System.currentTimeMillis();
            Bullet b = new Bullet(xPos, yPos, dir, this);
            parent.add(b);
        }
    }

    public void receiveDamage() {
        hp--;
        this.size /= 2;
        this.xPos += size/2;
        
        if (hp == 0) {
            switch (id) {
                case 0:
                    Main.gameOver("Merinde (el de abajo)");
                case 1:
                    Main.gameOver("Chincho (el de arriba)");
            }
        }
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public double getSize() {
        return size;
    }
    
    
}
