package network;

import controller.AbstractController;
import controller.fx.FXMLController;
import core.Network;
import core.AbstractNetwork;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import view.View;
import view.fxml.FxmlView;

/**
 *
 * @author dylanlevymorini
 */
public class NetworkFXMLVersion extends Application{
    
    /**
     * Main function
     * @param args 
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * Start method
     * @param stage
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    @Override
    public void start(final Stage stage) throws IOException, FileNotFoundException, ClassNotFoundException {
        final AbstractNetwork model = new Network();
        final AbstractController controller = new FXMLController(model);
        final View view = new FxmlView(stage, model,controller);
        controller.setView(view);
        controller.addObserver();
        controller.initialize();
        
    }
    
}
