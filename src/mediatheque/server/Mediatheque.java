package mediatheque.server;

import mediatheque.abonne.Abonne;
import mediatheque.abonne.IDocument;
import java.util.List;

public class Mediatheque {
    private final List<Abonne> abonnes;
    private final List<IDocument> documents;

    public Mediatheque(List<Abonne> abonnes, List<IDocument> documents) {
        this.abonnes = abonnes;
        this.documents = documents;
    }

    public Abonne trouverAbonne(int numero) {
        return abonnes.stream()
                .filter(ab -> ab.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    public IDocument trouverDocument(int numero) {
        return documents.stream()
                .filter(doc -> doc.numero() == numero)
                .findFirst()
                .orElse(null);
    }
}