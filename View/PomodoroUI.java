package View;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PomodoroUI implements ActionListener{
    
    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JButton enterButton = new JButton("ENTER");
    static JTextField textField = new JTextField();


    JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    static int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    JLabel pomodoroLbl = new JLabel("Number of pomodoro sessions");
    String seconds_string = String.format("%02d",seconds);
    String minutes_string = String.format("%02d",minutes);
    String hours_string = String.format("%02d",hours);






    public PomodoroUI(){
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timeLabel.setBorder((BorderFactory.createBevelBorder(1)));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        enterButton.setBounds(200,50,100,50);
        enterButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        enterButton.setFocusable(false);
        enterButton.addActionListener(this);

        textField.setBounds(100,50,100,50);
        textField.setPreferredSize(new Dimension(250,40));

        pomodoroLbl.setBounds(100,15,200,50);

        frame.add(pomodoroLbl);
        frame.add(textField);
        frame.add(timeLabel);
        frame.add(startButton);
        frame.add(resetButton);
        frame.add(enterButton);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==enterButton){
            pomodoro = Integer.parseInt(textField.getText());
        }
        if (e.getSource() == startButton) {
            start();
            if (started == false) {
                started = true;
                startButton.setText("STOP");
                start();
            } else {
                started = false;
                startButton.setText("START");
                stop();
            }
        }
        if (e.getSource() == resetButton) {
            started = false;
            startButton.setText("START");
            reset();
        }

    }

    


}
