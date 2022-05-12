package server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WorkerThread implements Runnable {
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private DataBase action;
    private String response;
    private Socket socket;
    private ServerSocket serverSocket;
    static boolean flag = true;
    private JsonObject map;

    public WorkerThread(ServerSocket server) throws IOException {
        this.serverSocket = server;
        socket = server.accept();
    }

    @Override
    public void run() {
        try (
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream inputStream = new DataInputStream(socket.getInputStream())
        ) {
            String input = inputStream.readUTF();
            Gson gson = new Gson();
            map = JsonParser.parseString(input).getAsJsonObject();
            switch (map.get("type").toString().replaceAll("\"", "")) {
                case "set":
                    lock.writeLock().lock();
                    try {
                        set();
                        outputStream.writeUTF(gson.toJson(response));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        lock.writeLock().unlock();
                    }
                    break;
                case "delete":
                    lock.writeLock().lock();
                    try {
                        delete();
                        outputStream.writeUTF(gson.toJson(response));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        lock.writeLock().unlock();
                    }
                    break;
                case "get":
                    lock.readLock().lock();
                    try {
                        get();
                        outputStream.writeUTF(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        lock.readLock().unlock();
                    }
                    break;
                case "exit":
                    flag = false;
                    try {
                        outputStream.writeUTF("{\"response\":\"OK\"}");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Server.executors.shutdown();
                    serverSocket.close();
                    break;
                default:
                    System.out.println(map.get("type").toString());
                    System.out.println("there is no such a command");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void set() {
        action = new Set(map.get("key"), map.get("value"));
        response = action.work();
    }

    private void get() {
        action = new Get(map.get("key"));
        response = action.work();
    }

    private void delete() {
        action = new Delete(map.get("key"));
        response = action.work();
        DataBase.update();
    }
}
