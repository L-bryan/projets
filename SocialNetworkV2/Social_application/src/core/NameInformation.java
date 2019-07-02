package core;

import java.io.Serializable;

/**
 * Class NameInformation
 * @author Bryan & Dylan
 */
public class NameInformation implements Information, Serializable{
    
    private static final long serialVersionUID = 354054054054L;

    /**
     * Name of the person
     */
    private  String name;
    
    /**
     * Firstname of the person
     */
    private  String firstname;
    
    /**
     * Construct a NameInformation
     * @param name String the family name
     * @param firstname  String the firstname
     */
    public NameInformation(final String name, final String firstname) {
        this.name = name;
        this.firstname = firstname;
    }
    
    /**
     * get the family name
     * @return string the name
     */
    public String getName() {
        return name;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    
    /**
     * get the firstname
     * @return string firstname
     */
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstName(String firstName){
        this.firstname=firstName;
    }
    
    /**
     * get the full name of a person
     * @return 
     */
    public String getFullName(){
        return firstname+" "+name;
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
        return InformationType.NAME;
    }
    
    @Override
    public String toString(){
        return name+" "+firstname;
    }
}
