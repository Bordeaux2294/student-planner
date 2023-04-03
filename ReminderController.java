import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.util.Scanner;


public class ReminderController {

    public static String assignmentname;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        System.out.println("What's the name of the event?");
        Scanner reminder = new Scanner(System.in);
        assignmentname = reminder.nextLine();
        ReminderModel model = new ReminderModel();
        ReminderUI.assignment.setText("Hey! Don't forget about " + ReminderController.assignmentname + "!");

        File file = new File("rasengan.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        ReminderUI launchPage = new ReminderUI();
        clip.start();

        // Your code here
    }
}