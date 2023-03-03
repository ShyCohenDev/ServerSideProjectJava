package com.hit.dao;

import com.hit.dm.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserFileDao implements Dao<User>{

    private final String PATH = "User.txt";

    public UserFileDao() { }

    @Override
    public User getOne(String id) {
        User user = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            Map<String, User> map = (Map<String, User>) ois.readObject();
            user = map.get(id);
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            Map<String, User> map = (Map<String, User>) ois.readObject();
            users = new ArrayList<User>(map.values());
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void removeOne(String id) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            Map<String, User> map = (Map<String, User>) ois.readObject();
            ois.close();
            map.remove(id);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
            oos.writeObject(map);
            oos.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createOne(User object) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            Map<String, User> map = (Map<String, User>) ois.readObject();
            ois.close();
            map.put(object.getId(), object);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
            oos.writeObject(map);
            oos.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOne(String id, User object) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            Map<String, User> map = (Map<String, User>) ois.readObject();
            ois.close();
            map.replace(id, object);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
            oos.writeObject(map);
            oos.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAll() {
        try {
            Map<String, User> map = new TreeMap<>();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
            oos.writeObject(map);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
