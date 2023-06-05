package org.example;

import org.example.transferobjects.TransferObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class MultiClientHandler {
    private final List<Client> clients = new LinkedList<>();

    public void add(Client client) {
        clients.add(client);
    }

    public int clientCount() {
        return clients.size();
    }

    public void runForever() {
        while(true) {
            for (int i = 0; i < clients.size(); i++) {
                final Client client = clients.get(i);

                if (client.isClosed()) {
                    clients.remove(i);
                    i--;
                    continue;
                }

                try {
                    process(client);
                } catch (IOException ex) {
                    // close socket on io error
                    ex.printStackTrace();

                    clients.remove(i);
                    i--;

                    try {
                        client.close();
                    } catch (IOException ex2) {
                        ex2.printStackTrace();
                    }
                } catch (RuntimeException ex) {
                    // do not close socket (error in handler)
                    ex.printStackTrace();
                }

                if (Thread.interrupted()) {
                    close();
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            if (Thread.interrupted()) {
                close();
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void close() {
        for (Client client : clients) {
            try {
                client.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        clients.clear();
    }

    private void process(Client client) throws IOException {
        if (client.hasInput()) {
            final TransferObject transferObject = client.read();

            try {
                transferObject.execute();
            } catch (RuntimeException ex) {
                throw new RuntimeException("Error executing transfer object method handler.", ex);
            } catch (NotImplementedByClientException ex) {
                throw new RuntimeException("Method is not implemented on server or client version is loaded.", ex);
            }
        }
    }
}
