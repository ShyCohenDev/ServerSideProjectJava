package com.hit.services;

import com.hit.dm.Item;
import com.hit.dm.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {

    @Test
    void usersLogicTest() {
        System.out.println("running usersLogicTest...");
        User user = new User("Dani", "dj8o");
        User user2 = new User("Doron", "83h37");
        User user3 = new User("Chipopo", "2398f");

        UserService userService = new UserService();
        userService.removeAllUsers();
        userService.signUp(user);
        userService.signUp(user2);
        userService.signUp(user3);

        assertTrue(userService.logIn("Doron", "83h37"));
        assertFalse(userService.logIn("Doron", "1243asd"));
    }

    @Test
    void chargeUserTest() {
        System.out.println("running chargeUserTest...");
        User user = new User("Doron", "hda9d", 100F);
        Item item = new Item("dog", "good boy", 50F, 4);
        Item item2 = new Item("dog2", "good boy", 49F, 4);
        Item item3 = new Item("dog3", "good boy", 51F, 4);
        List<Item> items = Arrays.asList(item, item2);
        List<Item> items2 = Arrays.asList(item, item3);

        UserService userService = new UserService();
        userService.removeAllUsers();
        userService.signUp(user);

        assertFalse(userService.chargeUser(user.getId(), items2));
        assertTrue(userService.chargeUser(user.getId(), items));


    }
}
