package be.he2b.esi.network;

import be.he2b.esi.db.LinkDB;
import be.he2b.esi.dto.LinkDto;
import be.he2b.esi.exception.DbException;
import be.he2b.esi.specification.LinkSpecification;
import java.util.List;

/**
 * Class LinkNetworkLogic
 * @author Bryan & Dylan
 */
public class LinkNetworkLogic {
    
    public static int add(LinkDto link) throws DbException {
        return LinkDB.insertDb(link);
    }
     
    public static List<LinkDto> getLinks() throws DbException {
        LinkSpecification sel = new LinkSpecification(0);
        List<LinkDto> col = LinkDB.getCollection(sel);
        return col;
    }
    
    public static int getLinkid(LinkDto link) throws DbException {
        return LinkDB.getLinkid(link);
    }
    
    public static void update(LinkDto link) throws DbException {
        LinkDB.update(getLinkid(link));
    }
    
    public static void remove(LinkDto link) throws DbException {
        LinkDB.remove(getLinkid(link));
    }
}
