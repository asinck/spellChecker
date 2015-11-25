/**
 * @author Matthew McManamon, Cody Morrow, Adam Sinck, and Yao
 * Zhou
 * 
 * This program will do spell checking on a user's input file.
 * 
 *     This program is a gui; first it will read the dictionary,
 * and then ask the user for a file to check. It will then go
 * through the input file, and for each word, it will remove the
 * punctuatin and see if that word is in the dictionary. If the
 * word is not in the dictionary, it will add it to an arraylist
 * to keep track of it. After checking the file, the program will
 * present all the misspelled words to the user in alphabetical
 * order. For each word, the user can either ignore the word or
 * tell the program to add the word to the dictionary. After the
 * user makes a decision for each word, the program will generate
 * two files, and update the dictionary. One of the two new files
 * will contain the words that were added to the dictionary; the
 * other will have the words that were not added.
 */
public class SpellChecker {
    
    private ArrayList misspelledWord;
    private ArrayList addedWords;
    private int counter;
    private String currentWord;
    private Scanner fileReader;
    /*
    //we may need this for the gui's interaction with the
    //misspelledWords Arraylist
    private int misspelledWordsIndex;
    */

    /**
     * This will check to see if the current word is actually a
     * word according to the dictionary.
     * 
     * @param word, the input word.
     * 
     * @return returnValue, whether or not it is a word
     */
    public boolean isWord (String word) {
        boolean returnValue;
    }

    /**
     * This will remove all characters from the input string that
     * are not either a letter, number, a hyphen, or an
     * apostrophe.
     * 
     * @param: inputString, the string to be processed
     * 
     * @return: a string with unacceptable characters removed
     */
    public String removePunct (String inputString) {
        currentWord = inputString.replaceAll("[^a-zA-Z'-]", "");
        //these are the two alternatives for how to handle case
        //differences
        //string.compareToIgnoreCase(otherword)
        //currentWord = currentWord.toLowerCase();
    }
}


/**
 * This class will store the dictionary in an ArrayList.
 */
class Dictionary {

    /**
     * This has the dictionary
     */
    ArrayList wordList;

    /**
     * This is the constructor for the class. It initializes
     * wordList.
     */
    public Dictionary () {
        //this assumes that the filename for the dictionary is
        //always the same
        /* for each word in the dictionary {
               add that word to wordList
           }
         */
    }
    
    /**
     * this function will search for a word in wordList, the
     * Arraylist containing the dictionary
     * 
     * @param word, the word to look for
     * 
     * @return index, the index that the word can be inserted at if
     * the word is not found, or -1 if the word is found. This will
     * allow this function to also indicate if the word is in the
     * dictionary or not.
    */
    public int search (String word) {
        
    }

    /**
     * This will add a word to wordList.
     * 
     * @param word, the word to insert
    */
    public void add (String word) {
        
    }
}


/**
  * This class will handle generating the files for the user after
  * the words are processed and updating the dictionary.
*/
public class Report {
    String fileName;

    /**
      * This will take a fileName, a list of words to add to the
      * dictionary and an "addedwords" file, and a list of words to
      * add to the "rejectedwords" file. This will also update the
      * dictionary file .txt file. 
      *
      * @param fileName, the name of the new files
      * @param addedWords, an ArrayList of the words that the user
      *        wants added to the dictionary
      * @param misspelledWords, an ArrayList of the words that the
      *        user didn't want to be added to the dictionary
    */
    public void genReport (String fileName, ArrayList addedWords,
                           ArrayList misspelledWords) {

    }

    /**
      * This will take a filename and an arraylist of words and
      * create a file with the words in the arraylist.
      *
      * @param fileName, the name of the file to be created
      * @param words, the ArrayList of the words to put in the file
    */
    public void createFile (String fileName, ArrayList words) {
        
    }
}
