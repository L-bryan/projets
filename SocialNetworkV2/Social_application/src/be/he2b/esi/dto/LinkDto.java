/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.esi.dto;

import be.he2b.esi.exception.DtoException;

/**
 *  Class LinkDto
 * @author Bryan & Dylan
 */
public class LinkDto extends EntityDto<Integer>  {
    
    private String linkType;
    private int weight;
    private int sender;
    private int receiver;
    private boolean confirmed;
    
    public LinkDto (Integer id, String linkType, Integer weight, Integer sender, Integer receiver, boolean confirmed ) throws DtoException{
        this(linkType, weight,sender, receiver,confirmed);
        this.id = id;
    }
    
    public LinkDto (String linkType, Integer weight, Integer sender, Integer receiver, boolean confirmed) throws DtoException{
        
        if(sender == null || receiver == null || linkType == null){
            throw new DtoException("les attributs sender, receiver et linkType sont obligatoires");
        }
        this.linkType = linkType;
        this.weight = weight;
        this.sender = sender;
        this.receiver = receiver;
        this.confirmed = confirmed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    @Override
    public String toString() {
        return "LinkDto{" + "linkType=" + linkType + ", weight=" + weight + 
                ", sender=" + sender + ", receiver=" + receiver + 
                ", confirmed=" + confirmed + '}';
    }
    
}
