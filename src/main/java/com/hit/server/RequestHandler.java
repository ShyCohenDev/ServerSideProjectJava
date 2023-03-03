package com.hit.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.controllers.BuyItemsController;
import com.hit.controllers.ItemController;
import com.hit.controllers.UserController;
import com.hit.dm.Item;
import com.hit.dm.User;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RequestHandler implements Runnable {
    private final Socket client;

    public RequestHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Scanner reader = new Scanner(new InputStreamReader(client.getInputStream()));
            String requestAsString = reader.nextLine();
            Gson gson = new Gson();
            Request request = gson.fromJson(requestAsString, Request.class);

            switch (request.getHeader()) {
                case "users/login":
                    String balanceAndId = UserController.logIn(request.getBody().toString());
                    Response response = new Response(!Objects.equals(balanceAndId, "-1") ? "true": "false", balanceAndId);
                    String responseAsString = gson.toJson(response);
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                    writer.println(responseAsString);
                    writer.flush();
                    writer.close();
                    break;
                case "users/signup":
                    User user = (User) gson.fromJson(request.getBody().toString(), User.class);
                    UserController.signUp(user);
                    break;
                case "buyItems":
                    String id = request.getBody().toString().split("!")[0];
                    String itemListAsString = request.getBody().toString().split("!")[1];
                    Type itemListType = new TypeToken<ArrayList<Item>>(){}.getType();
                    List<Item> itemList = new Gson().fromJson(itemListAsString, itemListType);
                    BuyItemsController.buyItems(id, itemList);
                    break;
                case "searchItems":
                    List<Item> itemList2 = ItemController.searchItems(request.getBody().toString());
                    Response response2 = new Response("searchItems", itemList2);
                    String responseAsString2 = gson.toJson(response2);
                    PrintWriter writer2 = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                    writer2.println(responseAsString2);
                    writer2.flush();
                    writer2.close();
                    break;
            }

            reader.close();
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
