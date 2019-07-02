package charabiacommon.message;

/**
 *
 * @author g42992
 */
public class MessageNewGame implements Message{

    public MessageNewGame() {
    }
    
    @Override
    public Type getType() {
        return Type.NEWGAME;
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
        return null;
    }
    
}
