package Model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.AccountController;
import Controller.EventReminderController;

public class Event{
    private int eventID;
    private String status;
    private String setReminder;

    private Date startDateTime;
    private Date endDateTime;

    private String eventName;
    private static String currentUsername;

    private static String url = "jdbc:mysql://localhost:3306/studentplannerdb";
    private static String user = "root";
    private static String password = "Myaccess123.";

public Event(String currentUsername, String eventName, Date startDateTime, Date endDateTime){
    Event.currentUsername=currentUsername;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.eventName = eventName;
    storeEvent();
    
    return;
}
public Event(){}

public String getEventName() {
        return eventName;
}  
public Date getStartDateTime() throws ParseException{
    return startDateTime;
}  

public Date getEndDateTime() throws ParseException{
     return endDateTime;
}  
    
public int getStartYear(){
    return startDateTime.getYear()+1900;
}

public int getEndYear(){
    return endDateTime.getYear()+1900;
}

public String getHours(Date d){
   String hrs= d.getHours()+"";
    if (hrs.length()==1)
        hrs= "0"+hrs;
    return hrs;
}

public String getMins(Date d){
    String mins= d.getMinutes()+"";
     if (mins.length()==1)
         mins= "0"+mins;
     return mins;
 }
 public static JTable listEvents() {
    Object[] outer;
    currentUsername = EventReminderController.getCurrentUser();
    
    DefaultTableModel model = new DefaultTableModel(new Object[]{"Event No."," Event Name", "Start Datetime", "End Datetime", "Status", "Reminder"},0);
    JTable t = new JTable(model);

    try (Connection connection = DriverManager.getConnection(url, user, password)) {
        // Select the events from the database
        String sql = "SELECT eid, ename, sdatetime, edatetime, status, reminder FROM events WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, currentUsername);
        ResultSet row = statement.executeQuery();

        // Add the rows to the JTable
        while (row.next()) {
            outer = new Object[] {
                row.getInt("eid"),
                row.getString("ename"),
                row.getString("sdatetime"),
                row.getString("edatetime"),
                row.getString("status"),
                row.getString("reminder")
            };
            model.addRow(outer);
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return t;
}

// public Event getEvent(int eid){}

//public int changeEventStatus(){}



public void storeEvent(){
       // Create a connection to the MySQL database    
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Insert the reminder into the database
            String sql = "INSERT INTO events (username, ename, sdatetime, edatetime) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, currentUsername);
            statement.setString(2, eventName);
            statement.setTimestamp(3, new Timestamp(startDateTime.getTime()));
            statement.setTimestamp(4, new Timestamp(endDateTime.getTime()));
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex); 
        }

   
}
} 