package charabiaclient.irimia.viewFX;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 *
 * @author g42992
 */
public class JoinScene extends Scene{
    
    private final JoinGame join;
    
    public JoinScene(Parent root, double width, double height) {
        super(root, width, height);
        this.join = (JoinGame) root;
    }
    
    public String register(){
        return join.getTextfield();
    }
    
    public String playerColor(){
        return join.getColor();
    }
    
    public Button getButton(){
        return join.getButton();
    }
    
    public String getIp(){
        return join.getIp();
    }
    
    public String getPort(){
        return join.getPort();
    }
}
