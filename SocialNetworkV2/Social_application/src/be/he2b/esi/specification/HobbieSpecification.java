/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.esi.specification;

/**
 * Class HobbieSpecification
 * @author Bryan & Dylan
 */
public class HobbieSpecification {
    
    private int id;
    private int idMember;
    private String name;

    public HobbieSpecification(int idMember, String name) {
        this.id = 0;
        this.idMember = idMember;
        this.name = name;
    }

    public HobbieSpecification(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
