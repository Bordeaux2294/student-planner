package View;

import javax.swing.*;

import Controller.PomodorUIController;
import Model.PomodoroModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroView implements ActionListener {
    public boolean began = false;
    boolean reset = false;
    JFrame frame = new JFrame();
    public JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    public JButton enterButton1 = new JButton("Enter");
    JButton enterButton2 = new JButton("Enter");
    JButton enterButton3 = new JButton("Enter");

    public JLabel timeLabel = new JLabel();
    JLabel pomodorolbl = new JLabel("Pomodoros:");
    JLabel breaklbl = new JLabel("Length of break:");
    JLabel studylbl = new JLabel("Length of study per cycle:");
    JTextField pomodoroNum = new JTextField();
    JTextField breakNum = new JTextField();
    JTextField studyNum = new JTextField();

    PomodoroModel model = new PomodoroModel();
    String sec = model.seconds_string;
    String min = model.minutes_string;
    String hrs = model.hours_string;

    public PomodoroView() {
        timeLabel.setText(hrs + ":" + min + ":" + sec);
        timeLabel.setBounds(100, 200, 200, 100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100, 300, 100, 50);
        startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        pomodoroNum.setBounds(250, 50, 50, 35);
        breakNum.setBounds(250, 100, 50, 35);
        studyNum.setBounds(250, 150, 50, 35);

        pomodorolbl.setBounds(100, 50, 700, 35);
        enterButton1.setBounds(300, 50, 70, 35);
        breaklbl.setBounds(100, 100, 700, 35);
        enterButton2.setBounds(300, 100, 70, 35);
        studylbl.setBounds(100, 150, 700, 35);
        enterButton3.setBounds(300, 150, 70, 35);

        resetButton.setBounds(200, 300, 100, 50);
        resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(enterButton1);
        frame.add(enterButton2);
        frame.add(enterButton3);
        frame.add(pomodorolbl);
        frame.add(breaklbl);
        frame.add(studylbl);
        frame.add(pomodoroNum);
        frame.add(breakNum);
        frame.add(studyNum);
        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!began) {
                began = true;
                start();
            } else {
                began = false;
                stop();
            }
        }
        if (e.getSource() == resetButton) {
            model.started = false;
            reset();
        }
    }

    public void start() {
        PomodorUIController.timer.start();
    }

    void stop() {
        PomodorUIController.timer.stop();
    }

    void reset() {
        PomodorUIController.timer.stop();
        reset = true;
        model.elapsedTime = 0;
        model.seconds = 0;
        model.minutes = 0;
        model.hours = 0;
        model.seconds_string = String.format("%02d", model.seconds);
        model.minutes_string = String.format("%02d", model.minutes);
        model.hours_string = String.format("%02d", model.hours);
        timeLabel.setText(model.hours_string + ":" + model.minutes_string + ":" + model.seconds_string);

    }
}
