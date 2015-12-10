import java.util.*;
import java.io.*;

/**
 * This class will store the dictionary in an ArrayList.
 */

public class Dictionary {
    /**
     * This has the dictionary
     */
    public ArrayList<String> wordList;

    /**
     * This is the constructor for the class. It initializes
     * wordList.
     */
    public Dictionary () throws FileNotFoundException {
        //this assumes that the filename for the dictionary is
        //always the same
        File dict = new File("dictionary.txt");
        wordList = new ArrayList<String>();
        
        //Create new dictionary file if it does not exist
        if(!dict.exists()) {
            try {
                dict.createNewFile();
            }
            catch (IOException e) {
                System.out.print("Error: Program was not able to create ");
                System.out.println("a dictionary file.");
            }
        }
        
        //Read dictionary file into wordList        
        Scanner input = new Scanner(new File("dictionary.txt"));
        wordList = new ArrayList<String>();
        while (input.hasNext()){
            wordList.add(input.next());
        }
        input.close();
        
    }

    /**
     * This function will search for a word in wordList, the
     * Arraylist containing the dictionary
     *
     * @param word, the word to look for
     *
     * @return index, the index that the word can be inserted at if
     * the word is not found, or -1 if the word is found. This will
     * allow this function to indicate if the word is in the
     * dictionary or not.
     */
    public int search (String word) {
        int start = 0;
        int end = wordList.size()-1;
        int index = 0;
        int pivot = 0;

        //binary search
        while (start <= end) {
            pivot = start + ((end - start)/2);
            String currentWord = wordList.get(pivot);
            int compare = word.compareToIgnoreCase(currentWord);
            if (compare < 0) {
                end = index = pivot - 1;
            }
            else if (compare > 0) {
                start = index = pivot + 1;
            }
            else {
                return -1;
            }
        }
        //Adjust index if out of bounds
        if (index < 0)
            return 0;
        else
            return index;
    }

    /**
     * This will add a word to wordList.
     *
     * @param word, the word to insert
     */
    public void add (String word) {
        int index = search(word);
        //Check for valid index before adding word to wordList
        if (index != -1) {
            wordList.add(index, word);
        }
    }
    
    /**
     * This will update the file "dictionary.txt" 
     * with the arrayList wordList.
     */
    public void update() {
        try {
            PrintWriter output = new PrintWriter("dictionary.txt");
            int index;
            //Print each word of the arrayList on a new line in "dictionary.txt"
            for(index = 0; index < wordList.size() - 1; index++){
                output.println(wordList.get(index));
            }
            //Print the last word without a new line after
            output.print(wordList.get(index));
            output.close();
        }
        catch (IOException e){
            System.out.print("Failed to generate file.");
        }

    }
    /*
     * Main method for Testing
     * Comment out when adding to program
     */
/*
    public static void main(String args[]){
        try {
            //Create new Dictionary
            Dictionary aDict = new Dictionary();
            
            //Add new words to blank dictionary
            aDict.add("apple");
            aDict.add("bear");
            aDict.add("cat");
            aDict.add("dad");
            aDict.add("dog");
            aDict.add("elephant");
            aDict.add("eyeball");
            
            //Check that arraylist size is correct
            System.out.println("Array size: " + aDict.wordList.size());
            System.out.println("\n" + aDict.wordList.toString());
            //Search for valid word "bear"
            System.out.println( "Search bear returns : " + aDict.search("bear"));
            //Search for invalid word "fly"
            System.out.println( "Search fly returns : " + aDict.search("fly"));
            //Add duplicate word to dictionary
            aDict.add("bear");
            //Add new word to dictionary
            aDict.add("fly");
            System.out.println("\nAdd fly: " + aDict.wordList.toString());
            
            //Update dictionary file
            aDict.update();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found in Main method");
        }
        
        
    }
    
    */
}
