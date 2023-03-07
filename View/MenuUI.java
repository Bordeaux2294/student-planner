package View;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;



import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Container;
import java.awt.Component;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MenuUI extends JFrame{
    JPanel optionspane;
    JButton Button1;
    JButton Button2;
    static MenuUI Frame;
    MenuUI oldFrame;
   public MenuUI(){
     Frame=this;
     oldFrame=Frame;
     optionspane=new JPanel();
     setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
     Button1= new JButton("Create Event");
     Button1.setBounds(500,1000,950,300);
     Button1.setAlignmentX(CENTER_ALIGNMENT);

     Button2= new JButton("Pomodoro Clock");
     Button2.setBounds(500,1500,950,300);
     Button2.setAlignmentX(CENTER_ALIGNMENT);
     
     Button1.addActionListener(new ButtonListener());
     this.getContentPane().add(Button1);

     Button2.addActionListener(new ButtonListener());
     this.getContentPane().add(Button2);
     
     Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = size.width;
                    int height = size.height;
                    setSize(width, height);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setVisible(true);
   }
    
    // public static void main(String[] args) throws ParseException{
    //     new MenuUI(); 
        // int sh= 1;
        // int sm = 30;
        // int day =30;
        // int month =3;
        // int year =2010;
       // Date currentDate = new Date();
       // System.out.println(currentDate);
        // // SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy;HH:mm");
        // Date dateTime =sf.parse(day+"-"+month+"-"+year+";"+sh+":"+sm);
        // try {
        //     // Open a file output stream in append mode
        //     FileOutputStream fos = new FileOutputStream("eventlist", true);
    
        //     // Create a print writer to write to the file output stream
        //     PrintWriter pw = new PrintWriter(fos);
    
        //     // Write the event's information to the file
        //     pw.println( day + "-" + month + "-" + year + ";"+
        //     sh + ":" + sm);
    
        //     // Close the print writer and file output stream
        //     pw.close();
        //     fos.close();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    // }
    
    // public void RefreshPage(Boolean b){
    //     if (b==true)
    //         Frame.setContentPane(oldFrame.getContentPane());

    // }
         
    
    class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){ 
            if(e.getSource()==Button1){
                MenuUI oldFrame = Frame;
                JPanel EventForm= new CalendarUI(Frame).DisplayEventForm();
                EventForm.setVisible(true);
                Frame.getContentPane().removeAll();
                Frame.getContentPane().revalidate();
                Frame.setContentPane(EventForm);
            } else if (e.getSource()==Button2){
                new PomodoroUI();
            }
        
        }  
    }
}