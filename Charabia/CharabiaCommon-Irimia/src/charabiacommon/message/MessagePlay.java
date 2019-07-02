package charabiacommon.message;

import charabiacommon.users.User;

/**
 *
 * @author g42992
 */
public class MessagePlay implements Message{
    
    String word;
    String name;

    public MessagePlay(String name,String word){
        this.word = word;
        this.name = name;
    }
    
    @Override
    public Type getType() {
        return Type.PLAY;
    }

    @Override
    public String getAuthor() {
        return name;
    }

    @Override
    public String getRecipient() {
        return null;
    }

    @Override
    public Object getContent() {
        return word;
    }
    
}
