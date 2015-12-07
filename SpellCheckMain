import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by Cody on 12/3/15.
 */
public class SpellCheck {
    private ArrayList<String> misspelledWords;
    private ArrayList<String> addedWords;
    private int counter;
    private Scanner fileReader;
    Dictionary dictionary;

    /**
     * Assuming the "int search(String)" method of dictionary returns -1 for an existing word, this method will
     * determine if a word exists in the dictionary
     * @param inputWord is the single word being analyzed for correctness of spelling (here correctness means having
     *                    a matching word in the dictionary)
     * @return outcome will be true if inputWord is in the dictionary, false if otherwise
     */
    public boolean isWord(String inputWord){
        boolean outcome;
        inputWord = this.removePunct(inputWord);
        if(dictionary.search(inputWord) == -1){
            outcome = true;
        } else {
            outcome = false;
        }
        return outcome;
    }

    /**
     * Removes all punctuation from words in array
     * @param inputWord A single word from the array
     * @return the lower case, punctuation-free word
     */
    private String removePunct(String inputWord){
        String currentWord = inputWord.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        return currentWord;
    }
    
}
