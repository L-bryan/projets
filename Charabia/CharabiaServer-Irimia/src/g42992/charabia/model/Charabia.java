package g42992.charabia.model;

import charabiacommon.irimia.Observer;
import charabiacommon.irimia.Observable;
import charabiacommon.irimia.CharabiaInterface;
import charabiacommon.irimia.GameState;
import charabiacommon.irimia.Player;
import charabiacommon.irimia.Tile;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Charabia
 * @author g42992
 */
public class Charabia implements CharabiaInterface, Observable {
    
    private final List<Observer> obs;
    private final List<Player> players;
    private final Dictionary ditionary;
    private final Bag bag;
    private final Table table;
    private GameState state;
    private int playerPlayed;
    private final int PLAYERNUMBER = 2;
    
    public Charabia(List<String> words, List<Tile> tiles){
        this.obs = new ArrayList();
        this.players = new ArrayList();
        this.ditionary = new Dictionary(words);
        this.bag = new Bag(tiles);
        this.table = new  Table(this.bag);
        this.state = GameState.CONFIGURE;
        this.playerPlayed = 0;
    }
    
    @Override
    public Player joinGame(String playerName) {
        
        if(this.state != GameState.CONFIGURE) 
            throw new IllegalStateException(
                    "The game are not in CONFIGURE state"
            );
        if(this.players.size()==1 && this.players
                .get(0).getName().equals(playerName))
            throw new IllegalStateException("The Players have the same name");
        
        Player player = new Player(playerName);
        this.players.add(player);
        return player;
    }
    
    @Override
    public boolean isPlayersReady(){
        if(this.players.size()==PLAYERNUMBER)
            this.state = GameState.ROUND_OVER;
        return this.players.size() == PLAYERNUMBER;
    }
    
    @Override
    public GameState getState(){
        return this.state;
    }
    
    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public List<Tile> getTiles() {
        
        if(this.state != GameState.STARTED)
            throw new IllegalStateException(
                    "The game are not in STARTED state"
            );
        while(bestWord().isEmpty()){
            resetAllTiles();
        }
        return this.table.getTiles();
    }
    
    @Override
    public void resetAllTiles(){
        this.table.removeAllTile();
        this.table.fillTiles(bag);
    }

    @Override
    public void play(Player player, String word) {
        if(player.hasPlayed()) throw new IllegalStateException(
                "player "+player.getName()+" have already play"
        );
        this.players.get(this.players.indexOf(player)).setPorposition(word);
        this.playerPlayed += 1;
    }

    @Override
    public List<String> getBestWords(){
        if(this.state != GameState.ROUND_OVER
                && this.state != GameState.GAME_OVER){
            throw new IllegalStateException(
                    "The game are not in ROUND_OVER or GAME_OVER state"
            );
        }
        List<String> bestWords = bestWord();
        return bestWords;
    }
    
    /**
     * get all words in the dictionary that
     * you can make with the Tile list
     * @return the list of words
     */
    List<String> possibleWords(){
        List<String> possibleWords = new ArrayList();
        for(String str : this.ditionary.getAllWords()){
            if(this.table.isPossible(str)){
                possibleWords.add(str);
            }
        }
        return possibleWords;
    }
    
    /**
     * get the best word or bests words with specific tiles
     * for be an best word it is necessary to be longest and the most point
     * value (sum of all Tile use)
     * @param tiles the Tiles used for create a word
     * @return 
     */
    List<String> bestWord(){
        List<String> possibleWord = possibleWords();
        return this.table.biggerStringsScores(this.table.biggersWords(possibleWord));
    }
    
    /**
     * Compare two string and return the bigger of bigger string(s)
     * @param tiles List of tile for know the point
     * @param str1 the first string
     * @param str2 the second string
     * @return return a list with the bigger string or both 
     */
    List<String> winnerWord(String str1, String str2){ 
        List<String> possibleWord = possibleWords();
        List<String> winnerWord = new ArrayList();
        
        if(possibleWord.contains(str1))
            winnerWord.add(str1);
        if(possibleWord.contains(str2))
            winnerWord.add(str2);
        
        winnerWord = this.table.biggersWords(winnerWord);
        
        return winnerWord;
    }
    
    @Override
    public List<Player> getRoundWinners() {
        if(this.state != GameState.ROUND_OVER) 
            throw new IllegalStateException(
                    "The game are not in ROUND_OVER state"
            );
        
        List<Player> winner = new ArrayList();
        List<String> winnerWord = 
        winnerWord(
                this.players.get(0).getProposition(),
                this.players.get(1).getProposition()
        );
        
        if(!winnerWord.isEmpty()&& winnerWord.size()>1 && 
            winnerWord.get(0).equals(winnerWord.get(1)))
            
            winnerWord.remove(0);
        
        for(String word : winnerWord){
           if(this.players.get(0).getProposition().equals(word))
               winner.add(this.players.get(0));
           if(this.players.get(1).getProposition().equals(word))
               winner.add(this.players.get(1));
        }
        
        givePoint(winner);
        if(!this.isGameOver()){
            this.table.removeTile(winnerWord);
            this.table.fillTiles(bag);
        }
        return winner;
    }
    
    private void givePoint(List<Player> winners){
        winners.forEach((p) -> 
                p.addPoint(this.table.wordScore(
                        p.getProposition()
                ))
        );
    }
    
    @Override
    public List<Player> getWinners() {
        List<Player> winners = new ArrayList();
        if(this.players.get(0).getScore() == this.players.get(1).getScore()){
            winners.addAll(this.players);
        }else if(this.players.get(0).getScore()>this.players.get(1).getScore()){
            winners.add(this.players.get(0));
        }else{
            winners.add(this.players.get(1));
        }
        return winners;
    }

    @Override
    public void nextRound() {
        
        if(this.state != GameState.ROUND_OVER) 
            throw new IllegalStateException(
                    "The game are not in ROUND_OVER state"
            );
        
        this.playerPlayed = 0;
        changeState();
        this.players.forEach((player) -> player.resetProposition());
    }

    @Override
    public boolean isRoundOver() {//started to round over
        if(this.playerPlayed == PLAYERNUMBER)
            changeState();
        return this.playerPlayed == PLAYERNUMBER;
    }

    @Override
    public boolean isGameOver() {
        
        boolean isGameOver;
        isGameOver = !this.table.hasNextRound();
        if(isGameOver){
            this.state = GameState.GAME_OVER;
        }
        return isGameOver;
    }
    
    private void changeState(){
        switch(this.state) {
            case CONFIGURE:
                this.state = GameState.ROUND_OVER;
                break;
            case ROUND_OVER:
                this.state = GameState.STARTED;
                break;
            case STARTED:
                this.state = GameState.ROUND_OVER;
                break;
        }
        notification();
    }
    
    @Override
    public void addObserver(Observer ob) {
        this.obs.add(ob);
    }

    @Override
    public void removeObserver(Observer ob) {
        this.obs.remove(ob);
    }

    private void notification(){
        this.obs.forEach((o) -> {
            o.update(this);
        });
    }
    
}
