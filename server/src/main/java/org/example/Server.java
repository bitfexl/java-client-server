package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.SocketException;

public class Server implements Closeable {
    private final ServerSocket serverSocket;

    private final MultiClientHandler clientHandler = new MultiClientHandler();

    private final Thread clientHandlerThread = new Thread(clientHandler::runForever);

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    /**
     * Wait for a client connection.
     * @return The newly connected client.
     */
    public Client waitForClient() throws IOException {
        return new Client(serverSocket.accept());
    }

    /**
     * Process requests forever on the current thread.
     * Call close() to stop.
     */
    public void runForever() {
        clientHandlerThread.start();

        try (this) {
            while (true) {
                try {
                    clientHandler.add(waitForClient());
                } catch (SocketException ex) {
                    // because of serverSocket.close()
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        clientHandlerThread.interrupt();
        serverSocket.close();
    }
}
