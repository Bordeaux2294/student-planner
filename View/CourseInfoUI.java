package View;


// import javax.swing.*;
// import java.awt.*;
// import java.sql.*;
// import java.util.ArrayList;

// public class CourseInfoUI extends JFrame {
//     private JList<String> timetableList;
//     private DefaultListModel<String> listModel;
//     private String currentUsername;
    

//     public CourseInfoUI(String currentUsername) {
//         this.currentUsername = currentUsername;
//         // Set up the window
//         setTitle("Timetable");
//         setSize(500, 500);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(new BorderLayout());

//         // Initialize the list and list model
//         timetableList = new JList<>();
//         listModel = new DefaultListModel<>();
//         timetableList.setModel(listModel);

//         // Add the list to a scroll pane
//         JScrollPane scrollPane = new JScrollPane(timetableList);
//         add(scrollPane, BorderLayout.CENTER);

//         // Get the timetable data from the database and add it to the list model
        // try {
        //     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentplannerdb", "root", "");
        //     String sql ="Select schedule.course_code, courses.course_name, schedule.day_of_week, schedule.start_time, schedule.end_time, schedule.instructor, schedule.room, schedule.ctype  from schedule inner join courses on schedule.course_code= courses.course_code where schedule.username=? group by start_time order by CASE WHEN day_of_week =  'Sunday' THEN 1 WHEN day_of_week= 'Monday' THEN 2 WHEN day_of_week= 'Tuesday' THEN 3 WHEN day_of_week= 'Wednesday' THEN 4 WHEN day_of_week= 'Thursday' THEN 5 WHEN day_of_week= 'Friday' THEN 6 WHEN day_of_week= 'Saturday' THEN 7 END ASC;";
        //     PreparedStatement stmt = conn.prepareStatement(sql);
        //     stmt.setString(1, currentUsername);
        //     ResultSet rs = stmt.executeQuery();
        //     while (rs.next()) {
        //         String courseCode = rs.getString("course_code");
        //         String name = rs.getString("course_name");
        //         String day = rs.getString("day_of_week");
        //         String stime = rs.getString("start_time");
        //         String etime = rs.getString("end_time");
        //         String room = rs.getString("room");
        //         String instructor = rs.getString("instructor");
        //         String type = rs.getString("ctype");
                
                

        //         String listItem = courseCode + " - " + name + " : " + day+ " - "+ stime +" - " + etime + " - " + room + " - " + instructor + " - "+type;
        //         listModel.addElement(listItem);
        //     }

//             conn.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }

//         // Display the window
//         setVisible(true);
//     }

//     public static void main(String[] args) {
//         new CourseInfoUI("lo");
//     }
// }




import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class CourseInfoUI extends JFrame {
    private JList<String> courseList;
    private JButton closeButton;
    private String currentUsername;

    public CourseInfoUI(String currentUsername) {
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
            String sql ="Select schedule.course_code, courses.course_name, schedule.day_of_week, schedule.start_time, schedule.end_time, schedule.instructor, schedule.room, schedule.ctype  from schedule inner join courses on schedule.course_code= courses.course_code where schedule.username=? group by start_time order by CASE WHEN day_of_week =  'Sunday' THEN 1 WHEN day_of_week= 'Monday' THEN 2 WHEN day_of_week= 'Tuesday' THEN 3 WHEN day_of_week= 'Wednesday' THEN 4 WHEN day_of_week= 'Thursday' THEN 5 WHEN day_of_week= 'Friday' THEN 6 WHEN day_of_week= 'Saturday' THEN 7 END ASC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, currentUsername);
            ArrayList<String> courseNames = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String courseCode = rs.getString("course_code");
                String name = rs.getString("course_name");
                String day = rs.getString("day_of_week");
                String stime = rs.getString("start_time");
                String etime = rs.getString("end_time");
                String room = rs.getString("room");
                String instructor = rs.getString("instructor");
                String type = rs.getString("ctype");
                
                

                String listItem = courseCode + " - " + name + " : " + day+ " - "+ stime +" - " + etime + " - " + room + " - " + instructor + " - "+type;
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
            setVisible(false);
        }
    }

    
}




