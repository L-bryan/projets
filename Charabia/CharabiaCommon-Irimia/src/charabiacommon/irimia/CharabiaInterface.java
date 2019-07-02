package charabiacommon.irimia;

import java.util.List;

/**
 * Represents a game of Charabia.
 * 
 * Charabia is a word game where players must find the longest word 
 * constructed from letters.
 * 
 * The letters are drawn from a bag of tile. 
 * Each tile, as in Scrabble, has a letter and a value.
 * 
 * The winner of a round is the player who found the longest word.
 * If both players have found a word of same length, both player win the round.
 * His score is increased by the sum of the value of the letters of his word.
 * 
 * The game can be in several states.
 *  - CONFIGURE: the game hasn't started and waits for exactly 2 players to join.
 *  - STARTED: just after the last player has joined, and until the round is over.
 *  - ROUND_OVER: both players played (proposed a word)
 *  - GAME_OVER: round is over and there is not enough tile to play a new round.
 * 
 * @author esi_atl
 */
public interface CharabiaInterface {
    /**
     * Join a game, game must be in state CONFIGURE. If it is the second (and
     * thus last) player to join, the state of the game changes to STARTED.
     *
     * @param playerName the name of the new player
     * @return 
     * @throws IllegalStateException when game is not in state CONFIGURE
     * @throws IllegalArgumentException if there is already a player with the
     * same name.
     */
    public Player joinGame(String playerName);

    
    /**
     * Gets the list of players. $
     * It is empty if no player has joined yet, 
     * and contains 1 or 2 players otherwise.
     * @return the list of players.
     */
    public List<Player> getPlayers() ;
    
    /** 
     * Gets the list of current tiles from which players search for the best word.
     * 
     * Game state must be STARTED.
     * 
     * @return the list of current tiles.
     * @throws IllegalStateException when game state is not STARTED
     */
    public List<Tile> getTiles();
    
    /**
     *
     * @return 
     */
    public List<String> getBestWords();
    
    /**
     * A player proposes a word.
     * Game must be in state STARTED.
     * The player must not have already played in this round.
     * 
     * If all players played, game state changes to ROUND_OVER,
     * and the score of each player is updated according to the rules.
     * 
     * @param player the player playing.
     * @param word the word proposed by the player.
     * @throws IllegalStateException if the player already played
     */
    public void play(Player player, String word);

    /**
     * The winners of the round are the player that proposed the longest word.
     * Game must be in state ROUND_OVER.
     * 
     * @return the list of last round winners.
     * @throws IllegalStateException if game is not in ROUND_OVER state.
     */
    public List<Player> getRoundWinners();
   

    /**
     * The winners of the round are the player that play the longest word.
     * @return 
     */
    public List<Player> getWinners();
    
    
    /**
     * Starts the next round.
     * Game state changes to STARTED.
     * Game must be in state ROUND_OVER.
     * 
     * @throws IllegalStateException is game is not in ROUND_OVER state.
     */
    public void nextRound() ;
    
    /**
     * 
     * @return 
     */
    public boolean isPlayersReady();
    
    /**
     * true if the round is over but game is not over.
     * @return true if round is over.
     */
    public boolean isRoundOver();

    
    /**
     * true when game is over.
     * @return true if game is over.
     */
    public boolean isGameOver() ;

    public GameState getState();
    
    public void resetAllTiles();
}
