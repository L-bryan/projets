package core;

import java.io.Serializable;

/**
 * Class Link
 * @author Bryan & Dylan
 */
public class Link implements Serializable { //TODO javadoc

    private static final long serialVersionUID = 354054054054L;
    
    /**
     * 
     */
    private LinkType type;
    
    /**
     * 
     */
    private int weight;
    
    /**
     * 
     */
    private final Node nodeSender; //emetteur
    
    /**
     * 
     */
    private final Node nodeReicever; //receiver
    
    /**
     * 
     */
    private boolean confirmed;
    
    /**
     * 
     * @param type
     * @param nodeSender
     * @param nodeReicever 
     */
    public Link(final LinkType type, final Node nodeSender, final Node nodeReicever) {
        this.type = type;
        this.nodeSender = nodeSender;
        this.nodeReicever = nodeReicever;
            this.confirmed = false;
        
    }
    
    /**
     * 
     * @return 
     */
    public LinkType getType() {
        return type;
    }
    
    /**
     * 
     * @param weight 
     */
    public void setWeight(final int weight) {
        this.weight = weight;
    }
    
    /**
     * 
     * @param type 
     */
    public void setType(final LinkType type) {
        this.type = type;
    }
    
    /**
     * 
     */
    public void setConfirmed() {
        this.confirmed = true;
    }
    
    /**
     * 
     * @return 
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * 
     * @return 
     */
    public Node getNodeSender() {
        return nodeSender;
    }
    
    /**
     * 
     * @return 
     */
    public int getSenderID(){
        return nodeSender.getNodeID();
    }
    
    /**
     * 
     * @return 
     */
    public Node getNodeReicever() {
        return nodeReicever;
    }
    
    /**
     * 
     * @return 
     */
    public int getReiceverID(){
        return nodeReicever.getNodeID();
    }
    
    /**
     * 
     * @return 
     */
    public boolean isConfirmed() {
        return confirmed;
    }

}
