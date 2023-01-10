package workshop03;

import java.io.Console;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMain {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Scanner in = new Scanner(args[0]);
        in.useDelimiter("@");
        String user = in.next();
        String remain = in.next();
        in = new Scanner(remain);
        in.useDelimiter(":");
        String host = in.next();
        int port = Integer.parseInt(in.next());

        System.out.println(user);
        System.out.println(host);
        System.out.println(port);

        Socket socket = new Socket(host,port);

        Console cons = System.console();
        boolean isExit = false;

        while(isExit != true){
            String input = cons.readLine("""
                        Welcome. Enter:
                        -listcarts <Lists existing carts>
                        -load "Name" <Load cart, or creates a new cart>
                        -save <Save current cart>
                        -list <Lists item in current cart>
                        -add item, item, item..
                        -delete number, number, number..
                        -exit
                        """);
            
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
            
            
            
            //READ-IN
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String received = ois.readUTF();
            System.out.println(received);

            if(isExit) break;
        }
        
    }

}
