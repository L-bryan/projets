package be.he2b.atl.chat.view.console;

import be.he2b.atl.chat.model.ChatServer;
import charabiacommon.message.Message;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The <code> ChatServerConsole </code> contains all the methods necessary view
 * in console mode the instant messaging client side.
 */
public class ChatServerConsole implements Observer {

    /**
     * Entry points to the instant messaging server side.
     *
     * @param args no arguments needed.
     */
    public static void main(String[] args) {
        try {
            int port = 12345;
            String logfile = "log";
            if(args.length != 0){
               port = Integer.parseInt(args[0]);
               if(args.length > 1){
                   logfile = args[1];
               }
            }
            ChatServer model = new ChatServer(port,logfile);
            ChatServerConsole console = new ChatServerConsole(model);
            model.addObserver(console);
            System.out.println("Server started");
            System.out.println("");
        } catch (IOException ex) {
            Logger.getLogger(ChatServerConsole.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }

    private final ChatServer model;

    /**
     * Constructs the console view. Subscribes to the instant messaging server.
     *
     * @param model instant messaging server.
     */
    public ChatServerConsole(ChatServer model) {
        this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {
        updateUser();
        if (arg != null) {
            Message message = (Message) arg;
            updateMessage(message);
        }

    }

    private void updateUser() {/*
        System.out.println("");
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Liste Users ---- ----\n");
        builder.append("Nombre d'utilisateurs connectes : ")
                .append(model.getNbConnected()).append("\n");
        builder.append("ID").append("\t");
        builder.append("IP").append("\t\t");
        builder.append("NAME").append("\n");
        for (User member : model.getMembers()) {
            builder.append(member.getId()).append("\t");
            builder.append(member.getAddress()).append("\t");
            builder.append(member.getName()).append("\n");
        }
        System.out.println(builder);
        System.out.println("");*/
    }

    private void updateMessage(Message message) {/*
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Message recu ---- ----\n");
        builder.append(LocalDateTime.now()).append(" \n");
        builder.append("Type : ").append(message.getType()).append("\n");
        builder.append("De : ").append(message.getAuthor()).append("\t");
        builder.append("Pour : ").append(message.getRecipient()).append("\n");
        builder.append("Contenu\t").append(message.getContent());
        builder.append("\n");
        System.out.println(builder);*/
    }
}
