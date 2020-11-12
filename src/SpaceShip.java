import org.newdawn.slick.Image;


/**
 *
 * @author Chatt
 */
public abstract class SpaceShip extends Entity {
    protected Image space_image;
    protected int rotation;
    
    public SpaceShip(Image ship) {
        space_image = ship;
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
    
    public abstract void fireLaser();
    public abstract void draw();
}
