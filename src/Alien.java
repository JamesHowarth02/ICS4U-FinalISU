import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;



/**
 *
 * @author jame1850
 */
public class Alien extends Entity {
    Image image;
    Rectangle hitbox;
    
    public Alien() throws SlickException {
        image = new Image("assets/images/alien.png");
        hitbox = new Rectangle(xLoc, yLoc, 100, 100);
    }
    
    public void draw() {
        image.draw();
        hitbox.setX(xLoc);
        hitbox.setY(yLoc);
    }
    
    public void fireLaser() {
        
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    
}
