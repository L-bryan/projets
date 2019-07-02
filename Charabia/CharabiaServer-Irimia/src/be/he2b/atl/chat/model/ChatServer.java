package be.he2b.atl.chat.model;

import charabiacommon.message.*;
import charabiacommon.users.*;
import be.he2b.atl.chat.view.console.DictionaryReader;
import be.he2b.atl.chat.view.console.TileReader;
import charabiacommon.message.Type;
import be.he2b.atl.server.AbstractServer;
import be.he2b.atl.server.ConnectionToClient;
import charabiacommon.irimia.CharabiaInterface;
import charabiacommon.irimia.Player;
import g42992.charabia.model.Charabia;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The <code> ChatServer </code> contains all the methods necessary to set up a
 * Instant messaging server.
 */
public class ChatServer extends AbstractServer {
    
    private CharabiaInterface game;
    //private static final int PORT = 12345;
    static final String ID_MAPINFO = "ID";
    private BufferedWriter bw;

    private static InetAddress getLocalAddress() {
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while (b.hasMoreElements()) {
                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
                    if (f.getAddress().isSiteLocalAddress()) {
                        return f.getAddress();
                    }
                }
            }
        } catch (SocketException e) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, "NetworkInterface error", e);
        }
        return null;
    }

    private int clientId;
    

    /**
     * Constructs the server. Build a thread to listen connection request.
     *
     * @throws IOException if an I/O error occurs when creating the server
     * socket.
     */
    public ChatServer(int port, String logfile) throws IOException {
        super(port);
        bw = new BufferedWriter(new FileWriter(logfile));
        game = new Charabia(
                new DictionaryReader(null).readDictionary(),
                new TileReader(null).readTiles()
        );
        this.listen();
    }


    /**
     * Return the server IP address.
     *
     * @return the server IP address.
     */
    public String getIP() {
        if (getLocalAddress() == null) {
            return "Unknown";
        }
        return getLocalAddress().getHostAddress();
    }

    /**
     * Return the number of connected users.
     *
     * @return the number of connected users.
     */
    public int getNbConnected() {
        return getNumberOfClients();
    }

    /**
     * Quits the server and closes all aspects of the connection to clients.
     *
     * @throws IOException
     */
    public void quit() throws IOException {
        this.stopListening();
        this.close();
    }

    /**
     * Return the next client id.
     *
     * @return the next client id.
     */
    final synchronized int getNextId() {
        clientId++;
        return clientId;
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Message message = (Message) msg;
        Type type = message.getType();
        switch (type) {
            case PROFILE:
                try {
                    bw.append(new Timestamp(System.currentTimeMillis())
                            .toString() + "le joueur "+message.getAuthor()
                            +"a rejoin la partie");
                } catch (IOException ex) {
                    System.err.println(ex.getCause());
                }
                
                Player player = game.joinGame(message.getAuthor());
                client.setPlayer(player);
                if(game.isPlayersReady()){
                    
                    try {
                        bw.append(new Timestamp(System.currentTimeMillis())
                            .toString() + "la partie commence");
                    } catch (IOException ex) {
                        System.err.println(ex.getCause());
                    }
                    
                    game.nextRound();
                    sendToAllClients(
                        new MessageRefresh(
                                new Refresh(
                                        game.getTiles(), game.getPlayers(),
                                        game.getState(), true,
                                        null, null, null
                                )
                        )
                    );
                }else{
                    sendToAllClients(
                        new MessageRefresh(
                                new Refresh(
                                        null, game.getPlayers(),
                                        game.getState(), false,
                                        null, null, null
                                )
                        )
                    );
                }
                break;
            case PLAY:
                try {
                    bw.append(new Timestamp(System.currentTimeMillis())
                            .toString() + "le joueur "+client.getName()
                            +"a jouer le mot "+(String)message.getContent());
                } catch (IOException ex) {
                    System.err.println(ex.getCause());
                }
                
                game.play(client.getPlayer(),(String) message.getContent());
                //envoie le Player pour savoir qu'il a joué
                sendToAllClients(new MessagePlayerPlay(client.getPlayer()));
                
                if(game.isRoundOver()){
                    
                    try {
                        bw.append(new Timestamp(System.currentTimeMillis())
                            .toString() + "fin de la manche");
                    } catch (IOException ex) {
                        System.err.println(ex.getCause());
                    }
                    
                    //envoie des gagants et des meilleurs mots
                    sendToAllClients(new MessageRefresh(new Refresh(
                        null, game.getPlayers(), game.getState(),
                        true,game.getBestWords(), game.getRoundWinners(), null))
                    );

                    if(game.getRoundWinners().isEmpty()){
                        game.resetAllTiles();
                    }

                    if(!game.isGameOver()){
                        game.nextRound();
                        game.getTiles();
                        
                        sendToAllClients(new MessageRefresh(new Refresh(
                            game.getTiles(), game.getPlayers(), game.getState(),
                            true,null, null, null))
                        );
                        
                    }else{
                        try {
                            bw.append(new Timestamp(System.currentTimeMillis())
                            .toString() + "fin de la partie");
                            bw.close();
                        } catch (IOException ex) {
                        System.err.println(ex.getCause());
                        }
                        //envoie la fin du jeu
                        sendToAllClients(new MessageRefresh(new Refresh(
                            null, game.getPlayers(), game.getState(),
                            true, null, null,
                                game.getWinners()))
                        );
                    }
                }else{
                    System.out.println("attendre un joueur");
                }

                break;
            case NEWGAME:
                //reçoie une demande de nouveau jeu ou de quit
                game = new Charabia(
                        new DictionaryReader(null).readDictionary(),
                        new TileReader(null).readTiles()
                );
                System.out.println("nouveau jeu");
                sendToAllClients(new MessageNewGame());
                break;
            case TRICHE:
                sendToAllClients(message);
                break;
            case TRICHEON:
                sendToAllClients(message);
                break;
            case TRICHEOFF:
                sendToAllClients(message);
                break;
            default:
                throw new IllegalArgumentException("Message type unknown " + type);
        }
        setChanged();
        notifyObservers(message);
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        super.clientConnected(client);
        client.setInfo("player", null);
        //sendToAllClients();
        setChanged();
        notifyObservers();
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {

    }

    @Override
    protected synchronized void clientException(ConnectionToClient client, Throwable exception) {
        exception.printStackTrace();
    }

    void sendToClient(Message message, User recipient) {
        sendToClient(message, recipient.getId());
    }

    void sendToClient(Message message, int clientId) {

    }
}
