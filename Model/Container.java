package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
//import java.util.*;

import Controller.AccountController;
import Controller.ContainerController;






public class Container {

    private  String courseCode;
    private  String courseName;
    private static ArrayList<Notes> notes;
    private  String courseinfo;


    
    public Container(String courseName, String courseCode){
        this.courseCode = courseCode;
        this.courseName = courseName;
        notes = Notes.getAllNotes(courseCode);
    }

    public Container(String courseinfo) {
        this.courseinfo = courseinfo;
        UpdateCourseContainer();      
    }

    public String getCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public static void addNotes(Notes note) {
        //courseContainer.addNotes(note);
        notes.add(note);
    }

    public ArrayList<Notes> getNotes(){
        return notes;
    }


    public void UpdateCourseContainer(){
        try (Connection connection = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword())) {
            String sql="UPDATE courses SET container = 1 WHERE username =? AND (course_code = ? OR course_name = ?)";                            
            PreparedStatement statement  = connection.prepareStatement(sql);                                
            statement.setString(1,  AccountController.getCurrentUser().getCurrentUsername());   
            statement.setString(2, courseinfo); 
            statement.setString(3, courseinfo);                                                                               
            statement.executeUpdate();
        }catch (SQLException ex) {
                System.out.println(ex); 
            }
    }

    public static void loadContainerslst(){
        try (Connection connection = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword())) {
            String sql="Select * from courses where container = 1 and username = ?";                            
            PreparedStatement statement  = connection.prepareStatement(sql);                                
            statement.setString(1, AccountController.getCurrentUser().getCurrentUsername());                                                                             
            ResultSet row = statement.executeQuery();
            while (row.next()){
               Container c = new Container(row.getString("course_name"), row.getString("course_code"));
               ContainerController.getCourseContainers().add(c);
               
            }
            row.close();
        }catch (SQLException ex) {
                System.out.println(ex); 
            }
    }

    

   
    

}

