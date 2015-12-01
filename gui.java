import java.awt.*;
import java.awt.event.*;
//import java.awt.Frame;

public class gui extends Frame implements ActionListener {
    private int currentNumber = 1;
    private Label label;
    private Button btn;
    private Button closeButton;
    public static void main(String[] args) {
        gui muGui = new gui();
    }

    public gui() {
        setLayout(new FlowLayout());
        
/*        Panel panel = new Panel();
        Button btn = new Button("press here");
        panel.add(btn);*/

        label = new Label("number: 1");
        add(label);
        btn = new Button("press here");
        btn.addActionListener(this);
        add(btn);

        setTitle("Test gui program");
        setSize(200,100);
        setVisible(true);

        closeButton = new Button("Close");
        //closeButton.addActionListener(new CloseListener());
        closeButton.addActionListener(this);
        add(closeButton);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
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
// class CloseListener implements ActionListener {
//     @Override
//     public void actionPerformed(ActionEvent e) {
//         System.exit(0);
//     }
// }
