package java.mediatheque.model;

import java.mediatheque.exception.EmpruntException;
import java.mediatheque.exception.ReservationException;

public interface IDocument {

    int numero() ;
    void reserver(Abonne a) throws ReservationException;
    void emprunter(Abonne a ) throws EmpruntException;
    void retourner();
}
