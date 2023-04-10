package View;
import javax.swing.*;

import Controller.PomodoroController;
import Model.PomodoroModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class PomodoroView implements ActionListener {
    private int pomodoros;
    private int study;
    private int breaks;
    private boolean began = false;
    private String cycles;
    private Integer Cycles;
    private boolean reset = false;
    private JFrame frame = new JFrame();
    private JButton startButton = new JButton("START");
    private JButton resetButton = new JButton("RESET");
    private static int resetnum=0;
    private JLabel timeLabel = new JLabel();
    private JLabel pomodorolbl = new JLabel("Pomodoros:");
    private JLabel breaklbl = new JLabel("Length of break:");
    private JLabel studylbl = new JLabel("Length of study per cycle:");
    private JTextField pomodoroNum = new JTextField();
    private JTextField breakNum = new JTextField();
    private JTextField studyNum = new JTextField();

    private PomodoroController model;
    private String sec = "00";
    private String min = "00";
    private String hrs = "00";

    public PomodoroView(){
        timeLabel.setText(hrs + ":" + min + ":" + sec);
        timeLabel.setBounds(100,200,200,100);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        
        startButton.setBounds(100,300,100,50);
        startButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        pomodoroNum.setBounds(250,50,50,35);
        breakNum.setBounds(250,100,50,35);
        studyNum.setBounds(250,150,50,35);

        pomodorolbl.setBounds(100,50,700,35);
        breaklbl.setBounds(100,100,700,35);
        studylbl.setBounds(100,150,700,35);



        resetButton.setBounds(200,300,100,50);
        resetButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(pomodorolbl);
        frame.add(breaklbl);
        frame.add(studylbl);
        frame.add(pomodoroNum);
        frame.add(breakNum);
        frame.add(studyNum);
        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void setTimelabel(String s){
        timeLabel.setText(s);
    }

    public void setBegan(boolean b){
        began = b;
    }
    public int getPomodoros(){
       return pomodoros;
    }
    public int getStudy(){
        return study;
    }
    public int getBreaks(){
        return breaks;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            if (!began){
                pomodoros = parseInt( (String) pomodoroNum.getText());
                breaks = parseInt( (String) breakNum.getText());
                study = parseInt( (String) studyNum.getText());

                began = true;
                model = new PomodoroController(this);
                sec = model.get_SecStr();
                min = model.get_MinStr();
                hrs = model.get_HourStr();
                startButton.setText("Stop");
                model.Start();
                
            }
            else {
                began=false;
                model.Stop();
                startButton.setText("Start");
            }
        }
        if(e.getSource()==resetButton){
            model.setStarted(false);
            model.Reset();
            startButton.setText("Start");
        }
    }

  
    

    public static void countReset(){
        resetnum += 1;
    }
}
