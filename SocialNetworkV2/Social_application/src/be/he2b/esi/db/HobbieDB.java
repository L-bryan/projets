/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.he2b.esi.db;

import be.he2b.esi.dto.HobbieDto;
import be.he2b.esi.exception.DbException;
import be.he2b.esi.exception.DtoException;
import be.he2b.esi.specification.HobbieSpecification;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class HobbieDB
 * @author Bryan & Dylan
 */
public class HobbieDB {
    
    private static final String RECORDNAME = "HOBBIES";
    
    public static List<HobbieDto> getCollection(HobbieSpecification sel) throws DbException {
        List<HobbieDto> hobbies = new ArrayList<>();

        try {
            String query = "Select id, idMember, name FROM HOBBIES";
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
                hobbies.add(new HobbieDto(rs.getInt("id"),
                        rs.getInt("idMember"), rs.getString("name"))
                );
            }
        } catch (DtoException ex) {
            throw new DbException("Instanciation de " + RECORDNAME + " impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DbException("Instanciation de " + RECORDNAME + " impossible:\nSQLException: " + eSQL.getMessage());
        }
        return hobbies;
    }
    
    public static int insertDb(HobbieDto record) throws DbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.LINKS);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into HOBBIES(id, idMember, name)"
                    + "values(?,?,?)");
            insert.setInt(1, num);
            insert.setInt(2, record.getIdMember());
            insert.setString(3, record.getName());
            insert.executeUpdate();
            return num;
        } catch (DbException | SQLException ex) {
            throw new DbException(RECORDNAME+": ajout impossible\n" + ex.getMessage());
        }
    }
}
