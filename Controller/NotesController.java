package Controller;

import java.util.ArrayList;

import Model.Notes;

/**
 * This class is used to control/ manipulate the Notes Class.
 * Create a note using the 'creatNote' method.
 */
public class NotesController {

    public Notes note;

    /**
     * Creates controller to manipulate note object.
     */
    public NotesController() {
    }

    /**
     * Creates and saves a Notes object to the database and returns it.
     * 
     * @param text
     *                 String the text for the note.
     * @param courseID
     *                 Int the course to which the note belongs to.
     * @param username
     *                 String username of the person to which the account belongs
     *                 to.
     * @return
     *         Notes object that was just created.
     */
    public Notes createNote(String text, int courseID, String username) {
        Notes note = new Notes(text, courseID, username);
        this.note = note;
        return this.note;
    }

    /**
     * Retrieves a note from the database.
     * 
     * @param noteID
     *                 Int unique value which identifies the Notes object
     * 
     * @param courseID
     *                 Course ID to which the note belongs to
     * @param username
     *                 String username of the person to which the account belongs to
     * @return
     *         Notes object. Notes object will be empty if match is not found in the
     *         database
     */
    public Notes getNote(int noteID, int courseID, String username) {
        Notes note = new Notes();
        return note.getNote(noteID, courseID, username);
    }

    /**
     * This method is used to get all the notes that exists for a particular course
     * 
     * @param courseID
     *                 Int unique value to identify course
     * @param username
     *                 String the name of the person to which the account belongs to
     * @return
     *         ArrayList<Notes> a list of Notes objects that belongs to the course
     */
    public ArrayList<Notes> getAllNotesForCourse(int courseID, String username) {
        Notes note = new Notes();
        return note.getAllNotes(courseID, username);
    }

    /**
     * Updates a note in the database
     * 
     * @param updatedText
     *                    String to which the note's text will be updated with
     * @param note
     *                    Notes to be updated
     * @return
     *         Notes object with updated text.
     */
    public Notes updateNote(String updatedText, Notes note) {
        note.updateNote(updatedText);
        return note;
    }

    /**
     * This method is used to return the note's information.
     * 
     * @param note
     *             Notes object to display information from.
     * @return
     *         String containing information on the note.
     */
    public String displayNote(Notes note) {
        return note.toString();
    }

    /**
     * This method is used to get the note's text
     * 
     * @param note
     *             Notes object to get the text from.
     * @return
     *         String
     */
    public String getText(Notes note) {
        return note.getText();
    }

    /**
     * This method is to get the note's course ID.
     * 
     * @param note
     *             Notes object to get the course ID from.
     * @return
     *         int
     */
    public int getCourseID(Notes note) {
        return note.getCourseID();
    }

    /**
     * This method is used to get the note's unique ID from the database
     * 
     * @param note
     *             Notes object to get the unique ID from.
     * @return
     *         int
     */
    public int getNoteID(Notes note) {
        return note.getNoteID();
    }

    /**
     * This method is used to check if the note is empty.
     * 
     * @param note
     *             Notes object to check
     * @return
     *         Boolean
     */
    public Boolean isNoteEmpty(Notes note) {
        return note.isEmpty();
    }
}
