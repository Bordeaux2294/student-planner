package Controller;

import javax.swing.JTable;


import Model.Event;
import Model.Reminder;
import View.MenuUI;

import java.util.Date;


public class EventReminderController{
     
    private static String currentUsername;

    public EventReminderController(String currentUsername){
    EventReminderController.currentUsername =  currentUsername;
    
    }

    public boolean addEvent(String eventName, Date startDateTime, Date endDateTime){
       
        new Event(currentUsername, eventName, startDateTime, endDateTime).storeEvent();;
       
        return true;
                
    }

    public boolean addReminder(Date datetime){
        int id = getlastEid();
        new Reminder(id, datetime).storeReminder();;
        return true;
    }
    public static JTable getevents(){
        currentUsername = MenuUI.getCurrentUser();
        return Event.listEvents();
    }
 
    public static String getCurrentUser(){
        return currentUsername;
    }

    public static int getlastEid(){
        return Event.getlastEid();
    }
}