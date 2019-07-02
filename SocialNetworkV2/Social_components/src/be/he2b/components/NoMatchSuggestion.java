package be.he2b.components;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * Class NoMatchSuggestion
 * @author Bryan & Dylan
 */
public class NoMatchSuggestion extends VBox {

    public NoMatchSuggestion() {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/noMatchSuggestionConponent.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CarouselControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
