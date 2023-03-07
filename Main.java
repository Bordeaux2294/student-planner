public class Main {
    public static void main(String[] args) {

        PomodoroUI pomodoroClock = new PomodoroUI();

        while (PomodoroUI.pomodoro == 0) {
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

        int pomodoro = PomodoroUI.pomodoro + 1;
        while (pomodoro > 0) {
        }
        pomodoroClock.start();
        pomodoro = -1;

        if(pomodoro == 0){
            pomodoroClock.stop();
        }
    }

    }


