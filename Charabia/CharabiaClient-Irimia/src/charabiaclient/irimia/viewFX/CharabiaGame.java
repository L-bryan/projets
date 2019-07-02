package charabiaclient.irimia.viewFX;

import charabiaclient.irimia.client.CharabiaProxy;
import charabiacommon.irimia.GameState;
import charabiacommon.irimia.Player;
import charabiacommon.irimia.Observer;
import com.sun.webkit.dom.EventImpl;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author g42992
 */
public class CharabiaGame extends Stage implements Observer{
    
    private final JoinScene join;
    private final WaitScene wait;
    private PlayScene play;
    private CharabiaProxy game;
    private final RestartGame restart;
    private String player;
    private String pColor;
    
    public CharabiaGame(){
        
        join = new JoinScene(new JoinGame(), 600,500);
        wait = new WaitScene(new WaitPlayer(), 800, 575);
        play = new PlayScene(new PlayGame(), 1485, 575);
        restart = new RestartGame(new Restart(), 300, 300);
        game = new CharabiaProxy();
        //this.game = game;
        play.setGame(game);
        game.addObserver(this);
        
        this.setScene(join);
        setProprietise();
    }
    private void setProprietise(){
        this.join.getButton().addEventHandler(
                ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                CharabiaGame.this.player = CharabiaGame.this.join.register();
                game.join(join.getIp(), Integer.parseInt(join.getPort()), player);
                
                CharabiaGame.this.pColor = CharabiaGame.this.join.playerColor();
                while(game.wait){
                    System.out.println("wait");
                }
                paintPlayer();
                setPlayer();
                CharabiaGame.this.setScene(wait);
                CharabiaGame.this.removeEventHandler(ActionEvent.ACTION, this);
                
                CharabiaGame.this.addEventHandler(
                        EventType.ROOT, new EventHandler<Event>(){
                    @Override
                    public void handle(Event event) {
                        if(game.getState()== GameState.STARTED){
                            CharabiaGame.this.setScene(play);
                            CharabiaGame.this
                                .removeEventHandler(EventType.ROOT, this);
                        }
                    }
                });
            }
        });
        setEvent();
    }
    
    private void setPlayer(){
       play.setPlayer(player);
    }
    
    private void paintPlayer(){
        String p = game.getPlayers().get(0).getName();
        if(p.equals(player)){
            play.getPlayGame().getPlayer2()
                .setFill(Paint.valueOf(pColor));
        }else{
            play.getPlayGame().getPlayer1()
                .setFill(Paint.valueOf(pColor));
        }
    }
    
    private void refreshHistorical(){
        if(game.getState()== GameState.ROUND_OVER){
            List<Player> roundWiner = game.getRoundWinners();
            for(Player p : game.getPlayers()){
                if(!p.getProposition().isEmpty()){
                    play.getPlayGame()
                        .addWord(p,p.getProposition(),true,
                        roundWiner.contains(p));
                }
            }
                
            for(String word : game.getBestWords()){
                play.getPlayGame()
                    .addWord(null, word, false, false);
            }
        }
    }
    
    private void reset(){
        getPlayScene().getPlayGame()
            .getInput().setDisable(false);
        getPlayScene().getPlayGame()
            .getSubmit().setDisable(false);
        getPlayScene().getPlayGame()
            .j1ToggleHasPlay(false);
        getPlayScene().getPlayGame()
            .j2ToggleHasPlay(false);
        getPlayScene().getPlayGame()
            .getInput().setText("");
    }
    
    private void endgame(){
        List<Player> winers = game.getWinners();
        if(winers.size()>1){
            restart.getRestart().setWiners(
                "les gagants sont "
                +winers.get(0).getName()
                +" & "
                +winers.get(1).getName()           
            );
        }else{
            restart.getRestart().setWiners(
                "le gagant est "
                +winers.get(0).getName()
            );
        }
        restart.getRestart().setRestartEvent((e) -> {
            game.newGame();
            play = new PlayScene(new PlayGame(), 1485, 575);
            play.setGame(game);
            setScene(join);
            setProprietise();
           
        });
        setScene(restart);
    }
    
    private void playerHasPlay(){
        if(game.getPlayerPlay() != null){
            for(Player p : game.getPlayerPlay()){
                if(p.getName().equals(player)){
                    play.getPlayGame().getInput().setDisable(true);
                    play.getPlayGame().getSubmit().setDisable(true);
                }
                String[] player1 = play.getPlayGame().getPlayer1().getText()
                        .split(" ");//care I put 3 spaces before so name start at[3]
                if(p.getName().equals(player1[3]) ){
                    getPlayScene().getPlayGame().j1ToggleHasPlay(true);
                }else{
                    getPlayScene().getPlayGame().j2ToggleHasPlay(true);
                }
            }
        }
    }
    
    
    private void setEvent(){
        EventHandler<KeyEvent> keyEventHandler = (KeyEvent keyEvent) -> {
            game.sendWord(play.getPlayGame().getWord());
        };
        play.getPlayGame().getInput().setOnKeyPressed(keyEventHandler);
        play.getPlayGame().getCheckBox().addEventHandler(ActionEvent.ACTION, (event)->{
            if(play.getPlayGame().getCheckBox().isSelected()){
                game.sendTricher(player);
            }else{
                game.sendTricherNo(player);
            }   
        });
        play.getPlayGame().getSubmit().addEventHandler(ActionEvent.ACTION, (event)->{
            game.play(player,play.getPlayGame().getWord());
        });
    }
    
    public PlayScene getPlayScene(){
        return play;
    }

    @Override
    public void update(Object o) {
        Platform.runLater(
                ()->{
            if(game.getState()== GameState.STARTED){
                play.update(o);
                playerHasPlay();
            }
            if(game.getState() == GameState.ROUND_OVER){
                refreshHistorical();
            }
            if(game.getPlayerPlay()!= null && 
                    game.getPlayerPlay().size() == 2){
                reset();
            }
            if(game.getState() == GameState.GAME_OVER){
                endgame();
            }
            setCheatWord();
            setAntiCheat();
        });
    }
    
    private void setAntiCheat(){
        play.getPlayGame().addTricher(game.getTricher());

    }
    private void setCheatWord(){
        play.getPlayGame().setWord(game.getWord());
    }
    /*
    private void newGame() {
        //new game
        Charabia game2 =  new Charabia(
            new DictionaryReader(null).readDictionary(),
            new TileReader(null).readTiles()
        );
        for(CharabiaGame g : stageList){
            g.play = new PlayScene(new PlayGame(), 1485, 575);
            g.game = game2;
            g.play.setGame(game2);
            g.game.addObserver(g);
            g.game.joinGame(g.player);
            g.paintPlayer();
            g.setPlayer();
            g.setScene(g.play);
            g.setEvent();
            if( g.game.isPlayersReady()){
                if(game.getState()== GameState.ROUND_OVER){
                    game.nextRound();
                }
            }
        }
    }*/
}
