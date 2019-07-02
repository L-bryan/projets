package view;

import core.Profil;
import java.util.Observer;

/**
 * A view can be any output representation of information, such as a chart or a
 * diagram.
 * @see
 * <a href="https://en.wikipedia.org/wiki/Model-view-controller"> Wikipedia</a>
 */
public interface View extends Observer {

    /**
     * Called to initialize a the view afterthe model initialisation.
     */
    void initialize();

    /**
     * Called to quit the view after the model's end.
     */
    void quit();


    /**
     * Display the error message.
     *
     * @param message the error message.
     */
    void displayError(String message);

    /**
     * Clear the error message display on the view. Useless in console mode.
     */
    void clearError();

    /**
     * Display a help message.
     *
     */
    void displayHelp();
    
    /**
    choose a specific view. Inscription
    */
    void setInscritpionView();
    
    /**
    choose a specific view. Connexion
     */
    void setConnexionView();
    /**
     * Set alone view when we don't have any potential match to show
     */
    public void setAloneView();
    /**
     * Set Math or friend viex to show
     * @param type choose of match or friend
     */
    public void setMatchFriendsView(final String type);
    /**
     * set main view
     */
    public void setHomeView();
    /**
     * Details of a members
     * @param type choose match or friend
     * @param profil information
     */
    public void setDetailView(final String type, final Profil profil);

    /**
     * Show information confirmation
     * @param demandeEnvoye message to show
     */
    public void info(String demandeEnvoye);

}
