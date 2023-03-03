package com.hit.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server implements Runnable {
    private boolean serverUp;
    private final int port;

    public Server(int port) {
        this.serverUp = true;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            closeConnectionListener(server);
            System.out.println("listening on port " + this.port + "...");
            while (this.serverUp) {
                Socket client = server.accept();
                System.out.println("connection found on " + client.getInetAddress());
                new Thread(new RequestHandler(client)).start();
            }
            server.close();
        } catch (SocketException e) {
            System.out.println("forced quit");
        } catch (Exception e) {
            System.out.println("Error while waiting for a connection...");
            e.printStackTrace();
        }
    }
    public void closeConnectionListener(ServerSocket server) {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (this.serverUp) {
                String command = scanner.nextLine();
                if (command.equals("kill")) {
                    this.serverUp = false;
                    try {
                        server.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            scanner.close();
        }).start();
    }
}
