/**
 * @author Matthew McManamon, Cody Morrow, Adam Sinck, and Yao Zhou
 * 
 * This program will allow the user to input a file and have the
 * spelling checked.
 *
 * This program will take a file name from the user and check the
 * spelling of all the words in the file, checking against
 * dictionary.txt. Once the program has checked the spelling of all
 * the words, it will present all the misspelled words to the user,
 * one by one, to either add the word to the dictionary or confirm
 * that the word was misspelled. Having finished that, the program
 * will generate two files: a list of the words added to the
 * dictionary, and a list of the words that the user didn't want added
 * to the dictionary.
 * If the user wants to, they can check multiple files.
 */

//for the gui
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//for other stuff
import java.io.*;
import java.util.*;


public class SpellCheckerGui extends Frame implements ActionListener {
    //stuff for the gui
    private JFrame myWindow;            //some frames
    private JPanel fileFinderFrame;     //some frames
    private JPanel userInteractionFrame;//some frames
    private Label inputFileLabel;       // asks the user for a file name
    private JTextField inputFileEntryForm; //entry box for file name
    private Button selectFileButton;    //lets the user select a file
    private Label notificationsLabel;   //lets the user know what's going on
    private Label misspelledWordLabel;  //the actual word
    private Button addButton;           //add to dict button
    private Button ignoreButton;        //skip word button

    //stuff for the program

    //these are ArrayLists of words; first, a list of words that were
    //not in the dictionary file, then a list of words that were
    //actually misspelled, and finally a list of words to add to the
    //dictionary. 
    private ArrayList<String> wordList;
    private ArrayList<String> misspelledWords;
    private ArrayList<String> addedWords;

    //other variables
    private SpellCheck checker;
    private String fileName;
    private Report report;

    /**
     * This is the main method of the program, go figure. It starts
     * everything. 
     */
    public static void main(String[] args) {
        SpellCheckerGui myGui = new SpellCheckerGui();
    }

    /**
     * This is the constructor for the program. It is what a) gets the
     * gui going, and b) gets everything else going.
     */
    public SpellCheckerGui() {
        //try to start the main support program. If it fails, that's
        //the end of that.
        try {
            checker = new SpellCheck();
        }
        catch (FileNotFoundException fail) {
            System.out.println("Could not start program.");
            System.exit(1);
        }

        //initialize the stuff it's going to need later
        addedWords = new ArrayList<String>();
        misspelledWords = new ArrayList<String>();
        fileName = "";
        report = new Report();
        
        //now for some fun... GUI by hand

        //myWindow is the top level Frame. It holds all the other gui
        //elements. 
        myWindow = new JFrame();
        myWindow.setLayout(new GridLayout(3,0));
        myWindow.setTitle("Spell Checker");
        myWindow.setSize(500,200);

        //fileFinderFrame is a second level frame. It holds the gui
        //elements that relate to opening the file. 
        fileFinderFrame = new JPanel();
        fileFinderFrame.setLayout(new FlowLayout());
        fileFinderFrame.setSize(500,100);
        
        //userInteractionFrame is a second level frame. It holds the
        //gui elements that relate to user interaction.
        userInteractionFrame = new JPanel();
        userInteractionFrame.setLayout(new GridLayout(2,2));
        userInteractionFrame.setSize(500,100);
        userInteractionFrame.setVisible(false);
        
        //some styling
        myWindow.getContentPane().setBackground(new Color(0,0,102));
        myWindow.getContentPane().setForeground(Color.WHITE);

        //some gui elements
        inputFileLabel = new Label("Enter a filename.");
        inputFileEntryForm = new JTextField(20);
        notificationsLabel = new Label("");
        misspelledWordLabel = new Label("");
        
        //a button for adding the current word to the dictionary
        addButton = new Button("Add");
        addButton.addActionListener(this);
        addButton.setBackground(Color.GRAY);

        //a button for confirming that the current word is spelled
        //incorrectly
        ignoreButton = new Button("Ignore");
        ignoreButton.addActionListener(this);
        ignoreButton.setBackground(Color.GRAY);

        //a button for selecting a file
        selectFileButton = new Button("Open");
        selectFileButton.addActionListener(this);
        selectFileButton.setBackground(Color.GRAY);

        //adding relevent elements to the frame
        fileFinderFrame.add(inputFileLabel);
        fileFinderFrame.add(inputFileEntryForm);
        fileFinderFrame.add(selectFileButton);

        //adding relevent elements to the frame
        userInteractionFrame.add(misspelledWordLabel);
        userInteractionFrame.add(new Label(""));
        userInteractionFrame.add(addButton);
        userInteractionFrame.add(ignoreButton);

        //adding everything to the top level frame. Note that there is
        //a label in the middle.
        myWindow.add(fileFinderFrame);
        myWindow.add(notificationsLabel);
        myWindow.add(userInteractionFrame);

        //yes, when the user hits the little (x) in the corner, they
        //actually want the program to close
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //yeah, we want the program to be visible. otherwise it's a
        //GUI without graphics or a user interface...
        myWindow.setVisible(true);
    }

