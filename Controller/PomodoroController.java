import javax.swing.*;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class PomodoroController {
    static Timer timer;
    static PomodoroView pomodoro;
    static PomodoroModel model;
    public static void main(String[] args) throws InterruptedException {
        pomodoro = new PomodoroView();
        model = new PomodoroModel();

        timer = new Timer(1000, new ActionListener(){
            int count =0;
            @Override
            public void actionPerformed(ActionEvent e) {

                model.elapsedTime +=1000;
                model.hours = model.elapsedTime/3600000;
                model.minutes = (model.elapsedTime/60000)%60;
                model.seconds =(model.elapsedTime/1000)%60;

                    if ((model.seconds-1) % pomodoro.study==0){
                    timer.stop();
                    try {
                        Thread.sleep(pomodoro.breaks*1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (count<pomodoro.pomodoros){
                    timer.start();
                    count++;
                    }
                    else if (count == pomodoro.pomodoros + 1) {
                        timer.stop();
                    }

                    System.out.println(count);
                }




                model.seconds_string = String.format("%02d",model.seconds);
                model.minutes_string = String.format("%02d",model.minutes);
                model.hours_string = String.format("%02d",model.hours);

                pomodoro.timeLabel.setText(model.hours_string + ":" + model.minutes_string + ":" + model.seconds_string);
            }
        });




        }

    }

