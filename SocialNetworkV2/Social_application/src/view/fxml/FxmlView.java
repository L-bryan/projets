package view.fxml;

import controller.AbstractController;
import core.AbstractNetwork;
import core.Profil;
import java.util.Observable;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.View;

/**
 *
 * @author dylanlevymorini
 */
public class FxmlView extends VBox implements View {

    /**
     * Width of the main window. Value fixed
     */
    public static final int MIN_WIDHT = 800;
    /**
     * Height of the main windiw. Value fixed
     */
    public static final int MIN_HEIGHT = 600;
    
    /**
     * Title of the main windows
     */
    public static final String TITLE = "The social Network";
    
    /**
     * Controller
     */
    private final AbstractController controller;
    /**
     * The JavaFX Stage class is the top level JavaFX container. 
     * The primary Stage is constructed by the platform.
     * Additional Stage objects may be constructed by the application.
     */
    private final Stage stage;
    /**
     * Model
     */
    private final AbstractNetwork model;

    /**
     * Menu Bar
     */
    private final MyMenuBar menuBar;
    /**
     * view connection.
     * Allow all users register to login in to the social network
     */
    private final Connexion connect;
    
    /**
     * Register new user in the social network
     */
    private final Inscription inscription;
    /**
     * Principal view
     */
    private final HomeView home;
    /**
     * This view appears when a user don't have any potential match
     */
    private final Alone alone;
    /**
     * View for see all macth/frien
     */
    private final Match_Friends mf;
    /**
     * See all details of yours match/friend
     */
    private final Match_Friends_Detail mfd;

    /**
     * Constructs the main view of the app.
     * @param stage javaFX stage build by the Application class.
     * @param model the model (MVC pattern).
     * @param controller the controller (MVC pattern).
     * @throws IllegalArgumentException if stage, model or controller is null.
     */
    public FxmlView(final Stage stage, final AbstractNetwork model, final AbstractController controller) {
        if (stage == null) {
            throw new IllegalArgumentException("Aucune stage passée en paramètre");
        }
        if (model == null) {
            throw new IllegalArgumentException("Aucun modèle passé en paramètre");
        }
        if (controller == null) {
            throw new IllegalArgumentException("Aucun controlleur passé en paramètre");
        }

        this.stage = stage;
        this.controller = controller;
        this.model = model;

        this.menuBar = new MyMenuBar(controller);
        this.connect = new Connexion(controller);
        this.inscription = new Inscription(controller);
        this.home = new HomeView(controller);
        this.alone = new Alone(controller);
        this.mf = new Match_Friends(controller);
        this.mfd = new Match_Friends_Detail(controller);

    }

    @Override
    public void initialize() {

        if (controller == null) {
            throw new IllegalStateException("Aucun controlleur associé à la vue");
        }
        initializeStage();

        stage.show();
    }

    private void initializeStage() {
        stage.setMinWidth(MIN_WIDHT);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setTitle(TITLE);
        stage.setResizable(false);

        final Scene scene = new Scene(this);

        stage.setScene(scene);

        stage.setOnCloseRequest((WindowEvent close) -> {
            controller.quit();
        });

        getChildren().addAll(menuBar, connect);

    }

    @Override
    public void update(Observable o, Object arg) {
        if ((boolean) arg) {
            setHomeView();
            home.initialize2();
            
        }else{
            connect.showError();
        }
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    @Override
    public void displayError(String message) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error with The Social Network");
        alert.setHeaderText("Il semble y avoir une erreur ! ");
        final Text text = new Text(message);
        text.setWrappingWidth(400);
        alert.getDialogPane().setContent(text);
        alert.show();
    }

    @Override
    public void clearError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayHelp() {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About The Social Network");
        alert.setHeaderText("Informations concernant Social Network");
        final Text text = new Text("Réseau social dans le cadre du cours ATL5j. Développé par Dylan Levy 41867 et Bryan Irimia Moles 42992 ");
        text.setWrappingWidth(400);
        alert.getDialogPane().setContent(text);
        alert.show();
    }

    @Override
    public void setInscritpionView() {
        this.getChildren().clear();
        this.getChildren().addAll(menuBar, inscription);

    }

    @Override
    public void setConnexionView() {
        this.getChildren().clear();
        this.getChildren().addAll(menuBar, connect);
    }

    @Override
    public void setAloneView() {
        this.getChildren().clear();
        this.getChildren().addAll(menuBar, alone);

    }

    @Override
    public void setMatchFriendsView(String type) {
        this.getChildren().clear();
        this.getChildren().addAll(menuBar,mf);
        mf.initialize(type);
    }

    @Override
    public void setHomeView() {
        this.getChildren().clear();
        this.getChildren().addAll(menuBar, home);

    }

    @Override
    public void info(String requestSend) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About The Social Network");
        alert.setHeaderText("Informations concernant Social Network");
        final Text text = new Text(requestSend);
        text.setWrappingWidth(400);
        alert.getDialogPane().setContent(text);
        alert.show();
    }

    @Override
    public void setDetailView(String type, Profil p) {

        if (type.equals("MATCH")) {
            mfd.setTitle("MATCH avec " + p.getStringFullName());
            mfd.init(p);
            getChildren().clear();
            getChildren().addAll(menuBar,mfd);
        } else {
            mfd.setTitle("AMI avec " + p.getStringFullName());
            mfd.init(p);
            getChildren().clear();
            getChildren().addAll(menuBar,mfd);
        }

        //
    }

}
