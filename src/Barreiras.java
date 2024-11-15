import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Barreiras {
    static CyclicBarrier barreira = new CyclicBarrier(2, () -> {
        System.out.println("As threads alcançaram a barreira e vão iniciar uma nova operação.\n");
    });

    public static void main(String[] args) {
        Thread P1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("P1 está executando sua tarefa.");
                    aguardarBarreira(barreira);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"Thread 1");

        Thread P2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("P2 está executando sua tarefa.");
                    aguardarBarreira(barreira);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"Thread 2");

        P1.start();
        P2.start();
    }


    private static void aguardarBarreira(CyclicBarrier barreira) {
        try {
            barreira.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
