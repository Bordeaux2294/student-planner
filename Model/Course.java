package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.*;

import Controller.CourseController;

public class Course {
    private String coursecode;
    private String coursetitle;
    private ArrayList<String[]> courseInfoList;
    private ArrayList<String[]> courseList;

    private static String currentUsername;

    public Course(){

    }
    public void setUsername(String usrname){
        Course.currentUsername = usrname;
    }

    public Course(String currentUsername,String coursecode, String coursetitle){
        Course.currentUsername = currentUsername;
        this.coursecode = coursecode;
        this.coursetitle = coursetitle;
        storeCourse();
    }


    public static ArrayList<String[]> getCourses() {
        currentUsername = CourseController.getCurrentUser();
        ArrayList<String[]> courseList = new ArrayList<>();
        String username = " where username = '"+currentUsername+"'";
        try (Connection connection = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT course_code, course_name FROM courses"+username)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            while (resultSet.next()) {
                String[] course = new String[columns];
                for (int i = 1; i <= columns; i++) {
                    course[i - 1] = resultSet.getString(i);
                }
                courseList.add(course);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return courseList;
    }

    public static List<String> listCourses() {
        currentUsername = CourseController.getCurrentUser();
        List<String> courseList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword());

            // Write a SQL query to retrieve the values
            String sql = "SELECT course_code FROM courses where username = ?";

            // Create a PreparedStatement object and execute the query
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, currentUsername);
            ResultSet rs = stmt.executeQuery();

            // Loop through the result set and add each value to a list
            while (rs.next()) {
                courseList.add(rs.getString("course_code"));
            }
            // Close the connection and statement objects
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return courseList;
    }
    // public static void main(String[] args) {
        
    //         System.out.println(listCourseInfo());
            
    //     }

    

    public void storeCourse(){
        try (Connection connection =DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword())) {
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
