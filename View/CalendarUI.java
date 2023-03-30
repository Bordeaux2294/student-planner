package View;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import javax.swing.*;

import Controller.EventReminderController;

public class CalendarUI {
     
    //attributes for EventForm
    private JTextField eventNameField;
    private JComboBox<Integer> startHourComboBox;
    private JComboBox<Integer> startMinuteComboBox;
    private JComboBox<Integer> startDayComboBox;
    private JComboBox<String> startMonthComboBox;
    private JComboBox<Integer> startYearComboBox;
    private JComboBox<Integer> endDayComboBox;
    private JComboBox<String> endMonthComboBox;
    private JComboBox<Integer> endYearComboBox;
    private JComboBox<Integer> endHourComboBox;
    private JComboBox<Integer> endMinuteComboBox;
    private JCheckBox setReminderBox;
    private JButton addButton;
    private JButton closeButton;


    private JComboBox<Integer> DayComboBox;
    private JComboBox<String> MonthComboBox;
    private JComboBox<Integer> YearComboBox;
    private JComboBox<Integer> HourComboBox;
    private JComboBox<Integer> MinuteComboBox;

    private JPanel panel;
    private JPanel Rpanel;
    private String  currentUsername;
    private MenuUI F;

    public CalendarUI(String currentUsername){
        this.currentUsername=currentUsername;
    }
    public JPanel DisplayEventForm(MenuUI F){
        this.F =F;
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 8, 5);

        // Add a label and text field for the event name
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Event Name:"), constraints);
        eventNameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridwidth = 3;
        panel.add(eventNameField, constraints);
        
        // Add a label and dropdown menus for the start date and time
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(new JLabel("Start Date:"), constraints);
        startDayComboBox = new JComboBox<>(getDayArray());
        constraints.gridx = 1;
        panel.add(startDayComboBox, constraints);
        startMonthComboBox = new JComboBox<>(getMonthArray());
        constraints.gridx = 2;
        panel.add(startMonthComboBox, constraints);
        startYearComboBox = new JComboBox<>(getYearArray());
        constraints.gridx = 3;
        panel.add(startYearComboBox, constraints);
        constraints.gridx = 4;
        panel.add(new JLabel("Start Time:"), constraints);
        startHourComboBox = new JComboBox<>(getHourArray());
        constraints.gridx = 5;
        panel.add(startHourComboBox, constraints);
        startMinuteComboBox = new JComboBox<>(getMinuteArray());
        constraints.gridx = 6;
        panel.add(startMinuteComboBox, constraints);

