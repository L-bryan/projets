/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.esi.network;

import be.he2b.esi.dto.HobbieDto;
import be.he2b.esi.dto.LinkDto;
import be.he2b.esi.dto.MemberDto;
import be.he2b.esi.exception.NetworkException;
import java.util.List;

/**
 * Interface BDFacade
 * @author Bryan & Dylan
 */
public interface BDFacade {
    
    public List<MemberDto> getMembers() throws NetworkException;
    
    public int addMember(MemberDto member) throws NetworkException;
    
    public List<LinkDto> getLinks() throws NetworkException;
    
    public int addLink(LinkDto link) throws NetworkException;
    
    public int getLinkid(LinkDto link) throws NetworkException;
    
    public void updateLink(LinkDto link) throws NetworkException;
    
    public void removeLink(LinkDto link) throws NetworkException;
    
    public List<HobbieDto> getHobbies() throws NetworkException;
    
    public int addHobbie(HobbieDto hobbie) throws NetworkException;
}
