//for the gui
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//for other stuff
import java.io.*;
import java.util.*;


public class SpellCheckerGui extends Frame implements ActionListener {
    //stuff for the gui
    private JFrame myWindow;
    private JPanel fileFinderFrame;
    private JPanel userInteractionFrame;
    private Label inputFileLabel;      // asks the user for a file name
    private JTextField inputFileEntryForm; //entry box for file name
    private Button selectFileButton;   //lets the user select a file
    private Label notificationsLabel;  // lets the user know what's going on
    private Label misspelledWordLabel; // the actual word
    private Button addButton;          //add to dict button
    private Button ignoreButton;       //skip word button

    //stuff for the program
    private ArrayList<String> wordList;
    private ArrayList<String> misspelledWords;
    private ArrayList<String> addedWords;
    private SpellCheck checker;
    private String fileName;
    private Report report;


    //start everything
    public static void main(String[] args) {// throws FileNotFoundException {
        
        SpellCheckerGui myGui = new SpellCheckerGui();
    }

    //the constructor
    public SpellCheckerGui() {
        try {
            checker = new SpellCheck();
        }
        catch (FileNotFoundException fail) {
            System.out.println("Could not start program.");
            System.exit(1);
        }
        addedWords = new ArrayList<String>();
        misspelledWords = new ArrayList<String>();
        fileName = "";
        report = new Report();
        
        
        myWindow = new JFrame();
        myWindow.setLayout(new GridLayout(3,0));
        myWindow.setTitle("Spell Checker");
        myWindow.setSize(500,200);

        fileFinderFrame = new JPanel();
        fileFinderFrame.setLayout(new FlowLayout());
        fileFinderFrame.setSize(500,100);
        
        
        userInteractionFrame = new JPanel();
        userInteractionFrame.setLayout(new GridLayout(2,2));
        userInteractionFrame.setSize(500,100);
        userInteractionFrame.setVisible(false);
        
        //myWindow.getContentPane().setBackground(new Color(17,51,68));
        myWindow.getContentPane().setBackground(new Color(210,105,30));
        myWindow.getContentPane().setForeground(Color.WHITE);
        
        inputFileLabel = new Label("Enter a filename.");
        inputFileEntryForm = new JTextField(20);
        notificationsLabel = new Label("");
        misspelledWordLabel = new Label("");
        
        addButton = new Button("Add");
        addButton.addActionListener(this);
        addButton.setBackground(Color.GRAY);
        
        ignoreButton = new Button("Ignore");
        ignoreButton.addActionListener(this);
        ignoreButton.setBackground(Color.GRAY);

        selectFileButton = new Button("Open");
        selectFileButton.addActionListener(this);
        selectFileButton.setBackground(Color.GRAY);
        
        fileFinderFrame.add(inputFileLabel);
        fileFinderFrame.add(inputFileEntryForm);
        fileFinderFrame.add(selectFileButton);

        myWindow.add(fileFinderFrame);
        myWindow.add(notificationsLabel);
        myWindow.add(userInteractionFrame);
        
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
    }

    public void updateWords(boolean add) {
        //if the user wants to add a word, add it to addedWords and
        //remove it from the list of misspelled words
        if (add) {
            addedWords.add(wordList.get(0));
        }
        else {
            misspelledWords.add(wordList.get(0));
        }
        wordList.remove(0);
        if (wordList.isEmpty()) {
            userInteractionFrame.remove(addButton);
            userInteractionFrame.remove(ignoreButton);
            userInteractionFrame.remove(misspelledWordLabel);
            userInteractionFrame.setVisible(false);
            notificationsLabel.setText("Done Checking.");
            update();
        }
        else {
            misspelledWordLabel.setText(wordList.get(0));
        }
    }

    public void update() {
        checker.updateDictionary(addedWords);
        report.genReport(fileName, addedWords, misspelledWords);
    }
    
    //this does the actions
    @Override
    public void actionPerformed(ActionEvent evt) {
        //command gets the text of the button
        String command = evt.getActionCommand();
        switch (command) {
            // add the current word
        case "Open":
            fileName = inputFileEntryForm.getText();
            wordList = checker.readFile(fileName);
            if (wordList == null) {
                notificationsLabel.setText("File not found.");
                myWindow.add(notificationsLabel);
            }
            else if (wordList.isEmpty()) {
                notificationsLabel.setText("File is empty.");
                myWindow.add(notificationsLabel);
            }
            else {
                notificationsLabel.setText("Misspelled word:");
                misspelledWordLabel.setText(wordList.get(0));
                userInteractionFrame.add(misspelledWordLabel);
                userInteractionFrame.add(new Label(""));
                userInteractionFrame.add(addButton);
                userInteractionFrame.add(ignoreButton);
//                myWindow.add(userInteractionFrame);
                userInteractionFrame.setVisible(true);

            }
            break;
            //ignore the current word
        case "Add":
            updateWords(true);
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
