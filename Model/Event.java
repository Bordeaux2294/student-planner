package Model;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Event{

    private Date startDateTime;
    private Date endDateTime;

    private String eventName;


public Event(String eventName, Date startDateTime, Date endDateTime){
    
     this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.eventName = eventName;
    storeEvent();
}
public Event(){}

public String getEventName() {
        return eventName;
}  
public Date getStartDateTime() throws ParseException{
    return startDateTime;
}  

public Date getEndDateTime() throws ParseException{
     return endDateTime;
}  
    
public int getStartYear(){
    return startDateTime.getYear()+1900;
}

public int getEndYear(){
    return endDateTime.getYear()+1900;
}

public String getHours(Date d){
   String hrs= d.getHours()+"";
    if (hrs.length()==1)
        hrs= "0"+hrs;
    return hrs;
}

public String getMins(Date d){
    String mins= d.getMinutes()+"";
     if (mins.length()==1)
         mins= "0"+mins;
     return mins;
 }

public void storeEvent(){
    try {
        // Open a file output stream in append mode
        FileOutputStream fos = new FileOutputStream("eventlist", true);

        // Create a print writer to write to the file output stream
        PrintWriter pw = new PrintWriter(fos);

        // Write the event's information to the file
        pw.println(getEventName() + "," + startDateTime.getDate() + "-" + startDateTime.getMonth() + "-" + getStartYear() +
                   " " + getHours(startDateTime) + ":" + getMins(startDateTime) + "," +
                    endDateTime.getDate() + "-" + endDateTime.getMonth() + "-" + getEndYear() 
                    + " " + getHours(endDateTime) + ":" + getMins(endDateTime));

        // Close the print writer and file output stream
        pw.close();
        fos.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
} 