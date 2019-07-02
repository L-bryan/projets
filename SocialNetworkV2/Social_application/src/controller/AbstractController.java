package controller;

import core.AbstractNetwork;
import core.Information;
import core.Link;
import core.Profil;
import java.util.List;
import view.View;

/**
 * The controller, accepts input and converts it to commands for the model or
 * view
 *
 * @see <a href="https://en.wikipedia.org/wiki/Model-view-controller">
 * Wikipedia</a>
 */
public abstract class AbstractController {

    /**
     * The model to controll. MVC pattern.
     */
    protected final AbstractNetwork model;

    /**
     * The view to controll. MVC pattern.
     */
    protected View view;

    /**
     *
     * Constructs the controller.
     *
     * @param model model to control.
     * @param view console view to control.
     *
     * @throws IllegalArgumentException if the model or the view are null.
     */
    public AbstractController(final AbstractNetwork model, final View view) {
        if (view == null) {
            throw new IllegalArgumentException("Vue non initialisée dans le controlleur");
        }
        if (model == null) {
            throw new IllegalArgumentException("Modèle non initialisé  dans le controlleur");
        }
        this.model = model;
        this.view = view;
    }

    /**
     *
     * Constructs the controller only with the model. The view has to be add
     * later.
     *
     * @param model model to control.
     *
     * @throws IllegalArgumentException if the model or the view are null.
     */
    public AbstractController(final AbstractNetwork model) {
        if (model == null) {
            throw new IllegalArgumentException("Modèle non initialisé  dans le controlleur");
        }
        this.model = model;
    }

    /**
     * Throws an exception if the model or the view are null.
     *
     * @throws IllegalArgumentException if the model or the view are null.
     */
    protected void checkMVC() {
        if (view == null) {
            throw new IllegalArgumentException("Vue non initialisée dans le controlleur");
        }
        if (model == null) {
            throw new IllegalArgumentException("Modèle non initialisé  dans le controlleur");
        }
    }

    /**
     * Initializes the app. Call the model to initialize the app, and the view
     * to display it.
     *
     * @throws IllegalArgumentException if the model or the view are null.
     */
    public void initialize() {
        checkMVC();
        //model.initialize();
        view.initialize();
    }

    /**
     * Quits the app. Call the model and the view to quit the app.
     *
     * @throws IllegalArgumentException if the model or the view are null.
     */
    public void quit() {
        checkMVC();
        //model.quit();
        view.quit();
    }

    /**
     * Builds the link between the model (Observable) and the view (Observer).
     *
     * @throws IllegalArgumentException if the model or the view are null.
     */
    public void addObserver() {
        checkMVC();
        model.addObserver(view);
    }

    /**
     * Sets the view (MVC pattern).
     *
     * @param view the view.
     */
    public void setView(final View view) {
        this.view = view;
    }

    /**
     * Set view Inscription
     */
    public void setInscritpionView() {
        view.setInscritpionView();
    }

    /**
     * Set ConnectionView
     */
    public void setConnectionView() {
        view.setConnexionView();
    }

    /**
     * Set Alone view
     */
    public void setAloneView() {
        view.setAloneView();
    }

    /**
     * Set Match/Friend View
     *
     * @param type choose of math or friend
     */
    public void setMatchFriendsView(final String type) {
        view.setMatchFriendsView(type);
    }

    /**
     * Main view
     */
    public void setHomeView() {
        view.setHomeView();
    }

    /**
     * Details of a match or friend
     *
     * @param type choose match friend
     * @param profil details
     */
    public void setDetailView(final String type, final Profil profil) {
        view.setDetailView(type, profil);
    }

    /**
     * Information help me
     */
    public void help() {
        view.displayHelp();
    }

    /**
     * Show a error message
     *
     * @param error error to print
     */
    public abstract void error(final String error);

    /**
     * Connect a person in the social network
     *
     * @param idNode id of the account
     * @param password passwor of the account
     */
    public abstract void connect(final String idNode, final String password);

    /**
     * Register a person in the social network
     *
     * @param list all information for the rigistration
     */
    public abstract void register(final List<Information> list);

    /**
     * Add a member as a friends
     *
     * @param receiver the member to add like a friend
     */
    public abstract void addFriend(final String receiver);

    /**
     * Accept a match from a member
     *
     * @param link the match to accept
     */
    public abstract void acceptMatch(final Link link);

    /**
     * Decline a match from a member
     *
     * @param link the match to accept
     */
    public abstract void declineMatch(final Link link);
    
    /**
     * Decline a match from a member
     *
     * @param link the match to accept
     */
    public abstract void deleteLink(final Link link);

    /**
     * Create connexion from all member register in the social network
     */
    public abstract void setMatch();

    /**
     * Get only link to show
     *
     * @return
     */
    public abstract Link getLinkIntrestingMatch();

    /**
     * Member connected
     *
     * @return member connected
     */
    public abstract Profil getConnected();

}
