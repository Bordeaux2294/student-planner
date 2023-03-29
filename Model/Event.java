package Model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import Controller.AccountController;

public class Event{

    private Date startDateTime;
    private Date endDateTime;

    private String eventName;
    private String currentUsername;

    private String url = "jdbc:mysql://localhost:3306/studentplannerdb";
    private String user = "root";
    private String password = "Myaccess123.";

public Event(String currentUsername, String eventName, Date startDateTime, Date endDateTime){
    this.currentUsername=currentUsername;
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
// public ArrayList<Event> listEvents(){
//     // return a list of events created by a user
// }

// public Event getEvent(int eid){}

//public int changeEventStatus(){}



public void storeEvent(){
       // Create a connection to the MySQL database
       System.out.println("true");
    
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