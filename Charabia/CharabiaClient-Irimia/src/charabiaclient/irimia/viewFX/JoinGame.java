package charabiaclient.irimia.viewFX;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author g42992
 */
public class JoinGame extends VBox{
    
    private final Label labelForName;
    private final TextField name;
    
    private final HBox ipLayout;
    private final HBox portLayout;
    
    private final TextField ip;
    private final TextField port;
    
    private final Label labelForColor;
    private final ColorPicker color;
    private final HBox colorLayout;
    private final Button play;
    
    public JoinGame(){
        this.labelForName = new Label("ENTRER VOTRE NOM");
        this.name = new TextField();
        
        ipLayout = new HBox(10);
        portLayout = new HBox(10);
        ip = new TextField("127.0.0.1");
        port = new TextField("12345");
        
        ipLayout.getChildren().addAll(new Label("adresse IP du serveur"),ip);
        portLayout.getChildren().addAll(new Label("port du serveur"),port);
        
        this.labelForColor = new Label("choisi ta couleur");
        this.color = new ColorPicker(Color.ROYALBLUE);
        this.play = new Button("JOUER");
        
        this.colorLayout = new HBox(25);
        this.colorLayout.getChildren().addAll(labelForColor,color);
        setProprietise();
        this.getChildren().addAll(
                labelForName,name,
                ipLayout,portLayout,
                colorLayout,
                play
        );
    }
    
    public String getIp(){
        return ip.getText();
    }
    
    public String getPort(){
        return port.getText();
    }
    
    public String getTextfield(){
        return this.name.getText();
    }
    
    public Button getButton(){
        return this.play;
    }
    
    public String getColor(){
        return this.color.getValue().toString();
    }

    private void setProprietise() {
        this.spacingProperty().set(35);
        this.setAlignment(Pos.CENTER);
        
        name.setMinSize(372, 25);
        name.setFont(Font.font("System", 22));
        
        ipLayout.setAlignment(Pos.CENTER);
        portLayout.setAlignment(Pos.CENTER);
        
        play.setMinSize(500, 100);
        play.setFont(Font.font("System", 54));
        
        labelForName.setFont(Font.font("System", 51));
        
        labelForColor.setFont(Font.font("System", 20));
        colorLayout.setAlignment(Pos.CENTER);
    }
}
