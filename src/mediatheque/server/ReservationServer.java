package mediatheque.server;

import java.io.*;

import mediatheque.exception.ReservationException;
import mediatheque.abonne.Abonne;
import mediatheque.abonne.IDocument;
import java.net.Socket;

import static java.lang.System.out;

public class ReservationServer extends Server {
    private Mediatheque m ;

    public ReservationServer(Mediatheque m ) {
        super(2000);
        this.m = m;
    }

    @Override
    public void traiterRequete(Socket client) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

            int numAbonne = Integer.parseInt(in.readLine());
            int numDoc = Integer.parseInt(in.readLine());

            Abonne ab = m.trouverAbonne(numAbonne);
            IDocument doc = m.trouverDocument(numDoc);

            doc.reserver(ab);
            out.println("Réservation réussie");

        } catch (ReservationException | IOException e) {
            out.println("Erreur: " + e.getMessage());
        }
    }
}