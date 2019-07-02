package g42992.charabia.model;

import java.util.List;

/**
 * Class Dictionary
 * @author g42992
 */
class Dictionary {
    private final List<String> words;
    
    /**
     * create a dictionary Object with a List of String
     */
    Dictionary(List<String> words){
        this.words = words;
    }
    
    /**
     * get all dictionary word in a list
     * @return List of string
     */
    List<String> getAllWords() {
        return words;
    }
}
