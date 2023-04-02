package View;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class CourseViewUI extends JFrame {
    private JList<String> courseList;
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
        courseList = new JList<String>();
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
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentplannerdb", "root", "");
            String sql ="Select course_code, course_name from courses where username =?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, currentUsername);
            ArrayList<String> courseNames = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String courseCode = rs.getString("course_code");
                String name = rs.getString("course_name");
                
                

                String listItem = courseCode + " - " + name;
                courseNames.add(listItem);
            }
            

            courseList.setListData(courseNames.toArray(new String[courseNames.size()]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Close the window
            dispose();
        }
    }

    
}





