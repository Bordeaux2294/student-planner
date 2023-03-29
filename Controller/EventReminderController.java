package Controller;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Event;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.*;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;

public class EventReminderController{
     
    private String currentUsername;

    public EventReminderController(String currentUsername){
    this.currentUsername =  currentUsername;
    
    }

    public boolean addEvent(String eventName, Date startDateTime, Date endDateTime){
       
        new Event(currentUsername, eventName, startDateTime, endDateTime);
        System.out.println("true");
        return true;
         


        
    }

 
   
}