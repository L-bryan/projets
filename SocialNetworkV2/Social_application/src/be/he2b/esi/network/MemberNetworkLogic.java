/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.esi.network;

import be.he2b.esi.db.MemberDB;
import be.he2b.esi.dto.MemberDto;
import be.he2b.esi.exception.DbException;
import be.he2b.esi.specification.MemberSpecification;
import java.text.ParseException;
import java.util.List;

/**
 * Class MemberNetworkLogic
 * @author Bryan & Dylan
 */
public class MemberNetworkLogic {

    public static int add(MemberDto member) throws DbException, ParseException {
        return MemberDB.insertDb(member);
    }

    public static List<MemberDto> getAllMembers() throws DbException {
        MemberSpecification sel = new MemberSpecification(0);
        List<MemberDto> col = MemberDB.getCollection(sel);
        return col;
    }
    
    public static MemberDto getAMembers(int id) throws DbException {
        MemberSpecification sel = new MemberSpecification(id);
        List<MemberDto> col = MemberDB.getCollection(sel);
        if(col.isEmpty()){
            return null;
        }
        return col.get(0);
    }
   
}
