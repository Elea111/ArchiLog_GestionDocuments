package java.mediatheque.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ReservationServer extends Server {
    public ReservationServer() { super(2000); }

    @Override
    protected void handleClient(Socket socket) {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            int abonneId = in.readInt();
            int docId = in.readInt();
            // 业务逻辑...
            out.writeObject("Réservation réussie");
        } catch (Exception e) {
            // 发送错误信息
        }
    }
}