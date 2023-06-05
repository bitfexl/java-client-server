package org.example;

import org.example.transferobjects.HelloWorldImpl;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner stdin = new Scanner(System.in);
        try (Client client = new Client(new Socket("localhost", 3456))) {
            while (true) {
                client.send(new HelloWorldImpl(stdin.nextLine()));
                System.out.println("Sent at: " + System.currentTimeMillis());
            }
        }
    }
}