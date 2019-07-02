package charabiacommon.irimia;

/**
 *
 * @author g42992
 */
public interface Observable {
    public void addObserver(Observer ob);
    public void removeObserver(Observer ob);
}
