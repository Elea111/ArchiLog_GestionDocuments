package mediatheque.model;

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

    public int getNumero(){
        return numero;
    }

    private int getAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    public boolean estMoinsDe16ans(){
        if (getAge() <16)
            return true;
        return false;
    }
}