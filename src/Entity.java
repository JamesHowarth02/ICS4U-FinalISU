
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Chatt
 */
public abstract class Entity {
    protected int health, maxhealth, damage, speed;
    protected int xLoc, yLoc;
    protected String name;

    public abstract Rectangle getHitbox();
    
    public void setName(String name) {
        this.name = name;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setMaxHealth(int health) {
        this.maxhealth = health;
    }
    
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    public void takeDamage(int amount) {
        this.health = this.health - amount;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setPosition(int xLoc, int yLoc) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }
    
    public int getHealth() {
        return this.health;
    }
    
    public int getMaxHealth() {
        return this.maxhealth;
    }
    
    public int getDamage() {
        return this.damage;
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getXPosition() {
        return this.xLoc;
    }
    
    public int getYPosition() {
        return this.yLoc;
    }

}
