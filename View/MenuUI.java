package View;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.plaf.ColorChooserUI;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxCursor.Open;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
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
    private String currentUsername;
    
    private JMenuBar menubar;
    private JMenu optionsMenu;
    private JPanel eventpane;

    private JMenuItem createEventMenuItem;
    private JMenuItem pomodoroClockMenuItem;
    private JMenuItem viewCoursesMenuItem;
    private JMenuItem courseTimetableMenuItem;

    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel tableModel;

    static MenuUI Frame;

   public MenuUI(String currentUsername){
    this.currentUsername =currentUsername;
     
     Frame=this;
     eventpane= new JPanel();
     menubar = new JMenuBar();
 
     // Create menu items
     createEventMenuItem = new JMenuItem("Add an Event");
     createEventMenuItem.addActionListener(new MenuItemListener());
     createEventMenuItem.setBackground(Color.yellow);
     pomodoroClockMenuItem = new JMenuItem(" Start Pomodoro Session");
     pomodoroClockMenuItem.addActionListener(new MenuItemListener());
     pomodoroClockMenuItem.setBackground(Color.pink);

     viewCoursesMenuItem = new JMenuItem("View Your Courses");
     viewCoursesMenuItem.addActionListener(new MenuItemListener());
     viewCoursesMenuItem.setBackground(Color.yellow);
     courseTimetableMenuItem = new JMenuItem(" Course Timetable");
     courseTimetableMenuItem.addActionListener(new MenuItemListener());
     courseTimetableMenuItem.setBackground(Color.pink);
     
     // Add menu items to the menu bar
     JMenu optionsMenu = new JMenu("Options");
   

     menubar.add(createEventMenuItem);
     menubar.add(pomodoroClockMenuItem);
     menubar.add(viewCoursesMenuItem);
     menubar.add(courseTimetableMenuItem);
     
     eventpane.setLayout(new BorderLayout());
     tableModel = new DefaultTableModel(new Object[]{"Event Name", "Start Datetime", "End Datetime", "Status", "Reminder"}, 16);
     table = new JTable(tableModel);
     table.setName("All Events");

     scrollPane = new JScrollPane(table);
     scrollPane.setPreferredSize(new Dimension(600, 200));
     JLabel elabel= new JLabel("All Events");
     elabel.setFont(elabel.getFont().deriveFont(30f));
     eventpane.add(elabel,  BorderLayout.NORTH);
     eventpane.add(scrollPane, BorderLayout.BEFORE_LINE_BEGINS);
 
     setJMenuBar(menubar);
     getContentPane().add(eventpane);
        
     Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
     int width = size.width;
     int height = size.height;
     setSize(width, height);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setVisible(true);
 
   }


    class MenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){ 
            if(e.getSource()==createEventMenuItem){

                System.out.println("true");
                JPanel EventForm = new CalendarUI(currentUsername).DisplayEventForm(Frame);
                EventForm.setVisible(true);
                Frame.getContentPane().removeAll();
                Frame.getContentPane().revalidate();
                Frame.setContentPane(EventForm);

            } else if (e.getSource()==pomodoroClockMenuItem){
                new PomodoroUI();
            }
        
        }  
    }
}