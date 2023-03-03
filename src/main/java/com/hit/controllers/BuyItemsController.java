package com.hit.controllers;

import com.hit.dm.Item;
import com.hit.services.ItemService;
import com.hit.services.UserService;

import java.util.List;

public class BuyItemsController {

    private static final ItemService itemService = new ItemService();
    private static final UserService userService = new UserService();

    public static void buyItems(String id, List<Item> itemList) {
        userService.chargeUser(id, itemList);
        for (Item item : itemList) {
            itemService.buyItem(item.getId());
        }
    }
}
