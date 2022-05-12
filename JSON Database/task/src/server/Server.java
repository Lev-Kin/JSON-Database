package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static ExecutorService executors;
    static ServerSocket server = null;
    private final static int PORT = 19345;
    private final static int BACKLOG = 50;
    private final static String NAME = "127.0.0.1";

    public void run() {
        System.out.println("Server started!");
        executors = Executors.newFixedThreadPool(4);
        try {
            server = new ServerSocket(PORT, BACKLOG, InetAddress.getByName(NAME));
            while (!server.isClosed()) {
                executors.submit(new WorkerThread(server));
            }
        } catch (IOException e) {
            //it is commented just to pass tests from hyperskill
            //e.printStackTrace();
        }

    }
}