package Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


import View.PomodoroUI;

public class Pomodoro {
    int pomodoros = PomodoroUI.getPomodoros();

    static int pomodoro;
    static int resetnum=0;
    static int seconds = 0;
    int minutes = 0;
    int hours = 0;
    int elapsedTime = 0;
    String seconds_string = String.format("%02d",seconds);
    String minutes_string = String.format("%02d",minutes);
    String hours_string = String.format("%02d",hours);


    Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                elapsedTime+= 1000;
                hours = (elapsedTime/3600000);
                minutes = (elapsedTime/60000)%60;
                seconds = (elapsedTime/1000)%60;
                if (seconds ==3) {
                    reset();
                    countReset();
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    start();
                }
               if (resetnum == pomodoro){
                   reset();
                   startButton.setText("START");
                }
                seconds_string = String.format("%02d",seconds);
                minutes_string = String.format("%02d",minutes);
                hours_string = String.format("%02d",hours);
                timeLabel.setText(hours_string+ ":" + minutes_string + ":" + seconds_string);

        }
    });

    public Pomodoro(){}
    public void start() {
        timer.start();
            }

    public void  stop(){
        timer.stop();
    }

    public void reset(){
        timer.stop();
        elapsedTime =0;
        seconds =0;
        hours =0;
        seconds_string = String.format("%02d",seconds);
        minutes_string = String.format("%02d",minutes);
        hours_string = String.format("%02d",hours);
        timeLabel.setText(hours_string+ ":" + minutes_string + ":" + seconds_string);
    }

    public String secStr(){
           
    }

    public String secStr(){

    }

    public String secStr(){

    }

    public static int getSeconds(){
        return (seconds);
    }

    public static void countReset(){
        resetnum += 1;
    }

    public static int getPomodoros(){
        return pomodoro;
    }
}