    /**
     * This takes the first word in the wordList arraylist and adds it
     * to either the addedWords or misspelledWords ArrayList.
     *
     * @param add, a flag to let the function know if the word is
     *        being added or ignored
     */
    public void updateWords(boolean add) {
        //if the user wants to add a word, add it to addedWords and
        //remove it from the list of misspelled words
        if (add) {
            addedWords.add(wordList.get(0));
        }
        else {
            misspelledWords.add(wordList.get(0));
        }
        //remove the word that was just processed
        wordList.remove(0);

        //if all the words have been taken care of, then repack and update
        if (wordList.isEmpty()) {
            //repack a bunch of stuff
            myWindow.remove(notificationsLabel);
            myWindow.remove(userInteractionFrame);
            myWindow.remove(fileFinderFrame);
            
            myWindow.add(fileFinderFrame);
            myWindow.add(notificationsLabel);
            myWindow.add(userInteractionFrame);
            
            fileFinderFrame.setVisible(true);
            userInteractionFrame.setVisible(false);
                
            notificationsLabel.setText("Done Checking.");
            
            //take care of updates
            update();
            misspelledWords.clear();
            addedWords.clear();
        }
        //otherwise, if there are some more words, show the user the
        //next word
        else {
            misspelledWordLabel.setText(wordList.get(0));
        }
    }

    /**
     * This will open the user's file.
     */
    public void openFile() {
        fileName = inputFileEntryForm.getText();
        wordList = checker.readFile(fileName);
        if (wordList == null) {
            notificationsLabel.setText("File not found.");
            myWindow.add(notificationsLabel);
        }
        else if (wordList.isEmpty()) {
            notificationsLabel.setText("No misspelled words.");
            myWindow.add(notificationsLabel);
        }
        else {
            notificationsLabel.setText("Misspelled word:");
            misspelledWordLabel.setText(wordList.get(0));

            myWindow.remove(notificationsLabel);
            myWindow.remove(userInteractionFrame);
            myWindow.remove(fileFinderFrame);
                
            myWindow.add(fileFinderFrame);
            myWindow.add(notificationsLabel);
            myWindow.add(userInteractionFrame);
                
            fileFinderFrame.setVisible(false);
            userInteractionFrame.setVisible(true);
                
        }
    }
    /**
     * This updates the dictionary and generates the output files.
     */
    public void update() {
        checker.updateDictionary(addedWords);
        report.genReport(fileName, addedWords, misspelledWords);
    }
    
    /**
     * This decides what happens when buttons are pressed.
     * 
     * @param evt, the event that just occurred
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        //command gets the text of the button
        String command = evt.getActionCommand();
        switch (command) {
            // add the current word
        case "Open":
            openFile();
            break;
            //ignore the current word
        case "Add":
            updateWords(true);
            notificationsLabel.setText("Word added.");
            break;    
        case "Ignore":
            updateWords(false);
            notificationsLabel.setText("Word ignored.");
            break;
        default:
            break;
        }
    }
}
