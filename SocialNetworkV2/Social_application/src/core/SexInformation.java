package core;

import java.io.Serializable;

/**
 * Class SexInformation
 * @author Bryan & Dylan
 */
public class SexInformation implements Information,Serializable{//TODO javadoc
    
    private static final long serialVersionUID = 42L;

    /**
     * 
     */
    private Sex sex;//maybe can be change
    
    /**
     * Construct a SexInformation
     * @param sex enum Sex
     */
    public SexInformation(final Sex sex) {
        this.sex = sex;
    }
    public SexInformation(final String sex) {
        switch (sex) {
            case "MEN":
                this.sex = Sex.MAN;
                break;
            case "WOMAN":
                this.sex = Sex.WOMAN;
                break;
            default:
                this.sex = null;
                break;
        }
    }
    
    /**
     * get the Sex enum
     * @return Sex enum
     */
    public Sex getSex() {
        return sex;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public boolean isPrivate() {
        return false;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public InformationType getType() {
        return InformationType.SEX;
    }
    
    /**
     * 
     * @param sex 
     */
    public void setSex(final Sex sex) {
        this.sex = sex;
    }
    
    
}
