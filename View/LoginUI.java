package View;

import javax.swing.*;

import Controller.AccountController;

import java.awt.*;
import java.awt.event.*;

public class LoginUI extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorLabel;

    public LoginUI() {
        setTitle(" Student Planner Login page");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 2));

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

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            // Check username and password against database or other authentication system
            if (new AccountController().checkLogincreds(username, password)==true) {
                JOptionPane.showMessageDialog(null, "Login successful.");
                // Do something after successful login, such as opening another window or changing the current window
                dispose();
                new MenuUI();
            } else {
                errorLabel.setText("Incorrect username or password.");
            }
        }
    }

    public static void main(String[] args) {
        new LoginUI();
    }
}
