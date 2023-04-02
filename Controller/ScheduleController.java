package Controller;

import Model.ScheduleEntry;
import java.sql.Time;
import View.MenuUI;


public class ScheduleController {
    private static String currentUsername;

    public ScheduleController(String currentUsername){
        ScheduleController.currentUsername = currentUsername;
    }

    public boolean addEntry(String courseTitle, String day, Time classStartTime, Time classEndTime, String lecturer, String courseRoom, String type){
        new ScheduleEntry( currentUsername, courseTitle, day, classStartTime, classEndTime,lecturer, courseRoom,type);
        
        return true;
    }

    
}
