package Controller;

import javax.swing.*;

import Model.Pomodoro;
import Model.PomodoroModel;
import Model.User;
import View.PomodoroView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.util.TimerTask;

public class PomodoroController {
    static Timer timer;
    static PomodoroView pomodoro;
    static PomodoroModel model;

    public PomodoroController(PomodoroView pUI){
        pomodoro = pUI;
        try {
            model = new PomodoroModel(pomodoro.getStudy(), pomodoro.getPomodoros(), pomodoro.getBreaks());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        }

        public static void setTimelabel(String s){
            pomodoro.setTimelabel(s);
        }
        public static void setBegan(boolean b){
            pomodoro.setBegan(b);
        }
        public String get_SecStr(){
             return model.seconds_string;
        }
        public String get_MinStr (){
            return model.minutes_string;
        }
        public String get_HourStr (){
             return model.hours_string;
        }
        
        public int getElapsedTime() {
            return model.elapsedTime;
        }
    
        public int getSeconds() {
            return model.seconds;
        }
    
        public int getMinutes() {
            return model.minutes;
        }
    
        public int getHours() {
            return model.hours;
        }
    
        public boolean isStarted() {
            return model.started;
        }
        public boolean setStarted(boolean b) {
            return model.started=b;
        }
        public void Reset(){
            model.reset();
        }
        public void Start(){
            model.start();
        }
        public void Stop(){
            model.stop();
        }
    }

