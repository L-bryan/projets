package charabiaclient.irimia.viewFX;

import charabiacommon.irimia.Player;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author g42992
 */
public class Historical extends VBox{
    
    private final Label title;
    private final ScrollPane historical;
    private final VBox node;
    private final Players players;
    
    //- CONSTANT -\\
    private final StrokeLineCap slc = StrokeLineCap.ROUND;
    private final StrokeLineJoin slj = StrokeLineJoin.ROUND;
    private final int RECTANGLE_WIDTH = 55;
    private final int RECTANGLE_HIGHT = 65;
    private final String RECTANGLE_FILL = "#ffdbb2e5";
    private final String RECTANGLE_STOKE = "#d3a9a9"; 
    
    private final int TILE_SCALE = 2;
    private final String TILE_FILL = "#8d5c5c";
    
    private final Image image;
    
    public Historical(){// \u1F451
        
        image = new Image("/win.png");//("https://images.emojiterra.com/google/android-oreo/512px/1f451.png");
        title = new Label("Historique");
        node = new VBox(10);
        historical = new ScrollPane(node);
        players = new Players();
        this.getChildren().addAll(title,historical,players);
        setProprietise();
    }
    
    public void addWord(Player p, String s, boolean isPlayer, boolean isWiner){
        List<StackPane> l = new ArrayList<>();
        for(int i = 0; i<s.length(); i++){
            l.add(new StackPane(
                    new Rectangle(
                            RECTANGLE_WIDTH,RECTANGLE_HIGHT)
                    ,new Label(""+s.charAt(i)))
            );
        }
        HBox word = new HBox(5);
        if(isPlayer){
            word.getChildren().addAll(l);
            if(isWiner){
                ImageView iv = new ImageView(image);
                iv.setFitWidth(RECTANGLE_WIDTH);
                iv.setFitHeight(RECTANGLE_HIGHT);
                word.getChildren().add(iv);
            }
            Label playerName = new Label(p.getName());
            playerName.fontProperty().set(new Font("System",20));
            Rectangle frame = new Rectangle(
                            RECTANGLE_WIDTH/2*p.getName().length(),
                            RECTANGLE_HIGHT);
            frame.setFill(Color.TRANSPARENT);
            StackPane player = new StackPane(
                    frame,
                    playerName
            );
            word.getChildren().add(player);
        }else{
            word.getChildren().addAll(l);
        }
        formateStack(l,isPlayer);
        word.setAlignment(Pos.CENTER_LEFT);
        node.getChildren().add(word);
    }

    private void setProprietise() {
        historical.setPrefViewportHeight(400);
        historical.setPrefViewportWidth(700);
        historical.setPadding(new Insets(5));
        this.setPadding(new Insets(5));
        title.fontProperty().set(new Font("System",20));
    }

    private void formateStack(List<StackPane> stacks, boolean isPlayer) {
        for(StackPane sp : stacks){
            
            //rectangle
            Rectangle r = (Rectangle) sp.getChildren().get(0);
            r.setFill(Paint.valueOf(RECTANGLE_FILL));
            r.setStroke(Paint.valueOf(RECTANGLE_STOKE));
            r.setStrokeLineCap(slc);
            r.setStrokeLineJoin(slj);
            
            //label
            Label l = (Label) sp.getChildren().get(1);
            l.setScaleX(TILE_SCALE);
            l.setScaleY(TILE_SCALE);
            l.setTextFill(Paint.valueOf(TILE_FILL));
            
            if(isPlayer){
                r.setFill(Paint.valueOf("#83c68c"));
            }else{
                r.setFill(Paint.valueOf("#209ddb"));
            }
            
        }
    }
    
    public Text getPlayer1(){
        return players.getPlayer1();
    }
    public Text getPlayer2(){
        return players.getPlayer2();
    }
    
    public void j1ToggleHasPlay(Boolean b){
        players.j1ToggleHasPlay(b);
    }
    
    public void j2ToggleHasPlay(Boolean b){
         players.j2ToggleHasPlay(b);
    }
    
    public void update(Object o){
        players.update(o);
    }
}
