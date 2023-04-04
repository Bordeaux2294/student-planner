package Model;

public class PomodoroModel {
    public int elapsedTime = 0;
    public int seconds = 0;
    public int minutes = 0;
    public int hours = 0;
    public boolean started = false;

    public String seconds_string = String.format("%02d", seconds);
    public String minutes_string = String.format("%02d", minutes);
    public String hours_string = String.format("%02d", hours);

}
