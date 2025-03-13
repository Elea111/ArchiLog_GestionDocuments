package java.mediatheque.model;

import java.mediatheque.exception.EmpruntException;
import java.mediatheque.exception.ReservationException;

public class Livre implements IDocument {
    private final int numero;
    private final String titre;
    private final int nbPages;
    private Abonne reservePar;
    private Abonne empruntePar;

    public Livre(int numero, String titre, int nbPages) {
        this.numero = numero;
        this.titre = titre;
        this.nbPages = nbPages;
    }

    @Override
    public int numero() { return numero; }

    @Override
    public void reserver(Abonne ab) throws ReservationException {
        synchronized (this) {
            if (empruntePar != null || (reservePar != null && reservePar != ab)) {
                throw new ReservationException("Document non disponible");
            }
            reservePar = ab;
        }
    }

    @Override
    public void emprunter(Abonne ab) throws EmpruntException {
        synchronized (this) {
            if (empruntePar != null || (reservePar != null && reservePar != ab)) {
                throw new EmpruntException("Emprunt refusé");
            }
            empruntePar = ab;
            reservePar = null;
        }
    }

    @Override
    public void retourner() {
        synchronized (this) {
            empruntePar = null;
            reservePar = null;
        }
    }
}
