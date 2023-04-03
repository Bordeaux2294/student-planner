package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

public class Course {
    private String coursecode;
    private String coursetitle;

    private static String currentUsername;
    private static String url = "jdbc:mysql://localhost:3306/studentplannerdb";
    private static String user = "root";
    private static String password = "";

    public Course(String currentUsername,String coursecode, String coursetitle){
        Course.currentUsername = currentUsername;
        this.coursecode = coursecode;
        this.coursetitle = coursetitle;
        storeCourse();
    }



    public void storeCourse(){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Insert the reminder into the database
            String sql = "INSERT INTO courses (username, course_code, course_name) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, currentUsername);
            statement.setString(2, coursecode);
            statement.setString(3,coursetitle);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex); 
        }
    }
}
