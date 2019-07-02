package charabiaclient.irimia.viewFX;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author g42992
 */
public class Triche extends VBox{
    private CheckBox modeTriche;
    private List<String> antiTriche;
    private ScrollPane words;
    private Label players;
    private VBox line;
    private HBox modeTricheLayout;
    
    public Triche(){
        modeTricheLayout = new HBox();
        modeTriche = new CheckBox();
        line = new VBox();
        words = new ScrollPane(line);
        antiTriche = new ArrayList<>();
        players = new Label();
        modeTricheLayout.getChildren()
                .addAll(modeTriche,new Label("mode triche"));
        getChildren().addAll(modeTricheLayout,players,words);
    }
    
    public CheckBox getCheckBox(){
        return modeTriche;
    }
    public void addTricher(List<String> player){
        antiTriche.clear();
        for(String p : player){
            antiTriche.add(p);
        }
        refresh();
    }
    public void removeTricher(String player){
        antiTriche.remove(player);
        refresh();
    }
    public void refresh(){
        players.setText("");
        for(String p : antiTriche){
            players.setText(players.getText() + p + " ");
        }
    }
    public void addWord(String word){
        if(modeTriche.isSelected()){
            line.getChildren().add(new Label(word));
        }
    }
}
