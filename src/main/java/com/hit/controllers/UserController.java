package com.hit.controllers;

import com.hit.dm.User;
import com.hit.services.UserService;

public class UserController {

    public static UserService userService = new UserService();

    public static void signUp(User user) {
        userService.signUp(user);
    }

    public static String logIn(String text) {
        String username = text.split("_")[0];
        String password = text.split("_")[1];
        if (userService.logIn(username, password)) {
            return userService.getBalanceAndId(username, password);
        } else {
            return "-1";
        }
    }
}
