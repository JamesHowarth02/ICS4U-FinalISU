
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jame1850
 */
public class Laser {
    Image laser;
    Rectangle hitbox;
    int xLoc, yLoc;
    Sound sound;
    
    public Laser(Image image, int x, int y) throws SlickException {
        this.sound = new Sound("assets/sounds/laser.wav");
        float height = (float)(15);
        float width = (float)(200);   
        laser = image;
        hitbox = new Rectangle(x, y, height, 30);
    }
    
    public void draw() {
        laser.draw(xLoc, yLoc, 15, 200);
        hitbox.setX(xLoc);
        hitbox.setY(yLoc + 100);
    }
    
    public int getPositionX() {
        return xLoc;
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public int getPositionY() {
        return yLoc;
    }
    
    public void setXPosition(int xLoc) {
        sound.play(1, 0.025f);
        this.xLoc = xLoc;
    }
    
    public void setYPosition(int yLoc) {
        this.yLoc = yLoc;
    }
    
}
