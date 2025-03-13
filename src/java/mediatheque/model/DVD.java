package java.mediatheque.model;

import java.mediatheque.exception.EmpruntException;
import java.mediatheque.exception.ReservationException;

public class DVD implements IDocument {
    private final int numero;
    private final String titre;
    private final boolean adulte;
    private Abonne reservePar;
    private Abonne empruntePar;

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
    public void reserver(Abonne a) throws ReservationException {
        synchronized (this) {
            if (empruntePar != null || (reservePar != null && reservePar != a)) {
                throw new ReservationException("Document non disponible");
            }
            reservePar = a;
        }
    }

    // 类似Livre的实现，添加年龄检查
    @Override
    public void emprunter(Abonne ab) throws EmpruntException {
        if (adulte && ab.age() < 16) {
            throw new EmpruntException("Âge insuffisant");
        }
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


    private boolean isAdulte(){
        return adulte;
    }
    // 其他方法类似Livre
}