package be.he2b.esi.db;

import be.he2b.esi.dto.LinkDto;
import be.he2b.esi.exception.DbException;
import be.he2b.esi.exception.DtoException;
import be.he2b.esi.specification.LinkSpecification;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class LinkDB
 * @author Bryan & Dylan
 */
public class LinkDB {
    private static final String RECORDNAME = "LINKS";
    
    public static List<LinkDto> getCollection(LinkSpecification sel) throws DbException {
        List<LinkDto> links = new ArrayList<>();

        try {
            String query = "Select id, linkType, weight, sender, receiver, confirmed FROM LINKS";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getId() != 0) {
                where = where + " id = ? ";
            }

            if (where.length() != 0) {
                where = " where " + where;
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getId() != 0) {
                    stmt.setInt(i, sel.getId());
                    i++;

                }
            } else {
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                links.add(new LinkDto(rs.getInt("id"),rs.getString("linkType"),
                        rs.getInt("weight"), rs.getInt("sender"),
                        rs.getInt("receiver"), rs.getBoolean("confirmed"))
                );
            }
        } catch (DtoException ex) {
            throw new DbException("Instanciation de " + RECORDNAME + " impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DbException("Instanciation de " + RECORDNAME + " impossible:\nSQLException: " + eSQL.getMessage());
        }
        return links;
    }
    
    public static int insertDb(LinkDto record) throws DbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.LINKS);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into LINKS(id, linkType, weight, sender, receiver, confirmed)"
                    + "values(?,?,?,?,?,?)");
            insert.setInt(1, num);
            insert.setString(2,record.getLinkType());
            insert.setInt(3, 0);
            insert.setInt(4, record.getSender());
            insert.setInt(5, record.getReceiver());
            insert.setBoolean(6, record.isConfirmed());
            insert.executeUpdate();
            return num;
        } catch (DbException | SQLException ex) {
            throw new DbException(RECORDNAME+": ajout impossible\n" + ex.getMessage());
        }
    }
    
    public static int getLinkid(LinkDto link) throws DbException {
        int linkid = 0;
        try {
            String query = "Select id, linkType, weight, sender, receiver, confirmed FROM LINKS";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = " where linkType = ? AND weight = ? AND sender = ? AND receiver = ? AND confirmed = ? ";
            query = query + where;
            stmt = connexion.prepareStatement(query);
            
            stmt.setString(1, link.getLinkType());
            stmt.setInt(2, 0);
            stmt.setInt(3, link.getSender());
            stmt.setInt(4, link.getReceiver());
            stmt.setBoolean(5, link.isConfirmed());
            
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                linkid = rs.getInt("id");
            }
        } catch (SQLException ex) {
            throw new DbException(RECORDNAME+": ajout impossible\n" + ex.getMessage());
        }
        
        return linkid;
    }
    
    public static void update(int id) throws DbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update LINKS set "                   
                    + "confirmed=? "
                    + "where id=?";
            update = connexion.prepareStatement(sql);
            update.setBoolean(1, true);            
            update.setInt(2, id);
            update.executeUpdate();
        } catch (Exception ex) {
            throw new DbException(RECORDNAME+", modification impossible:\n" + ex.getMessage());
        }
    }
    
    public static void remove(int id) throws DbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("delete from LINKS where id=" + id);
        } catch (Exception ex) {
            throw new DbException(RECORDNAME+": suppression impossible\n" + ex.getMessage());
        }
    }
    
}
