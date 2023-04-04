package Controller;

import javax.swing.*;

import Model.PomodoroModel;
import View.PomodoroView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroController {
    public static Timer timer;

    public static void main(String[] args) {
        PomodoroView pomodoro = new PomodoroView();
        PomodoroModel model = new PomodoroModel();

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.elapsedTime += 1000;
                model.hours = model.elapsedTime / 3600000;
                model.minutes = (model.elapsedTime / 60000) % 60;
                model.seconds = (model.elapsedTime / 1000) % 60;

                model.seconds_string = String.format("%02d", model.seconds);
                model.minutes_string = String.format("%02d", model.minutes);
                model.hours_string = String.format("%02d", model.hours);

                pomodoro.timeLabel
                        .setText(model.hours_string + ":" + model.minutes_string + ":" + model.seconds_string);
                if (pomodoro.began == true) {
                    model.started = true;
                    pomodoro.startButton.setText("STOP");
                } else {
                    model.started = false;
                    pomodoro.startButton.setText("START");
                }
                if (e.getSource() == pomodoro.enterButton1) {
                    pomodoro.start();
                }

            }

        });

    }
}
