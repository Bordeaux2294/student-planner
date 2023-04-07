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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Controller.EventReminderController;
import Controller.GradeCalculatorController;

import java.awt.Dimension;

import java.awt.Font;

import java.awt.GridLayout;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.*;


public class MenuUI extends JFrame{
    private static String currentUsername;
    
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

    private JScrollPane scrollPane;
    private static JTable table;


    static MenuUI Frame;

    public MenuUI(String currentUsername) {
        MenuUI.currentUsername = currentUsername;
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

        pomodoroClockMenuItem = new JMenuItem("Start Pomodoro Session");
        pomodoroClockMenuItem.addActionListener(new MenuItemListener());
        pomodoroClockMenuItem.setBackground(new Color(255, 192, 203)); // Pink background

        viewCoursesMenuItem = new JMenuItem("View Your Courses");
        viewCoursesMenuItem.addActionListener(new MenuItemListener());
        viewCoursesMenuItem.setBackground(new Color(255, 215, 0)); // Yellow background

        courseTimetableMenuItem = new JMenuItem("Course Timetable");
        courseTimetableMenuItem.addActionListener(new MenuItemListener());
        courseTimetableMenuItem.setBackground(new Color(255, 192, 203)); // Pink background

        AddCourseMenuItem = new JMenuItem("Add Course");
        AddCourseMenuItem.addActionListener(new MenuItemListener());
        AddCourseMenuItem.setBackground(new Color(255, 215, 0)); // Yellow background

        gradeCalcMenuItem= new JMenuItem("Calculate Course Grade");
        gradeCalcMenuItem.addActionListener(new MenuItemListener());
        gradeCalcMenuItem.setBackground(new Color(255, 192, 203)); // Pink background


        LogoutMenuItem = new JMenuItem("Log Out");
        LogoutMenuItem.addActionListener(new MenuItemListener());
        LogoutMenuItem.setBackground(new Color(255, 215, 0));

        // Add menu items to the menu bar
        
        menubar.add(HomeMenuItem);
        menubar.add(createEventMenuItem);
        menubar.add(pomodoroClockMenuItem);
        menubar.add(viewCoursesMenuItem);
        menubar.add(courseTimetableMenuItem);
        menubar.add(AddCourseMenuItem);
        menubar.add(gradeCalcMenuItem);
        menubar.add(LogoutMenuItem);

        // Create right panel
        rightpanel = new JPanel();
        rightpanel.setBackground(new Color(255, 228, 225)); // Light pink background
       
        // Create left panel
        leftpanel =new JPanel();
        leftpanel.setBackground(new Color(255, 228, 225)); // Light pink background
        Font wFont = new Font("Garamond", Font.BOLD | Font.ROMAN_BASELINE, 15);
        JLabel welcome1 = new JLabel("Hello "+ currentUsername +"! Welcome to our Student Planner App" );
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
        eventpane.setBackground(new Color(255, 228, 225)); // Light pink background
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
        setColumnWidths(table, new int[] { 100, 100, 150, 150, 100,100 });
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setShowGrid(false);
    
        // Add table to scroll pane
        scrollPane = new JScrollPane(table);
        eventpane.add(scrollPane, BorderLayout.CENTER);

        // Add left and right panels to main panel
        panel.setLayout(new GridLayout(2, 5));
        panel.add(leftpanel);
        panel.add(rightpanel);
        panel.add(eventpane);
        

        // Add menu bar and main panel to JFrame
        setJMenuBar(menubar);
        getContentPane().add(panel);

        // Set JFrame size and visibility
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void setColumnWidths(JTable t, int[] widths) {
        TableColumnModel columnModel = t.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(widths[i]);
            column.setMaxWidth(widths[i]);
            column.setMinWidth(widths[i]);
        }
    }
    public static JTable loadEventTable(){
        return EventReminderController.getevents();
        
    }
    public static String getCurrentUser(){
        return currentUsername;
    }

    class MenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){ 
            if(e.getSource()==HomeMenuItem){
                Frame.getContentPane().removeAll();
                Frame.getContentPane().revalidate();
                Frame.setJMenuBar(menubar);
                panel.add(leftpanel);
                panel.add(rightpanel);
                panel.add(eventpane);
                Frame. setContentPane(panel);
            }
        
            if(e.getSource()==createEventMenuItem){

             
                JPanel EventForm = new CalendarUI(currentUsername).DisplayEventForm(Frame);
                EventForm.setVisible(true);
                Frame.getContentPane().removeAll();
                Frame.getContentPane().revalidate();
                Frame.setContentPane(EventForm);

            } if (e.getSource()==pomodoroClockMenuItem){
                new PomodoroUI();
            }
            if (e.getSource()==LogoutMenuItem){
                Frame.dispose();
                new LoginUI();
            }
            if (e.getSource()==viewCoursesMenuItem){
                new ContainerUI();
            }

            if(e.getSource()==AddCourseMenuItem){
                new CourseUI(currentUsername);
            }
            if(e.getSource()==courseTimetableMenuItem){
                JPanel tt = new TimetableUI(currentUsername).DisplayTimeTable(Frame);
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
}