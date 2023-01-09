package workshop03;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class shoppingCart{

    public static void main(String[] args) {
        
        List<String> shoppingList = new LinkedList<>();
        String choice="";
        
        String user="";
        Scanner in = new Scanner(System.in);

        while(!choice.equals("exit")){
            
            System.out.println("""
                    Welcome. Enter:
                    -load "Name"
                    -save "Name"
                    -list
                    -add item, item, item
                    -delete number
                    -deleteMult number, number, number
                    -exit
                    """);

            choice = in.next().trim().toLowerCase();

            switch(choice){
                case "load":{
                    user = in.next();
                    DBhandler.LogIn(user);

                    shoppingList = DBhandler.readList(user);
                    break;
                }

                case "save":{
                    try{
                        DBhandler.saveList(user, shoppingList);
                    } catch (Exception e){

                    }
                    break;
                }

                case "list":{
                    int itemNo = 1;

                    for(String item: shoppingList){
                        System.out.printf("%d. %s\n",itemNo,item);
                        itemNo++;
                    }
                    break;
                }

                case "add":{
                    Scanner itemSplitter = new Scanner(in.nextLine());
                    itemSplitter.useDelimiter(",");
                    
                    while(itemSplitter.hasNext()){
                        String item = itemSplitter.next().trim().toLowerCase();
                        
                        if(shoppingList.contains(item)){
                            System.out.printf("List already contains %s!\n",item);
                        } else{
                            shoppingList.add(item);
                            System.out.printf("%s was added!\n",item);
                        }
                    }

                    break;
                }

                case "deletemult":{

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
                            } else{
                                listToDelete.add(number);
                            }
                        } catch (Exception e){
                            System.out.println("Non-integer entered!");
                        }
                       
                    }

                    //reverse sort
                    Collections.sort(listToDelete,Collections.reverseOrder());

                    System.out.println(listToDelete);

                    //execute deletion
                    for(Integer number:listToDelete){
                        System.out.printf("%s was deleted!\n",shoppingList.get(number-1));
                        shoppingList.remove(number-1);
                    }

                    break;

                }

                case "delete":{
                    
                    int deleteChoice;

                    try{
                        deleteChoice = in.nextInt();
                    } catch (Exception e){
                        System.out.println("Please enter a number\n");
                        in.next();
                        break;
                    }

                    if(deleteChoice <1 || deleteChoice > shoppingList.size()){
                        System.out.println("Choice is out of shopping list range.");
                    } else {
                        System.out.printf("%s deleted!\n",shoppingList.get(deleteChoice-1));
                        shoppingList.remove(deleteChoice-1);
                    }
                    
                    break;
                }

                case "exit":{
                    System.out.println("Bye");
                    break;
                }
                default:{
                    System.out.println("Enter a valid choice");
                    in.nextLine();
                    break;
                }
            }


        }

    }

}