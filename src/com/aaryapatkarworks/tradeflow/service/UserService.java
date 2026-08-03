package com.aaryapatkarworks.tradeflow.service;

import com.aaryapatkarworks.tradeflow.model.User;

import java.util.ArrayList;

public class UserService {

    private ArrayList<User> users;

    public UserService() {

        users = new ArrayList<>();
    }

    // ---------------- Register ----------------

    public void registerUser(User user) {

        users.add(user);
    }

    public boolean emailExists(String email) {

        for (User user : users) {

            if (user.getEmail().equalsIgnoreCase(email)) {

                return true;
            }
        }

        return false;
    }

    public boolean userIdExists(int id) {

        for (User user : users) {

            if (user.getUserId() == id) {

                return true;
            }
        }

        return false;
    }

    // ---------------- Login ----------------

    public User login(String email,
                      String password) {

        for (User user : users) {

            if (user.getEmail().equalsIgnoreCase(email)
                    &&
                    user.getPassword().equals(password)) {

                return user;
            }
        }

        return null;
    }

    // ---------------- Users ----------------

    public ArrayList<User> getUsers() {

        return users;
    }

    // ---------------- Display ----------------

    public void displayUsers() {

        System.out.println(
                "\n========== USERS ==========\n"
        );

        for (User user : users) {

            user.displayUser();

            System.out.println();
        }
    }
}