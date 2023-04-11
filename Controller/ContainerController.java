
import Model.Container;
import java.util.*;

public class ContainerController {

    public Container cContainer;


    public ContainerController(){

    }

    public Container createContainer(String courseName, String courseCode) {
        Container cContainer = new Container(courseName, courseCode);
        this.cContainer = cContainer;
        return this.cContainer;
    }

    public Container getContainer(String courseName, String courseCode) {
        Container cContainer = new Container();
        return Container.getContainer(courseName, courseCode);
    }

    public ArrayList<Container> getCourseContainers() {
        Container CC = new Container();
        return CC.getCourseContainers();
    }


    
}
