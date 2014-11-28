import java.util.HashMap;

/**
 * Created by Jung on 2014-11-11.
 */
public class Main {
    public static void main(String[] args) {
        HashTable<Integer, Integer> h = new HashTable<Integer, Integer>();


        for(int i=0; i<10;i++){
            h.insert(i,i);
        }
        for(int i=0; i<10;i++){
            h.remove(i);
        }


        h.printHashTable();

    }
}
