package be.he2b.atl.chat.view.console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g42992
 */
public class DictionaryReader {
    
    private final String fileName;
    
    public DictionaryReader(String fileName){
        if(fileName == null || fileName.isEmpty())
            this.fileName = "Dictionary.txt";
        else
            this.fileName = fileName;
    }
    /**
     * read a file Dictionary.txt in the root folder
     * and put each line in a words list of string
     */
    public List<String> readDictionary(){
        List<String> dic = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(
            new FileReader(this.fileName))){
            
            String s = br.readLine();
            while(s!= null){
                dic.add(s);
                s = br.readLine();
            }
            
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return dic;
    }
}
