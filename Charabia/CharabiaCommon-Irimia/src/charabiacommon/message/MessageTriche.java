package charabiacommon.message;

/**
 *
 * @author g42992
 */
public class MessageTriche implements Message{
    
    String word;
    
    public MessageTriche(String word){
        this.word = word;
    }
    
    @Override
    public Type getType() {
        return Type.TRICHE;
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
        return word;
    }
    
}
