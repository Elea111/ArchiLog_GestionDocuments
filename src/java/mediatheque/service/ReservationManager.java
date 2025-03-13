package java.mediatheque.service;

import java.mediatheque.model.Abonne;
import java.mediatheque.model.IDocument;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReservationManager {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void scheduleExpiration(IDocument doc, Abonne ab, long delay) {
        scheduler.schedule(() -> {
            synchronized (doc) {
                if (doc.reservePar() == ab) {
                    doc.retourner();
                }
            }
        }, delay, TimeUnit.HOURS);
    }
}
