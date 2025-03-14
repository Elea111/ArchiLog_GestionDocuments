package mediatheque.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract   class Server implements Runnable{
    private final int port;
    private ServerSocket serverSocket;

    public Server(int port) { this.port = port; }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket client = serverSocket.accept();
                new Thread(() -> traiterRequete(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void traiterRequete(Socket clientSocket);
}
