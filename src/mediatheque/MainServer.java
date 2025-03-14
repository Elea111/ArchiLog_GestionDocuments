package mediatheque;

import mediatheque.model.Abonne;
import mediatheque.model.DVD;
import mediatheque.model.IDocument;
import mediatheque.model.Livre;
import mediatheque.server.EmpruntServer;
import mediatheque.server.Mediatheque;
import mediatheque.server.ReservationServer;
import mediatheque.server.RetourServer;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MainServer {
    public static void main(String[] args) {
        // 初始化数据
        List<IDocument> documents = Arrays.asList(
                new Livre(1, "Le Petit Prince", 100),
                new DVD(2, "Inception", true)
        );
        List<Abonne> abonnes = Arrays.asList(
                new Abonne(101, "Alice", LocalDate.of(2000, 1, 1)),
                new Abonne(102, "Bob", LocalDate.of(2010, 1, 12))
        );
        Mediatheque m = new Mediatheque(abonnes,documents);
        // 启动三个服务
        new Thread(new ReservationServer(m)).start();
        new Thread(new EmpruntServer(m)).start();
        new Thread(new RetourServer(m)).start();
    }
}