package workshop03;

import java.io.Console;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Socket socket = new Socket("localhost",3000);

        Console cons = System.console();
        boolean isExit = false;

        while(isExit != true){
            String input = cons.readLine("Message?");
            
            switch (input){
                case "exit":{
                    isExit = true;
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeUTF(input);
                    oos.flush();
                    break;
                }
                default:{
                    //SEND
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    
                    oos.writeUTF(input);
                    oos.flush();
                    break;
                }
            }
            
            if(isExit) break;
            
            //READ-IN
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String received = ois.readUTF();
            System.out.println(received);
        }
        
    }

}
