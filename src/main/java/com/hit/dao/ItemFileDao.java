package com.hit.dao;

import com.hit.dm.Item;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ItemFileDao implements Dao<Item>{

    private final String PATH = "Item.txt";

    public ItemFileDao() { }

    @Override
    public Item getOne(String id) {
        Item item = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            Map<String, Item> map = (Map<String, Item>) ois.readObject();
            item = map.get(id);
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            Map<String, Item> map = (Map<String, Item>) ois.readObject();
            items = new ArrayList<Item>(map.values());
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void removeOne(String id) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            Map<String, Item> map = (Map<String, Item>) ois.readObject();
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
    public void createOne(Item object) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            Map<String, Item> map = (Map<String, Item>) ois.readObject();
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
    public void updateOne(String id, Item object) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            Map<String, Item> map = (Map<String, Item>) ois.readObject();
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
            Map<String, Item> map = new TreeMap<>();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
            oos.writeObject(map);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
