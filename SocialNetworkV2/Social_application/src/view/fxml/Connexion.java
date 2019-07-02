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
public class Connexion extends Parent {

    /**
     * Controller
     */
    private final AbstractController controller;

    /**
     * Connexion button
     */
    @FXML
    private Button connexion;
    /**
     * Inscription button
     */
    @FXML
    private Button inscription;
    /**
     * id of profil
     */
    @FXML
    private TextField id;
    /**
     * Password of a profil
     */
    @FXML
    private TextField password;
    

    /**
     * Constructor
     * @param controller 
     */
    public Connexion(final AbstractController controller) {
        this.controller=controller;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/connexion.fxml"));
            loader.setController(this);
            final AnchorPane pane = (AnchorPane) loader.load();
            getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(FxmlView.class.getName()).log(Level.SEVERE, "loader.load() failed", ex);
        }

        connexion.setOnAction((event) -> {
            controller.connect(id.getText(),password.getText());
        });
        
        inscription.setOnAction((event) -> {
            controller.setInscritpionView();
        });
    }
    
    /**
     * show a error message at screen
     */
    public void showError(){
        controller.error("User ID ou mots de passe incorrecte");
    }
}
