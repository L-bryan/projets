package core;

import java.util.Map;
import java.util.Observable;

/**
 * Abstract class Network 
 * @author Bryan & Dylan
 */
public abstract class AbstractNetwork extends Observable {
    
    /**
     * create a invitation link between the node n and the node m
     * @param n node how add
     * @param m node add
     * @param l the type of the add link
     */
    public abstract void addLink(Node n, Node m, LinkType l);
    
    /**
     * dicline a invitation link between the node n and the node m
     * @param n node how dicline
     * @param m node diclined
     * @param l the type of the diclined link
     */
    public abstract void declineLink(Node n, Node m,LinkType l);
    
    /**
     * Accept a invitation link between the node n and the node m
     * @param n node how accept
     * @param m node accepted
     * @param l the type of the accepted link
     */
    public abstract void acceptLink(Node n, Node m,LinkType l);
    
    /**
     * delete a link between the node n and the node m
     * @param n node how delete
     * @param m node deleted
     */
    public abstract void deleteLink(Node n, Node m);
    
    
    /**
     * Create and add a Profil(node) in the network with basic information
     * @param info varargs of Information
     */
    public abstract void createProfil(Information... info);
    
    /**
     * add somes informations to a Profil (node) 
     * @param n the node to add information
     * @param info varargs of Information
     */
    public abstract void addProfilInfo(Profil n, Information... info);
    
    /**
     * search in all network the other Profile for probablie make a link 
     */
    public abstract void matchSuggestion();
    
    /**
     * Get all people register on the socialNetwork
     * @return a Map of all Node in the network
     */
    public abstract Map getMap();
    
    /**
     * Connect a user in the plateform
     * @param node The user to connect
     * @param password password of the user
     */
    public abstract void connect(String node, String password);
    
    /**
     * return the user who is connected on the site
     * @return the user currentely connected
     */
    public abstract Node getConnected();
}
