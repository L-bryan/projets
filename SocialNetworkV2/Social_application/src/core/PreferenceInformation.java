package core;

import java.io.Serializable;

/**
 * Class PreferenceInformation
 * @author Bryan & Dylan
 */
public class PreferenceInformation implements Information, Serializable{
    private static final long serialVersionUID = 354054054054L;
    
    /**
     * Indication for the sex of the person
     */
    private SexInformation sex;
    
    /**
     * This is the number of friends between your and the partners
     */
    private int friendDistance;
    
    /**
     * Minimum age of your partners
     */
    private int ageMin;
    
    /**
     * Maximum age of your partners
     */
    private int ageMax;
    
    /**
     * Construct a PreferenceInformation
     * @param sex sexInformation the sex with who we want match
     * @param friendDistance int the distance of friends that separetes you
     * @param ageMin int corresponding the minimal age with who we want match
     * @param ageMax int corresponding the maximal age with who we want match
     */
    public PreferenceInformation(final SexInformation sex, final int friendDistance, 
            final int ageMin, final int ageMax) {
        this.sex = sex;
        this.friendDistance = friendDistance;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
    }
    
    /**
     * getSexInformation get sex information
     * @return a SexInformation
     */
    public SexInformation getSexInformation() {
        return sex;
    }
    
    /**
     * 
     * @return 
     */
    public Sex getSex() {
        return sex.getSex();
    }
    
    /**
     * get the friend_distance
     * @return a int friend_distance
     */
    public int getFriendDistance() {
        return friendDistance;
    }
    
    /**
     * get the minimal age
     * @return int the minimal age
     */
    public int getAgeMin() {
        return ageMin;
    }
    
    
    /**
     * get the maximal age
     * @return int the maximal age
     */
    public int getAgeMax() {
        return ageMax;
    }
    
    /**
     * 
     * @param sex 
     */
    public void setSex(final Sex sex) {
        this.sex.setSex(sex);
    }

    /**
     * 
     * @param friendDistance 
     */
    public void setFriendDistance(final int friendDistance) {
        this.friendDistance = friendDistance;
    }
    
    /**
     * 
     * @param ageMin 
     */
    public void setAgeMin(final int ageMin) {
        this.ageMin = ageMin;
    }
    
    /**
     * 
     * @param ageMax 
     */
    public void setAgeMax(final int ageMax) {
        this.ageMax = ageMax;
    }
    
    
    
    /**
     * 
     * @return 
     */
    @Override
    public boolean isPrivate() {
        return true;
    }

    /**
     * Express the type of information
     * @return tyoe of information
     */
    @Override
    public InformationType getType() {
        return InformationType.PREFERENCE;
    }
    
}
