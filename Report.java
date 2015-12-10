/**
 * @author Matthew McManamon, Cody Morrow, Adam Sinck, and Yao Zhou
 * 
 * This program supports the spell checking java files. It will handle
 * generating the files for the user after the words are processed.
 */

import java.util.ArrayList;
import java.lang.System;
import java.io.*;

public class Report {

    /**
     * This is the constructor for the class. It's the most boring
     * constructor ever written.
     */
    public Report() {}
    
    /**
     * This will take a file name and two arrayLists of words and
     * generate output files for the user based on those two
     * arrayLists.
     * 
     * @param addedWords, an ArrayList of the words that the user
     *        wants added to the dictionary
     * @param misspelledWords, an ArrayList of the words that the user
     *        didn't want to be added to the dictionary
     * 
     */
    public void genReport(String fileName,
                          ArrayList<String> addedWords,
                          ArrayList<String> misspelledWords) {
        
        String addedWordsFilename = fileName + "-added-words";
        String misspelledWordsFilename = fileName + "-ignored-words";
        
        createFile(addedWordsFilename,addedWords);
        
        createFile(misspelledWordsFilename,misspelledWords);
    }

    /**
     * This will take a filename and an arraylist of words and create a file
     * with the words in the arraylist.
     *
     * @param fileName: the base of the name of the file to be created
     * @param words: the ArrayList of the words to put in the file
     */
    public void createFile(String fileName, ArrayList<String> words) {
        if (words.size() > 0) {
            try {
                String name = fileName;
                name = name.replaceAll(".txt", "");
                name += "-" + System.currentTimeMillis() + ".txt";
                PrintWriter output = new PrintWriter(name);
                int index;
                for(index = 0; index < words.size() - 1; index++){
                    output.println(words.get(index));
                }
                //Print the last word without a new line after
                output.print(words.get(index));
                output.close();
            }
            catch (IOException e) {
                System.out.println("Failed to generate output file.");
            }
        }
        else {
            String name = fileName;
            name = name.replaceAll(".txt", "");
            name += "-" + System.currentTimeMillis() + ".txt";
            File myFile = new File(name);
            if(!myFile.exists()) {
                try {
                    myFile.createNewFile();
                }
                catch (IOException e) {
                    System.out.println("Failed to generate output file.");
                }
            }
        }
    }
}
