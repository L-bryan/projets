package charabiaclient.irimia.viewFX;

import charabiacommon.irimia.Player;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 *
 * @author g42992
 */
public class AlertGameWiner extends Alert{
    
    public AlertGameWiner(AlertType at){
        super(at);
        this.setHeaderText("le gagnant du jeu est");
        this.initModality(Modality.NONE);
        this.setOnCloseRequest((event) -> {
           //System.exit(0);
        });
    }
    public void setContent(List<Player> players){
        
        StringBuilder str = new StringBuilder();
        for(Player p : players){
            str.append(p.getName()).append("\n");
        }
        this.setContentText(str.toString());
        
    }
}
