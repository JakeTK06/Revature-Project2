package com.revature.planetarium.service.user;


import java.util.Optional;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;

public class UserServiceImp implements UserService {
    
    private UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String createUser(User newUser) {
        String username = newUser.getUsername();
        String password = newUser.getPassword();
        String regex_valid_chars = "^[a-zA-Z0-9_-]+$";
        String regex_must_contain = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
        if (username.length() < 5 || username.length() > 30) {
            throw new UserFail("Invalid username");
        }
        else if (!Character.isLetter(username.charAt(0))) {
            throw new UserFail("Invalid username");
        }
        else if (!username.matches(regex_valid_chars)) {
            throw new UserFail("Invalid username");
        }
        else if (password.length() < 5 || password.length() > 30) {
            throw new UserFail("Invalid password");
        }
        else if (!password.matches(regex_must_contain)) {
            throw new UserFail(("Invalid password"));
        }
        else if (!Character.isLetter(password.charAt(0))) {
            throw new UserFail("Invalid password");
        }
        else if (!password.matches(regex_valid_chars)) {
            throw new UserFail("Invalid password");
        }
        Optional<User> foundUser = userDao.findUserByUsername(username);
        if (foundUser.isPresent()) {
            throw new UserFail("Invalid username");
        }
        else {
            Optional<User> createdUser = userDao.createUser(newUser);
            if (createdUser.isPresent()) {
                return "User created successfully";
            } else {
                throw new UserFail("Failed to create user, please try again");
            }
        }

    }

    @Override
    public User authenticate(User credentials) {
        Optional<User> foundUser = userDao.findUserByUsername(credentials.getUsername());
        if (foundUser.isPresent()) {
            if (foundUser.get().getPassword().equals(credentials.getPassword())) {
                User user = new User(foundUser.get().getId(), foundUser.get().getUsername(), null);
                return user;
            }
        }
        throw new UserFail("Invalid credentials");
    }

}
