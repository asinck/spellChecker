import java.io.*;
import java.util.*;

/**
 * @author a b c d I need to fill in comments.
 */
public class SpellCheck {
    private Dictionary myDictionary;
    
/*
    public static void main(String[]args){
        SpellCheckerGui myGui = new SpellCheckerGui();
    }
*/
    /**
       * This is the constructor for the class.
     */
    public SpellCheck() throws FileNotFoundException {
        myDictionary = new Dictionary();
    }

    public void updateDictionary(ArrayList wordList) {
        for (int index = 0; index < wordList.size(); index++) {
            myDictionary.add((String) wordList.get(index));
        }
        myDictionary.update();
    }
    /**
     * This accepts an input filename, and iterates through all the words
     * in the file, adding them to the misspelledWords arraylist as
     * necessary.
     *
     * @param filename, the name of the file to be checked
     * 
     * @return misspelledWords, the list of words in the file that were not
     * spelled correctly.
     */
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> words = new ArrayList<String>();
        try {
            
            Scanner fileReader = new Scanner(new File(fileName));
            while(fileReader.hasNext()){
                String currentWord = fileReader.next();
                currentWord = removePunct(currentWord);
                currentWord = currentWord.toLowerCase();
                if (!isWord(currentWord) && currentWord.length() > 0) {
                    words.add(currentWord);
                }
            }
            fileReader.close();
        }
        catch (FileNotFoundException exception) {
            words = null;
        }
        
        ArrayList<String> misspelledWords = new ArrayList<String>();
        if (words == null) {
            misspelledWords = null;
        }
        else if (words.size() > 0) {
            Collections.sort(words);
            misspelledWords.add(words.get(0));
            for (int index = 0; index < words.size(); index++) {
                int size = misspelledWords.size()-1;
                String previousWord = misspelledWords.get(size);
                String currentWord = words.get(index);
                if (! previousWord.equals(currentWord)) {
                    misspelledWords.add(currentWord);
                }
                
            }
        }
        return misspelledWords;
    }

    /**
     * Assuming the "int search(String)" method of dictionary returns
     * -1 for an existing word, this method will determine if a word
     * exists in the dictionary
     * 
     * @param inputWord is the single word being analyzed for
     *        correctness of spelling (here correctness means having a
     *        matching word in the dictionary)
     * 
     * @return outcome will be true if inputWord is in the dictionary,
     *         false otherwise
     */
    public boolean isWord (String inputWord) {
        return (myDictionary.search(inputWord) == -1);
    }

    /**
     * This removes all punctuation from the input word
     * 
     * @param inputWord, a word to remove punctuation from
     * 
     * @return the punctuation-free word
     */
    private static String removePunct(String inputWord){
        return inputWord.replaceAll("[^a-zA-Z-' ]", "");
    }

}
