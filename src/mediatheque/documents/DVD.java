package mediatheque.documents;

import mediatheque.abonne.IDocument;
import mediatheque.abonne.Abonne;
import mediatheque.exception.EmpruntException;
import mediatheque.exception.ReservationException;
import mediatheque.service.ReservationManager;
import java.util.Timer;

public class DVD implements IDocument {
    private final int numero;
    private final String titre;
    private final boolean adulte;
    private Abonne reservePar;
    private Abonne empruntePar;
    private Timer timerReservation;


    public DVD(int numero, String titre, boolean adulte) {
        this.numero = numero;
        this.titre = titre;
        this.adulte = adulte;
    }
    @Override
    public int numero() {
        return numero;
    }

    @Override
    public void reserver(Abonne ab) throws ReservationException {
        synchronized (this) {
            if (this.empruntePar != null || (this.reservePar != null && this.reservePar != ab)) {
                throw new ReservationException("Document indisponible");
            }
            if (adulte && ab.estMoinsDe16ans()) {
                throw new ReservationException("Âge insuffisant");
            }
            this.reservePar = ab;
            // Planification de l'annulation après 1h
            ReservationManager.scheduleExpiration(this, ab, 1);
        }
    }

    @Override
    public void emprunter(Abonne ab) throws EmpruntException {
        synchronized (this){
            if (empruntePar != null || (reservePar != null && reservePar != ab)) {
                throw new EmpruntException("Emprunt impossible");
            }
            empruntePar = ab;
            reservePar = null; // Réinitialise la réservation
            if (timerReservation != null) timerReservation.cancel();
        }
    }


    @Override
    public void retourner() {
        synchronized (this) {
            empruntePar = null;
            notifyAll();
        }
    }
    public synchronized void annulerReservation() {
        reservePar = null;
    }
    public synchronized Abonne getReservePar() {
        return reservePar;
    }
    public Abonne empruntePar() {
        return empruntePar;
    }

    private boolean isAdulte() {
        return adulte;
    }
}