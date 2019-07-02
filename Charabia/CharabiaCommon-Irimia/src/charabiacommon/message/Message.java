package charabiacommon.message;

import java.io.Serializable;

/**
 * The <code> Message </code> represents a general message send to a user.
 */
public interface Message extends Serializable {

    /**
     * Return the message type.
     *
     * @return the message type.
     */
    Type getType();

    /**
     * Return the message author.
     *
     * @return the message author.
     */
    String getAuthor();

    /**
     * Return the message recipient.
     *
     * @return the message recipient.
     */
    String getRecipient();

    /**
     * Return the message content.
     *
     * @return the message content.
     */
    Object getContent();

}
