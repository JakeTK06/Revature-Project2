package com.revature.planetarium.repository.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.utility.DatabaseConnector;

public class UserDaoImp implements UserDao {

    @Override
    public Optional<User> createUser(User newUser) {

        String username = newUser.getUsername();
        String password = newUser.getPassword();
        // Check that username is valid
        // username is unique
        Optional<User> existingUser = findUserByUsername(username);
        if(existingUser.isPresent()){
            throw new UserFail("Invalid username");
        }

        // username is between 5 and 30 characters
        if(username.length() < 5 || username.length() > 30){
            throw new UserFail("Invalid username");
        }

        // username should start with a letter
        if(!Character.isLetter(username.charAt(0))){
            throw new UserFail("Invalid username");
        }

        // usernames should support lowercase, upper case, numbers, underscores, dashes
        String validCharacters ="^[a-zA-Z0-9 _-]+$";
        if (!username.matches(validCharacters)){
            throw new UserFail("Invalid username");
        }

        // Check that password is valid
        // password is between 5 - 30 characters
        if(password.length() < 5 || password.length() > 30){
            throw new UserFail("Invalid password");
        }

        // password should start with a letter
        if(!Character.isLetter(password.charAt(0))){
            throw new UserFail("Invalid password");
        }

        // password should contain a lowercase, uppercase, and number
        String regex_must_contain = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
        if (!password.matches(regex_must_contain)){
            throw new UserFail("Invalid password");
        }

        // password should support underscores and dashes
        if (!password.matches(validCharacters)){
            throw new UserFail("Invalid password");
        }

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, newUser.getUsername());
            stmt.setString(2, newUser.getPassword());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    newUser.setId(rs.getInt(1));
                    return Optional.of(newUser);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new UserFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    return Optional.of(user);
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println(e);
            throw new UserFail(e.getMessage());
        }
    }


}
