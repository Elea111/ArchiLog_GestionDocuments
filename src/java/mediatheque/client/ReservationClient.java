package java.mediatheque.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ReservationClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 2000);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Numéro abonné: ");
            int abonneId = scanner.nextInt();
            System.out.print("Numéro document: ");
            int docId = scanner.nextInt();

            out.writeInt(abonneId);
            out.writeInt(docId);
            String response = (String) in.readObject();
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
