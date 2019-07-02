package charabiacommon.users;

import charabiacommon.irimia.GameState;
import charabiacommon.irimia.Player;
import charabiacommon.irimia.Tile;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author g42992
 */
public class Refresh implements Serializable {
    
    private final List<Tile> tiles;
    private final List<Player> players;
    private final GameState state;
    private final boolean isPlayersReady;
    private final List<String> bestWord;
    private final List<Player> winnerRound;
    private final List<Player> winnerGamer;

    public Refresh(
        List<Tile> tiles,
        List<Player> players,
        GameState state,
        boolean isPlayersReady,
        List<String> bestWord, 
        List<Player> winnerRound,
        List<Player> winnerGamer
    ) {
        this.tiles = tiles;
        this.players = players;
        this.state = state;
        this.isPlayersReady = isPlayersReady;
        this.bestWord = bestWord;
        this.winnerRound = winnerRound;
        this.winnerGamer = winnerGamer;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getState() {
        return state;
    }

    public boolean isIsPlayersReady() {
        return isPlayersReady;
    }

    public List<String> getBestWord() {
        return bestWord;
    }

    public List<Player> getWinnerRound() {
        return winnerRound;
    }

    public List<Player> getWinnerGamer() {
        return winnerGamer;
    }

        
}
