
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author jame1850
 */

public class Debris extends Entity {
    Image debris;
    Rectangle hitbox;
    
    public Debris() throws SlickException {
        debris = new Image("assets/images/debris.png");
        float height = (float)(75);
        float width = (float)(75);   
        hitbox = new Rectangle(xLoc, yLoc, height, width);
    }
    
    public void draw() {
        debris.draw(xLoc, yLoc, 75, 75);
        hitbox.setX(xLoc);
        hitbox.setY(yLoc);
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }

    
}
