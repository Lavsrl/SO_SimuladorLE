import java.util.Random;

public class Monitores {
    static Random random = new Random();

    public static void main(String[] args) {
        Thread P1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("P1 quer entrar na seção crítica.");
                    System.out.println("P1 está na seção crítica.");
                    Thread.sleep(random.nextInt(100));
                    System.out.println("P1 está fora da seção crítica.");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("P1 terminou suas execuções.");
                }
            }
        }, "Thread 1");

        Thread P2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("P2 quer entrar na seção crítica.");
                    System.out.println("P2 está na seção crítica.");
                    Thread.sleep(random.nextInt(100));
                    System.out.println("P2 está fora da seção crítica.");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("P2 terminou suas execuções.");
                }
            }
        }, "Thread 2");

        P1.start();
        P2.start();
    }

}
