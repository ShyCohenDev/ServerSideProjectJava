package com.hit.util;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BackupAndRestore {

    Timer timer;

    public BackupAndRestore() {
        this.timer = new Timer();
    }

    public void backup(String fromFilePath, String toPathBackup, long delay, long period) {
        TimerTask backupTask = new TimerTask() {
            public void run() {
                System.out.println("Backing up...");
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fromFilePath));
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(toPathBackup));
                    Object object = objectInputStream.readObject();
                    objectOutputStream.writeObject(object);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("ObjectInputStream ERROR!");
                    e.printStackTrace();
                }

            }
        };
        timer.scheduleAtFixedRate(backupTask, delay, period);
    }

    public Object restore(String fromFilePath) {
        System.out.println("Restoring...");
        Object object = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fromFilePath));
            object = objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("ObjectInputStream ERROR!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ObjectInputStream ERROR!");
            e.printStackTrace();
        }
        return object;
    }
}