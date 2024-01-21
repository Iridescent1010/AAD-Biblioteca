package auto.chincho;

import chincho.merinde.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;

public class Bullet {
    
    Ellipse2D.Double sprite;
    
    static Color defaultColor = Color.YELLOW;
    static double size = 4;
    
    private double bulletOffsetx;
    private double bulletOffsety;
    private double velocity = 5;
    
    private double xPos;
    private double yPos;
    int direction;
    
    public Bullet(double xPos, double yPos, int dir, Player parent) {
        bulletOffsetx = parent.size / 2;
        if (dir == 1){
            bulletOffsety = parent.size - 5;
        } else {
            bulletOffsety = 5;
        }
        this.xPos = xPos + bulletOffsetx;
        this.yPos = yPos + bulletOffsety;
        velocity *= dir;
        
    }
    
    public void draw(Graphics2D g2d) {
        sprite = new Ellipse2D.Double(xPos, yPos, size, size);
        g2d.setColor(defaultColor);
        g2d.fill(sprite);
        
        yPos += velocity;
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

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
    
    
}
