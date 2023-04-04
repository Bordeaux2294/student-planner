package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Course;



public class CourseController {
    private static String currentUsername="lo";

    public CourseController(String currentUsername){
        CourseController.currentUsername = currentUsername;
    }

    public boolean addCourse(String coursecode, String coursetitle){
        new Course(currentUsername, coursetitle, coursecode);
        return true;
    }

    public static List<String> listCourses() {
        return Course.listCourses();
    }

    public static String getCurrentUser(){
        return currentUsername;
    }


    public  static ArrayList<String[]> getCourses(){
        // ArrayList<String[]> slist = new ArrayList<>();
        // slist = getCourses();
        return Course.getCourses();
    }


    // public static void main(String[] args) {
        
    //     System.out.println(getCourses());
        
    // }
}
