package workshop03;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CartServer {
    
    private ServerSocket server;
    private Socket socket;

    public void openServer(){

        try {
            server = new ServerSocket(3000);
            System.out.println("Waiting");
            socket = server.accept();
            System.out.println("Connected");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String readInput(){

        System.out.println("Wait read");
        
        String input=null;
        //READ-IN
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            input = ois.readUTF();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return input;

    }

}
