import java.awt.AlphaComposite;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author jame1850
 */

public class levelVarient1 extends BasicGameState {
    Image background;
    ArrayList<Player> players = new ArrayList();
    ArrayList<Debris> debris = new ArrayList();
    long debrisCooldown = System.currentTimeMillis();
    int range = 10000;
    int goal = 400;
    
    public int getID() {
        return 2;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("assets/images/level_1_background.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        int renderListOrder = 10;
        background.draw(0, 0);
        players = Player.getPlayers();
        grphcs.drawString("Active Fleet: ", 0, 0);
        for(Player player: players) {
            SpaceShip spaceship;
            Controller controller;
            spaceship = player.getSpaceShip();
            controller = player.getController();
            renderListOrder = renderListOrder + 15;
            grphcs.drawString(player.getName() + ": " + spaceship.getHealth() + "/" + spaceship.getMaxHealth(), 0, renderListOrder);
            int xLoc = spaceship.getXPosition();
            int yLoc = spaceship.getYPosition();
            
            int rotation = spaceship.getRotation();
            xLoc = (int) (controller.getXAxisValue() * spaceship.getSpeed() + xLoc);
            yLoc = (int) (controller.getYAxisValue() * spaceship.getSpeed() + yLoc);
            
            rotation = (int) (rotation + controller.getRXAxisValue() + controller.getRYAxisValue());
            spaceship.setRotation(rotation/360);
            spaceship.setPosition(xLoc, yLoc);
            spaceship.draw();
            controller.poll();
            
            boolean isShooting = player.isButtonPressed("A");
            if(isShooting) {
                spaceship.fireLaser();
                boolean destroyed = spaceship.checkForHit(debris);
                if(destroyed) {
                    goal = goal - 1;
                }
            }
            grphcs.drawString("Remaining Aestroids: " + goal, 1150, 0);
            grphcs.drawString(player.getName(), spaceship.getXPosition(), spaceship.getYPosition() + 85);
        }

        for (int i = 0; i < debris.size(); i++) {
            Debris debrisObj = debris.get(i);
            if(debrisObj != null) {
                int yLocD = debrisObj.getYPosition();
                int xLocD = debrisObj.getXPosition();
                yLocD = yLocD + 5;
                debrisObj.draw();
                debrisObj.setPosition(xLocD, yLocD);
                if(yLocD>1000) {
                    debris.remove(debrisObj);
                }else{
                    for (int z = 0; z < players.size(); z++) {
                        Player player = players.get(z);
                        if(player != null) {
                            SpaceShip spaceship = player.getSpaceShip();
                            if(spaceship.getHitbox().intersects(debrisObj.getHitbox())) {
                                spaceship.takeDamage(15);
                                spaceship.playDamageSound();
                                debris.remove(debrisObj);
                                if(spaceship.getHealth()<=0) {
                                    players.remove(player);
                                    if(players.size() == 0) {
                                        player.setMission(false);
                                        sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        if(goal<=0) {
            players.get(0).setMission(true);
            sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
        }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int iz) throws SlickException {
        int rand = (int)(Math.random() * range) + 1;
        int xLoc = (int)(Math.random() * 1400) + 1;
        int timePassed = (int) (System.currentTimeMillis() - debrisCooldown);
        if(timePassed>rand) {
            Debris debrisI = new Debris();
            debrisI.setPosition(xLoc, 0);
            debris.add(debrisI);
            debrisCooldown = System.currentTimeMillis();
        }
        range--;
    }
}
