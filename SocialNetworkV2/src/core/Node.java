package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lyan
 */
public class Node implements Serializable {

    private final int id;
    private final List<Link> links;

    public Node(int id) {
        this.id = id;
        links = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public List<Link> getLinks() {
        return links;
    }

    public List<Link> getLinks2(LinkType type, boolean requests) {
        List<Link> linkTMP = new ArrayList<>();
        
        if (requests) {
            for (Link l : links) {
                if (l.getType() == type && !l.isConfirmed()) {
                    linkTMP.add(l);
                }
            }
            

        }else{
            for (Link l : links) {
                if (l.getType() == type && l.isConfirmed()) {
                    linkTMP.add(l);
                }
            }
        }
        return linkTMP;

    }

    public List<Link> getMatchWait() {
        List<Link> waitMatchs = new ArrayList<>();
        for (Link l : links) {
            if (l.getType() == LinkType.MATCH && !l.isConfirmed()) {
                waitMatchs.add(l);
            }
        }
        return waitMatchs;
    }

    public List<Link> getMatchs() {
        List<Link> matchs = new ArrayList<>();
        for (Link l : links) {
            if (l.getType() == LinkType.MATCH && l.isConfirmed()) {
                matchs.add(l);
            }
        }
        return matchs;
    }

    public List<Link> getPotentialMatchs() {
        List<Link> Potentialmatchs = new ArrayList<>();
        for (Link l : links) {
            if (l.getType() == LinkType.MATCH_POTENTIAL) {
                Potentialmatchs.add(l);
            }
        }
        return Potentialmatchs;
    }

    public List<Link> getFreindWait() {
        List<Link> waitFriend = new ArrayList<>();
        for (Link l : links) {
            if (l.getType() == LinkType.FRIENDS && !l.isConfirmed()) {
                waitFriend.add(l);
            }
        }
        return waitFriend;
    }

    public List<Link> getFreind() {
        List<Link> friends = new ArrayList<>();
        for (Link l : links) {
            if (l.getType() == LinkType.FRIENDS && l.isConfirmed()) {
                friends.add(l);
            }
        }
        return friends;
    }

    public List<Link> getDeadLink() {
        List<Link> dead = new ArrayList<>();
        for (Link l : links) {
            if (l.getType() == LinkType.REFUSED) {
                dead.add(l);
            }
        }
        return dead;
    }

    public void addLinks(Link l) {
        links.add(l);
    }

    public void deleteLink(Link l) {
        this.links.remove(l);
    }

    @Override
    public boolean equals(Object o) {
        Node n = (Node) o;
        return this.id == n.id;
    }

}
