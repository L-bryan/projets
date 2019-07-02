package charabiaclient.irimia.viewFX;

import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 *
 * @author g42992
 */
public class AlertBestWord extends Alert {
    public AlertBestWord(AlertType at){
        super(at);
        this.setHeaderText("Meilleur Mot");
        this.initModality(Modality.NONE);
    }
    public void setContent(List<String> words){
        
        StringBuilder str = new StringBuilder();
        for(String s : words){
            str.append(s).append("\n");
        }
        this.setContentText(str.toString());
        
    }
}
