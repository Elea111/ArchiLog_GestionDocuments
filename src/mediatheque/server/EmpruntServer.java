package mediatheque.server;

import java.io.*;

import mediatheque.exception.EmpruntException;
import mediatheque.model.Abonne;
import mediatheque.model.IDocument;
import java.net.Socket;

import static java.lang.System.out;

public class EmpruntServer extends Server {
    private final Mediatheque m;


    public EmpruntServer(Mediatheque m) {
        super(3000);
        this.m = m;
    }

    @Override
    protected void traiterRequete(Socket socket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            int numAbonne = Integer.parseInt(in.readLine());
            int numDoc = Integer.parseInt(in.readLine());

            Abonne ab = m.trouverAbonne(numAbonne);
            IDocument doc = m.trouverDocument(numDoc);

            doc.emprunter(ab);
            out.println("Réservation réussie");

        } catch (EmpruntException | IOException e) {
            out.println("Erreur: " + e.getMessage());
        }
    }
}