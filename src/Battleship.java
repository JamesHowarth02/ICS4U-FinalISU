import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Chatt
 */
public class Battleship extends SpaceShip {

    public Battleship() throws SlickException {
        super(new Image("assets/images/spaceship_varient3.png"));
        speed = 2;
        health = 200;
        damage = 15;
        name = "The Battleship";
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
