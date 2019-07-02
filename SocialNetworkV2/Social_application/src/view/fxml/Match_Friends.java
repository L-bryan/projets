package view.fxml;

import controller.AbstractController;
import core.Link;
import core.Profil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author dylanlevymorini
 */
public class Match_Friends extends Parent{
    
    private AbstractController controller;
    
    @FXML
    private ListView Lview;
    
    @FXML
    private Button back;
    
    @FXML
    private Button show;
    
    @FXML
    private Button delete;
    
    @FXML
    private Label title;
    
    private ObservableList<String> obsFM;

    public Match_Friends(AbstractController controller) {
        this.controller = controller;
        obsFM = FXCollections.observableArrayList();
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/List_MATCHS_FRIENDS.fxml"));
            loader.setController(this);
            AnchorPane pane = (AnchorPane) loader.load();
            getChildren().add(pane);
        } catch (IOException ex) {
            System.err.println("error loader fxml -> List_MATCHS_FRIENDS.fxml");
        }
        
        back.setOnAction((event) -> {
            controller.setHomeView();
        });
        
        show.setOnAction((event) -> {
            //check la personne selectionner
            String name = (String) Lview.getSelectionModel().getSelectedItem();
            if(title.getText().equals("Mes Friends")){
                for(Link l : controller.getConnected().getFreind()){
                    if(l.getNodeReicever() != controller.getConnected()){
                        if( 
                            ((Profil)l.getNodeReicever()).getStringFullName().equals(name)
                        ){
                            controller.setDetailView("FRIENDS",
                                    (Profil)l.getNodeReicever());
                        }
                    }else{
                        if( 
                            ((Profil)l.getNodeSender())
                                    .getStringFullName().equals(name)
                        ){
                            controller.setDetailView("FRIENDS",
                                    (Profil)l.getNodeSender());
                        }
                    }
                }
            }else{
                for(Link l : controller.getConnected().getMatchs()){
                    if(l.getNodeReicever() != controller.getConnected()){
                        if( 
                            ((Profil)l.getNodeReicever())
                                    .getStringFullName().equals(name)
                        ){
                            controller.setDetailView("MATCH",
                                    (Profil)l.getNodeReicever());
                        }
                    }else{
                        if( 
                            ((Profil)l.getNodeSender())
                                    .getStringFullName().equals(name)
                        ){
                            controller.setDetailView("MATCH",
                                    (Profil)l.getNodeSender());
                        }
                    }
                }
            }
        });
    }

    public void initialize(String str) {
        
        obsFM.clear();
        Profil connect = controller.getConnected();
        
        if(str.equals("FRIEND")){
            title.setText("Mes Friends");
            for(Link l : connect.getFreind() ){
                if(l.getNodeReicever() != connect){ 
                    obsFM.add(((Profil) l.getNodeReicever()).getStringFullName());
                }else{
                    obsFM.add(((Profil) l.getNodeSender()).getStringFullName());
                }
            }
            
        }else{
            title.setText("Mes Matchs");
            for(Link l : connect.getMatchs() ){
                if(l.getNodeReicever() != connect){ 
                    obsFM.add(((Profil) l.getNodeReicever()).getStringFullName());
                }else{
                    obsFM.add(((Profil) l.getNodeSender()).getStringFullName());
                }
            }
        }
        if(!obsFM.isEmpty()){
            Lview.setItems(obsFM);
            System.out.println(Lview.getItems().get(0));
        }
    }
    
    
    
    
}
