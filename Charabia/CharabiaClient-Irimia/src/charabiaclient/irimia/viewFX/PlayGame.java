package charabiaclient.irimia.viewFX;

import charabiacommon.irimia.Player;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author g42992
 */
public class PlayGame extends GridPane{
    
    private final GameView gameView;
    
    private final Historical historical;
    private Triche triche;
            
    private final AlertBestWord abw;
    private final AlertGameWiner agw;
    private final AlertRoundWiner arw;
    
    public PlayGame(){
        gameView = new GameView();
        historical = new Historical();
        triche = new Triche();
        this.abw = new AlertBestWord(Alert.AlertType.INFORMATION);
        this.arw = new AlertRoundWiner(Alert.AlertType.INFORMATION);
        this.agw = new AlertGameWiner(Alert.AlertType.INFORMATION);
        
        this.add(gameView, 0, 0);
        this.add(historical, 1, 0);
        this.add(triche,0,1);
        this.setAlignment(Pos.CENTER);
    }
    
    public CheckBox getCheckBox(){
        return triche.getCheckBox();
    }
    public void setWord(String word){
        triche.addWord(word);
    }
    public void addTricher(List<String> player){
        triche.addTricher(player);
    }
    public void removeTricher(String player){
        triche.removeTricher(player);
    }
    public Button getSubmit(){
        return gameView.getSubmit();
    }
    public String getWord(){
        return gameView.getWord();
    }
    public TextInputControl getInput(){
        return gameView.getInput();
    }
    public Text getPlayer1(){
        return historical.getPlayer1();
    }
    public Text getPlayer2(){
        return historical.getPlayer2();
    }
    public void addWord(Player p, String s, boolean isPlayer, boolean isWiner){
        historical.addWord(p,s,isPlayer,isWiner);
    }
    
    public void j1ToggleHasPlay(Boolean b){
        historical.j1ToggleHasPlay(b);
    }
    
    public void j2ToggleHasPlay(Boolean b){
        historical.j2ToggleHasPlay(b);
    }
    
    
    public void setAlertBestWord(List<String> words){
        this.abw.setContent(words);
    }
    
    public void setAlertRoundWiner(List<Player> players){
        this.arw.setContent(players);
    }
    
    public void setAlertGameWiner(List<Player> players){
        this.agw.setContent(players);
    }
    
    public void showAlertBestWord(){
        this.abw.show();
    }
    
    public void showAlertRoundWiner(){
        this.arw.show();
    }
    
    public void showAlertGameWiner(){
        this.agw.show();
    }
    
    public void update(Object o) {
        gameView.update(o);
        historical.update(o);
    }

}
