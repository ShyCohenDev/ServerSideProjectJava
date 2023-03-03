package com.hit.services;

import com.hit.dao.UserFileDao;
import com.hit.dm.Item;
import com.hit.dm.User;

import java.util.List;
import java.util.Objects;

public class UserService {
    private UserFileDao dao;

    public UserService() {
        this.dao = new UserFileDao();
    }

    public void signUp(User user) {
        this.dao.createOne(user);
        System.out.println("signed up user: " + user.toString() + "...");
    }

    public boolean logIn(String userName, String hashedPassword) {
        List<User> users = this.dao.getAll();
        for (User _user: users) {
            if (Objects.equals(_user.getUserName(), userName) && Objects.equals(_user.getHashedPassword(), hashedPassword)) {
                System.out.println(userName + " logged in...");
                return true;
            }
        }
        System.out.println(userName + " could not log in...");
        return false;
    }

    public String getBalanceAndId(String userName, String hashedPassword) {
        List<User> users = this.dao.getAll();
        for (User _user: users) {
            if (Objects.equals(_user.getUserName(), userName) && Objects.equals(_user.getHashedPassword(), hashedPassword)) {
                System.out.println(userName + " logged in...");
                return _user.getBalance() + "!" + _user.getId();
            }
        }
        System.out.println(userName + " could not log in...");
        return "-1";
    }

    private static float calcPrice(List<Item> items) {
        float sum = 0;
        for (Item item: items) {
            sum += item.getPrice();
        }
        return sum;
    }

    public boolean chargeUser(String id, List<Item> items) {
        float charge = calcPrice(items);
        User user = this.dao.getOne(id);
        if (charge > user.getBalance()) {
            return false;
        }
        user.setBalance(user.getBalance() - charge);
        this.dao.updateOne(user.getId(), user);
        return true;
    }

    public User getUser(String id) {
        return this.dao.getOne(id);
    }

    public List<User> getAllUsers() {
        return this.dao.getAll();
    }

    public void removeAllUsers() {
        this.dao.removeAll();
    }
}
