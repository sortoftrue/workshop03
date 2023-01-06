package workshop03;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Test {
    
    public static void main(String[] args) {
        
        List<String> currentList = new LinkedList<>();

        DBhandler.createNew("apple");

        try {
            DBhandler.saveList("apple");
            DBhandler.saveList("apple");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            currentList = DBhandler.readList("apple");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(currentList);

    }

}
