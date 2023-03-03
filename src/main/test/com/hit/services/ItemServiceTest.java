package com.hit.services;

import com.hit.dm.Item;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemServiceTest {

    @Test
    public void itemServiceSearchQueryTest() {
        System.out.println("running itemServiceSearchQueryTest...");
        Item item = new Item("dog", "good boy", 255.99F, 7);
        Item item2 = new Item("cat", "mew mew", 255.99F, 7);
        Item item4 = new Item("dogs", "good boy", 255.99F, 7);
        Item item5 = new Item("dgo", "good boy", 255.99F, 7);

        ItemService itemService = new ItemService();
        itemService.removeAllItems();
        itemService.addItem(item);
        itemService.addItem(item2);
        itemService.addItem(item4);
        itemService.addItem(item5);
        itemService.updateAlgoUnit();

        List<Item> searchResult = itemService.itemSearch("dog");
        List<Item> expected = Arrays.asList(item, item4, item5);

        assertEquals(expected.size(), searchResult.size());
        assertEquals(expected.get(0).getId(), searchResult.get(0).getId());
        assertEquals(expected.get(1).getId(), searchResult.get(1).getId());
        assertEquals(expected.get(2).getId(), searchResult.get(2).getId());
    }

    @Test
    public void itemServiceBuyTest() {
        System.out.println("running itemServiceBuyTest...");
        Item item = new Item("dog", "good boy", 255.99F, 7);
        Item item2 = new Item("cat", "mew mew", 255.99F, 7);
        Item item4 = new Item("dogs", "good boy", 255.99F, 7);
        Item item5 = new Item("dgo", "good boy", 255.99F, 7);

        ItemService itemService = new ItemService();
        itemService.removeAllItems();
        itemService.addItem(item);
        itemService.addItem(item2);
        itemService.addItem(item4);
        itemService.addItem(item5);
        itemService.updateAlgoUnit();

        itemService.buyItem(item2.getId());

        assertEquals(itemService.getItem(item2.getId()).getCountRemaining(), 6);
    }
}
