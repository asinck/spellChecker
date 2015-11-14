//Adam Sinck
//Hash
//This program lets the user play with a hash table.

import java.util.*;

public class Hash {
    public static final Scanner keyboard = new Scanner( System.in );
    public static void main(String [] args) {

        //this is the table
        Map<String, String> words = new HashMap<String, String>();
        int option = 1;
        
        //while the user wants to continue
        while (option != 0) {

            //output the menu and see what they want to do
            printMenu();
            System.out.println("Please select an option. (1..6 or 0)  ");
            option = getInt();
            System.out.println("");

            String word;

            switch (option) {
            //insert a word
            case 1:
                System.out.println("Insert what word?");
                word = keyboard.next();
                if (!words.containsKey(word.toLowerCase())) {
                    words.put(word.toLowerCase(), word.toLowerCase());
                    System.out.println("Word inserted.");
                }
                else {
                    System.out.println("Word already in table.");
                }
                break;
            //delete a word
            case 2:
                System.out.println("Delete what word?");
                word = keyboard.next();
                if (words.containsKey(word.toLowerCase())) {
                    words.remove(word.toLowerCase());
                    System.out.println("Word deleted.");
                }
                else {
                    System.out.println("Word not in table.");
                }
                break;
            //find a word
            case 3:
                System.out.println("Find what word?");
                word = keyboard.next();
                if (words.containsKey(word.toLowerCase())) {
                    System.out.println(words.get(word.toLowerCase()));
                }
                else {
                    System.out.println("Word not in table.");
                }
                break;
            //get the size of the table
            case 4:
                System.out.println("There are " + words.size() + " words in the table.");
                break;
            //output in form "[key1=value1, key2=value2, ...]", but unsorted
            case 5:
                System.out.println(words.entrySet());
                break;
            //output in form "[value1, value2, ...]", but unsorted
            case 6:
                System.out.println(words.values());
                break;
            //invalid option
            default:
                if (option != 0) {
                    System.out.println("Invalid option.");
                }
                break;
            }
            if (option != 0) {
                System.out.println("\nEnter anything to continue.  ");
                String s = keyboard.next();
                System.out.println("");
            }
            
        }
            
    }

    //This outputs the menu for the user
    public static void printMenu() {
        System.out.println("______________________________________________________________________");
        System.out.println("Main Menu _/");
        System.out.println("---------/");
        System.out.println("");
        System.out.println("1 - insert word");
        System.out.println("2 - delete word");
        System.out.println("3 - check for an word");
        System.out.println("4 - get size of table");
        System.out.println("5 - output words (set)");
        System.out.println("6 - output words (collection)");
        System.out.println("");
        System.out.println("0 - quit");
        System.out.println("");
    }

    //this gets an integer input from the user
    public static int getInt() {
        String choice = keyboard.next();
        int output = -1;
        boolean done = false;
        while (!done) {
            try {
                output = Integer.parseInt(choice);
                done = true;
            }
            catch (Exception e) {
                System.out.println("Enter a number.  ");
                choice = keyboard.next();
            }
        }
        return output;
    }
}
