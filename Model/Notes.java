package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Notes {
    private static String url = "jdbc:mysql://localhost:3306/studentplannerdb";
    private static String user = "root";
    private static String password = ""; // replace empty string with your database password

    private String text = "";
    public int courseID = 0;
    private int noteID = -999;

    /**
     * Creates a note and saves it to the database - database stores a notedID,
     * courseID and the text
     * 
     * @param noteString
     *                   String which is the text of the note
     * @param courseID
     *                   Int which tells which course the note belongs to
     */
    public Notes(String noteString, int courseID) {
        this.text = noteString;
        this.courseID = courseID;
        saveNote();
    }

    /**
     * To create a note object. This blank note object can be used to search and
     * update notes in the database.
     * Create the note object, use the 'getNote' method to retrieve a note in the
     * database.
     * 
     */
    public Notes() {
    };

    /**
     * checks if note object is empty
     * 
     * @return
     *         Boolean true for empty
     */
    public Boolean isEmpty() {
        return this.noteID == -999;
    }

    /**
     * This method is used is used to return the text of the Note
     * 
     * @return
     *         String which is the text of the note
     */
    public String getText() {
        return this.text;
    }

    /**
     * This method is used to return the Course ID that the note belongs to
     * 
     * @return
     *         Int which is the Course ID
     */
    public int getCourseID() {
        return this.courseID;
    }

    /**
     * This method is used to return the NoteID. This is the uniqure ID that
     * identifies the note in the database
     * 
     * @return
     *         Int which is the unique Note ID
     */
    public int getNoteID() {
        return this.noteID;
    }

    /**
     * This method is used to set the text of the note.
     * 
     * @param textString
     *                   String to be set as the text of the note
     */
    public void setText(String textString) {
        this.text = textString;
    }

    /**
     * This method is used to set the Course ID of the note. To which course does
     * the note belong to
     * 
     * @param courseID
     *                 Int to be set as teh courseID of the note
     */
    public void setCourse(int courseID) {
        this.courseID = courseID;
    }

    /**
     * This method is used to set the unique value of the note
     * 
     * @param noteID
     *               Int to be set as the unique noteID
     */
    private void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    /**
     * Inserts a note into the database. Stores the noteID, courseID and text of the
     * note.
     */
    public void saveNote() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Insert note into the database
            String sql = "INSERT INTO notes (courseID, text) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, getCourseID());
            statement.setString(2, getText());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int noteID = generatedKeys.getInt(1);
                setNoteID(noteID);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Retrieves a note from the database given the courseID as parameter.
     * Method will return an empty note if there were no results found in the
     * database
     * 
     * @param courseID
     *                 Int to which course the note belongs to
     * @return
     *         Notes object that has all the updated attributes
     */
    public Notes getNote(int courseID) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM notes WHERE courseID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, courseID);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                setText(result.getString("text"));
                setCourse(result.getInt("courseID"));
                setNoteID(result.getInt("noteID"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return this;
    }

    /**
     * This method takes a string and updates the text for the note object in the
     * database.
     * 
     * @param updatedText
     *                    String that is the updated string for a note in the
     *                    database.
     * 
     */
    public void updateNote(String updatedText) {
        setText(updatedText);
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Insert note into the database
            String sql = "UPDATE notes SET text=? WHERE courseID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, getText());
            statement.setInt(2, getCourseID());
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Rows updated : " + rowsUpdated);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public String toString() {
        return "<NoteID>: " + getNoteID() + " <CourseID>: " + getCourseID() + " <Note>: " + getText();
    }
}
