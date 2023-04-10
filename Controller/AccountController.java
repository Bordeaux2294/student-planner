package Controller;

import Model.User;

public class AccountController {

    private User currentUser;

    public AccountController(){}

    public Boolean checkLogincreds(String username, String password){
        currentUser = new User();
        return currentUser.checkLogincreds(username, password);
    }

    public Boolean AddUser(String username, String password){
        currentUser= new User(username, password);
        if (currentUser.getCurrentUsername()==""){
            currentUser=null;
            return true;
        }else
            return false;
    }
    
    public User getCurrentUser(){
        return currentUser;
    } 
}


