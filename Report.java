import java.util.ArrayList;
import java.io.*;

/**
 * This class will handle generating the files for the user after the words are
 * processed.
 */
public class Report {
    /*        
    String fileName;

    /**
     * This is the constructor
     * @param name : the name that requires user to type in 
     *                      
     * /
    
    public Report(String name){
        fileName = name;
    }
    */        
    public Report() {
        
    }
    /**
     * This will take a list of words to add to the dictionary and
     * an "addedwords" file, and a list of words to add to the "rejectedwords"
     * file.
     *
     * @param addedWords: an ArrayList of the words that the user
     * wants added to the dictionary
     * 
     * @param misspelledWords: an ArrayList of the words that the
     * user didn't want to be added to the dictionary
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
     * @param fileName: the name of the file to be created
     * @param words: the ArrayList of the words to put in the file
     */
    public void createFile(String fileName, ArrayList<String> words) {
        try {
            String name = fileName;
            int begin = fileName.length() - 5;
            int end = fileName.length()-1;
            String tail = fileName.substring(begin, end);
            if (tail.equals(".txt")) {
                name = fileName.substring(0, begin);
            }
            PrintWriter output = new PrintWriter(fileName + ".txt");
            int index;
            //Print each word of the arrayList on a new line in "dictionary.txt"
            for(index = 0; index < words.size() - 1; index++){
                output.println(words.get(index));
            }
            //Print the last word without a new line after
            output.print(words.get(index));
            output.close();
        }
        catch (IOException e) {
            System.out.print("Fail to generate file.");
        }
    }
        
    /**
     * This is the test main method, delete this method when use Report Class in spellChecker project
     * 
     */
/*        
    public static void main(String args[]){
        ArrayList<String> testAdd = new ArrayList<String>();
        testAdd.add("A");
        testAdd.add("B");
        testAdd.add("C");
                
        ArrayList<String> testRej = new ArrayList<String>();
        testRej.add("Aa");
        testRej.add("Bb");
        testRej.add("cC");
                
        Report testRE = new Report("testReport");
        testRE.genReport(testAdd,testRej);
        //testRE.createFile("test",testAL);
        System.out.print("Files have been generated successfully!");
    }
*/
}
