package Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

import Controller.PomodoroController;

import java.awt.event.*;


public class PomodoroModel {
    
    public int elapsedTime = 0;
    public int seconds = 0;
    public int minutes = 0;
    public int hours = 0;
    public boolean started = false;

    public String seconds_string = String.format("%02d",seconds);
    public String minutes_string = String.format("%02d",minutes);
    public String hours_string = String.format("%02d",hours);
    public static Timer timer;

    public PomodoroModel(int study, int pomodoros, int breaks)throws InterruptedException{
    
    timer = new Timer(1000, new ActionListener(){
        int count =0;
        @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000;
                hours = elapsedTime / 3600000;
                minutes = (elapsedTime / 60000) % 60;
                seconds = (elapsedTime / 1000) % 60;

                if (count < pomodoros ) {
                    if (!started && (seconds ) % study == 1 && seconds >= study) {
                        started = true;
                        System.out.println("Time for a break!");
                        DisplayNotification("Pomodoro Timer", "Time for a break!");
                        try {
                            Thread.sleep(study * 1000);
                        } catch (InterruptedException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        

                    } else if (started && (seconds ) % breaks == 1) {
                        started = false;
                        System.out.println("Back to work!");
                        DisplayNotification("Pomodoro Timer", "Back to work!");
                        count++;
                        try {
                            Thread.sleep(breaks * 1000);
                        } catch (InterruptedException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    
                }  if (count==pomodoros){
                    timer.stop();
                    System.out.println("All done!");
                    DisplayNotification("Pomodoro Timer", "All done!");
                }

                seconds_string = String.format("%02d", seconds);
                minutes_string = String.format("%02d", minutes);
                hours_string = String.format("%02d", hours);

                PomodoroController.setTimelabel(hours_string + ":" + minutes_string + ":" + seconds_string);
            }
        });

      
    
    // Start the timer
    timer.start();
}   
    public void start(){
        timer.start();
        PomodoroController.setBegan(true);
    }
    public void stop(){
        timer.stop();
    }
    public void reset(){
        timer.stop();
        elapsedTime=0;
        seconds =0;
        minutes=0;
        hours=0;
        seconds_string = String.format("%02d",seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        PomodoroController.setTimelabel(hours_string+":"+minutes_string+":"+seconds_string);
        PomodoroController.setBegan(false); 
    }
      
    private static void DisplayNotification(String title, String message) {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Pomodoro Timer");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Pomodoro Timer");
        
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
            trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            System.out.println("TrayIcon could not be added");
        }
    }
}






