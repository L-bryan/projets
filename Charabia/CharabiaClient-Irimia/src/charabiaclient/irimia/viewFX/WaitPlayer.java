package charabiaclient.irimia.viewFX;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

/**
 *
 * @author g42992
 */
public class WaitPlayer extends VBox{
    
    private final Label label;
    private final ProgressBar bar;
    
    public WaitPlayer(){
        this.label = new Label("ATTENTE D'UN AUTRE JOUEUR");
        this.bar = new ProgressBar(0.5);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(label,bar);
    }
    
}
