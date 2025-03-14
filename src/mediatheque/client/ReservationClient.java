package mediatheque.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ReservationClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 2000);
             BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.print("Numéro abonné: ");
            out.println(clavier.readLine());
            System.out.print("Numéro document: ");
            out.println(clavier.readLine());

            System.out.println(in.readLine()); // Réponse du serveur

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
