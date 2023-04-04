package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


//Add Databse to save current course containers which will update when a course container is deleted
public class ContainerUI extends JPanel{

    private JPanel addcontainerpnl;
    private JTextField courseNameField;
    private JTextField courseCodeField;
    private ArrayList<courseContainer> courseContainers;
    private JPanel courseListPanel;
   



    private JLabel InstructionsLabel;

    private JButton addButton;
    private JButton deleteButton;
    

    public ContainerUI(){
        //thisRef = this;   
        courseContainers = new ArrayList<>();
        addcontainerpnl = new JPanel();
        addcontainerpnl.setPreferredSize(new Dimension(370,350));


        //courseContainer sampleContainer = new courseContainer("Sample Container", "Sample Course");
        //addC(sampleContainer);


        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameField = new JTextField(20);
        JPanel courseNamePanel = new JPanel();
        courseNamePanel.add(courseNameLabel);
        courseNamePanel.add(courseNameField);

        JLabel coursecodeLabel = new JLabel("Course Code:");
        courseCodeField = new JTextField(20);
        JPanel courseCodePanel = new JPanel();
        courseCodePanel.add(coursecodeLabel);
        courseCodePanel.add(courseCodeField);
        
        JButton  cmdClose = new JButton("Close");
        JPanel closePanel = new JPanel();
        closePanel.add(cmdClose);

        JButton viewButton = new JButton("View");


        JPanel courseListPanel = new JPanel();
        courseListPanel.setLayout(new BoxLayout(courseListPanel, BoxLayout.Y_AXIS));
        

        //Main Panel
        setLayout(new BorderLayout());
        addcontainerpnl.setBackground(Color.decode("#211e1c"));
        

        //Label
        InstructionsLabel = new JLabel("FILL IN COURSE INFORMATION", SwingConstants.CENTER);
        InstructionsLabel.setBackground(Color.decode("#6bdd70"));
        


        //Button Panel
        addButton = new JButton("Add Container");
        deleteButton = new JButton("Delete Container");
        viewButton = new JButton("View Containers");
       

        //Button Colour 
        addButton.setBackground(Color.decode("#00ccff")); 
        deleteButton.setBackground(Color.decode("#ffff66"));
        cmdClose.setBackground(Color.decode("#e25e54"));
        viewButton.setBackground(Color.decode("#6C5D85"));
        

        // Button listeners
        addButton.addActionListener(new addlistener());
        deleteButton.addActionListener(new deleteListener());
        cmdClose.addActionListener(new CloseListener());

        addcontainerpnl.add(InstructionsLabel);
        addcontainerpnl.add(courseNamePanel);
        addcontainerpnl.add(courseCodePanel);
        
        //addcontainerpnl.add(viewPanel);

        addcontainerpnl.add(addButton);   
        
        addcontainerpnl.add(viewButton);
        addcontainerpnl.add(deleteButton);
        addcontainerpnl.setBackground(Color.decode("#d9d9d9"));
        closePanel.setBackground(Color.decode("#d9d9d9"));

        add(InstructionsLabel, BorderLayout.NORTH);
        add(addcontainerpnl, BorderLayout.CENTER);
        add(courseListPanel, BorderLayout.NORTH);
        add(closePanel, BorderLayout.SOUTH);
        

    }

    

    //Action Listeners
    private class addlistener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            
            String courseName = courseNameField.getText();
            String courseCode= courseCodeField.getText();

            if (courseName.isEmpty() || courseCode.isEmpty()) {
                JOptionPane.showMessageDialog(addcontainerpnl, "Please enter a course name and a container name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            ContainerUI.courseContainer cContainer = new courseContainer(courseCode, courseName);
            courseContainers.add(cContainer);
            courseNameField.setText("");
            courseCodeField.setText("");
            JOptionPane.showMessageDialog(addcontainerpnl, "Course container created for " + courseName);
        }
    }    

    private class deleteListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //String containerName = courseCodeField.getText();
            String courseName = courseNameField.getText();

            courseContainer container = findCourseContainer(courseName);
            if (container != null) {
                courseContainers.remove(container);
                JOptionPane.showMessageDialog(ContainerUI.this, "Course container deleted: " + courseName);
            } else {
                JOptionPane.showMessageDialog(ContainerUI.this, "Course container not found: " + courseName);
            }
        }
    }



    
    

    class ViewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Clear the existing content of the courseListPanel
            courseListPanel.removeAll();
    
            // Loop through the list of added course containers and add them to the courseListPanel
            for (int i = 0; i < courseContainers.size(); i++) {
                courseContainer container = courseContainers.get(i);
                JLabel courseNameLabel = new JLabel(container.getCourseName());
                JLabel containerNameLabel = new JLabel(container.getCourseName());
                courseListPanel.add(courseNameLabel);
                courseListPanel.add(containerNameLabel);
                courseListPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            JScrollPane scrollPane = new JScrollPane(courseListPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(400, 400));
            JFrame frame = new JFrame("Course Containers");
            frame.getContentPane().add(scrollPane);
            frame.pack();
            frame.setVisible(true); 
            // Refresh the courseListPanel with the new content
            courseListPanel.revalidate();
            courseListPanel.repaint();
        }

    }  

   

    private courseContainer findCourseContainer(String courseName) {
        for (courseContainer container : courseContainers) {
            if (container.getCourseName().equals(courseName)) {
                return container;
            }
        }
        return null;
    }


    private class CloseListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }


    class courseContainer {
        private String courseCode;
        private String courseName;
    
        public courseContainer(String courseCode, String courseName) {
            this.courseCode = courseCode;
            this.courseName = courseName;
        }
    
        public String getCode() {
            return courseCode;
        }
    
        public String getCourseName() {
            return courseName;
        }
    }

    
   
    






    ////Driver
    public static void main(String[] args) {
        JFrame frame = new JFrame("ADD COURSE CONTAINER");

        frame.setContentPane(new ContainerUI());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}


