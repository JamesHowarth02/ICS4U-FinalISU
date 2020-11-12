import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Chatt
 */
public class Destroyer extends SpaceShip {

    public Destroyer() throws SlickException {
        super(new Image("assets/images/spaceship_varient1.png"));
        speed = 4;
        health = 75;
        damage = 15;
        name = "The Destroyer";
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
