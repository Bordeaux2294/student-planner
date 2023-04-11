package Model;

import java.util.Date;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Timestamp;


public class Reminder {





    private int eid;
    private int rid;
    private Date datetime;
    
    public Reminder(int eid, Date datetime) {
        this.eid = eid;
        
        this.datetime = datetime;
        
    }

    // public ArrayList<Reminder> listReminders(){}

    // public static void sendNotif(){}

    // public void setTimer(int seconds) {

    // }

    public void storeReminder() {
        try (Connection connection = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword())) {
            // Insert the reminder into the database
            System.out.println(eid + "" + datetime);
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

    public int getEid() {
        return eid;
    }

    public int getRid() {
        return rid;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setRid (int id){
        rid=id;
    }
    
    public void delReminder(){
        try (Connection connection = DriverManager.getConnection(User.getUrl(), User.getUser(), User.getPword())) {
        String sql3 = "DELETE FROM reminder WHERE rid = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql3);

        // Set the parameter for the prepared statement
        pstmt.setInt(1, getRid());

        // Execute the query
        int rows = pstmt.executeUpdate();
        if (rows>=1)
            System.out.println("rows deleted: "+  rows);
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }

// public class ScheduledReminderNotification {


  
    
    
    
    
    
    

}

// private static ArrayList<Date> getScheduleTime() {
//

// Calendar calendar = Calendar.getInstance();
// calendar.set(Calendar.YEAR, 2023); // set the year
// calendar.set(Calendar.MONTH, Calendar.APRIL); // set the month (Note that
// January is 0)
// calendar.set(Calendar.DAY_OF_MONTH, 1); // set the day
// calendar.set(Calendar.HOUR_OF_DAY, 9); // set the hour (24-hour clock)
// calendar.set(Calendar.MINUTE, 0); // set the minute
// calendar.set(Calendar.SECOND, 0); // set the second
// Date scheduledTime = calendar.getTime();
// return scheduledTime;
// }
// }
