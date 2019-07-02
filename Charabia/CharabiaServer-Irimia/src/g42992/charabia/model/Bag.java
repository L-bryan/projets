package g42992.charabia.model;

import java.util.Collections;
import java.util.List;
import charabiacommon.irimia.Tile;

/**
 * Class Bag
 * @author g42992
 */
class Bag {
    private final List<Tile> tiles;
    
    /**
     * create a bag with Tile
     */
    Bag(List<Tile> tiles){
       this.tiles = tiles;
       Collections.shuffle(tiles);
    }
    
    Tile tileDraw(){
        Tile t = this.tiles.get(tiles.size()-1);
        this.tiles.remove(t);
        return t;
    }
    
    void removeTile(List<Tile> tilesToRemove){
        this.tiles.removeAll(tilesToRemove);
    }
    
    /**
     * get the size of tiles list
     * @return int size of the list
     */
    int getSize(){
        return this.tiles.size();
    }
}
