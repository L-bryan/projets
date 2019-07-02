package core;

import java.util.Map;

/**
 *
 * @author Lyan
 */
interface NetworkIterface {
    
    /**
     * create a invitation link between the node n and the node m
     * @param n node how add
     * @param m node add
     * @param l the type of the add link
     */
    public void addLink(Node n, Node m, LinkType l);
    
    /**
     * dicline a invitation link between the node n and the node m
     * @param n node how dicline
     * @param m node diclined
     */
    public void diclineLink(Node n, Node m);
    
    /**
     * Accept a invitation link between the node n and the node m
     * @param n node how accept
     * @param m node accepted
     * @param l the type of the accepted link
     */
    public void acceptLink(Node n, Node m);
    
    /**
     * delete a link between the node n and the node m
     * @param n node how delete
     * @param m node deleted
     * @param l the type of the delete link
     */
    public void deleteLink(Node n, Node m);
    
    
    /**
     * Create and add a Profil(node) in the network with basic information
     * @param info varargs of Information
     */
    public void createProfil(Information... info);
    
    /**
     * add somes informations to a Profil (node) 
     * @param n the node to add information
     * @param info varargs of Information
     */
    public void addProfilInfo(Profil n, Information... info);
    
    /**
     * search in all network the other Profile for probablie make a link 
     * @param n the node to find suggestion
     */
    public void matchSuggestion();
    
    /**
     * Get all people register on the socialNetwork
     */
    public Map getMap();
}
