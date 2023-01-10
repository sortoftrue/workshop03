package workshop03;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CartServer {
    
    private ServerSocket server;
    private Socket socket;

    public void openServer(int port){

        try {
            server = new ServerSocket(port);
            // System.out.println("Waiting");
            // socket = server.accept();
            // System.out.println("Connected");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void waitConn(){

        try {
            // server = new ServerSocket(3000);
            System.out.println("Waiting");
            socket = server.accept();
            System.out.println("Connected");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String readInput() throws IOException{

        System.out.println("Wait read");
        
        String input=null;
        //READ-IN
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            input = ois.readUTF();
    

        return input;

    }

    public void sendOutput(String output){
            try {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                //input=input.toUpperCase();

                oos.writeUTF(output);
                oos.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

}
