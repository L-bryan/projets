package charabiaclient.irimia.viewFX;

import charabiacommon.irimia.Player;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 *
 * @author g42992
 */
public class AlertRoundWiner extends Alert{
    
    public AlertRoundWiner(AlertType at){
        super(at);
        this.setHeaderText("le gagnant du round est");
        this.initModality(Modality.NONE);
    }
    public void setContent(List<Player> players){
       
        StringBuilder str = new StringBuilder();
        for(Player p : players){
            str.append(p.getName())
                    .append(" avec le mot ")
                    .append(p.getProposition())
                    .append("\n");
        }
        this.setContentText(str.toString());

    }
}
