package org.example;

import org.example.transferobjects.TransferObject;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Closeable {
    private final Socket socket;

    private final ObjectOutputStream outputStream;

    private final ObjectInputStream inputStream;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void send(TransferObject transferObject) throws IOException {
        outputStream.writeObject(transferObject);
        outputStream.flush();
    }

    public boolean hasInput() throws IOException {
        return socket.getInputStream().available() > 0;
    }

    public TransferObject read() throws IOException {
        try {
            return (TransferObject) inputStream.readObject();
        } catch (Exception ex) {
            throw new IOException("Error deserializing transfer object.", ex);
        }
    }

    public boolean isClosed() {
        return socket.isClosed();
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}