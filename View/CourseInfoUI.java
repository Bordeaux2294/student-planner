package View;

import Controller.ScheduleController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class CourseInfoUI extends JFrame {
    private JList<String[]> courseList;
    private JButton closeButton;


    public CourseInfoUI() {
       
        setVisible(true);
        setTitle("Course List");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        courseList = new JList<String[]>();
        closeButton = new JButton("Close");
        closeButton.addActionListener(new CloseButtonListener());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components to the main panel
        mainPanel.add(new JScrollPane(courseList), BorderLayout.CENTER);
        mainPanel.add(closeButton, BorderLayout.SOUTH);

        // Add main panel to the frame
        add(mainPanel);

        // Load courses from the database
        
            
            ScheduleController sc = new ScheduleController();
            ArrayList<String[]> clist = listCourseInfo();
            courseList.setListData(clist.toArray(new String[clist.size()][]));
            courseList.setCellRenderer(new ListCellRenderer<String[]>() {
                @Override
                public Component getListCellRendererComponent(JList<? extends String[]> list, String[] value, int index, boolean isSelected, boolean cellHasFocus) {
                    String labelText = String.join(" - ", value);
                    JLabel label = new JLabel(labelText);
                    if (isSelected) {
                        label.setBackground(list.getSelectionBackground());
                        label.setForeground(list.getSelectionForeground());
                    } else {
                        label.setBackground(list.getBackground());
                        label.setForeground(list.getForeground());
                    }
                    return label;
                }
            });
    }

    public static ArrayList<String[]> listCourseInfo(){
        return ScheduleController.listCourseInfo();
    }


    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Close the window
            setVisible(false);
        }
    }
    
}





