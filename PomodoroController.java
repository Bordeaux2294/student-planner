import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroController {
    static Timer timer;
    public static void main(String[] args) {
        PomodoroView pomodoro = new PomodoroView();
        PomodoroModel model_pomo = new PomodoroModel();


        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model_pomo.elapsedTime +=1000;
                model_pomo.hours = model_pomo.elapsedTime/3600000;
                model_pomo.minutes = (model_pomo.elapsedTime/60000)%60;
                model_pomo.seconds =(model_pomo.elapsedTime/1000)%60;

                model_pomo.seconds_string = String.format("%02d",model_pomo.seconds);
                model_pomo.minutes_string = String.format("%02d",model_pomo.minutes);
                model_pomo.hours_string = String.format("%02d",model_pomo.hours);

                pomodoro.timeLabel.setText(model_pomo.hours_string + ":" + model_pomo.minutes_string + ":" + model_pomo.seconds_string);

            }


        });


    }

}
