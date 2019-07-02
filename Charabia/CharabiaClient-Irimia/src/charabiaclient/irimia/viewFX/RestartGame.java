package charabiaclient.irimia.viewFX;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author g42992
 */
public class RestartGame extends Scene{
    
    private final Restart restart;
    
    public RestartGame(Parent root, double width, double height) {
        super(root, width, height);
        restart = (Restart)root;
    }
    
    public Restart getRestart(){
        return restart;
    }
}
