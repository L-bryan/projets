package g42992.charabia.model;

import java.util.ArrayList;
import java.util.List;
import charabiacommon.irimia.Tile;
/**
 * Class Table
 * @author g42992
 */
class Table {
    private final List<Tile> tiles;
    private final int TILESNUMBER = 10;
    
    /**
     * Create a table with a bag and a Tiles list
     */
    Table(Bag bag){
        this.tiles = new ArrayList<>();
        this.fillTiles(bag);
    }
    
    /**
     * Get the list of Tiles (TILESNUMBER)
     * it's the 10 tiles select 'randomly'
     * @return the tiles list
     */
    List<Tile> getTiles(){
        return new ArrayList(tiles);
    }
    
    /**
     * there is a next round if the table have 10 Tile
     * @return true if table have 10 Tile else return false
     */
    boolean hasNextRound(){
        return this.tiles.size()>=TILESNUMBER;
    }
    
    
    void fillTiles(Bag bag){
        while(bag.getSize()>0 && this.tiles.size() != TILESNUMBER){
            this.tiles.add(bag.tileDraw());
        }
    }
    
    /**
     * remove the letter use by the winnersWords
     * @param winnersWords a list of winnersWords
     * @return à int the number of Tiles remove
     */
    int removeTile(List<String> winnersWords){
        int count = 0;
        List<Tile> toRemove = new ArrayList();
        String word = fusion(winnersWords);
        if(word != null){
            for(int i = 0; i<word.length(); i++){
                    for(Tile t : this.tiles){
                        if(t.getLetter() == word.charAt(i)){
                            if(!toRemove.contains(t)){
                                toRemove.add(t);
                                break;
                            }
                        }
                    }
                }
            for(Tile t : toRemove){
                count++;
                this.tiles.remove(t);
            }
        }
        return count;
    }
    
    /**
     * remove the letter use by the winnersWords
     * @param winnersWords a list of winnersWords
     * @return à int the number of Tiles remove
     */
    void removeAllTile(){
        this.tiles.removeAll(tiles);
    }
    
    /**
     * make a string fusion with all string in winnerWords list
     * without duplicate letter.
     * exemple: s1 = "abc", s2= "abcc" -> result = "abcc"
     * @param winnerWords List of string
     * @return a string fusion of all string
     */
    private String fusion(List<String> winnerWords){
        if(winnerWords.size()>1){
            for(int i = 0; i<winnerWords.get(0).length();i++){
                for(int j = 0; j<winnerWords.get(1).length();j++){
                    if(
                        winnerWords.get(0).charAt(i) == 
                        winnerWords.get(1).charAt(j)
                    ){
                        String newString = 
                                winnerWords.get(1).substring(0,j)+'0'+
                                winnerWords.get(1).substring(j+1);
                        winnerWords.remove(1);
                        winnerWords.add(1,newString);
                        break;
                    }
                }
            }
            return winnerWords.get(0)+winnerWords.get(1);
        }else if(winnerWords.size()==1){
            return winnerWords.get(0);
        }else{
            return null;
        }
    }
    
    
    /**
     * Get the bigger words in a list
     * @param words is the list of string
     * @return the bigger strings
     */
    List<String> biggersWords(List<String> words){
        
        List<String> biggersWords = new ArrayList();
        String biggerWord = null;
        
        if(!words.isEmpty())
            biggerWord = words.get(0);
        for(String word : words){
            if(word.length() == biggerWord.length()){
                biggersWords.add(word);
            }else if(word.length() > biggerWord.length()){
                biggersWords.clear();
                biggersWords.add(word);
                biggerWord = word;
            }
        }
        return biggersWords;
    }
    
    /**
     * get the point value of a string
     * @param word String we want know the point
     * @param tiles List of tile for know the point
     * @return int sum of each tiles in the string
     */
    int wordScore(String word){
        List<Tile> cpTiles = new ArrayList<>(this.tiles);
        cpTiles.sort((Tile a , Tile b) -> b.getValue() - a.getValue());
        int score = 0;
        for(int i = 0 ; i<word.length();i++){
            for(Tile t : cpTiles){
                if( word.charAt(i)== t.getLetter() ){
                    score += t.getValue();
                    cpTiles.remove(t);
                    break;
                }
            }
        }
        return score;
    }
    
    /**
     * give a list of string with the hight score
     * @param biggersWords String list of big word (same lenght)
     * @param tiles List of tile for know the point
     * @return String list of all bigger and higher score
     */
    List<String> biggerStringsScores(List<String> biggerWords){
        
        List<String> biggerStringsScores = new ArrayList();
        int biggerScore = 0;
        int score;
                
        if(!biggerWords.isEmpty()){
            biggerScore = wordScore(biggerWords.get(0));
        }
        
        for(String word : biggerWords){
            score = wordScore(word);
            if(score == biggerScore){
                biggerStringsScores.add(word);
            }else if(score > biggerScore){
                biggerStringsScores.clear();
                biggerStringsScores.add(word);
                biggerScore = score;
            }
        }
        return biggerStringsScores;
    }
    
    
    /**
     * check if the string can be make with this Tile list
     * @param str String we want check
     * @return return true if you can make it else return false
     */
    boolean isPossible(String str){
        List<Tile> lt = new ArrayList<>(this.tiles);
        boolean isPossible = false;
        for(int i = 0 ; i<str.length() ; i++){
            if(lt.isEmpty()) isPossible = false;
            for(Tile t : lt){
                isPossible = str.charAt(i) == t.getLetter();
                if(isPossible){
                    lt.remove(t);
                    break;
                }
            }
            if(!isPossible)
                break;
        }
        return isPossible;
    }
    
}
