package java.mediatheque.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract   class Server {
    private final int port;
    private ServerSocket serverSocket;

    public Server(int port) { this.port = port; }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void handleClient(Socket clientSocket);
}
