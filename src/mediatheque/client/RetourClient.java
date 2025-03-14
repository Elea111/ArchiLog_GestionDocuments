package mediatheque.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class RetourClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4000);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Numéro document: ");
            int docId = scanner.nextInt();

            out.writeInt(docId);
            String response = (String) in.readObject();
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
