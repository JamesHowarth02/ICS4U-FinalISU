import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Chatt
 */
public class Battleship extends SpaceShip {

    ArrayList<Laser> lasers = new ArrayList();
    
    long cooldown_time = System.currentTimeMillis();
    int cooldownMs = 150;
    
    public Battleship() throws SlickException {
        super(new Image("assets/images/spaceship_varient3.png"));
        hitbox = new Rectangle(xLoc, yLoc, 75, 75);
        speed = 2;
        health = 200;
        maxhealth = 200;
        damage = 15;
        name = "The Battleship";
        
    }

    @Override
    public void fireLaser() throws SlickException {
        Laser laser = new Laser(new Image("assets/images/laser.png"), xLoc, yLoc);
        long currentTime = System.currentTimeMillis();
        int duration = (int) (currentTime - cooldown_time);
        if(duration>=cooldownMs) {
            laser.setXPosition(xLoc + 30);
            laser.setYPosition(yLoc - 75);
            lasers.add(laser);
            cooldown_time = System.currentTimeMillis();
        }
    }
    
    @Override
    public ArrayList<Laser> getLasers() {
        return lasers;
    }
    
    @Override
    public boolean checkForHit(ArrayList<Debris> debrisList) {
        for (int i = 0; i < debrisList.size(); i++) {
            Debris debrisObject = debrisList.get(i);
            if(debrisObject != null) {
                for (int z = 0; z < lasers.size(); z++) {
                    if(debrisObject.getHitbox().intersects(lasers.get(z).getHitbox())) {
                        debrisList.remove(debrisObject);
                        lasers.remove(lasers.get(z));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void draw() {
        space_image.setRotation(rotation);
        space_image.draw(xLoc, yLoc, 75, 75);
        hitbox.setX(xLoc);
        hitbox.setY(yLoc);
        for (int i = 0; i < lasers.size(); i++) {
            Laser laser = lasers.get(i);
            int x = laser.getPositionX();
            int y = laser.getPositionY();
            
            y = y - 8;
            laser.draw();
            laser.setYPosition(y);
            
            if(y == -50) {
                lasers.remove(i);
            }
        }
    }  
}
