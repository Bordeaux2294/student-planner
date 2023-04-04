package Controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

import Model.Reminder;
import View.RemindernotifUI;

import java.util.Scanner;

public class RemindernotifController {

    public static String assignmentname;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        System.out.println("What's the name of the event?");
        Scanner reminder = new Scanner(System.in);
        assignmentname = reminder.nextLine();
        Reminder model = new Reminder(0, null);
        RemindernotifUI.assignment.setText("Hey! Don't forget about " + RemindernotifController.assignmentname + "!");

        File file = new File("rasengan.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        RemindernotifUI launchPage = new RemindernotifUI();
        clip.start();

        // Your code here
    }
}