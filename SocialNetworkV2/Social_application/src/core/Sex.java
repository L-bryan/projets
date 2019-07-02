package core;


/**
 * enum Sex
 * @author Bryan & Dylan
 */
public enum Sex {
    
    /**
     * MAN a man - boy
     */
    MAN("M"),
    
    /**
     * WOMAN a woman - girl
     */
    WOMAN("F");
    
    final private String name;
    
    /**
     * Private Construc for show a string representation of enum
     * @param name string
     */
    Sex(final String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }  
    
}
