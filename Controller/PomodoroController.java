package Controller;
import View.PomodoroUI;

public class PomodoroController {
    public PomodoroController() {
        
        PomodoroUI pomodoroClock = new PomodoroUI();

        while (PomodoroUI.getPomodoros() == 0) {
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

        int pomodoro = PomodoroUI.getPomodoros() + 1;
        while (pomodoro > 0) {
        }
        pomodoroClock.start();
        pomodoro = -1;

        if(pomodoro == 0){
            pomodoroClock.stop();
        }
    }

    }


