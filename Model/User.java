package Model;

import java.sql.*;


public class User {
    private  String currentUsername = "";

    private static String url = "jdbc:mysql://localhost:3306/studentplannerdb";
    private static String user = "root";
    private static String pword =  "Myaccess123.";
    public User(){}
    public User(String username, String password) {

        if (checkUser(username) == false) {
            
            
            try (Connection connection = DriverManager.getConnection(url, user, pword)) {
                // Insert the user into the database
                String sql = "INSERT INTO users (username, password) VALUES (?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();
                currentUsername = username;

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

    public  String getCurrentUsername() {
        return currentUsername;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPword() {
        return pword;
    }

}
