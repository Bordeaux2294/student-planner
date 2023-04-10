package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Course;



public class CourseController {
    
    private ArrayList<String[]> courseInfoList;
    private ArrayList<String[]> courseList;

    public CourseController(){
       
    }

    public boolean addCourse(String coursecode, String coursetitle){
        new Course(coursetitle, coursecode);
        return true;
    }

    public static List<String> listCourses() {
        return Course.listCourses();
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
