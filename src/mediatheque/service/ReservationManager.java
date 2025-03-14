package mediatheque.service;

import mediatheque.model.Abonne;
import mediatheque.model.DVD;
import mediatheque.model.IDocument;
import mediatheque.model.Livre;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReservationManager {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void scheduleExpiration(IDocument doc, Abonne ab, long delay) {
        scheduler.schedule(() -> {
            if (doc instanceof DVD) {
                DVD dvd = (DVD) doc;
                synchronized (dvd) {
                    if (dvd.getReservePar() == ab) {
                        dvd.annulerReservation();
                        System.out.println("Réservation annulée pour le document " + doc.numero());
                    }
                }
            } else if (doc instanceof Livre) {
                Livre livre = (Livre) doc;
                synchronized (livre) {
                    if (livre.getReservePar() == ab) {
                        livre.annulerReservation();
                        System.out.println("Réservation annulée pour le document " + doc.numero());
                    }
                }
            }
        }, delay, TimeUnit.HOURS);
    }
}
