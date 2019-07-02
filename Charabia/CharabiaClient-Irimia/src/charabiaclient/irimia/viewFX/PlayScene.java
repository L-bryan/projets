package charabiaclient.irimia.viewFX;

import charabiaclient.irimia.client.CharabiaProxy;
import charabiacommon.irimia.Player;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author g42992
 */
public class PlayScene extends Scene{
    
    private final PlayGame play;
    private CharabiaProxy game;
    private Player player;
            
    public PlayScene(Parent root, double width, double height) {
        super(root, width, height);
        play = (PlayGame) root;
    }
    
    public void setGame(CharabiaProxy game){
        this.game = game;
    }
    
    public void setPlayer(String player){
 
        for(Player p : game.getPlayers()){
            if(p.getName().equals(player)){
                this.player = p;
            }
        }
    }
    
    public void setEvent(){
        play.getSubmit().addEventHandler(ActionEvent.ACTION, (event)->{
            game.play(player.getName(), play.getWord());
        });
    }
    
    public PlayGame getPlayGame(){
        return play;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public void update(Object o){
        play.update(o);
    }
}
