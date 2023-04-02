public class PomodoroModel {
    int elapsedTime = 0;

    int seconds = 0;
    int minutes = 0;
    int hours = 0;

    boolean started = false;


    String seconds_string = String.format("%02d",seconds);
    //might also be good to put in the model
    String minutes_string = String.format("%02d",minutes);
    String hours_string = String.format("%02d",hours);

    public boolean changeBool(){
        if (started==true){
            return false;
        }
        else {
            return true;
        }
    }
}
