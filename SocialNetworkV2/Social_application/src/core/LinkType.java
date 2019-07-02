package core;


/**
 * enum Link
 * type of relation between Node
 * @author Bryan & Dylan
 */
public enum LinkType {
    
    /**
     * FRIENDS is a friendship relation
     */
    FRIENDS("FRIENDS"),
    
    /**
     * MATCH is a Match relation
     */
    MATCH("MATCH"),
    
    /**
     * MATCH_POTENTIAL is a potential match relation
     */
    MATCH_POTENTIAL("MATCH_POTENTIAL"),
    
    /**
     * REFUSED is the relation when a Node refuse a Match.
     */
    REFUSED("REFUSED");
    
    private final String name;
    
    private LinkType(final String name){
        this.name = name;
    }
}
