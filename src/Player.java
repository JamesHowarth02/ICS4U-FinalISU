import org.lwjgl.input.Controller;

/**
 *
 * @author Chatt
 */


/**
 * 0 = A
 * 1 = B
 * 2 = X
 * 3 = Y
 * 4 = LB (left bumper)
 * 5 = RB (right bumper)
 * 6 = BK (back)
 * 7 = ST (start)
 * 8 = LJ (left joy)
 * 9 = RJ (right joy)
 */

public class Player extends Entity {
    private final Controller controller;
    private final String identifier;
    
    public Player(Controller controller) {
        this.controller = controller;
        this.identifier = controller.getName();
    }
    
    public Controller getController() {
        return this.controller;
    }
    
    public boolean isButtonPressed(String map) { // Maping the buttons. This was painful to do-- apparently no one on the internet has mapped an Xbox One Controller??
        if("A".equals(map)) return controller.isButtonPressed(0);
        if("B".equals(map)) return controller.isButtonPressed(1);
        if("X".equals(map)) return controller.isButtonPressed(2);
        if("Y".equals(map)) return controller.isButtonPressed(3);
        if("LB".equals(map)) return controller.isButtonPressed(4);
        if("RB".equals(map)) return controller.isButtonPressed(5);
        if("BK".equals(map)) return controller.isButtonPressed(6);
        if("ST".equals(map)) return controller.isButtonPressed(7);
        if("LJ".equals(map)) return controller.isButtonPressed(8);
        if("RJ".equals(map)) return controller.isButtonPressed(9);
        return false;
    }  
}
