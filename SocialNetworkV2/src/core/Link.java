package core;

import java.io.Serializable;

public class Link implements Serializable {

    private LinkType type;
    private int weight;
    private final Node nodeSender; //emetteur
    private final Node nodeReicever; //receiver
    private boolean confirmed;

    public Link(LinkType type, Node nodeSender, Node nodeReicever) {
        this.type = type;
        this.nodeSender = nodeSender;
        this.nodeReicever = nodeReicever;
        this.confirmed=false;
    }

    public LinkType getType() {
        return type;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    public void setType(LinkType type) {
        this.type = type;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    int getWeight() {
        return weight;
    }

    public Node getNodeSender() {
        return nodeSender;
    }

    public Node getNodeReicever() {
        return nodeReicever;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

}
