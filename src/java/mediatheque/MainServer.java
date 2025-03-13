package java.mediatheque;

import java.mediatheque.model.Abonne;
import java.mediatheque.model.IDocument;
import java.mediatheque.server.ReservationServer;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MainServer {
    public static void main(String[] args) {
        // 初始化数据
        List<IDocument> documents = Arrays.asList(
         //       new Livre(1, "Le Petit Prince", 100),
           //     new DVD(2, "Inception", true)
        );
        List<Abonne> abonnes = Arrays.asList(
                new Abonne(101, "Alice", LocalDate.of(2000, 1, 1))
        );

        // 启动三个服务
        new Thread(() -> new ReservationServer().start()).start();
      //  new Thread(() -> new EmpruntServer().start()).start();
        //new Thread(() -> new RetourServer().start()).start();
    }}