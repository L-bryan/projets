package charabiaclient.irimia.viewFX;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author g42992
 */
public final class Restart extends VBox{

    private final Label winers;
    private final Label title;
    private final Button restart;
    private final Button exit;
    
    public Restart(){
        
        winers = new Label("les Gagnants sont ...");
        title = new Label("Voulez vous rejouez");
        restart = new Button("OUI");
        exit = new Button("NON");
        
        this.getChildren().addAll(winers,title,restart,exit);
        
        setExitEvent();
        setProprietise();
    }
    
    public void setRestartEvent(EventHandler e){
        restart.setOnAction(e);
    }
    
    public void setExitEvent(){
        exit.setOnAction((e)->{
            System.exit(0);
        });
    }
    
    public void setWiners(String s){
        winers.setText(s);
    }

    private void setProprietise() {
        winers.setFont(Font.font(20));
        this.setAlignment(Pos.CENTER);
    }
}
