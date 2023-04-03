package Controller;

import Model.Course;



public class CourseController {
    private static String currentUsername;

    public CourseController(String currentUsername){
        CourseController.currentUsername = currentUsername;
    }

    public boolean addCourse(String coursecode, String coursetitle){
        new Course(currentUsername, coursetitle, coursecode);
        return true;
    }
}
