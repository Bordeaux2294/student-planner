package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.Date;



public class Reminder {

    private static String url = "jdbc:mysql://localhost:3306/studentplannerdb";
    private static String user = "root";
    private static String password = "Myaccess123.";

 
    private int eid;
    private Date datetime;

    public Reminder(int eid, Date datetime){
      this.eid =eid;
      this.datetime =datetime;
      storeReminder();
      return;
    }


//public ArrayList<Reminder> listReminders(){}

// public static void sendNotif(){}

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
}



// import java.util.Date;
// import java.util.Timer;
// import java.util.TimerTask;

// public class Reminder {
//     Timer timer;

//     public Reminder(int seconds) {
//         timer = new Timer();
//         timer.schedule(new RemindTask(), seconds * 1000);
//     }

//     class RemindTask extends TimerTask {
//         public void run() {
//             System.out.println("Reminder: Time's up!");
//             timer.cancel(); //Terminate the timer thread
//         }
//     }

//     public static void main(String args[]) {
//         System.out.println("Setting reminder for 5 seconds...");
//         new Reminder(5);
//         System.out.println("Reminder set.");
//     }
// }
