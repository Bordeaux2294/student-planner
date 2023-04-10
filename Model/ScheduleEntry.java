package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.ResultSetMetaData;
import java.util.*;

import Controller.AccountController;
import Controller.ScheduleController;


public class ScheduleEntry {

    private String courseTitle;
    private String lecturer ="";
    private Time classStartTime;
    private Time classEndTime;
    private String courseRoom ="";
    private String day;
    private String type = "";
   

   



    public ScheduleEntry( String courseTitle, String day, Time classStartTime, Time classEndTime, String lecturer, String courseRoom, String type){
     
        this.courseTitle = courseTitle;
        this.day = day;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.lecturer = lecturer;
        this.courseRoom = courseRoom;
        this.type = type;
        storeEntry();
    }
    
    public static List<String> getScheduleEntry(){
        String currentUsername = AccountController.getCurrentUser().getCurrentUsername();
        List<String> entries = new ArrayList<>();
        // String username = " where username= '"+currentUsername+"'";
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
                String entry =courseName+ " "+courseDay+" "+courseStartTime+" "+courseEndTime;
                entries.add(entry);
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return entries;
    }

    // public static void main(String[] args) {
    //     getScheduleEntry();
    // }
    public static ArrayList<String[]> listCourseInfo(){
        String currentUsername = AccountController.getCurrentUser().getCurrentUsername();
        ArrayList<String[]> courseInfoList = new ArrayList<>();
        String username = " where schedule.username= '"+currentUsername+"' group by start_time order by CASE WHEN day_of_week =  'Sunday' THEN 1 WHEN day_of_week= 'Monday' THEN 2 WHEN day_of_week= 'Tuesday' THEN 3 WHEN day_of_week= 'Wednesday' THEN 4 WHEN day_of_week= 'Thursday' THEN 5 WHEN day_of_week= 'Friday' THEN 6 WHEN day_of_week= 'Saturday' THEN 7 END ASC";
        try (Connection connection = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("Select schedule.course_code, courses.course_name, schedule.day_of_week, schedule.start_time, schedule.end_time, schedule.instructor, schedule.room, schedule.ctype  from schedule inner join courses on schedule.course_code= courses.course_code"+username)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            while (resultSet.next()) {
                String[] course = new String[columns];
                for (int i = 1; i <= columns; i++) {
                    course[i - 1] = resultSet.getString(i);
                }
                courseInfoList.add(course);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return courseInfoList;
    }

    public void storeEntry(){
        try (Connection connection = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword())) {
            // Insert the reminder into the database
            String sql = "INSERT INTO schedule (username, day_of_week, start_time, end_time, course_code, instructor, room, ctype) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, AccountController.getCurrentUser().getCurrentUsername());
            statement.setString(2, day);
            statement.setTime(3, classStartTime);
            statement.setTime(4, classEndTime);
            statement.setString(5, courseTitle);
            statement.setString(6, lecturer);
            statement.setString(7, courseRoom);
            statement.setString(8, type);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex); 
        }
        getScheduleEntry();
    }
}
