package View;


import java.awt.*;
import java.awt.event.*;

import javax.sound.midi.ControllerEventListener;
import javax.swing.*;

import Controller.CourseController;
import Model.Course;

import java.sql.*;
import java.util.*;

public class CourseViewUI extends JFrame {
    private JList<String[]> courseList;
    
    private JButton closeButton;
    private String currentUsername;

    public CourseViewUI(String currentUsername) {
        this.currentUsername = currentUsername;
        setVisible(true);
        setTitle("Course List");
        setSize(400, 300);
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

       //Load courses from database
            CourseController cc = new CourseController(currentUsername);
            ArrayList<String[]> clist= getCourses();
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
        // }
    }
    public static ArrayList<String[]> getCourses(){
        return CourseController.getCourses();
    }

    // private JList<String[]> createJList(ArrayList<String[]> data) {
    //     DefaultListModel<String[]> model = new DefaultListModel<>();
    //     for (String[] item : data) {
    //         model.addElement(item);
    //     }
    //     JList<String[]> list = new JList<>(model);
    //     return list;
    // }

    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Close the window
            dispose();
        }
    }
    // public static void main(String[] args) {
        
    //     CourseViewUI gg = new CourseViewUI("lo");
    //     gg.setVisible(true);
    //     //System.out.println(getCourses());
        
    // }

    
}





