package workshop03;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Test {
    
    public static void main(String[] args) {

        String output = "output";
        String input;
        String choice ="";

        CartServer server = new CartServer();

        server.openServer(3000);

        while(!choice.equals("exit")){

            input = server.readInput();

            if(input.equals("exit")) break;

            System.out.println(input);

            server.sendOutput("return message");
        }
        
    }

}
