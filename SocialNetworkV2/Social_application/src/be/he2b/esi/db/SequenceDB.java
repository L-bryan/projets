package be.he2b.esi.db;

import be.he2b.esi.exception.DbException;



/**
 * Classe d'accès au gestionnaire de persistance pour les Séquences
 */
public class SequenceDB {

    static final String MEMBERS = "MEMBERS";
    static final String LINKS = "LINKS";
    static final String HOBBIES = "HOBBIES";

    static synchronized int getNextNum(String sequence) throws DbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            String query = "Update SEQUENCES set sValue = sValue+1 where id='" + sequence + "'";
            java.sql.PreparedStatement update = connexion.prepareStatement(query);
            update.execute();
            java.sql.Statement stmt = connexion.createStatement();
            query = "Select sValue FROM Sequences where id='" + sequence + "'";
            java.sql.ResultSet rs = stmt.executeQuery(query);
            int nvId;
            if (rs.next()) {
                nvId = rs.getInt("sValue");
                return nvId;
            } else {
                throw new DbException("Nouveau n° de séquence inaccessible!");
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DbException("Nouveau n° de séquence inaccessible!\n" + eSQL.getMessage());
        }
    }

}
