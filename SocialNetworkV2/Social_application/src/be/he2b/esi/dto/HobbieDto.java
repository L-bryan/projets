/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.esi.dto;

import be.he2b.esi.exception.DtoException;

/**
 * Class HobbieDto
 * @author Bryan & Dylan
 */
public class HobbieDto extends EntityDto<Integer> {
    
    private int idMember;
    private String name;

    public HobbieDto(Integer id, Integer idMember, String name) throws DtoException {
        this(idMember, name);
        this.id = id;
    }

    public HobbieDto(Integer idMember, String name) throws DtoException {
        if(idMember == null || name == null){
            throw new DtoException("les attributs idMember et name sont obligatoires");
        }
        this.idMember = idMember;
        this.name = name;
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

    @Override
    public String toString() {
        return "HobbieDto{" + "idMember=" + idMember + ", name=" + name + '}';
    }
    
}
