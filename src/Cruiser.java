import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Chatt
 */
public class Cruiser extends SpaceShip {

    public Cruiser() throws SlickException {
        super(new Image("assets/images/spaceship_varient2.png"));
        speed = 2;
        health = 100;
        damage = 30;
        name = "The Cruiser";
    }

    @Override
    public void fireLaser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw() {
        space_image.setRotation(rotation);
        space_image.draw(xLoc, yLoc, 75, 75);
    }
    
}
