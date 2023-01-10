package workshop03;

import java.io.IOException;
import java.net.SocketException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class shoppingCartMain{

    public static void main(String[] args) {
        
        //Setup Server
        String dbPath = args[0];
        int port = Integer.parseInt(args[1]);
        CartServer server = new CartServer();
        server.openServer(port);

        List<String> shoppingList = new LinkedList<>();
        String input;
        

        while(true){
            server.waitConn();
            String user="";
            String choice="";

            while(!choice.equals("exit")){
            
                String output="";
                try{
                    input = server.readInput();
                } catch (IOException e){
                    System.out.println("Connection lost");
                    break;
                }
                Scanner in = new Scanner(input);
                choice = in.next().trim().toLowerCase();
                
    
                // System.out.println("""
                //         Welcome. Enter:
                //         -load "Name"
                //         -save "Name"
                //         -list
                //         -add item, item, item
                //         -delete number
                //         -deleteMult number, number, number
                //         -exit
                //         """);
    
                switch(choice){
                    case "listcarts":{
                        server.sendOutput(DBhandler.listFiles(dbPath));
                        break;
                    }
                    
                    case "load":{
                        System.out.println("Loading user");
                        user = in.next();
                        //user = "Fred";
                        DBhandler.LogIn(dbPath,user);
    
                        shoppingList = DBhandler.readList(dbPath,user);
    
                        server.sendOutput("Loaded");
    
                        break;
                    }
    
                    case "save":{
                        if (user.equals("")){
                            server.sendOutput("No cart loaded!");
                            break;
                        }
                        try{
                            DBhandler.saveList(dbPath, user, shoppingList);
                        } catch (Exception e){
    
                        }
                        server.sendOutput("Cart saved to " + user);
                        break;
                    }
    
                    case "list":{
                        int itemNo = 1;
    
                        for(String item: shoppingList){
                            System.out.printf("%d. %s\n",itemNo,item);
                            output += String.format("%d. %s\n",itemNo,item);
                            itemNo++;
                        }
                        server.sendOutput(output);
                        break;
                    }
    
                    case "add":{
                        Scanner itemSplitter = new Scanner(in.nextLine());
                        itemSplitter.useDelimiter(",");
    
                        while(itemSplitter.hasNext()){
                            String item = itemSplitter.next().trim().toLowerCase();
                            
                            if(shoppingList.contains(item)){
                                System.out.printf("List already contains %s!\n",item);
                                output += String.format("List already contains %s!\n",item);
                            } else{
                                shoppingList.add(item);
                                System.out.printf("%s was added!\n",item);
                                output += String.format("%s was added!\n",item);
                            }
                        }
    
                        server.sendOutput(output);
                        break;
                    }
    
                    case "delete":{
    
                        Scanner numberSplitter = new Scanner(in.nextLine());
                        numberSplitter.useDelimiter(",");
                        List<Integer> listToDelete = new LinkedList<>();
    
                        //store numbers in list
                        while(numberSplitter.hasNext()){
                            Integer number; 
                            
                            try{
                                number = Integer.parseInt(numberSplitter.next().trim());
                                if(number<1 || number>shoppingList.size()){
                                    System.out.printf("%d out of bounds!\n",number);
                                    output += String.format("%d out of bounds!\n",number);
                                } else{
                                    listToDelete.add(number);
                                }
                            } catch (Exception e){
                                System.out.println("Non-integer entered!");
                                output += String.format("Non-integer entered!");
                            }
                           
                        }
    
                        //reverse sort
                        Collections.sort(listToDelete,Collections.reverseOrder());
    
                        System.out.println(listToDelete);
    
                        //execute deletion
                        for(Integer number:listToDelete){
                            System.out.printf("%s was deleted!\n",shoppingList.get(number-1));
                            output += String.format("%s was deleted!\n",shoppingList.get(number-1));
                            shoppingList.remove(number-1);
                        }
    
                        server.sendOutput(output);
                        break;
    
                    }
    
                    // case "delete":{
                        
                    //     int deleteChoice;
    
                    //     try{
                    //         deleteChoice = in.nextInt();
                    //     } catch (Exception e){
                    //         System.out.println("Please enter a number\n");
                    //         in.next();
                    //         break;
                    //     }
    
                    //     if(deleteChoice <1 || deleteChoice > shoppingList.size()){
                    //         System.out.println("Choice is out of shopping list range.");
                    //     } else {
                    //         System.out.printf("%s deleted!\n",shoppingList.get(deleteChoice-1));
                    //         shoppingList.remove(deleteChoice-1);
                    //     }
                        
                    //     break;
                    // }
    
                    case "exit":{
                        System.out.println("Bye");
                        break;
                    }
                    default:{
                        System.out.println("Enter a valid choice");
                        server.sendOutput("Invalid choice!");
                        try{
                            in.nextLine();
                        }catch (Exception e){};
                        break;
                    }
                }
    
    
            }


        }
        
        

    }

}