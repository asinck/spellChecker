import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;// File;

/**
 * This class will store the dictionary in an ArrayList.
 */
public class Dictionary {

    /**
     * This has the dictionary
     */

	private ArrayList<String> wordList = new ArrayList<String>();

    /**
     * This is the constructor for the class. It initializes
     * wordList.
     */
    public Dictionary () throws FileNotFoundException {
        //this assumes that the filename for the dictionary is
        //always the same
        /* for each word in the dictionary {
           add that word to wordList
           }
        */

        //Create new dictionary file if it does not exist
        File dict = new File("dictionary.txt");
        if(!dict.exists()) {
            try {
                dict.createNewFile();
            }
            catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
            catch (IOException e) {
                System.out.println("Program Error.");
            }
        }

        //Read dictionary file into wordList
        Scanner input = new Scanner(new File("dictionary.txt"));
        while (input.hasNextLine()){
            wordList.add(input.next());
        }
        input.close();
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
		int start = 0;
		int end = wordList.size();
		int index = 0;

		//binary search
		while(start <= end){
			int pivot = start + ((end - start)/2);

			if (word.compareTo(wordList.get(pivot)) < 0){
				end = pivot;
				//keep track of return index position
				index = pivot - 1;
			}
			else if (word.compareTo(wordList.get(pivot)) > 0){
				start = pivot;
				//keep track of return index position
				index = pivot + 1;
			}
			else
				return -1;
		}

		return index;
    }

    /**
     * This will add a word to wordList.
     *
     * @param word, the word to insert
    */
    public void add (String word) {
        int index = this.search(word);
        //Check for valid index before adding word to wordList
        if (index != -1)
        	wordList.add(index, word);
    }
}
