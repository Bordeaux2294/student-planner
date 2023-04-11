import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.*;
//import java.util.*;






class Container {

    private static String url = "jdbc:mysql://localhost:3306/studentplannerdb";
    private static String user = "root";
    private static String password = "";
    private String courseCode;
    private String courseName;
    private ArrayList<Note> notes;
    private static ArrayList<Container> courseContainers;


    
    public Container(){

    }

    public Container(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        notes = new ArrayList<>();
        courseContainers = new ArrayList<>();
        
        
    }

    public String getCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void addNotes(Note note) {
        //courseContainer.addNotes(note);
        notes.add(note);
    }

    class Note{
        private String courseCode;
        private String noteContent;
        private String Title;

        public Note(String noteContent, String courseCode){
            this.noteContent = noteContent;
            this.courseCode= courseCode;
            //Note myNote = new Note("Some note text");
            

        }

        public String getTitle() {
            return this.Title;
        }

        public String getContent() {
            return this.noteContent;
        }
        public String getCourseCode() {
            return this.courseCode;
        }

    }


    public void UpdateCourseContainer(){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql="Update courses Set container = 1 where course_code = ?";                            
            PreparedStatement statement  = connection.prepareStatement(sql);                                
            statement.setString(1, courseCode);                                                                             
            statement.executeUpdate();
        }catch (SQLException ex) {
                System.out.println(ex); 
            }
    }

    public static Container getContainer(String courseName, String courseCode) {
        for (Container container : courseContainers) {
            if (container.getCourseName().equals(courseName) && container.getCode().equals(courseCode)) {
                return container;
            }
        }
        return null;
    }
    

    public ArrayList<Container> getCourseContainers() {
        return courseContainers;
    }
    

}

