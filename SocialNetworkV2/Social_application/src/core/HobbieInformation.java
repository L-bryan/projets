package core;

import java.io.Serializable;

/**
 * Class HobbieInformation
 * @author Bryan & Dylan
 */
public class HobbieInformation implements Information, Serializable{
    private static final long serialVersionUID = 354054054054L;
    
    /**
     * The name of the information (Hobbie)
     */
    private final String name;
    
    /**
     * Construct a HobbieInformation
     * @param name the hobbie's name
     */
    public HobbieInformation(final String name) {
        this.name = name;
    }
    
    /**
     * get the name of the Hobbie
     * @return string the hobbie's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Express the confidentiality of information
     * @return the confidentiality
     */
    @Override
    public boolean isPrivate() {
        return false;
    }

    /**
     * Express the type of information
     * @return tyoe of information
     */
    @Override
    public InformationType getType() {
        return InformationType.HOBBIE;
    }
}
