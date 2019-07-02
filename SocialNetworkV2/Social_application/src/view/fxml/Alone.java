package view.fxml;

import controller.AbstractController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author dylanlevymorini
 */
public class Alone extends Parent {

    /**
     * Controller
     */
    final private AbstractController controller;
    
    /**
     * Add friend button
     */
    @FXML
    private Button addFriend;
    /**
     * Match button. See match
     */
    @FXML
    private Button match; 
    /**
     * Add friend button. See friends
     */
    @FXML
    private Button friends;
    /**
     * input add friend
     */
    @FXML
    private TextField textaddfriend;

    /**
     * Constructor
     * @param controller 
     */
    public Alone(final AbstractController controller) {
        this.controller = controller;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/alone.fxml"));
            loader.setController(this);
            final AnchorPane pane = (AnchorPane) loader.load();
            getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(FxmlView.class.getName()).log(Level.SEVERE, "loader.load() failed", ex);
        }
        
        match.setOnAction((event) -> {
            controller.setMatchFriendsView("MATCH");
        });
        friends.setOnAction((event) -> {
            controller.setMatchFriendsView("FRIEND");
        });
        
        addFriend.setOnAction((event) -> {
            controller.addFriend(textaddfriend.getText());
        });
        
        
    }

}
