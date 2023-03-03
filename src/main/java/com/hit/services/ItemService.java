package com.hit.services;

import com.hit.algorithm.IAlgoSearch;
import com.hit.algorithm.Levenshtein_KMP_AlgoSearchImpl;
import com.hit.dao.ItemFileDao;
import com.hit.dm.Item;
import java.util.*;

public class ItemService {
    private ItemFileDao dao;
    private IAlgoSearch algoUnit;

    public ItemService() {
        this.dao = new ItemFileDao();
        List<Item> items = this.dao.getAll();
        Map<String, String> idToNameMap = new TreeMap<>();
        for (Item item : items) {
            idToNameMap.put(item.getId(), item.getName());
        }
        this.algoUnit =  new Levenshtein_KMP_AlgoSearchImpl(idToNameMap);
    }

    public List<Item> itemSearch(String searchQuery) {
        Set<String> perfectSearchIds = algoUnit.perfectSearch(searchQuery);
        Set<String> containsSearchIds = algoUnit.containsSearch(searchQuery);
        Set<String> fuzzySearchIds = algoUnit.fuzzySearch(searchQuery, 2);

        if (perfectSearchIds == null) {
            perfectSearchIds = new TreeSet<>();
        }
        if (containsSearchIds == null) {
            containsSearchIds = new TreeSet<>();
        }
        if (fuzzySearchIds == null) {
            fuzzySearchIds = new TreeSet<>();
        }

        List<Item> items = new ArrayList<>();
        for (String id: perfectSearchIds) {
            items.add((this.getItem(id)));
        }
        for (String id: containsSearchIds) {
            Item item = this.getItem(id);
            if (!perfectSearchIds.contains(id)) {
                items.add(item);
            }
        }
        for (String id: fuzzySearchIds) {
            Item item = this.getItem(id);
            if (!perfectSearchIds.contains(id) && !containsSearchIds.contains(id)) {
                items.add(item);
            }
        }

        return items;
    }

    public void buyItem(String id) {
        Item item = this.dao.getOne(id);
        item.setCountRemaining(item.getCountRemaining() - 1);
        this.dao.updateOne(id, item);
    }

    public Item getItem(String id) {
        return this.dao.getOne(id);
    }

    public List<Item> getAllItems() {
        return this.dao.getAll();
    }

    public void addItem(Item item) {
        this.dao.createOne(item);
    }

    public void updateItem(String id, Item item) {
        this.dao.updateOne(id, item);
    }

    public void removeItem(String id) {
        this.dao.removeOne(id);
    }

    public void removeAllItems() {
        this.dao.removeAll();
    }

    public void updateAlgoUnit() {
        List<Item> items = this.dao.getAll();
        Map<String, String> idToNameMap = new TreeMap<>();
        for (Item item : items) {
            idToNameMap.put(item.getId(), item.getName());
        }
        this.algoUnit =  new Levenshtein_KMP_AlgoSearchImpl(idToNameMap);
    }
}
