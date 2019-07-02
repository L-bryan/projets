package controller.fx;

import controller.AbstractController;
import core.AbstractNetwork;
import core.Information;
import core.Link;
import core.LinkType;
import core.Profil;
import java.util.ArrayList;
import java.util.List;

/**
 * FXML CONTROLLER
 *
 * @author dylanlevymorini
 */
public class FXMLController extends AbstractController {

    /**
     * Constructor
     *
     * @param model model
     */
    public FXMLController(final AbstractNetwork model) {
        super(model);
    }

    /**
     * Quit the social network
     */
    @Override
    public void quit() {
        super.quit();
        System.exit(0);
    }

    /**
     * Connect a person in the social network
     *
     * @param idNode id of the account
     * @param password passwor of the account
     */
    @Override
    public void connect(final String idNode, final String password) {
        model.connect(idNode, password);
    }

    /**
     * Register a person in the social network
     *
     * @param list all information for the rigistration
     */
    @Override
    public void register(final List<Information> list) {
        model.createProfil(list.toArray(new Information[list.size()]));
    }

    /**
     * Show a error message
     *
     * @param error error to print
     */
    @Override
    public void error(String error) {
        view.displayError(error);
    }

    /**
     * Accept a match from a member
     *
     * @param link the match to accept
     */
    @Override
    public void acceptMatch(final Link link) {
        if (link.getNodeReicever().equals(model.getConnected())) {
            model.acceptLink(link.getNodeSender(), model.getConnected(), link.getType());
        } else {
            model.acceptLink(model.getConnected(), link.getNodeReicever(), link.getType());
        }

    }

    /**
     * decline a match from a member
     *
     * @param link the match to accept
     */
    @Override
    public void declineMatch(final Link link) {
        if (link.getNodeReicever().equals(model.getConnected())) {
            model.declineLink(link.getNodeSender(), model.getConnected(), link.getType());
        } else {
            model.declineLink(model.getConnected(), link.getNodeReicever(), link.getType());
        }
    }

    /**
     * Create connexion from all member register in the social network
     */
    @Override
    public void setMatch() {
        model.matchSuggestion();
    }

    /**
     * Get only link to show
     *
     * @return link to show
     */
    @Override
    public Link getLinkIntrestingMatch() {
        
        final Profil connected = (Profil) model.getConnected();
       
        Link match = null;
        //getmatchwait retourn tout les match a false (reicever & sender!!!)

        final List<Link> intrestingMatch = new ArrayList();

        if (!connected.getLinks2(LinkType.MATCH, true, connected).isEmpty()) {
            intrestingMatch.addAll(connected.getLinks2(LinkType.MATCH, true, connected));
        }
        if (!connected.getPotentialMatchs().isEmpty()) {
            intrestingMatch.addAll(connected.getPotentialMatchs());
        }
        if (!intrestingMatch.isEmpty()) {
            match = intrestingMatch.get(0);
        }
        return match;
        //return null;
    }

    /**
     * Add a member as a friends
     *
     * @param receiver the member to add like a friend
     */
    @Override
    public void addFriend(final String receiver) {
        final Profil connected = (Profil) model.getConnected();
        final Profil sender = (Profil) model.getMap().get(Integer.parseInt(receiver));
        
        // 
        Link link = connected.getLinks2(LinkType.FRIENDS, false, sender).get(0);
        if(link == null){
            try {
                model.addLink(connected, sender, LinkType.FRIENDS);
                view.info("Demande envoyé");
            } catch (IllegalArgumentException e) {
                this.error(e.getMessage());
            }
        }else{
            try {
                model.acceptLink(sender, connected, LinkType.FRIENDS);
                view.info("Demande confirmer");
            } catch (IllegalArgumentException e) {
                this.error(e.getMessage());
            }
        
        }
        
        

    }

    /**
     * Member connected
     *
     * @return member connected
     */
    @Override
    public Profil getConnected() {
        return (Profil) model.getConnected();
    }

    @Override
    public void deleteLink(Link link) {
        model.deleteLink(link.getNodeSender(), link.getNodeReicever());
    }

}
