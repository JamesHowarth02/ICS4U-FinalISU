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

public class endScreen extends BasicGameState {
    
    Image background, end_text;
    
    @Override
    public int getID() {
        return 3;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("assets/images/menu_background.png");
        if(Player.getMissionStatus()) {
            end_text = new Image("assets/images/mission_success.png");
        }else{
            end_text = new Image("assets/images/mission_failed.png");
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0);
        end_text.draw(225, 350);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int iz) throws SlickException {
        
    } 
}
