package com.hit.controllers;

import com.hit.dm.Item;
import com.hit.services.ItemService;

import java.util.List;

public class ItemController {

    private static final ItemService itemService = new ItemService();

    public static List<Item> searchItems(String searchQuery) {
        return itemService.itemSearch(searchQuery);
    }

}
