package be.he2b.esi.db;

import be.he2b.esi.dto.MemberDto;
import be.he2b.esi.exception.DbException;
import be.he2b.esi.exception.DtoException;
import be.he2b.esi.specification.MemberSpecification;
import core.BirthDateInformation;
import core.ContactInformation;
import core.NameInformation;
import core.PreferenceInformation;
import core.Sex;
import core.SexInformation;
import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Representation of all members register in the social Network
 *
 * @author Dylan et Bryan
 */
public class MemberDB {

    private static final String RECORDNAME = "MEMBERS";

    public static List<MemberDto> getCollection(MemberSpecification sel) throws DbException {
        List<MemberDto> members = new ArrayList<>();

        try {
            String query = "Select id, name, firstName, birthDate, sex, email, tel,sexPref,ageMin, ageMax, friendDist FROM MEMBERS";
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
                
                members.add(
                        
                        new MemberDto(rs.getInt("id"),
                                new NameInformation(rs.getString("name"), rs.getString("firstName")),
                                new BirthDateInformation(rs.getDate("birthDate")),
                                new ContactInformation(rs.getString("tel"),rs.getString("email")),
                                new SexInformation(rs.getString("sex")),
                                //new PreferenceInformation(null,0,0,0)
                                new PreferenceInformation(new SexInformation(Sex.MAN), rs.getInt("friendDist"), rs.getInt("ageMin"), rs.getInt("ageMax"))   
                        )
                );
            }
        } catch (DtoException ex) {
            throw new DbException("Instanciation de " + RECORDNAME + " impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DbException("Instanciation de " + RECORDNAME + " impossible:\nSQLException: " + eSQL.getMessage());
        }
        return members;
    }

    public static int insertDb(MemberDto record) throws DbException, ParseException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.MEMBERS);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;

            String query = "Insert into MEMBERS(id, name, firstName, birthDate, sex)"
                    + "values(?,?,?,?,?)";
            int cpt=0;
            if(record.getContactInformation()!=null){
                cpt++;
            }
            if(record.getPreferenceInformation()!=null){
                cpt=cpt+2;
            }
            switch (cpt) {
                case 1:
                    query = "Insert into MEMBERS(id, name, firstName, birthDate, sex, email, tel)"
                            + "values(?,?,?,?,?,?,?)";
                    break;
                case 2:
                    query = "Insert into MEMBERS(id, name, firstName, birthDate, sex,sexPref,ageMin,ageMax,friendDist)"
                            + "values(?,?,?,?,?,?,?,?,?)";
                    break;
                case 3:
                    query = "Insert into MEMBERS(id, name, firstName, birthDate, sex,"
                            + " email, tel, sexPref, ageMin, ageMax, friendDist)"
                            + "values(?,?,?,?,?,?,?,?,?,?,?)";
                    break;
                default:
                    break;
            }
            insert= connexion.prepareStatement(query);
            insert.setInt(1, num);
            insert.setString(2, record.getNameInformation().getName());
            insert.setString(3, record.getNameInformation().getFirstname());
            java.sql.Date sqlDate = new java.sql.Date(record.getBirthDateInformation().getDate().getTime());
            insert.setDate(4, sqlDate);
            insert.setString(5, record.getSexInformation().getSex().name());
            
            
            switch (cpt) {
                case 1:
                    insert.setString(6, record.getContactInformation().getEmail());
                    insert.setString(7, record.getContactInformation().getTel());
                    break;
                case 2:
                    insert.setString(6, record.getPreferenceInformation().getSex().name());
                    insert.setInt(7, record.getPreferenceInformation().getAgeMin());
                    insert.setInt(8, record.getPreferenceInformation().getAgeMax());
                    insert.setInt(9, record.getPreferenceInformation().getFriendDistance());
                    
                    break;
                case 3:
                    insert.setString(6, record.getContactInformation().getEmail());
                    insert.setString(7, record.getContactInformation().getTel());
                    insert.setString(8, record.getPreferenceInformation().getSex().name());
                    insert.setInt(9, record.getPreferenceInformation().getAgeMin());
                    insert.setInt(10, record.getPreferenceInformation().getAgeMax());
                    insert.setInt(11, record.getPreferenceInformation().getFriendDistance());
                    break;
                default:
                    break;
            }


            
            insert.executeUpdate();
            return num;
        } catch (DbException | SQLException ex) {
            throw new DbException(RECORDNAME + ": ajout impossible\n" + ex.getMessage());
        }
    }
}
