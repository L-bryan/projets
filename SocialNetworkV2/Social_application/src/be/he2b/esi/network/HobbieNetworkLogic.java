/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.esi.network;

import be.he2b.esi.db.HobbieDB;
import be.he2b.esi.dto.HobbieDto;
import be.he2b.esi.exception.DbException;
import be.he2b.esi.specification.HobbieSpecification;
import java.util.List;

/**
 * Class HobbieNetworkLogic
 * @author Bryan & Dylan
 */
public class HobbieNetworkLogic {
    public static int add(HobbieDto hobbie) throws DbException {
        return HobbieDB.insertDb(hobbie);
    }
     
    public static List<HobbieDto> getHobbies() throws DbException {
        HobbieSpecification sel = new HobbieSpecification(0);
        List<HobbieDto> col = HobbieDB.getCollection(sel);
        return col;
    }
}
