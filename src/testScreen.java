//import org.newdawn.slick.Input;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author jame1850
 */

public class testScreen extends BasicGameState {

    static String name;
    
    
    
    public static void setName(String nm) {
        name = nm;
    }
    
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        try {
            Controllers.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(startScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        Controller controller = Controllers.getController(1);
        controller.setRumblerStrength(0, 0);
        grphcs.setColor(Color.red);
        grphcs.drawString(name, 10, 10);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        
    }
}
