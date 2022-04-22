package edu.college;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SimpleClient {
    private final Socket socket;

    public SimpleClient() throws  Exception {
        int port = 8080;
        String host = "localhost";
        socket = new Socket(host, port);
        System.out.println("Client is connected to the server.");
    }

    public void start() {
        try {
            Scanner scanner = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()), true);

            Thread t = new Thread(new ServerHandler());
            t.start();

            while (true) {
                pw.println(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            SimpleClient client = new SimpleClient();
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection problems!");
        }
    }
}
