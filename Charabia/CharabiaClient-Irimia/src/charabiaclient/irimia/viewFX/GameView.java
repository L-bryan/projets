package charabiaclient.irimia.viewFX;

import charabiaclient.irimia.client.CharabiaProxy;
import charabiacommon.irimia.CharabiaInterface;
import charabiacommon.irimia.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author g42992
 */
public class GameView extends VBox{
    private final Tiles tiles;
    private final Label label;
    private final TextField word;
    private final Button btn;

    public GameView() {
        this.tiles = new Tiles();
        this.label = new Label("ENTRER UN MOT");
        this.word = new TextField();
        this.btn = new Button("SOUMETTRE");
        
        setEvent();
        setProprietise();
        
        this.getChildren().addAll(tiles,label,word,btn);
    }
    
    private void setEvent(){
        this.word.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event)->{
            char c = event.getCharacter().toUpperCase().charAt(0);
            if(this.tiles.isCharPresent(c)){
                event.consume();
            }
        });
    }
    private void setProprietise() {
        
        this.setSpacing(40);
        this.alignmentProperty().set(Pos.CENTER);
        
        this.tiles.alignmentProperty().set(Pos.CENTER);
        
        this.label.fontProperty().set(new Font("System",20));
        this.word.fontProperty().set(new Font("System",20));
        this.btn.setPadding(new Insets(25,250,25,250));
    }
    
    public Button getSubmit(){
        return this.btn;
    }
    public String getWord(){
        return this.word.getText().toUpperCase();
    }
    public TextInputControl getInput(){
        return this.word;
    }
    
    public void update(Object o) {
        CharabiaProxy game = (CharabiaProxy) o;
        if(game.getState()== GameState.STARTED){
            this.tiles.update(game.getTiles());
        }
    }
}
