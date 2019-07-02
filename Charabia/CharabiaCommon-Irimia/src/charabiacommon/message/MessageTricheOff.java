package charabiacommon.message;

/**
 *
 * @author g42992
 */
public class MessageTricheOff implements Message{
    
    String player;

    public MessageTricheOff(String player) {
        this.player = player;
    }
    
    
    
    @Override
    public Type getType() {
        return Type.TRICHEOFF;
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
