import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
  the layout will look like:
  ______________________
  |  input file?       |
  |  [_______]         |
  |  *ms word:         |
  |   *asdfasdf        |
  |   *[keep] [ignore] |
  |____________________|

  * = hidden as needed
a future version might look like:
  ___________________________________
  |            |  input file?       |
  |   user's   |  [_______]         |
  |  document  |  *ms word: (3/21)  |
  |            |   *asdfasdf        |
  |            |   *[keep] [ignore] |
  |____________|____________________|

*/


public class SpellCheckerGui extends Frame implements ActionListener {
    private Label inputFileLabel;  // asks the user for a file name
    private JTextField inputFileEntryForm; //entry box for file name
    private Label misspelledWordTitleLabel; // "misspelled word here"
    private Label misspelledWordLabel; // the actual word
    private Button addButton;    //add to dict button
    private Button ignoreButton; //skip word button
    private Button closeButton;  //close program button

    //start everything
    public static void main(String[] args) {
        SpellCheckerGui myGui = new SpellCheckerGui();
    }

    //the constructor
    public SpellCheckerGui() {
        JFrame myWindow = new JFrame("other");
        myWindow.setLayout(new FlowLayout());
        myWindow.setTitle("Spell Checker");
        myWindow.setSize(500,200);
        myWindow.getContentPane().setBackground(new Color(17,51,68));
        myWindow.getContentPane().setForeground(Color.WHITE);
        //getContentPane().setBackground(Color.ORANGE);
        
        inputFileLabel = new Label("Enter a filename.");
        inputFileEntryForm = new JTextField(20);
        misspelledWordTitleLabel = new Label("Current Word:");
        //this should be updated by the program
        misspelledWordLabel = new Label(""); 
        addButton = new Button("Add");
        addButton.addActionListener(this);
        addButton.setBackground(Color.GRAY);
        ignoreButton = new Button("Ignore");
        ignoreButton.addActionListener(this);
        ignoreButton.setBackground(Color.GRAY);
        
        myWindow.add(inputFileLabel);
        myWindow.add(inputFileEntryForm);
        myWindow.add(misspelledWordTitleLabel);
        myWindow.add(misspelledWordLabel);
        myWindow.add(addButton);
        myWindow.add(ignoreButton);
        
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
    }

    //this does the actions
    @Override
    public void actionPerformed(ActionEvent evt) {
        //command gets the text of the button
        String command = evt.getActionCommand();
        switch (command) {
        // add the current word
        case "Add":
            String s = inputFileEntryForm.getText();
            misspelledWordLabel.setText(s);
            break;
        //ignore the current word
        case "Ignore":
            misspelledWordLabel.setText("Word ignored.");
            break;
        default:
            break;
        }
    }
}
