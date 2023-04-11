package Controller;

import Model.Container;
import java.util.*;

public class ContainerController {

    public Container cContainer;

    private static ArrayList<Container> courseContainers;

    public ContainerController(){
        courseContainers = new ArrayList<>();
        Container.loadContainerslst();
       
    }

    public Container createContainer(String courseinfo) {
        courseContainers = new ArrayList<>();
        Container cContainer = new Container(courseinfo);
        Container.loadContainerslst();
        this.cContainer = cContainer;
  
        return this.cContainer;
    }

    // public Container getContainer(String courseName, String courseCode) {
    //     Container cContainer = new Container();
    //     return Container.getContainer(courseName, courseCode);
    // }

    // public ArrayList<Container> getCourseContainers() {
    //     Container CC = new Container();
    //     return CC.getCourseContainers();
    // }

    
    public static Container getContainer(String courseCode) {
        for (Container container : courseContainers) {
            if (container.getCode().equals(courseCode)) {
                return container;
            }
        }
        return null;
    }

    public static ArrayList<Container> getCourseContainers() {
    
        return courseContainers;
    }

    
}
