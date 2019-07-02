package charabiacommon.irimia;

import java.io.Serializable;

/**
 * Class Player
 * @author g42992
 */
public class Player implements Serializable {
    
    private final String name;
    private int score;
    private String proposition;
    
    /**
     * create a player with a name
     * @param name String player name
     */
    public Player(String name){
        this.name = name;
        this.score = 0;
        this.proposition = "";
    }
    
    /**
     * get the player name
     * @return String player name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * get the player score
     * @return int player score
     */
    public int getScore(){
        return this.score;
    }
    
    /**
     * get the player proposition
     * @return String value of proposition
     */
    public String getProposition(){ //@srv: peut-être public pour l'afficher dabs l'interface
        return this.proposition;
    }
    
    /**
     * set the proposition string
     * @param proposition 
     */
    public void setPorposition(String proposition){
        this.proposition = proposition;
    }
    
    /**
     * add point to a player
     * @param n number of point
     */
    public void addPoint(int n){
        this.score += n;
    }
    
    
    public void resetProposition(){
        this.proposition = "";
    }
    
    public boolean hasPlayed(){
        return ! this.proposition.equals("");
    }
}
