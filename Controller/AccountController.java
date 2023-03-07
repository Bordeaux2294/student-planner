package Controller;

import Model.User;

public class AccountController {
    public AccountController(){}

    public Boolean checkLogincreds(String username, String password){
       
        return new User().checkLogincreds(username, password);
    }
}
