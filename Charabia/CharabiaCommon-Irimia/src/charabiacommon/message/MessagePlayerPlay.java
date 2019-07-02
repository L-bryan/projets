package charabiacommon.message;

import charabiacommon.irimia.Player;

/**
 *
 * @author g42992
 */
public class MessagePlayerPlay implements Message{
    
    Player playerPlay;
    
    public MessagePlayerPlay(Player player) {
        playerPlay = player;
    }
    
    @Override
    public Type getType() {
        return Type.PLAYERPLAY;
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
        return this.playerPlay;
    }
    
}
