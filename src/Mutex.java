import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mutex {
    static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread P1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("P1 está na seção crítica.");
                System.out.println("P1 está fora da seção crítica.");
                System.out.println("P2 está fora da seção crítica.");
            } finally {
                lock.unlock();
                System.out.println("P1 terminou suas execuções.");
            }
        }, "Thread 1");

        Thread P2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("P2 está na seção crítica.");
                System.out.println("P2 está fora da seção crítica.");
            } finally {
                lock.unlock();
                System.out.println("P2 terminou suas execuções.");
            }
        }, "Thread 2");

        P1.start();
        P2.start();
    }
}
