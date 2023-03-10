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
    
    public static void LogIn(String dbPath, String name){

        File file = new File("%s/%s.txt".formatted(dbPath,name));

        try {
            if (file.createNewFile()){
                System.out.println("Cart created. Cart loaded");
            } else {
                System.out.println("Cart exists. Cart loaded");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String listFiles(String dbPath){
        String output="";

        File cartPath = new File("%s".formatted(dbPath));
        File[] files = cartPath.listFiles();

        for(File file: files){
            output+=file.getName() + "\n";
        }

        return output;
    }

    public static void saveList(String dbPath, String name, List<String> textToAdd) throws IOException{

        File file = new File("%s/%s.txt".formatted(dbPath,name));
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        
        for(String item:textToAdd){
            writer.append(item+"\n");
        }
        
        writer.close();

    }

    public static List<String> readList(String dbPath, String name){

        File file = new File("%s/%s.txt".formatted(dbPath,name));
        List<String> output = new LinkedList();
        String readLine = null;


        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while ((readLine = reader.readLine()) != null) {
                output.add(readLine);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return output;
    }

}
