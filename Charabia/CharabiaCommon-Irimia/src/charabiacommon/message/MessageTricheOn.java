package charabiacommon.message;

/**
 *
 * @author g42992
 */
public class MessageTricheOn implements Message{
    
    String player;

    public MessageTricheOn(String player) {
        this.player = player;
    }
    
    
    @Override
    public Type getType() {
        return Type.TRICHEON;
    }

    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public String getRecipient() {
        return null;
    }

    @Override
    public Object getContent() {
        return player;
    }
    
}
