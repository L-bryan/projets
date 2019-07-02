package charabiacommon.message;

import charabiacommon.users.User;

/**
 * The <code> Message </code> represents a message with the profile of a specific
 * user.
 */
public class MessageProfile implements Message {

    String name;
    /**
     * Constructs message with the profile of a specific user. The author of the
     * message give his profile to be send.
     *
     * @param id userID of the author.
     * @param name user name of the author.
     */
    public MessageProfile(String name) {
        this.name = name;
    }

    /**
     * Return the author of the message. The author send his profile.
     *
     * @return the author of the message.
     */
    @Override
    public String getAuthor() {
        return name;
    }

    /**
     * Return the recipient of this message. A profile message is always send to
     * the administrator.
     *
     * @return the recipient of this message.
     */
    @Override
    public String getRecipient() {
        return "";
    }

    /**
     * Return the type of the message, in this case Type.PROFILE.
     *
     * @return the type of the message, in this case Type.PROFILE.
     */
    @Override
    public Type getType() {
        return Type.PROFILE;
    }

    /**
     * Return the content of the message : the author profile.
     *
     * @return the content of the message : the author profile.
     */
    @Override
    public Object getContent() {
        return name;
    }

}
