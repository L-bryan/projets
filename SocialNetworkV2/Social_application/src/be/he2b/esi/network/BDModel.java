/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.esi.network;

import be.he2b.esi.db.DBManager;
import be.he2b.esi.dto.HobbieDto;
import be.he2b.esi.dto.LinkDto;
import be.he2b.esi.dto.MemberDto;
import be.he2b.esi.exception.DbException;
import be.he2b.esi.exception.NetworkException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class BDModel implement BDFacade
 * @author Bryan & Dylan
 */
public class BDModel implements BDFacade {
    
     @Override
     public List<MemberDto> getMembers() throws NetworkException {
        try {
            DBManager.startTransaction();
            List<MemberDto> member = MemberNetworkLogic.getAllMembers();
            DBManager.validateTransaction();
            return member;
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new NetworkException("Liste des Members inaccessible! \n" + msg);
            }
        }
    }
    

     @Override
    public int addMember(MemberDto member) throws NetworkException {
        try {
            DBManager.startTransaction();
            int id = MemberNetworkLogic.add(member);
            DBManager.validateTransaction();
            return id;
        } catch (DbException ex) {
            try {
                DBManager.cancelTransaction();
                throw new NetworkException(ex.getMessage());
            } catch (DbException ex1) {
                throw new NetworkException(ex1.getMessage());
            }
        } catch (ParseException ex) {
             Logger.getLogger(BDModel.class.getName()).log(Level.SEVERE, null, ex);
         }
         return 0;
    }
    
    @Override
    public List<LinkDto> getLinks() throws NetworkException{
        try {
            DBManager.startTransaction();
            List<LinkDto> col = LinkNetworkLogic.getLinks();
            DBManager.validateTransaction();
            return col;
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new NetworkException("Liste des Links inaccessible! \n" + msg);
            }
        }
    }
    
    @Override
    public int addLink(LinkDto link) throws NetworkException {
        try {
            DBManager.startTransaction();
            int id = LinkNetworkLogic.add(link);
            DBManager.validateTransaction();
            return id;
        } catch (DbException ex) {
            try {
                DBManager.cancelTransaction();
                throw new NetworkException(ex.getMessage());
            } catch (DbException ex1) {
                throw new NetworkException(ex1.getMessage());
            }
        }
    }

    @Override
    public List<HobbieDto> getHobbies() throws NetworkException {
        try {
            DBManager.startTransaction();
            List<HobbieDto> col = HobbieNetworkLogic.getHobbies();
            DBManager.validateTransaction();
            return col;
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new NetworkException("Liste des Hobbies inaccessible! \n" + msg);
            }
        }
    }

    @Override
    public int addHobbie(HobbieDto hobbie) throws NetworkException {
        try {
            DBManager.startTransaction();
            int id = HobbieNetworkLogic.add(hobbie);
            DBManager.validateTransaction();
            return id;
        } catch (DbException ex) {
            try {
                DBManager.cancelTransaction();
                throw new NetworkException(ex.getMessage());
            } catch (DbException ex1) {
                throw new NetworkException(ex1.getMessage());
            }
        }
    }

    @Override
    public int getLinkid(LinkDto link) throws NetworkException {
         try {
            DBManager.startTransaction();
            int linkid = LinkNetworkLogic.getLinkid(link);
            DBManager.validateTransaction();
            return linkid;
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new NetworkException("Liste des Link inaccessible! \n" + msg);
            }
        }
    }

    @Override
    public void updateLink(LinkDto link) throws NetworkException {
        try {
            DBManager.startTransaction();
            LinkNetworkLogic.update(link);
            DBManager.validateTransaction();
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new NetworkException("Mise à jour de link impossible! \n" + msg);
            }
        }
    }

    @Override
    public void removeLink(LinkDto link) throws NetworkException {
        try {
                DBManager.startTransaction();
                LinkNetworkLogic.remove(link);
                DBManager.validateTransaction();
        } catch (DbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new NetworkException("Suppression de Link impossible! \n" + msg);
            }
        }
    }

}
