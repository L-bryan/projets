package charabiacommon.irimia;

import java.io.Serializable;

/**
 * Class enumeration GameState
 * @author g42992
 */
public enum GameState implements Serializable {
    
    /**
     * CONFIGURE: 
     * the game hasn't started and waits for exactly 2 players to join.
     */
    CONFIGURE,
    
    /**
     * STARTED: 
     * just after the last player has joined, and until the round is over.
     */
    STARTED,
    
    /**
     * ROUND_OVER: 
     * both players played (proposed a word)
     */
    ROUND_OVER,
    
    /**
     * GAME_OVER: 
     * round is over and there is not enough tile to play a new round.
     */
    GAME_OVER
}
