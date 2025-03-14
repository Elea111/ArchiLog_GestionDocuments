package mediatheque.model;

import mediatheque.exception.EmpruntException;
import mediatheque.exception.ReservationException;

public interface IDocument {
//ne pas modifier
    int numero() ;
    void reserver(Abonne ab) throws ReservationException;
    void emprunter(Abonne ab ) throws EmpruntException;
    void retourner();
}
