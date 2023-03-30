package View;

import javax.swing.*;

import Controller.AccountController;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginUI extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton loginButton;
    private JButton signupButton;
    private JButton login;
    private JButton close;
    private JButton signup;
    private JLabel errorLabel;

    public LoginUI() {
        setTitle(" Student Planner Login page");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JPanel optionspane = new JPanel();

        
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        optionspane.add(loginButton);
        
        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(this);
        optionspane.add(signupButton);

        add(optionspane);
        setVisible(true);


    }
     //login option for a user that already has an account 
    public void loginOpt(){
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        panel.add(errorLabel);

        panel.add(new JLabel(""));

        close = new JButton("< Back");
        close.addActionListener(this);
        panel.add(close);

        login = new JButton("Login");
        login.addActionListener(this);
        panel.add(login);

        this.getContentPane().removeAll();
        this.getContentPane().revalidate();
        this.setContentPane(panel);
    }


    // sign up option for a user that does not have an account
    public void signupOpt(){
        JPanel panel = new JPanel(new GridLayout(5, 4));

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);
        
        JLabel CpasswordLabel = new JLabel("Confirm Password:");
        panel.add(CpasswordLabel);

        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        panel.add(errorLabel);

        panel.add(new JLabel(""));

        close = new JButton("< Back");
        close.addActionListener(this);
        panel.add(close);

        signup = new JButton("sign up");
        signup.addActionListener(this);
        panel.add(signup);

        this.getContentPane().removeAll();
        this.getContentPane().revalidate();
        this.setContentPane(panel);
    }

    public void actionPerformed(ActionEvent e) {
        // String username = usernameField.getText();
        // String password = String.valueOf(passwordField.getPassword());
        if (e.getSource() == loginButton) {

            loginOpt();

        }
        if (e.getSource() == signupButton) {
            signupOpt();
            
        }


        if (e.getSource() == login) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            AccountController currAccount = new AccountController();
           
            // Check username and password against database or other authentication system
            if (currAccount.checkLogincreds(username, password)==true) {
                JOptionPane.showMessageDialog(null, "Login successful.");
                // successful login, open another window or change current window
                dispose();
                new MenuUI( currAccount.getCurrentUser().getCurrentUsername());
            } else {
                errorLabel.setText("Incorrect username or password.");
            }
        }


        if (e.getSource() == signup) {

            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String Cpassword = String.valueOf(confirmPasswordField.getPassword());
            AccountController currAccount = new AccountController();

            if (Cpassword.equals(password)){
                if (currAccount.AddUser(username, password)==true) {
                    errorLabel.setText("Username Already Exists.");
                    // Do something after successful login, such as opening another window or changing the current window
                    
                }
                else {
                     
                    JOptionPane.showMessageDialog(null, "Your Account Was Successfully Created.");
                    
                    dispose();
                    new MenuUI(currAccount.getCurrentUser().getCurrentUsername());
                }
            }
            else{
                errorLabel.setText("Passwords Do Not Match. Try Again");
            }
        }

        if (e.getSource()==close){
            dispose();
            new LoginUI();
        }
    }

    public static void main(String[] args) {
        new LoginUI();
    }
}
