package workshop03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class DBhandler {
    
    public static void createNew(String name){

        File file = new File("cartdb/%s.txt".formatted(name));

        try {
            if (file.createNewFile()){
                System.out.println("File created");
            } else {
                System.out.println("file exists");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void saveList(String name) throws IOException{

        File file = new File("cartdb/%s.txt".formatted(name));
        String str = "Hello";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
        writer.append(str);
        
        writer.append("\n");
        writer.append("test");

        writer.close();

    }

    public static List readList(String name) throws IOException{

        File file = new File("cartdb/%s.txt".formatted(name));
        List<String> output = new LinkedList();
        String readLine = null;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        while((readLine = reader.readLine())!=null){
            output.add(readLine);
        }

        reader.close();

        return output;
    }

}
