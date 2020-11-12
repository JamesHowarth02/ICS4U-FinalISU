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
        this.addState(new startScreen());
    }


    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(0).init(gc, this);
        this.addState(new selectScreen());
        this.addState(new levelVarient1());
    }
    
    public static void main(String[] args) throws SlickException {
        GameLauncher game = new GameLauncher("Galaxy Warriors");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(1400, 800, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(120);
        app.start();
    }
}