        // Add a label and dropdown menus for the end date and time
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("End Date:"), constraints);
        endDayComboBox = new JComboBox<>(getDayArray());
        constraints.gridx = 1;
        panel.add(endDayComboBox, constraints);
        endMonthComboBox = new JComboBox<>(getMonthArray());
        constraints.gridx = 2;
        panel.add(endMonthComboBox, constraints);
        endYearComboBox = new JComboBox<>(getYearArray());
        constraints.gridx = 3;
        panel.add(endYearComboBox, constraints);
        constraints.gridx = 4;
        panel.add(new JLabel("End Time:"), constraints);
        endHourComboBox = new JComboBox<>(getHourArray());
        constraints.gridx = 5;
        panel.add(endHourComboBox, constraints);
        endMinuteComboBox = new JComboBox<>(getMinuteArray());
        constraints.gridx = 6;
        panel.add(endMinuteComboBox, constraints);

        // Add a checkbox to add reminder field
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        setReminderBox = new JCheckBox("Set Reminder");
        setReminderBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (setReminderBox.isSelected()) {
                    Rpanel.setVisible(true);
                } else {
                    Rpanel.setVisible(false);
                }
            }
        });
        panel.add(setReminderBox, constraints);


        // Add a panel for the reminder field
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 7;
        panel.add(getRpanel(), constraints);

        // Add a button to submit the form
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 7;
        addButton = new JButton("Add Event");
        addButton.addActionListener(new AddButtonListener());
        panel.add(addButton, constraints);

        // Add a button to go back to previous window
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 7;
        closeButton = new JButton(" < Back");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==closeButton) {
                       F.dispose();
                       new MenuUI(currentUsername);
                } 
            }
        });
        panel.add(closeButton, constraints);
      
        // Add the panel to the JFrame and make it visible
        return panel;
        
    }

    private JPanel getRpanel(){
        Rpanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 8, 5);
         

        // Add a label and dropdown menus for the date and time of the Reminder
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        Rpanel.add(new JLabel("Date:"), constraints);
        DayComboBox = new JComboBox<>(getDayArray());
        constraints.gridx = 1;
        Rpanel.add(DayComboBox, constraints);
        MonthComboBox = new JComboBox<>(getMonthArray());
        constraints.gridx = 2;
        Rpanel.add(MonthComboBox, constraints);
        YearComboBox = new JComboBox<>(getYearArray());
        constraints.gridx = 3;
        Rpanel.add(YearComboBox, constraints);
        constraints.gridx = 4;
        Rpanel.add(new JLabel("Time:"), constraints);
        HourComboBox = new JComboBox<>(getHourArray());
        constraints.gridx = 5;
        Rpanel.add(HourComboBox, constraints);
        MinuteComboBox = new JComboBox<>(getMinuteArray());
        constraints.gridx = 6;
        Rpanel.add(MinuteComboBox, constraints);
        constraints.gridx = 7;
        Rpanel.setVisible(false);
        return Rpanel;
    }


    private Integer[] getDayArray() {
        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = i + 1;
        }
        return days;
    }
    
    private String[] getMonthArray() {
        String[] months = new String[12];
        months[0] = "January";
        months[1] = "February";
        months[2] = "March";
        months[3] = "April";
        months[4] = "May";
        months[5] = "June";
        months[6] = "July";
        months[7] = "August";
        months[8] = "September";
        months[9] = "October";
        months[10] = "November";
        months[11] = "December";
        return months;
    }
    
    private Integer[] getYearArray() {
        Integer[] years = new Integer[100];
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        for (int i = 0; i < 100; i++) {
            years[i] = currentYear + i;
        }
        return years;
    }
    
    private Integer[] getHourArray() {
        Integer[] hours = new Integer[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = i;
        }
        return hours;
    }
    
    private Integer[] getMinuteArray() {
        Integer[] minutes = new Integer[60];
        for (int i = 0; i < 60; i++) {
            minutes[i] = i;
        }
        return minutes;
    }
    
    class AddButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        String eventName = eventNameField.getText();
        String startDate = startYearComboBox.getSelectedItem().toString() + "-" + 
                           (startMonthComboBox.getSelectedIndex() + 1) + "-" + 
                           startDayComboBox.getSelectedItem().toString();
        String startTime = startHourComboBox.getSelectedItem().toString() + ":" + 
                           startMinuteComboBox.getSelectedItem().toString() + ":00";
        String endDate = endYearComboBox.getSelectedItem().toString() + "-" + 
                         (endMonthComboBox.getSelectedIndex() + 1) + "-" + 
                         endDayComboBox.getSelectedItem().toString();
        String endTime = endHourComboBox.getSelectedItem().toString() + ":" + 
                         endMinuteComboBox.getSelectedItem().toString() + ":00";
        

        // Convert the dates and times to Date objects
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDateTime = null;
        Date endDateTime = null;
        Date currentDate = new Date();
        try {
            startDateTime = dateFormat.parse(startDate + " " + startTime);
            endDateTime = dateFormat.parse(endDate + " " + endTime);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date or time format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        // create date time variables for reminder
        String date = YearComboBox.getSelectedItem().toString() + "-" + 
                        (MonthComboBox.getSelectedIndex() + 1) + "-" + 
                        DayComboBox.getSelectedItem().toString();
        
        String time =  HourComboBox.getSelectedItem().toString() + ":" + 
                        MinuteComboBox.getSelectedItem().toString() + ":00";
        
        // Convert the dates and times to Date objects
         Date dateTime=null;
         try {
            dateTime = dateFormat.parse(date + " " + time);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date or time format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if end date/time is after start date/time
        if (endDateTime.before(startDateTime)) {
            JOptionPane.showMessageDialog(null, "End date/time must be after start date/time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if start date/time is before current date/time
        else if (startDateTime.before(currentDate)) {
            JOptionPane.showMessageDialog(null, "Start date/time cannot be before current date/time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if end date/time is before current date/time
        else if (endDateTime.before(currentDate)) {
            JOptionPane.showMessageDialog(null, "End date/time cannot be before current date/time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
        else {
             EventReminderController er =new EventReminderController(currentUsername);
            if (er.addEvent(eventName, startDateTime, endDateTime)){
                    if (setReminderBox.isSelected()){
                            if (dateTime.before(currentDate)){
                                JOptionPane.showMessageDialog(null, "Start date/time cannot be before current date/time.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            
                            }else {
                                    if (er.addReminder(dateTime) ==false){
                                        JOptionPane.showMessageDialog(null, "Something went wrong.", "Error", JOptionPane.ERROR_MESSAGE);
                                    
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(null, "Event saved successfully");
                                        F.dispose();
                                        new MenuUI(currentUsername);
                                        return;
                                    }       
                                }
                       
                    } else { 
                        JOptionPane.showMessageDialog(null, "Event saved successfully");
                        F.dispose();
                        new MenuUI(currentUsername);
                        return;
                    }   
           }
        }

    }
}
}


