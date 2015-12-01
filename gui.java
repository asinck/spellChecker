import java.awt.*;
import java.awt.event.*;


public class gui extends Frame implements ActionListener {
    private int currentNumber = 1;
    private Label label;
    private Button btn;
    private Button closeButton;

    //start everything
    public static void main(String[] args) {
        gui muGui = new gui();
    }

    //the constructor
    public gui() {
        setLayout(new FlowLayout());

        label = new Label("number: 1");
        add(label);
        btn = new Button("press here");
        btn.addActionListener(this);
        add(btn);

        setTitle("Test gui program");
        setSize(200,100);
        setVisible(true);

        closeButton = new Button("Close");
        closeButton.addActionListener(this);
        add(closeButton);
    }

    //this does the actions
    @Override
    public void actionPerformed(ActionEvent evt) {
        //command gets the text of the button
        String command = evt.getActionCommand();
        if (command.equals("Close")) {
            System.exit(0);
        }
        else {
            currentNumber *= 2;
            label.setText("number: " + currentNumber);
        }
    }


}
