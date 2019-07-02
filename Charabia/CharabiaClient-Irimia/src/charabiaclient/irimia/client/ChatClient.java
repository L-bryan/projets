package charabiaclient.irimia.client;

import charabiacommon.message.Message;
import charabiacommon.message.MessageProfile;
import charabiacommon.message.Type;
import java.io.IOException;

/**
 * The <code> ChatClient </code> contains all the methods necessary to set up a
 * Instant messaging client.
 */
public class ChatClient extends AbstractClient {


    /**
     * Constructs the client. Opens the connection with the server. Sends the
     * user name inside a <code> MessageProfile </code> to the server. Builds an
     * empty list of users.
     *
     * @param host the server's host name.
     * @param port the port number.
     * @param name the name of the user.
     * @param password the password needed to connect.
     * @throws IOException if an I/O error occurs when opening.
     */
    public ChatClient(String host, int port, String name, String password) throws IOException {
        super(host, port);
        openConnection();
        updateName(name);
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        Message message = (Message) msg;
        Type type = message.getType();
        switch (type) {
            case PLAYERPLAY:
                //reçoie le Player qui on joué
                showMessage(message);
                break;
            case REFRESH:
                showMessage(message);
                break;
            case NEWGAME:
                showMessage(message);
                break;
            case TRICHE:
                showMessage(message);
                break;
            case TRICHEON:
                showMessage(message);
                break;
            case TRICHEOFF:
                showMessage(message);
                break;
            default:
                throw new IllegalArgumentException("Message type unknown " + type);
        }
    }
    

    /**
     * Quits the client and closes all aspects of the connection to the server.
     *
     * @throws IOException if an I/O error occurs when closing.
     */
    public void quit() throws IOException {
        closeConnection();
    }

    void showMessage(Message message) {
        notifyChange(message);
    }

    private void updateName(String name) throws IOException {
        sendToServer(new MessageProfile(name));
    }

    private void notifyChange() {
        setChanged();
        notifyObservers();
    }

    private void notifyChange(Message message) {
        setChanged();
        notifyObservers(message);
    }

    @Override
    protected void connectionException(Exception exception) {
        exception.printStackTrace();
    }

}
