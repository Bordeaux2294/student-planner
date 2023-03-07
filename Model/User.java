package Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
    public User(){}
    
    public Boolean checkLogincreds(String username, String password){
        Boolean success=false;
         try {
             File myObj = new File("Model/Security/creds.txt");
             Scanner myReader = new Scanner(myObj);
             while (myReader.hasNextLine()) {
               String data[] = myReader.nextLine().split(" ");
 
               if (data[0].equals(username)&& data[1].equals(password))
                 success= true;
                 break;
             }
             myReader.close();
           } catch (FileNotFoundException e) {
             System.out.println("An error occurred.");
             e.printStackTrace();
         }
         return success;
     }
}
