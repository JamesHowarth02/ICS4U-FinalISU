/**
 *
 * @author Chatt
 */
public abstract class Entity {
    protected int health, shield;
    protected int xLoc, yLoc;
    protected String name;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public void setPosition(int xLoc, int yLoc) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }
    
    public int getXPosition() {
        return this.xLoc;
    }
    
    public int getYPosition() {
        return this.yLoc;
    }
    
    public int getHealth() {
        return this.health;
    }
    
    public String getName() {
        return this.name;
    }
}
