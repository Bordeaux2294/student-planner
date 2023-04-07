package Model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.datatransfer.FlavorListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
    private String currentUsername = "";

    public User() {
    }

    private String url = "jdbc:mysql://localhost:3306/studentplannerdb";
    private String user = "root";
    private String pword = ""; // "Myaccess123."

    public User(String username, String password) {

        if (checkUser(username) == false) {
            currentUsername = username;
            // Create a connection to the MySQL database
            try (Connection connection = DriverManager.getConnection(url, user, pword)) {
                // Insert the user into the database
                String sql = "INSERT INTO users (username, password) VALUES (?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public Boolean checkLogincreds(String username, String password) {
        Boolean success = false;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(url, user, pword);
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet row = stmt.executeQuery()) {
                if (row.next()) { // check if there is at least one row
                    success = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        if (success == true)
            currentUsername = username;
        return success;

    }

    public Boolean checkUser(String username) {
        Boolean success = false;
        try (Connection connection = DriverManager.getConnection(url, user, pword)) {
            // check database to see if username already exist
            String sql = "Select * From users Where username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet row = statement.executeQuery();
            if (row.next()) {
                success = true;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return success;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

}
