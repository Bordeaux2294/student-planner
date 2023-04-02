package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;


public class ScheduleEntry {

    private String courseTitle;
    private String lecturer ="";
    private Time classStartTime;
    private Time classEndTime;
    private String courseRoom ="";
    private String day;
    private String type = "";

    private static String currentUsername;
    private static String url = "jdbc:mysql://localhost:3306/studentplannerdb";
    private static String user = "root";
    private static String password = "";


    public ScheduleEntry(String currentUsername, String courseTitle, String day, Time classStartTime, Time classEndTime, String lecturer, String courseRoom, String type){
        ScheduleEntry.currentUsername = currentUsername;
        this.courseTitle = courseTitle;
        this.day = day;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.lecturer = lecturer;
        this.courseRoom = courseRoom;
        this.type = type;
        storeEntry();
    }



    public void storeEntry(){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Insert the reminder into the database
            String sql = "INSERT INTO schedule (username, day_of_week, start_time, end_time, course_code, instructor, room, ctype) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, currentUsername);
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

    }
}
