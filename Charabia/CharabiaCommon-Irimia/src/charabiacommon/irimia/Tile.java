package charabiacommon.irimia;

import java.io.Serializable;

/**
 * Class Tile
 * @author g42992
 */
public class Tile implements Serializable {
    
    private final char letter;
    private final int value;
    
    /**
     * Create a tile object
     * @param letter char a letter
     * @param value int a value
     */
    public Tile(char letter, int value){
        this.letter = letter;
        this.value = value;
    }
    
    /**
     * get the char letters of tile
     * @return char tile letter
     */
    public char getLetter() {
        return letter;
    }
    
    /**
     * get the value of tile
     * @return int tile value
     */
    public int getValue() {
        return value;
    }
    
    
    
}
