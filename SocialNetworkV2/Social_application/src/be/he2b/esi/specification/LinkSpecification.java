package be.he2b.esi.specification;

/**
 * Class LinkSpecification
 * @author Bryan & Dylan 
 */
public class LinkSpecification {
    
    private int id;
    private String linkType;
    private int sender;
    private int receiver;
    private Boolean confirmed;
    
    public LinkSpecification(String linkType, int sender, int receiver, Boolean confirmed){
        this.id = 0;
        this.linkType = linkType;
        this.sender = sender;
        this.receiver = receiver;
        this.confirmed = confirmed;
    }
    
    public LinkSpecification(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }
    
}
