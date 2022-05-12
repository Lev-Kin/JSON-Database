package client;

import com.beust.jcommander.JCommander;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private final static String ADDRESS = "127.0.0.1";
    private final static int PORT = 19345;
    private Request request;

    public Client(String[] args) {
        request = new Request();
        JCommander.newBuilder()
                .addObject(request)
                .build()
                .parse(args);
    }

    public void run() {
        System.out.println("Client started!");
        String message;
        if (request.getDataSource() != null) {
            message = request.readFile();
        } else {
            message = request.toJson();
        }
        if (message == null) {
            System.out.println("Error, message can't be null");
            return;
        }
        System.out.println("Sent: " + message.replaceAll("\n", ""));
        try (Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            output.writeUTF(message);
            message = input.readUTF();
            // костыль для избавления от "
            if ("\"\"".equals(message)) {
                System.out.println("{\"response\":\"OK\"}");
            } else {
                System.out.println("Received: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
