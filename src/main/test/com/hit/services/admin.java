package com.hit.services;

import com.hit.dm.Item;
import org.junit.jupiter.api.Test;

public class admin {

    @Test
    public void setItems() {
        ItemService itemService = new ItemService();
        itemService.removeAllItems();
        itemService.addItem(new Item("dog", "good-boy", 7F, 3));
        itemService.addItem(new Item("red-dog", "good-boy", 3F, 5));
        itemService.addItem(new Item("cat", "not-that-good-of-a-boy", 9F, 6));
        itemService.addItem(new Item("monkey", "very-good-boy", 3F, 10));
    }
}
