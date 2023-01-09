package workshop03;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Test {
    
    public static void main(String[] args) {
        

        String output = "output";
        String input;

        CartServer server = new CartServer();

        server.openServer();

        input = server.readInput();

        System.out.println(input);
    }

}
