package charabiacommon.message;

import charabiacommon.users.Refresh;
import charabiacommon.users.User;

/**
 *
 * @author g42992
 */
public class MessageRefresh implements Message{

    private final Refresh obj;

    public MessageRefresh(Refresh obj) {
        this.obj = obj;
    }
    
    @Override
    public Type getType() {
        return Type.REFRESH;
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
        return obj;
    }
    
}
