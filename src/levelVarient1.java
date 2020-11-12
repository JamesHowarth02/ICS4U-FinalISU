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
            grphcs.drawString(player.getName(), 0, renderListOrder);
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
        }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int iz) throws SlickException {
       
    }
   
}
