package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.AccountController;
import Controller.EventReminderController;

public class Event{
    private int eventID;
    private String status;
    private String setReminder;

    private Date startDateTime;
    private Date endDateTime;

    private String eventName;
    
    
    
 


public Event(int eid, String eventName, Date startDateTime, Date endDateTime, String status, String reminder){
    this.eventID = eid;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.eventName = eventName;
    this.status = status;
    this.setReminder = reminder;
}


public Event(String eventName, Date startDateTime, Date endDateTime){
    
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.eventName = eventName;
    return;
}

 public static JTable listEvents() {
    Object[] outer;
   
    
    DefaultTableModel model = new DefaultTableModel(new Object[]{"Ref No."," Event Name", "Start Datetime", "End Datetime", "Status", "Reminder"},0);
    JTable t = new JTable(model);
    t.setEnabled(false);
    
    try (Connection connection = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword())) {
        // Select the events from the database
        String sql = "SELECT eid, ename, sdatetime, edatetime, status, reminder FROM events WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, AccountController.getCurrentUser().getCurrentUsername());
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

 public static int getlastEid(){
    
    int id=0;

    try (Connection connection = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword())) {
        // Insert the reminder into the database
        String sql = "SELECT * FROM events ORDER BY eid DESC LIMIT 1;";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        ResultSet row = statement.executeQuery();
        row.next();
        id = row.getInt("eid");

    } catch (SQLException ex) {
        System.out.println(ex); 
    }

    return id;
}

//public static int changeEventStatus(){}



public void storeEvent(){
       // Create a connection to the MySQL database    
        try (Connection connection = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword())) {
            // Insert the reminder into the database
            String sql = "INSERT INTO events (username, ename, sdatetime, edatetime) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, AccountController.getCurrentUser().getCurrentUsername());
            statement.setString(2, eventName);
            statement.setTimestamp(3, new Timestamp(startDateTime.getTime()));
            statement.setTimestamp(4, new Timestamp(endDateTime.getTime()));
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex); 
        }

   
}


public int getEventID() {
    return eventID;
}


public String getStatus() {
    return status;
}


public String getSetReminder() {
    return setReminder;
}


public Date getStartDateTime() {
    return startDateTime;
}


public Date getEndDateTime() {
    return endDateTime;
}


public String getEventName() {
    return eventName;
}




} 