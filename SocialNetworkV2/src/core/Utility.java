package core;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;


/**
 *
 * @author Lyan
 */
public class Utility{
    
    public static void writeToFile(Map<Integer, Node> map) throws FileNotFoundException, IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/core/data/Person.dat"));
        objectOutputStream.writeObject(map);    
    }
    
    public static Object readFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/core/data/Person.dat"));
        Object object=objectInputStream.readObject();
        return object;
    }

}
