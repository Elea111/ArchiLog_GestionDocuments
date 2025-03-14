package mediatheque.server;

import java.io.*;

import mediatheque.abonne.IDocument;
import java.net.Socket;

import static java.lang.System.out;

public class RetourServer extends Server {
    private Mediatheque m;

    public RetourServer(Mediatheque m) {
        super(4000);
        this.m = m;
    }

    @Override
    protected void traiterRequete(Socket client) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

            int numAbonne = Integer.parseInt(in.readLine());
            int numDoc = Integer.parseInt(in.readLine());

            IDocument doc = m.trouverDocument(numDoc);

            doc.retourner();
            out.println("Réservation réussie");

        } catch (IOException e) {
            out.println("Erreur: " + e.getMessage());
        }
    }
}