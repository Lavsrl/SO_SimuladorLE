package TrocaDeMensagens;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TrocaDeMensagens {
    public static void main(String[] args) {
        BlockingQueue<String> filaMensagens = new ArrayBlockingQueue<>(50);
        Random random = new Random();

        Thread processadorMensagens = new Thread(() -> {
            try {
                while (true) {
                    // Processa a mensagem recebida
                    String mensagem = filaMensagens.take();
                    System.out.println("Processador recebeu: " + mensagem);

                    // Simula tempo de processamento
                    Thread.sleep(500);

                    // Finaliza se mensagem de saída for recebida
                    if ("FIM".equals(mensagem)) {
                        System.out.println("Processador encerrando...");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Processador interrompido.");
            }
        });

        processadorMensagens.start();

        Runnable gerarMensagensP1 = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    String mensagem = "Mensagem de P1: " + i;
                    filaMensagens.put(mensagem);
                    System.out.println("P1 enviou: " + mensagem);
                    Thread.sleep(random.nextInt(100) + 50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        Runnable gerarMensagensP2 = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    String mensagem = "Mensagem de P2: " + i;
                    filaMensagens.put(mensagem);
                    System.out.println("P2 enviou: " + mensagem);
                    Thread.sleep(random.nextInt(100) + 50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        Thread t1 = new Thread(gerarMensagensP1, "P1");
        Thread t2 = new Thread(gerarMensagensP2, "P2");

        t1.start();
        t2.start();

        new Thread(() -> {
            try {
                t1.join();
                t2.join();
                filaMensagens.put("FIM");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
