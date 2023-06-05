package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        try (final Server server = new Server(3456)) {
            Thread serverThread = new Thread(server::runForever);
            serverThread.start();
            System.out.println("PRESS ENTER TO SHUTDOWN");
            System.in.read();
            System.out.println("CLOSING");
        }
        System.out.println("CLOSED");
    }
}