import java.util.concurrent.Semaphore;

public class Semaforo {
    static final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        Thread P1 = new Thread(() -> {
            try {
                System.out.println("P1 quer entrar na seção crítica.");
                semaphore.acquire();
                System.out.println("P1 está na seção crítica.");
                System.out.println("P1 está fora da seção crítica.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println("P1 terminou suas execuções.");
            }
        },"Thread 1");

        Thread P2 = new Thread(() -> {
            try {
                System.out.println("P2 quer entrar na seção crítica.");
                semaphore.acquire();
                System.out.println("P2 está na seção crítica.");
                System.out.println("P2 está fora da seção crítica.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println("P2 terminou suas execuções.");
            }
        },"Thread 2");

        P1.start();
        P2.start();
    }
}
