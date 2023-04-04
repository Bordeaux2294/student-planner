package View;

import Controller.ScheduleController;
import Controller.CourseController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ScheduleEntryUI extends JFrame {
    private JLabel courseCodeLabel;
    private JComboBox<String> courseCodeComboBox;
    private JLabel dayOfWeekLabel;
    private JComboBox<String> dayOfWeekComboBox;
    private JLabel startTimeLabel;
    private JComboBox<String> startHourComboBox;
    private JComboBox<String> startMinuteComboBox;
    private JLabel endTimeLabel;
    private JComboBox<String> endHourComboBox;
    private JComboBox<String> endMinuteComboBox;
    private JLabel typeLabel;
    private JComboBox<String> typeComboBox;
    private JLabel lecturerLabel;
    private JTextField lecturerField;
    private JLabel roomLabel;
    private JTextField roomField;
    private JButton submitButton;
    private JButton cancelButton;


    private String currentUsername;

    public ScheduleEntryUI(String currentUsername) {
        this.currentUsername = currentUsername;
        List<String> values = listCourses(currentUsername);
        // Create a DefaultComboBoxModel and pass in the list of values
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(values.toArray(new String[0]));

        // Create a JComboBox and pass in the DefaultComboBoxModel
        courseCodeComboBox = new JComboBox<>(model);
        


        setVisible(true);
        setTitle("Course Information");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        courseCodeLabel = new JLabel("Course Code:");
        courseCodeLabel.setForeground(new Color(91, 50, 86));
        courseCodeComboBox.setBackground(new Color(240, 205, 218));
        dayOfWeekLabel = new JLabel("Day of the Week:");
        dayOfWeekLabel.setForeground(new Color(91, 50, 86));
        dayOfWeekComboBox = new JComboBox<String>(new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});
        dayOfWeekComboBox.setBackground(new Color(240, 205, 218));
        startTimeLabel = new JLabel("Start Time:");
        startTimeLabel.setForeground(new Color(91, 50, 86));
        startHourComboBox = new JComboBox<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
        startHourComboBox.setBackground(new Color(240, 205, 218));
        startMinuteComboBox = new JComboBox<String>(new String[] {"00"});
        startMinuteComboBox.setBackground(new Color(240, 205, 218));
        endTimeLabel = new JLabel("End Time:");
        endTimeLabel.setForeground(new Color(91, 50, 86));
        endHourComboBox = new JComboBox<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
        endHourComboBox.setBackground(new Color(240, 205, 218));
        endMinuteComboBox = new JComboBox<String>(new String[] {"00"});
        endMinuteComboBox.setBackground(new Color(240, 205, 218));
        lecturerLabel = new JLabel("Lecturer:");
        lecturerLabel.setForeground(new Color(91, 50, 86));
        lecturerField = new JTextField(10);
        roomLabel = new JLabel("Room:");
        roomLabel.setForeground(new Color(91, 50, 86));
        roomField = new JTextField(10);
        typeLabel = new JLabel("Type");
        typeLabel.setForeground(new Color(91, 50, 86));
        typeComboBox = new JComboBox<String>(new String[] {"Lecture","Lab","Tutorial","Seminar","Field"});
        typeComboBox.setBackground(new Color(240, 205, 218));
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(245, 150, 170));
        submitButton.setForeground(Color.WHITE);
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(245, 150, 170));
        cancelButton.setForeground(Color.WHITE);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 240, 245));
        panel.setLayout(new GridBagLayout());
    
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);
    
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(courseCodeLabel, constraints);
    
        constraints.gridx = 1;
        panel.add(courseCodeComboBox, constraints);
    
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(dayOfWeekLabel, constraints);
    
        constraints.gridx = 1;
        panel.add(dayOfWeekComboBox, constraints);
    
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(startTimeLabel, constraints);
    
        constraints.gridx = 1;
        panel.add(startHourComboBox, constraints);
    
        // constraints.gridx = 1;
        // panel.add(new JLabel(":"), constraints);
    
        constraints.gridx = 2;
        panel.add(startMinuteComboBox, constraints);
    
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(endTimeLabel, constraints);
    
        constraints.gridx = 1;
        panel.add(endHourComboBox, constraints);
    
        // constraints.gridx = 1;
        // panel.add(new JLabel(":"), constraints);
    
        constraints.gridx = 2;
        panel.add(endMinuteComboBox, constraints);
    
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(lecturerLabel, constraints);
    
        constraints.gridx = 1;
        panel.add(lecturerField, constraints);
    
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(roomLabel, constraints);
    
        constraints.gridx = 1;
        panel.add(roomField, constraints);
    
        constraints.gridx = 0;
        constraints.gridy = 6;

        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(typeLabel,constraints);

        constraints.gridx = 1;
        panel.add(typeComboBox,constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        //constraints.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, constraints);
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        panel.add(cancelButton,constraints);
        cancelButton.addActionListener(new cbActionListener());
        submitButton.addActionListener(new submitButtonActionListener());
            
            
        
    
        add(panel);
    
        pack();
        
    }
    class cbActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }

    class submitButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // Get the values of the input fields
            String courseCode = (String) courseCodeComboBox.getSelectedItem();
            String dayOfWeek = (String) dayOfWeekComboBox.getSelectedItem();
            int startHour = Integer.parseInt((String) startHourComboBox.getSelectedItem());
            int startMinute = Integer.parseInt((String) startMinuteComboBox.getSelectedItem());
            int endHour = Integer.parseInt((String) endHourComboBox.getSelectedItem());
            int endMinute = Integer.parseInt((String) endMinuteComboBox.getSelectedItem());
            String lecturer = lecturerField.getText();
            String type = (String) typeComboBox.getSelectedItem();
            String room = roomField.getText();
            LocalTime startTime = LocalTime.of(startHour, startMinute);
            LocalTime endTime = LocalTime.of(endHour, endMinute);
            Time st = Time.valueOf(startTime);
            Time et = Time.valueOf(endTime);
            // Do something with the input values, e.g. store them in a database or print them to the console
            System.out.println("Course code: " + courseCode);
            System.out.println("Day of the week: " + dayOfWeek);
            System.out.println("Start time: " + startTime);
            System.out.println("End time: " + endTime);
            System.out.println("Lecturer: " + lecturer);
            System.out.println("Room: " + room);
            System.out.println("Type: "+type);
    
           //Error messages
            
            if (endTime.isBefore(startTime)) {
                JOptionPane.showMessageDialog(null, "End time must be after start time.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (startTime.equals(endTime)){
                JOptionPane.showMessageDialog(null, "Start time must be different from end time.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                ScheduleController scc = new ScheduleController(currentUsername);
                scc.addEntry(courseCode, dayOfWeek, st, et, lecturer, room, type);
                JOptionPane.showMessageDialog(null, "Data submitted successfully");

                setVisible(false);
            }
             // Show a message dialog to indicate that the data has been submitted
            //
        }
    
    }
    
    public static List<String> listCourses(String currentUsername){
        CourseController scc = new CourseController(currentUsername);
        return CourseController.listCourses();
    }
    // public static void main(String[] args) {
    //     ScheduleEntryUI gui = new ScheduleEntryUI("hel");
    //     gui.setVisible(true);
    // }
}
    

