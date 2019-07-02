package be.he2b.atl.chat.view.console;

import charabiacommon.irimia.Tile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g42992
 */
public class TileReader {
    
    private final String fileName;
    
    public TileReader(String fileName){
        if(fileName == null || fileName.isEmpty())
            this.fileName = "Tiles.txt";
        else
            this.fileName = fileName;
    }
    /**
     * read a file Tiles.txt in the root folder
     * and put each line in a tiles list of Tiles
     */
    public List<Tile> readTiles(){
        List<Tile> tiles = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(
                new FileReader(this.fileName))){
            
            char c;
            int val;
            int nb;
            
            String s = br.readLine();
            while(s!= null){
                c = s.charAt(0);
                val = Integer.parseInt(s.substring(2,4));
                nb = Integer.parseInt(s.substring(5));
                for(int i = 0; i<nb; i++)
                    tiles.add(new Tile(c,val));
                s = br.readLine();
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return tiles;
    }
}
