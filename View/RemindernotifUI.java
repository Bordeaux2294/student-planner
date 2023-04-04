package View;

import javax.swing.*;

public class RemindernotifUI {

    JFrame frame = new JFrame();
    public static JLabel assignment = new JLabel();

    public RemindernotifUI() {
        assignment.setBounds(50, 100, 350, 50);
        frame.add(assignment);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 350);
        frame.setLayout(null);
        frame.setVisible(true);

    }

}
