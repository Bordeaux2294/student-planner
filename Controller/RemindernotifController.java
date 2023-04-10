package Controller;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
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
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import javax.sound.sampled.*;

import Model.Event;
import Model.Reminder;
import View.LoginUI;
import View.RemindernotifUI;

import java.util.Scanner;

public class RemindernotifController {

private static String url = "jdbc:mysql://localhost:3306/studentplannerdb";
private static String user = "root";
private static String password = "Myaccess123.";

private static ArrayList<Reminder> rlst;
private static ArrayList<Event> elst;

private static void getscheduledReminders() throws AWTException{
  
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
        rlst =new ArrayList<Reminder>();
        elst =new ArrayList<Event>();
        String sql = "SELECT * from reminder where datetime = str_to_date(?, '%Y-%m-%d %h:%i %p')";
        PreparedStatement statement = connection.prepareStatement(sql);
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        String currentDateTimeString = formatter.format(currentDate);
        System.out.println(currentDateTimeString);
        statement.setString(1, currentDateTimeString);
        ResultSet row = statement.executeQuery();
        while(row.next()){
            Reminder r = new Reminder(row.getInt("eid"), row.getTimestamp("datetime"));
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
            System.out.println("event added");
            row2.close();
        }
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
            }
            }else {
            System.err.println("System tray not supported!");
            }
        
    } catch (SQLException ex) {
        System.out.println(ex);
    }

}



        


public static void main(String[] args) throws AWTException {
    



    // public static void main(String[] args) {
        Thread schedulerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    getscheduledReminders();
                } catch (AWTException e) {
                    System.err.println("Error creating system tray icon: " + e.getMessage());
                }
            }
        });
        
        // Set the thread to run in the background and start it
        schedulerThread.setDaemon(true);
        schedulerThread.start();
        
        // Your main program logic continues here
        new LoginUI();
    }
    
    
    
    
    
        
    //     if (SystemTray.isSupported()) {
    //         SystemTray tray = SystemTray.getSystemTray();
    //         Image icon = Toolkit.getDefaultToolkit().getImage("icon.png"); // specify the path to your icon image
    //         TrayIcon trayIcon = new TrayIcon(icon, "Reminder Notification");
    //         trayIcon.setImageAutoSize(true);
    //         trayIcon.setToolTip("Reminder Notification");
            
    //         Map<Integer, String> eventMap = new HashMap<>(); // map to store events by ID
    //         for (Event e : elst) {
    //             eventMap.put(e.getEventID(), e.getEventName());
    //         }
            
    //         for (Reminder r : rlst) {
    //             String ename = eventMap.get(r.getEid());
    //             if (ename == null) { // handle case where event doesn't exist
    //                 continue;
    //             }
    //             Date scheduledTime = r.getDatetime(); // get the scheduled time
    //             TimerTask task = new TimerTask() {
    //                 public void run() {
    //                     try {
    //                         trayIcon.displayMessage("Reminder", "Your Event " + ename + " is happening soon!", MessageType.INFO);
    //                     } catch (Exception ex) {
    //                         System.out.println(ex);
    //                     }
    //                 }
    //             };
    //             Timer timer = new Timer();
    //             timer.schedule(task, scheduledTime); // schedule the task to run at the scheduled time
                
    //         }
    //     } else {
    //         System.out.println("System tray not supported");
         }
     
 

    // public static String assignmentname;

    // public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    //     System.out.println("What's the name of the event?");
    //     Scanner reminder = new Scanner(System.in);
    //     assignmentname = reminder.nextLine();
    //     Reminder model = new Reminder(0, null);
    //     RemindernotifUI.assignment.setText("Hey! Don't forget about " + RemindernotifController.assignmentname + "!");

    //     File file = new File("rasengan.wav");
    //     AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    //     Clip clip = AudioSystem.getClip();
    //     clip.open(audioStream);
    //     RemindernotifUI launchPage = new RemindernotifUI();
    //     clip.start();

    //     // Your code here
    // }
