/**
 * @author Matthew McManamon, Cody Morrow, Adam Sinck, and Yao Zhou
 * 
 * This program will store the dictionary in an ArrayList.
 * 
 * This program will open the file "dictionary.txt" and put all the
 * words in the file into the wordList. This program implements
 * functions for a binary search of the dictionary, adding a word into
 * the dictionary such that the dictionary remains in sorted order,
 * and a function for updating the dictionary file.
 */

import java.util.*;
import java.io.*;

public class Dictionary {
    //this will have the dictionary
    public ArrayList<String> wordList;

    /**
     * This is the constructor for the class. It initializes
     * wordList.
     */
    public Dictionary() throws FileNotFoundException {
        //this assumes that the filename for the dictionary is
        //always the same
        File dict = new File("dictionary.txt");
        wordList = new ArrayList<String>();
        
        //create new dictionary file if it does not exist
        if(!dict.exists()) {
            try {
                dict.createNewFile();
            }
            catch (IOException e) {
                System.out.print("Error: Program was not able to create ");
                System.out.println("a dictionary file.");
            }
        }
        
        //read dictionary file into wordList
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
    public int search(String word) {
        int start = 0;
        int end = wordList.size() - 1;
        int index = 0;
        int pivot = 0;
        
        //binary search
        while (start <= end) {
            pivot = start + ((end - start) / 2);
            String currentWord = wordList.get(pivot);
            int compare = word.compareTo(currentWord);
            if (compare < 0) {
                end = pivot - 1;
                index = pivot;
            }
            else if (compare > 0) {
                start = index = pivot + 1;
            }
            else {
                index = -1;
                start = end + 1;
            }
        }
        return index;
    }

    /**
     * This will add a word to wordList.
     *
     * @param word, the word to insert
     */
    public void add(String word) {
        int index = search(word);
        //Check for valid index before adding word to wordList
        if (index != -1) {
            wordList.add(index, word);
        }
    }
    
    /**
     * This will update the file "dictionary.txt" with the arrayList
     * wordList.
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
}
