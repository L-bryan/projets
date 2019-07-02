package charabiaclient.irimia;

import charabiaclient.irimia.viewFX.CharabiaGame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author g42992
 */
public class ApplicationFx extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        CharabiaGame client = new CharabiaGame();
        client.show();
    }
}
