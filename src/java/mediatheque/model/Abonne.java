package java.mediatheque.model;

import java.time.LocalDate;
import java.time.Period;

public class Abonne {
    private final int numero;
    private final String nom;
    private final LocalDate dateNaissance;

    public Abonne(int numero, String nom, LocalDate dateNaissance) {
        this.numero = numero;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public int age() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }
}