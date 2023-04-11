package View;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import Controller.AccountController;
import Controller.EventReminderController;
import Controller.GradeCalculatorController;
import Model.Course;

import java.awt.Dimension;

import java.awt.Font;

import java.awt.GridLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class MenuUI extends JFrame{
   
    
    private JMenuBar menubar;
    private JPanel eventpane;
    private JPanel panel;
    private JPanel rightpanel;
    private JPanel leftpanel;
    private JMenuItem createEventMenuItem;
    private JMenuItem pomodoroClockMenuItem;
    private JMenuItem viewCoursesMenuItem;
    private JMenuItem courseTimetableMenuItem;
    private JMenuItem LogoutMenuItem;
    private JMenuItem AddCourseMenuItem;
    private JMenuItem gradeCalcMenuItem;
    private JMenuItem HomeMenuItem;
    private JMenuItem containerMenuItem;

    private JScrollPane scrollPane;
    private static JTable table;


    static MenuUI Frame;

    public MenuUI() {
     
        Frame=this;
        
        // Set up JFrame properties
        setTitle("Home Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        // Create main panel
        panel = new JPanel();
        panel.setBackground(new Color(255, 228, 225)); // Light pink background
        
        // Create menu bar and items
        menubar = new JMenuBar();
       
        HomeMenuItem = new JMenuItem("Home");
        HomeMenuItem.addActionListener(new MenuItemListener());
        HomeMenuItem.setBackground(new Color(255, 192, 203)); // Pink background
       
        createEventMenuItem = new JMenuItem("Add an Event");
        createEventMenuItem.addActionListener(new MenuItemListener());
        createEventMenuItem.setBackground(new Color(255, 215, 0)); // Yellow background

        pomodoroClockMenuItem = new JMenuItem("Pomodoro Session");
        pomodoroClockMenuItem.addActionListener(new MenuItemListener());
        pomodoroClockMenuItem.setBackground(new Color(255, 192, 203)); // Pink background

        viewCoursesMenuItem = new JMenuItem("View Courses");
        viewCoursesMenuItem.addActionListener(new MenuItemListener());
        viewCoursesMenuItem.setBackground(new Color(255, 215, 0)); // Yellow background

        containerMenuItem = new JMenuItem("Course Containers");
        containerMenuItem.addActionListener(new MenuItemListener());
        containerMenuItem.setBackground(new Color(255, 192, 203)); // Pink background

        courseTimetableMenuItem = new JMenuItem("Timetable");
        courseTimetableMenuItem.addActionListener(new MenuItemListener());
        courseTimetableMenuItem.setBackground(new Color(255, 215, 0)); // Yellow background

        AddCourseMenuItem = new JMenuItem("Add Course");
        AddCourseMenuItem.addActionListener(new MenuItemListener());
        AddCourseMenuItem.setBackground(new Color(255, 192, 203)); // Pink background

        gradeCalcMenuItem= new JMenuItem("Grade Calculator");
        gradeCalcMenuItem.addActionListener(new MenuItemListener());
        gradeCalcMenuItem.setBackground(new Color(255, 215, 0)); // Pink background


        LogoutMenuItem = new JMenuItem("Log Out");
        LogoutMenuItem.addActionListener(new MenuItemListener());
        LogoutMenuItem.setBackground(new Color(255, 192, 203)); // Yellow background

        // Add menu items to the menu bar
        
        menubar.add(HomeMenuItem);
        menubar.add(createEventMenuItem);
        menubar.add(pomodoroClockMenuItem);
        menubar.add(viewCoursesMenuItem);
        menubar.add(containerMenuItem);
        menubar.add(courseTimetableMenuItem);
        menubar.add(AddCourseMenuItem);
        menubar.add(gradeCalcMenuItem);
        menubar.add(LogoutMenuItem);

        // Create right panel
        rightpanel = new JPanel();
        rightpanel.setBackground(new Color(255, 233, 225)); // Light pink background
       
        // Create left panel
        leftpanel =new JPanel();
        leftpanel.setBackground(new Color(255, 233, 225)); // Light pink background
        Font wFont = new Font("Garamond", Font.BOLD | Font.ROMAN_BASELINE, 15);
        JLabel welcome1 = new JLabel("Hello "+ AccountController.getCurrentUser().getCurrentUsername() +"! Welcome to our Student Planner App" );
        welcome1.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome1.setFont(wFont);
        JLabel welcome2 = new JLabel("Remember 'Failing to plan is planning to fail.' - Alan Lakein." );
        welcome2.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome2.setFont(wFont);
        
        leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));
        leftpanel.add(Box.createVerticalGlue());
        leftpanel.add(welcome1);
        leftpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftpanel.add(welcome2);
        leftpanel.add(Box.createVerticalGlue());
        

        // Create event panel
        eventpane = new JPanel();
        eventpane.setBackground(new Color(255, 233, 225)); // Light pink background
        eventpane.setLayout(new BorderLayout());
        eventpane.setBounds(200, 1, 1000, 800);

        
        // Create label for events table
        Font eFont = new Font("Garamond", Font.BOLD | Font.ITALIC, 30);
        JLabel elabel = new JLabel("Your Events");
        elabel.setFont(eFont);
        elabel.setHorizontalAlignment(JLabel.CENTER);
        elabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        eventpane.add(elabel, BorderLayout.NORTH);
        
        // Create table for events
        
        
        table = loadEventTable();
        table.setBackground(Color.white);
        table.getTableHeader().setBackground(new Color(255, 192, 203)); // Pink background
        table.setRowHeight(20);
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setShowGrid(false);
        TableModel model = table.getModel();
        MyTableModelListener listener = new MyTableModelListener();
        model.addTableModelListener(listener);
      
        // Add table to scroll pane
        scrollPane = new JScrollPane(table);
        eventpane.add(scrollPane, BorderLayout.CENTER);

        // Add left and right panels to main panel
        panel.setLayout(new GridLayout(2, 5));
        panel.add(leftpanel);
        // panel.add(rightpanel);
        panel.add(eventpane);
        

        // Add menu bar and main panel to JFrame
        setJMenuBar(menubar);
        getContentPane().add(panel);

        // Set JFrame size and visibility
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
  
    public static JTable loadEventTable(){
        return EventReminderController.getevents();
        
    }

    public static JTable getEventTable(){
        return table;
    }

    public static void changeEventTable(JTable t) {
    
        DefaultTableModel m = (DefaultTableModel) t.getModel();
        table.setModel(m);
        m.fireTableDataChanged();
       
        
    }

 
    class MenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){ 
            if(e.getSource()==HomeMenuItem){
                Frame.getContentPane().removeAll();
                Frame.getContentPane().revalidate();
                Frame.setJMenuBar(menubar);
                panel.add(leftpanel);
                // panel.add(rightpanel);
                panel.add(eventpane);
                Frame. setContentPane(panel);
            }
        
            if(e.getSource()==createEventMenuItem){

             
                JPanel EventForm = new CalendarUI().DisplayEventForm(Frame);
                EventForm.setVisible(true);
                Frame.getContentPane().removeAll();
                Frame.getContentPane().revalidate();
                Frame.setContentPane(EventForm);

            } if (e.getSource()==pomodoroClockMenuItem){
                new PomodoroView();
            }
            if (e.getSource()==LogoutMenuItem){
                Frame.dispose();
                new LoginUI();
            }
            if (e.getSource()==viewCoursesMenuItem){
                new CourseInfoUI();
            }
            if (e.getSource()==containerMenuItem){
                new ContainerUI();
            }

            if(e.getSource()==AddCourseMenuItem){
                new CourseUI();
            }
            if(e.getSource()==courseTimetableMenuItem){
                JPanel tt = new TimetableUI().DisplayTimeTable(Frame);
                tt.setVisible(true);
                Frame.getContentPane().removeAll();
                Frame.getContentPane().revalidate();
                Frame.setContentPane(tt);
            }
            if (e.getSource()==gradeCalcMenuItem){
                CalculatorUI window = new CalculatorUI();
                GradeCalculatorController gc = new GradeCalculatorController(window);
                window.frame.setVisible(true);

                Frame.getContentPane().removeAll();
                Frame.getContentPane().revalidate();
                Frame.setContentPane(window.frame);
                
            }
        
        }  
    }

    
    class MyTableModelListener implements TableModelListener {
        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            Object data = model.getValueAt(row, column);
            System.out.println("Cell at row " + row + " and column " + column + " is changed to " + data);
        }
    }
    
}    

