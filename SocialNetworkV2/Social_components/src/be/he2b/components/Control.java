package be.he2b.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author dylanlevymorini
 */
public interface Control {
    
    /**
     * Accept a potential match
     * @param handler 
     */
    void accept(EventHandler<ActionEvent> handler);
    
    /**
     * Decline a potential match
     * @param handler 
     */
    void decline(EventHandler<ActionEvent> handler);
    void clear();
    
}
