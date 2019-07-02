package charabiaclient.irimia.viewFX;

import charabiacommon.irimia.Tile;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.util.Duration;

/**
 *
 * @author g42992
 */
public class Tiles extends HBox {
    
    private final List<StackPane> tiles;
    
    //- CONSTANT -\\
    private final StrokeLineCap slc = StrokeLineCap.ROUND;
    private final StrokeLineJoin slj = StrokeLineJoin.ROUND;
    private final int RECTANGLE_WIDTH = 55;
    private final int RECTANGLE_HIGHT = 65;
    private final String RECTANGLE_FILL = "#ffdbb2e5";
    private final String RECTANGLE_STOKE = "#d3a9a9"; 
    
    private final int TILE_SCALE = 2;
    private final String TILE_FILL = "#8d5c5c";
    
    
    public Tiles(){
        tiles = new ArrayList<>();
        for(int i = 0; i<10 ; i++){
            tiles.add(
                new StackPane(
                    new Rectangle(RECTANGLE_WIDTH,RECTANGLE_HIGHT),
                    new Label()
                )
            );
        }
        
        setTilesProprietise();
        
        for(StackPane sp : tiles){
            this.getChildren().add(sp);
        }
    }
    
    private void setTilesProprietise(){
        for(StackPane sp : tiles){
            
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
            
        }
        this.setSpacing(20);
    }
    boolean isCharPresent(char c){
        boolean b = true;
        for(StackPane sp : tiles){
            Label l = (Label) sp.getChildren().get(1);
            b = b && c != l.getText().charAt(0);
        }
        return b;
    }
    private void animation(Node node){
        TranslateTransition anime = new TranslateTransition();
        
        anime.setDuration(Duration.seconds(0.5));
        anime.setFromY(0);
        anime.setToY(-50);
        anime.setCycleCount(2);
        anime.setAutoReverse(true);
        anime.setNode(node);
        anime.play();
    }
    
    void update(List<Tile> tiles){
        if(tiles != null){
            int i = 0;
            Label l;
            for(StackPane sp : this.tiles){
               l = (Label) sp.getChildren().get(1);
               l.setText(tiles.get(i).getLetter()
                        +"\n   "+tiles.get(i).getValue());
               i++;
            }
            for(Node n : this.getChildren()){
                animation(n);
            }
        }
    }
    
}
