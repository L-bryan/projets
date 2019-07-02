package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan & Dylan
 */
public class Node implements Serializable {//TODO javadoc && change name function
    
    private static final long serialVersionUID = 354054054054L;
    
    /**
     * 
     */
    private final int NodeID;
    
    /**
     * 
     */
    private final List<Link> links;

    /**
     * 
     * @param NodeId 
     */
    public Node(final int NodeId) {
        this.NodeID = NodeId;
        links = new ArrayList<>();

    }
    
    /**
     * 
     * @return 
     */
    public int getNodeID() {
        return NodeID;
    }
    
    /**
     * 
     * @return 
     */
    public List<Link> getLinks() {
        return links;
    }
    
    /**
     * 
     * @param type
     * @param requests
     * @return 
     */
    public List<Link> getLinks2(final LinkType type, final boolean requests) {
        final List<Link> linkTMP = new ArrayList<>();
        
        if (requests) {
            for (final Link l : links) {
                if (l.getType() == type && !l.isConfirmed()) {
                    linkTMP.add(l);
                }
            }
            

        }else{
            for (final Link l : links) {
                if (l.getType() == type && l.isConfirmed()) {
                    linkTMP.add(l);
                }
            }
        }
        return linkTMP;

    }
    
    /**
     * 
     * @param type
     * @param requests
     * @param sender
     * @return 
     */
    public List<Link> getLinks2(final LinkType type, final boolean requests, final Profil sender) {
        final List<Link> linkTMP = new ArrayList<>();
        
        if (requests) {
            for (final Link l : links) {
                if (l.getType() == type && !l.isConfirmed() && l.getNodeSender()!=sender) {
                    linkTMP.add(l);
                }
            }
            

        }else{
            for (final Link l : links) {
                if (l.getType() == type && l.isConfirmed()) {
                    linkTMP.add(l);
                }
            }
        }
        return linkTMP;

    }
    
    /**
     * 
     * @return 
     */
    public List<Link> getMatchWait() {
        final List<Link> waitMatchs = new ArrayList<>();
        for (final Link l : links) {
            if (l.getType() == LinkType.MATCH && !l.isConfirmed()) {
                waitMatchs.add(l);
            }
        }
        return waitMatchs;
    }
    
    /**
     * 
     * @return 
     */
    public List<Link> getMatchs() {
        final List<Link> matchs = new ArrayList<>();
        for (final Link l : links) {
            if (l.getType() == LinkType.MATCH && l.isConfirmed()) {
                matchs.add(l);
            }
        }
        return matchs;
    }
    
    /**
     * 
     * @return 
     */
    public List<Link> getPotentialMatchs() {
        final List<Link> potentialmatchs = new ArrayList<>();
        for (final Link l : links) {
            if (l.getType() == LinkType.MATCH_POTENTIAL) {
                potentialmatchs.add(l);
            }
        }
        return potentialmatchs;
    }
    
    /**
     * 
     * @param node
     * @return 
     */
    public List<Link> getPotentialMatchs(final Node node) {
        final List<Link> potentialmatchs = new ArrayList<>();
        for (final Link l : links) {
            if (l.getType() == LinkType.MATCH_POTENTIAL && (l.getNodeReicever()==node ||l.getNodeSender()==node)) {
                potentialmatchs.add(l);
            }
        }
        return potentialmatchs;
    }
    
    /**
     * 
     * @return 
     */
    public List<Link> getFreindWait() {
        final List<Link> waitFriend = new ArrayList<>();
        for (final Link l : links) {
            if (l.getType() == LinkType.FRIENDS && !l.isConfirmed()) {
                waitFriend.add(l);
            }
        }
        return waitFriend;
    }
    
    /**
     * 
     * @return 
     */
    public List<Link> getFreind() {
        final List<Link> friends = new ArrayList<>();
        for (final Link l : links) {
            if (l.getType() == LinkType.FRIENDS && l.isConfirmed()) {
                friends.add(l);
            }
        }
        return friends;
    }
    
    /**
     * 
     * @return 
     */
    public List<Link> getDeadLink() {
        final List<Link> dead = new ArrayList<>();
        for (final Link l : links) {
            if (l.getType() == LinkType.REFUSED) {
                dead.add(l);
            }
        }
        return dead;
    }
    
    /**
     * 
     * @param link 
     */
    public void addLinks(final Link link) {
        links.add(link);
    }
    
    /**
     * 
     * @param link 
     */
    public void deleteLink(final Link link) {
        this.links.remove(link);
    }
    
    /**
     * 
     * @param object
     * @return 
     */
    @Override
    public boolean equals(final Object object) {
        final Node node = (Node) object;
        return (NodeID == node.NodeID);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.NodeID;
        return hash;
    }

}
