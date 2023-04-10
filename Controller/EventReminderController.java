package Controller;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import Model.Event;
import Model.Reminder;
import Model.User;
import View.MenuUI;




public class EventReminderController{
     
    private static String currentUsername;
    private static ArrayList<Reminder> rlst;
    private static ArrayList<Event> elst;
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

    public static void getscheduledReminders() throws AWTException{
  
        try (Connection connection = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword())) {
            rlst =new ArrayList<Reminder>();
            elst =new ArrayList<Event>();
            String sql = "SELECT * from reminder where datetime = str_to_date(?, '%Y-%m-%d %h:%i %p')";
            PreparedStatement statement = connection.prepareStatement(sql);
            Date currentDate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
            String currentDateTimeString = formatter.format(currentDate);
            statement.setString(1, currentDateTimeString);
            ResultSet row = statement.executeQuery();
            while(row.next()){
                Reminder r = new Reminder(row.getInt("eid"),row.getTimestamp("datetime"));
                r.setRid(row.getInt("rid"));
                rlst.add(r);
                String sql2 = "SELECT * FROM events WHERE eid = ?";
                PreparedStatement stmt = connection.prepareStatement(sql2);
                stmt.setInt(1, r.getEid());
                
                ResultSet row2 = stmt.executeQuery();
                
                row2.next();
                Event e = new Event(row2.getInt("eid"), row2.getString("ename"),
                row2.getTime("sdatetime"), row2.getTime("edatetime"), row2.getString("status"),
                row2.getString("reminder"));
                elst.add(e);
                row2.close();
                
            }
            row.close();
            if (SystemTray.isSupported()) {
                //Obtain only one instance of the SystemTray object
                SystemTray tray = SystemTray.getSystemTray();
        
                //If the icon is a file
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                //Alternative (if the icon is on the classpath):
                //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));
        
                TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
                //Let the system resize the image if needed
                trayIcon.setImageAutoSize(true);
                //Set tooltip text for the tray icon
                trayIcon.setToolTip("System tray icon demo");
                tray.add(trayIcon);
                
                Map<Integer, String> eventMap = new HashMap<>(); // map to store events by ID
                for (Event e : elst) {
                    eventMap.put(e.getEventID(), e.getEventName());
                }
    
                for (Reminder r : rlst) {
                    String ename = eventMap.get(r.getEid());
                    if (ename == null) { // handle case where event doesn't exist
                        continue;
                    }
                    
                    

                    trayIcon.displayMessage("Reminder", "Your Event " + ename + " is happening soon!", MessageType.INFO);
                    r.delReminder();
             
                }
                }else {
                System.err.println("System tray not supported!");
                }
             
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
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