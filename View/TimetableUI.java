 package View;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class TimetableUI extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JPanel buttonPanel;
    private JButton info;
    private JScrollPane tableScrollPane;
    private JTable timetableTable;
    private JButton closeButton;
    private JButton addEntry;
    private String  currentUsername;
    private MenuUI F;

    public TimetableUI(String currentUsername) {
        this.currentUsername=currentUsername;
        

    }
    public JPanel DisplayTimeTable(MenuUI F){
        
        this.F=F;
        // Set up the main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 228, 225));
        setContentPane(mainPanel);

        // Add the title label
        titleLabel = new JLabel("Weekly Timetable");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Set up the timetable table
        String[] columnNames = {"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Object[][] data = new Object[14][8];

        for (int i = 7; i <= 20; i++) {
            data[i - 7][0] = String.format("%02d:00", i);
        }
        timetableTable = new JTable(data, columnNames);
        timetableTable.setFillsViewportHeight(true);

        // Add the timetable table to a scroll pane
        tableScrollPane = new JScrollPane(timetableTable);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Retrieve timetable data from database and update the table
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentplannerdb", "root", "");
            String sql = "SELECT * FROM schedule where username =?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, currentUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String courseName = rs.getString("course_code");
                String courseDay = rs.getString("day_of_week");
                String courseStartTime = rs.getString("start_time");
                String courseEndTime = rs.getString("end_time");

                int rowStart = Integer.parseInt(courseStartTime.substring(0, 2));
                int rowEnd = Integer.parseInt(courseEndTime.substring(0, 2));

                int column = -1;
                switch (courseDay) {
                    case "Sunday":
                        column = 1;
                        break;
                    case "Monday":
                        column = 2;
                        break;
                    case "Tuesday":
                        column = 3;
                        break;
                    case "Wednesday":
                        column = 4;
                        break;
                    case "Thursday":
                        column = 5;
                        break;
                    case "Friday":
                        column = 6;
                        break;
                    case "Saturday":
                        column = 7;
                        break;
                }

                for (int i = rowStart - 7; i < rowEnd - 7; i++) {
                    data[i][column] = courseName;
                }
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addEntry = new JButton("Add Entry");
        addEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==addEntry) {
                       new ScheduleEntryUI(currentUsername);
                } 
            }
        });
        closeButton = new JButton("Back");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==closeButton) {
                       F.dispose();
                       new MenuUI(currentUsername);
                } 
            }
        });
        info = new JButton("Schedule Info");
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==info) {
                       new CourseInfoUI(currentUsername);
                } 
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addEntry);
        buttonPanel.add(closeButton);
        buttonPanel.add(info);
        mainPanel.add(buttonPanel,BorderLayout.SOUTH);

        // Set up the frame
        // setSize(600, 400);
        // setLocationRelativeTo(null);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setVisible(true);

        
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        timetableTable.setModel(model);
        return mainPanel;
    }

    // public static void main(String[] args) {
    //     new TimetableUI();
    // }
}
