import java.awt.AlphaComposite;
import java.util.ArrayList;
import java.util.HashSet;
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

public class selectScreen extends BasicGameState {
    
    int playerIndex = 0;
    int controllerPosition = 0;
    
    ArrayList<Player> players = new ArrayList();
    Player currentPlayer;
    
    Sound select1, select2, select3, takeoff, backgroundSound;
    
    Image background, spaceShip1, spaceShip2, spaceShip3, selectPrompt;
    Image high_velocity, moderate_velocity, low_velocity, high_damage;
    Image moderate_damage, low_damage, high_health, moderate_health, low_health;
    
    Image name1, name2, name3;
    
    @Override
    public int getID() {
        return 1;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("assets/images/select_background.png");
        
        spaceShip1 = new Image("assets/images/spaceship_varient1.png");
        spaceShip2 = new Image("assets/images/spaceship_varient2.png");
        spaceShip3 = new Image("assets/images/spaceship_varient3.png");
        
        selectPrompt = new Image("assets/images/select_prompt.png");
        
        select1 = new Sound("assets/sounds/select_1.wav");
        select2 = new Sound("assets/sounds/select_2.wav");
        select3 = new Sound("assets/sounds/select_3.wav");
        takeoff = new Sound("assets/sounds/transition.wav");
        backgroundSound = new Sound("assets/sounds/select_background.wav");
        
        high_velocity = new Image("assets/images/high_velocity.png");
        moderate_velocity = new Image("assets/images/moderate_velocity.png");
        low_velocity = new Image("assets/images/low_velocity.png");
        
        high_health = new Image("assets/images/high_health.png");
        moderate_health = new Image("assets/images/moderate_health.png");
        low_health = new Image("assets/images/low_health.png");
        
        high_damage = new Image("assets/images/high_damage.png");
        moderate_damage = new Image("assets/images/moderate_damage.png");
        low_damage = new Image("assets/images/low_damage.png");
        
        name1 = new Image("assets/images/the_destroyer.png");
        name2 = new Image("assets/images/the_cruiser.png");
        name3 = new Image("assets/images/the_battle_ship.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        int renderListOrder = 10;
        background.draw(0, 0);
        spaceShip1.draw(350, 400, 150, 150);
        spaceShip2.draw(600, 375, 200, 200);
        spaceShip3.draw(900, 400, 150, 150);
        selectPrompt.draw(350, 200);
        
        grphcs.drawString("Active Fleet: ", 0, 0);
        for(Player player: players) {
            renderListOrder = renderListOrder + 15;
            grphcs.drawString(player.getName(), 0, renderListOrder);
        }
        players = Player.getPlayers();
        currentPlayer = players.get(playerIndex);
        grphcs.drawString("Registered Players: " + players.size(), 0, 780);
        grphcs.drawString(currentPlayer.getName() + " is selecting their spaceship.", 525, 780);
        
        if(controllerPosition == -1) {
            grphcs.drawLine(325, 600, 525, 600);
            high_velocity.draw(35, 400, 250, 25);
            moderate_health.draw(35, 475, 270, 25);
            low_damage.draw(35, 550, 250, 25);
            name1.draw(515, 700);
        }else if(controllerPosition == 0) {
            grphcs.drawLine(600, 600, 800, 600);
            moderate_velocity.draw(25, 400, 300, 25);
            moderate_health.draw(25, 475, 300, 25);
            moderate_damage.draw(25, 550, 300, 25);
            name2.draw(550, 700);
        }else if(controllerPosition == 1) {
            grphcs.drawLine(875, 600, 1075, 600);
            low_velocity.draw(35, 400, 250, 25);
            high_health.draw(35, 475, 270, 25);
            low_damage.draw(35, 550, 250, 25);
            name3.draw(515, 700);
        }
        
        if(backgroundSound.playing() == false) {
           // backgroundSound.play(1, 1);
        }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int iz) throws SlickException {
        Controller currentController = currentPlayer.getController();
        SpaceShip selectedShip;
        int position = (int) currentController.getXAxisValue();
        boolean isConfirmed = currentPlayer.isButtonPressed("A");
        if(position != controllerPosition) {
            controllerPosition = position;
            switch (position) {
                case -1:
                    select1.play(1, 2);
                    selectedShip = new Destroyer();
                    currentPlayer.setSpaceShip(selectedShip);
                    break;
                case 0:
                    select2.play(1, 2);
                    selectedShip = new Cruiser();
                    currentPlayer.setSpaceShip(selectedShip);
                    break;
                default:
                    select3.play(1, 2);
                    selectedShip = new Battleship();
                   currentPlayer.setSpaceShip(selectedShip);
                    break;
            }
        }
        if(isConfirmed) {
            takeoff.play(1, 2);
            if(playerIndex == players.size()-1) {
                backgroundSound.stop();
                sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
            }else{
                playerIndex = playerIndex + 1;
            }
        }
    }
    
}
