package Controller;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import Model.Event;
import Model.Reminder;
import View.MenuUI;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.*;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;

public class EventReminderController{
     
    private static String currentUsername;

    public EventReminderController(String currentUsername){
    EventReminderController.currentUsername =  currentUsername;
    
    }

    public boolean addEvent(String eventName, Date startDateTime, Date endDateTime){
       
        new Event(currentUsername, eventName, startDateTime, endDateTime);
       
        return true;
                
    }

    public boolean addReminder(Date datetime){
        int id = getlastEid();
        new Reminder(id, datetime);
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