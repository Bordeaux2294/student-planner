package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Controller.NotesController;
import Model.Container;
import Model.Notes;

public class NotesUI extends JFrame {

    /**
     * Constructor - use method from class to create a specific notes frame.
     */
    public NotesUI() {
    }

    /**
     * This method is used to create a NotesUI frame that allows the user to write a
     * note for a course and save it to the database.
     * 
     * @param courseID
     *                 Int this uniquely identifies the course that the note will
     *                 belong to.
     * @param username
     *                 String this uniquely identifies the person to which the note
     *                 belongs to. This is to prevent different users of the app
     *                 from accessing notes that do not belong to them.
     */
    public void writeNoteUI(String courseID) {
        setTitle("Write Note");
   
        JTextArea textArea = new JTextArea(20, 40);
        JTextField titleField = new JTextField(20);
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(textArea.getText());
                String noteText = textArea.getText();
                String title = titleField.getText();
                if (noteText.compareToIgnoreCase("") != 0) {
                    JFrame loadingFrame = new JFrame("Saving...");
                    loadingFrame.setSize(400, 50); // set the size of the JFrame
                    loadingFrame.setVisible(true); // set the JFrame to be visible
                    Thread thread = new Thread(() -> {
                        NotesController controller = new NotesController();
                        Notes note = controller.createNote(title, noteText, courseID);
                       
                        Container.addNotes(note);
                    });

                    thread.start();

                    try {
                        thread.join();
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                    loadingFrame.dispose();
                    JOptionPane.showMessageDialog(null, "Your note has been saved successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot save an empty note!\nEnter some text.");

                }
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        JPanel heading = new JPanel();
       
        heading.add(new JLabel("Give your notes a title"));
        heading.add (titleField);
        setLayout(new BorderLayout());
        add(heading, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * This method is used to display a note from a course as well as the option to
     * update that specific note
     * 
     * @param courseID
     *                 Int this is used to uniquely indentify the course to which
     *                 the note belongs to.
     * @param username
     *                 String this is used to uniquely indentify the person to which
     *                 the note belongs to. [SECURITY FEATURE - SEE 'WRITENOTEUI'
     *                 METHOD]
     */
    public void displayNoteUI(int noteID, String courseID) {
        setTitle("Your Note");

        JTextArea textArea = new JTextArea(20, 40);
        JFrame loadingFrame = new JFrame("Loading...");
        loadingFrame.setSize(400, 50); // set the size of the JFrame
        loadingFrame.setVisible(true);
        Thread thread1 = new Thread(() -> {
            NotesController controller = new NotesController();
            Notes note = controller.getNote(noteID, courseID);
            textArea.setText(note.getText());
        });

        thread1.start();

        try {
            thread1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadingFrame.dispose();

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(textArea.getText());
                String noteText = textArea.getText();
                if (noteText.compareToIgnoreCase("") != 0) {
                    JFrame loadingFrame = new JFrame("Updating...");
                    loadingFrame.setSize(400, 50); // set the size of the JFrame
                    loadingFrame.setVisible(true); // set the JFrame to be visible
                    Thread thread = new Thread(() -> {
                        NotesController controller = new NotesController();
                        Notes note = controller.getNote(noteID, courseID);
                        controller.updateNote(noteText, note);
                        textArea.setText(note.getText());
                    });

                    thread.start();

                    try {
                        thread.join();
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                    loadingFrame.dispose();
                    JOptionPane.showMessageDialog(null, "Your note has been updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot save an empty note!\nEnter some text.");

                }
                dispose();
            }
        });

        JButton cancelButton = new JButton("Close");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // public static void main(String[] args) {
    //     NotesUI ui = new NotesUI();
    //     // ui.writeNoteUI(0, "hel");
    //     ui.displayNoteUI(20, 0);
    //     // ui.writeNoteUI(0, "hel");
    // }
}
