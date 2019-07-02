package charabiaclient.irimia.viewFX;

import charabiaclient.irimia.client.CharabiaProxy;
import charabiacommon.irimia.CharabiaInterface;
import charabiacommon.irimia.Player;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author g42992
 */
public class Players extends VBox{
    
    private final HBox player1Info;
    private final Text player1;
    private final Label player1HasPlay;
     
    private final HBox player2Info;
    private final Text player2;
    private final Label player2HasPlay;
    
    public Players(){
        
        this.player1Info = new HBox(5);
        this.player1 = new Text("JOUEUR 1");
        this.player1HasPlay = new Label("");

        this.player2Info = new HBox(5);
        this.player2 = new Text("JOUEUR 2");
        this.player2HasPlay = new Label("");
        
        this.player1Info.getChildren().setAll(player1,player1HasPlay);
        this.player2Info.getChildren().setAll(player2,player2HasPlay);
        this.getChildren().setAll(player1Info,player2Info);
        
        setProprietise();
    }
    
    public Text getPlayer1(){
        return player1;
    }
    public Text getPlayer2(){
        return player2;
    }
    
    public void j1ToggleHasPlay(Boolean b){
        if(b){
            this.player1HasPlay.setText("\u2714");
        }else{
            this.player1HasPlay.setText("");
        }
    }
    
    public void j2ToggleHasPlay(Boolean b){
        if(b){
            this.player2HasPlay.setText("\u2714");
        }else{
            this.player2HasPlay.setText("");
        }
    }
    
    private void setProprietise() {
        this.setSpacing(10);
        //this.alignmentProperty().set(Pos.CENTER);
        
        this.player1.setFont(new Font("Comics sans MS",20));
        this.player2.setFont(new Font("Comics sans MS",20));
        this.player1HasPlay.setFont(new Font("Comics sans MS",20));
        this.player2HasPlay.setFont(new Font("Comics sans MS",20));
        this.player1HasPlay.setTextFill(Paint.valueOf("#82E285"));
        this.player2HasPlay.setTextFill(Paint.valueOf("#82E285"));
    }
    
    public void update(Object o) {
        CharabiaProxy game = (CharabiaProxy) o;
        List<Player> playerss = game.getPlayers();
            player1.setText("   "+playerss.get(1).getName()
                    +" score "+playerss.get(1).getScore());
            player2.setText("   "+playerss.get(0).getName()
                    +" score "+playerss.get(0).getScore());
    }
}


