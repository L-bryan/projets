package core;

/**
 * Interface Information
 * It's for all inforamtions concerning a Profil
 * @author Bryan & Dylan
 */
public interface Information {
    
    /**
     * Boolean for know if the information is public or private
     * @return true if is private else return false
     */
    boolean isPrivate();
   
    /**
     * get the information type from the enum InformationType
     * @return InformationType like :
     * {BRITHDATE, SEX, NAME, CONTACT, HOBBIE, PREFERENCE}
     */
    InformationType getType();
}
