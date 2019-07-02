package charabiaclient.irimia.client;

import charabiacommon.users.Refresh;
import charabiacommon.message.Message;
import charabiacommon.message.Type;
import charabiacommon.irimia.GameState;
import charabiacommon.irimia.Player;
import charabiacommon.irimia.Tile;
import charabiacommon.message.MessageNewGame;
import charabiacommon.message.MessagePlay;
import charabiacommon.message.MessageTriche;
import charabiacommon.message.MessageTricheOff;
import charabiacommon.message.MessageTricheOn;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author g42992
 */
public class CharabiaProxy implements Observer, charabiacommon.irimia.Observable {
    
    private ChatClient client;
    private final List<charabiacommon.irimia.Observer> obs;
    
    public CharabiaProxy(){
        obs = new ArrayList<>();
        playerPlay = new ArrayList<>();
        tiles = null;
        bestWord = null;
        winnerRound = null;
        winnerGamer = null;
        tricher = new ArrayList<>();
    }

    public void join(String ip, int port, String name){
        try {
            client = new ChatClient(ip, port, name, "");
            client.addObserver(this);
        } catch (IOException ex) {
            Logger.getLogger(CharabiaProxy.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void play(String name, String word){
        try {
            
            client.sendToServer(new MessagePlay(name,word));
        } catch (IOException ex) {
            Logger.getLogger(CharabiaProxy.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendWord(String w){
        try {
            client.sendToServer(new MessageTriche(w));
        } catch (IOException ex) {
            Logger.getLogger(CharabiaProxy.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendTricher(String p){
        try {
            client.sendToServer(new MessageTricheOn(p));
        } catch (IOException ex) {
            Logger.getLogger(CharabiaProxy.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendTricherNo(String p){
        try {
            client.sendToServer(new MessageTricheOff(p));
        } catch (IOException ex) {
            Logger.getLogger(CharabiaProxy.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //DATA
    public boolean wait = true;
    private List<Player> playerPlay;
    private List<Tile> tiles;
    private List<Player> players;
    private GameState state;
    private List<String> bestWord;
    private List<Player> winnerRound;
    private List<Player> winnerGamer;
    private String word;
    private List<String> tricher;
    
    
    
    //CONNECTION
    
    public String getWord() {
        return word;
    }
    
    public List<Player> getPlayers() {
        return players;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<String> getBestWords() {
        return bestWord;
    }


    public List<Player> getRoundWinners() {
        return winnerRound;
    }

    public List<Player> getWinners() {
        return winnerGamer;
    }
    
    public GameState getState() {
        return state;
    }

    public List<Player> getPlayerPlay() {
        return playerPlay;
    }
    
    public List<String> getTricher(){
        return tricher;
    }
    
    public void newGame() {
        try {
            client.sendToServer(new MessageNewGame());
        } catch (IOException ex) {
            Logger.getLogger(CharabiaProxy.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            Message message = (Message) arg;
            switch (message.getType()) {
                case REFRESH:
                    Refresh re = (Refresh) message.getContent();
                    if(re.getTiles() != null){
                        tiles = re.getTiles();
                    }   players = re.getPlayers();
                    state = re.getState();
                    if(re.getBestWord() != null){
                        bestWord = re.getBestWord();
                    }   if(re.getWinnerRound() != null){
                        winnerRound = re.getWinnerRound();
                    }   if(re.getWinnerGamer() != null){
                        winnerGamer = re.getWinnerGamer();
                    }   wait = false;
                    break;
                case PLAYERPLAY:
                    if(playerPlay.size() == 2){
                        playerPlay.clear();
                    }   playerPlay.add((Player)message.getContent());
                    break;
                case NEWGAME:
                    wait = true;
                    playerPlay = null;
                    tiles = null;
                    players = null;
                    state = null;
                    bestWord = null;
                    winnerRound = null;
                    winnerGamer = null;
                    break;
                case TRICHE:
                    word = (String) message.getContent();
                case TRICHEON:
                    tricher.add((String) message.getContent());
                    break;
                case TRICHEOFF:
                    tricher.remove((String) message.getContent());
                    break;
                default:
                    break;
            }
            notification();
        }
    }

    @Override
    public void addObserver(charabiacommon.irimia.Observer ob) {
        this.obs.add(ob);
    }

    @Override
    public void removeObserver(charabiacommon.irimia.Observer ob) {
        this.obs.remove(ob);
    }
    
    private void notification(){
        this.obs.forEach((o) -> {
            o.update(this);
        });
    }
}
