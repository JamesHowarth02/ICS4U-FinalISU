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

public class startScreen extends BasicGameState {
    
    ArrayList<Player> players = new ArrayList();
    ArrayList<Controller> available_controllers = new ArrayList();
    
    Image template, background, logo, start;
    Sound sound;
    
    boolean flipInterval = false;
    int flipX = 500;
    int flipY = 25;

    @Override
    public int getID() {
        return 0;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        sound = new Sound("assets/sounds/menu.wav");
        template = new Image("assets/images/controller-template.png");
        background = new Image("assets/images/menu_background.png");
        logo = new Image("assets/images/LOGO.png");
        start = new Image("assets/images/press-start.png");
        sound.play(1f, 0.5f);
        sound.loop();
        try {
           Controllers.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(startScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Controllers.poll();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0);
        logo.draw(250, 100);
        start.draw(450, 350);
        template.draw(375, 425, 640, 360);
        grphcs.drawString("Left Bumper", 525, 425);
        grphcs.drawString("Right Bumper", 775, 425);
        grphcs.drawString("Register your controller by holding Left Bumper and Right Bumper", 415, 775);
        grphcs.drawString("Registered Players: " + players.size(), 0, 780);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int iz) throws SlickException {
        parseControllers();        
        
        for(Controller controller : available_controllers) {
            boolean isReady = controller.isButtonPressed(4) && controller.isButtonPressed(5);
            if(isReady) {
                String name = JOptionPane.showInputDialog(null, "Please enter a name for " + controller.getName() + ": ", "Username");
                System.out.println("Controller Detected: " + name);
                Player player = new Player(controller);
                controller.setZAxisDeadZone(1); // Since we can't rename a controller, we mark this to signify it's already claimed.
                player.setName(name);
                players.add(player);
            }
        }
        
        for(Player player: players) {
            Controller controller = player.getController();
            boolean isReady = player.isButtonPressed("ST");
            if(isReady) {
                sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
            }
        }
    }
    
    /**
     * Helper Methods
     */
    
    public void parseControllers() {
        available_controllers.clear();
        for (int i = 0; i < Controllers.getControllerCount(); i++) {
            Controller controller = Controllers.getController(i);
            String name = controller.getName();
            float deadzone = controller.getZAxisDeadZone();
            if(name.equals("Controller (Xbox One For Windows)") && deadzone != 1) {
                available_controllers.add(controller);
            }
        }
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
