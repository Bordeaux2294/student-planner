import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroView implements ActionListener {
    JFrame frame = new JFrame();
    JButton startButton =  new JButton("START");
    JButton resetButton =  new JButton("RESET");
    JLabel timeLabel = new JLabel();
    PomodoroModel pomodoro = new PomodoroModel();
    String sec = pomodoro.seconds_string;
    String min = pomodoro.minutes_string;
    String hrs = pomodoro.hours_string;



     PomodoroView(){
        timeLabel.setText(hrs + ":" + min + ":" + sec);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        startButton.setFocusable(false);


        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        resetButton.setFocusable(false);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== startButton){
            if(pomodoro.started==false){
                pomodoro.started=true;
                startButton.setText("STOP");
                start();
            }
            else {
                pomodoro.started=false;
                startButton.setText("START");
                stop();
            }

        }
        if (e.getSource()==resetButton){
            pomodoro.started=false;
            startButton.setText("START");
            stop();
            pomodoro.elapsedTime = 0;
            pomodoro.seconds = 0;
            pomodoro.minutes = 0;
            pomodoro.hours = 0;
            pomodoro.seconds_string = String.format("%02d",pomodoro.seconds);
            pomodoro.minutes_string = String.format("%02d",pomodoro.minutes);
            pomodoro.hours_string = String.format("%02d",pomodoro.hours);
            timeLabel.setText(hrs + ":" + min + ":" + sec);

        }
    }


    void start(){
        PomodoroController.timer.start();
    }

    void stop(){
        PomodoroController.timer.stop();
    }

    void reset(){
        PomodoroController.timer.stop();
    }

}
