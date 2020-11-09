import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


/**
 *
 * @author jame1850
 */
public class GameLauncher extends StateBasedGame {

    public GameLauncher(String title) {
        super(title);
    }


    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new startScreen());
        this.addState(new testScreen());
    }
    
    public static void main(String[] args) throws SlickException {
        GameLauncher game = new GameLauncher("");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(360);
        app.start();
    }
}
