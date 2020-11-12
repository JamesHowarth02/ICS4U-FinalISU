import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;


/**
 *
 * @author Chatt
 */
public abstract class SpaceShip extends Entity {
    protected Image space_image;
    protected int rotation;
    Rectangle hitbox;
    Sound A, B, C;
    
    public SpaceShip(Image ship) throws SlickException {
        space_image = ship;
        A = new Sound("assets/sounds/hit_1.wav");
        B = new Sound("assets/sounds/hit_2.wav");
        C = new Sound("assets/sounds/hit_3.wav");
    }
    
    public Image getImage() {
        return space_image;
    }
    
    public void setRotation(int rot) {
        this.rotation = rot;
    }
    
    public int getRotation() {
        return this.rotation;
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public void playDamageSound() {
        int rand = (int)(Math.random() * 3) + 1;
        if(rand == 1) {
            A.play(1f, 0.25f);
        }else if(rand == 2) {
            B.play(1f, 0.25f);
        }else {
            C.play(1f, 0.25f);
        }
    }
    
    
    public abstract void fireLaser() throws SlickException;
    public abstract ArrayList<Laser> getLasers();
    public abstract boolean checkForHit(ArrayList<Debris> debrisList);
    public abstract void draw();
}
