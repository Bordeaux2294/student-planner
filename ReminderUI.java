import javax.swing.*;

public class ReminderUI {


    JFrame frame = new JFrame();
    static JLabel assignment = new JLabel();

    ReminderUI() {
        assignment.setBounds(50,100,350,50);
        frame.add(assignment);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 350);
        frame.setLayout(null);
        frame.setVisible(true);

    }

}
