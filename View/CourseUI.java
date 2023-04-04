package View;

import javax.swing.*;

import Controller.CourseController;

import java.awt.*;
import java.awt.event.*;

public class CourseUI extends JFrame{
    private JLabel codeLabel;
    private JTextField codeField;
    private JLabel titleLabel;
    private JTextField titleField;
    private JButton submitButton;
    private JButton cancelButton;


    private String currentUsername;

    public CourseUI(String currentUsername){
        this.currentUsername = currentUsername;
        setTitle("Add New Course");
        setSize(100,50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         // Create JLabels and JTextFields for course code and course title
        codeLabel = new JLabel("Course Code:");
        codeField = new JTextField(20);
        titleLabel = new JLabel("Course Title:");
        titleField = new JTextField(20);
         
         // Customize the appearance of the text fields
        Color color = new Color(252, 180, 213);
        codeField.setBorder(BorderFactory.createLineBorder(color, 2));
        titleField.setBorder(BorderFactory.createLineBorder(color, 2));
         
         // Create a JButton to submit the course information
        submitButton = new JButton("Submit");
        submitButton.setBorder(BorderFactory.createLineBorder(color, 2));
        submitButton.setBackground(Color.WHITE);
        submitButton.addActionListener(new submitButtonActionListener());

        cancelButton = new JButton("Cancel");
        cancelButton.setBorder(BorderFactory.createLineBorder(color, 2));
        cancelButton.setBackground(Color.WHITE);
        cancelButton.addActionListener(new cancelButtonActionListener());
        // Create a JPanel and add the components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(codeLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(codeField, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(titleLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(titleField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        panel.add(submitButton, c);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        panel.add(cancelButton,c);
        panel.setBackground(new Color(255, 229, 248));
         
         // Add the panel to the frame and display it
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
     


        
    }
    class submitButtonActionListener implements ActionListener{
        // Get the values entered in the text fields
       public void actionPerformed(ActionEvent e){
            String courseCode = codeField.getText();
            String courseTitle = titleField.getText();
            if (courseCode.equals("") || courseTitle.equals("")){
                JOptionPane.showMessageDialog(null, "These fields must be completed", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                CourseController cc = new CourseController(currentUsername);
                cc.addCourse(courseTitle, courseCode);
            // Do something with the course information (e.g. add to a timetable)
                System.out.println("Course Code: " + courseCode);
                System.out.println("Course Title: " + courseTitle);
                JOptionPane.showMessageDialog(null, "Data submitted successfully");
                setVisible(false);
            }
       }
    }

    class cancelButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
        }
    }
    
   
}
