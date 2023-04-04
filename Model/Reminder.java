package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Reminder {

    private static String url = "jdbc:mysql://localhost:3306/studentplannerdb";
    private static String user = "root";
    private static String password = "";

 
    private int eid;
    private Date datetime;

public Reminder(int eid, Date datetime){
      this.eid =eid;
      this.datetime =datetime;
      return;
    }


//public ArrayList<Reminder> listReminders(){}

// public static void sendNotif(){}

    // public void setTimer(int seconds) {
       
    // }

public void storeReminder(){
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
        // Insert the reminder into the database
        System.out.println(eid+""+datetime);
        String sql = "INSERT INTO reminder (eid, datetime) VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, eid); 
        statement.setTimestamp(2, new Timestamp(datetime.getTime()));
        statement.executeUpdate();

        String sql2 = "Update events Set reminder= 'Set' Where eid = ? ";
        PreparedStatement stmt = connection.prepareStatement(sql2);
        stmt.setInt(1, eid); 
        stmt.executeUpdate();

    } catch (SQLException ex) {
        System.out.println(ex); 
    }


}


public static String getUrl() {
    return url;
}


public static String getUser() {
    return user;
}


public static String getPassword() {
    return password;
}


public static Date getCurrentDate() {
    return currentDate;
}


public static ArrayList<Reminder> getRlst() {
    return rlst;
}


public static ArrayList<Event> getElst() {
    return elst;
}


public int getEid() {
    return eid;
}


public Date getDatetime() {
    return datetime;
}
}



// import java.awt.*;
// import java.awt.TrayIcon.MessageType;
// import java.util.Calendar;
// import java.util.Date;
// import java.util.Timer;
// import java.util.TimerTask;

// public class ScheduledReminderNotification {
    
//     public static void main(String[] args) {
//         if (SystemTray.isSupported()) {
//             SystemTray tray = SystemTray.getSystemTray();
//             Image icon = Toolkit.getDefaultToolkit().getImage("icon.png"); // specify the path to your icon image
//             TrayIcon trayIcon = new TrayIcon(icon, "Reminder Notification");
//             trayIcon.setImageAutoSize(true);
//             trayIcon.setToolTip("Reminder Notification");
//             getscheduledTimes();
//             for( Reminder r: rlst){
        //             String ename = "";
        //             for( Event e: elst){
        //                 if (e.getEid()==r.getEid())
        //                    ename = e.getEventName();
        //             }
        //             Date scheduledTime = r.getDatetime(); // get the scheduled time
        //             TimerTask task = new TimerTask() {
        //                 public void run() {
        //                     trayIcon.displayMessage("Reminder", "Your Event "+ename+ " is happening soon!", MessageType.INFO);
        //                 }
        //             };
        //             Timer timer = new Timer();
        //             timer.schedule(task, scheduledTime); // schedule the task to run at the scheduled time
        //         } else {
        //             System.out.println("System tray not supported");
        //         }
//            }
//     }
//     private static void getscheduledTimes() {
//         String sql = "Select * From reminder Where datetime = ?";
//         statement.setTimestamp(1, new Timestamp(currentDate.getTime())); 
//         Resultset row = statement.executeQuery();
//         while(row.nex()){
//                 Reminder r = new Reminder(row.getInt("eid"),row.getTime("datetime"));
//                 rlst.add(r);
//                 String sql2 = Select * From events Where eid = ?";
//                 sttmt.setInt(1, r.getEid()); 
//                 Resultset row2 = stmt.executeQuery();
//                 row2.next();
//                 Event e = new Event(row2.getInt("eid"), row2.getString("ename"), row2.get("sdatetime"), row2.get("edatetime"), row2.getString("status"), row2.get("reminder"));  
//                 elst.add(e);
//          } 
//         
//      }
//     private static ArrayList<Date> getScheduleTime() {
//         
    
//         Calendar calendar = Calendar.getInstance();
//         calendar.set(Calendar.YEAR, 2023); // set the year
//         calendar.set(Calendar.MONTH, Calendar.APRIL); // set the month (Note that January is 0)
//         calendar.set(Calendar.DAY_OF_MONTH, 1); // set the day
//         calendar.set(Calendar.HOUR_OF_DAY, 9); // set the hour (24-hour clock)
//         calendar.set(Calendar.MINUTE, 0); // set the minute
//         calendar.set(Calendar.SECOND, 0); // set the second
//         Date scheduledTime = calendar.getTime();
//         return scheduledTime;
//     }
// }
