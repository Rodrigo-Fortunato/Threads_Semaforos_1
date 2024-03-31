package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class OvercookedThread extends Thread {

    private static final Semaphore semaphorePratos = new Semaphore(5);
    private static final Semaphore semaphoreEntrega = new Semaphore(1);
    private final int id;
    private final String prato;

    private final double tempoMin;
    private final double tempoMax;

    public OvercookedThread(int id, String prato, double tempoMin, double tempoMax) {
        this.id = id;
        this.prato = prato;
        this.tempoMin = tempoMin;
        this.tempoMax = tempoMax;

    }

    @Override
    public void run() {

        pratos();
    }

    private void pratos() {

        try {
            semaphorePratos.acquire();

            Random random = new Random();
            double tempoCozimento = random.nextDouble(tempoMin, tempoMax);
            double percentual = 0.1 / tempoCozimento;
            int progress = 0;


            System.out.println("Iniciando cozimento da " + prato);
            System.out.println(tempoCozimento * 10);

            while (progress <= 100) {

                progress += (int) (percentual * 100);
                sleep(100);

                System.out.println("Percentual de Conclusão do prato: "+id+" " + progress + "%");

            }

            System.out.println("Prato " + id+" "+ prato  + " finalizado.");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphorePratos.release();
        }
        delivery();



    }

    private void delivery() {

        try {
            semaphoreEntrega.acquire();
            System.out.println("Realizando entrega do prato " + id+" "+ prato );
            sleep(500);
            System.out.println("Prato entregue " + id+" "+ prato );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphoreEntrega.release();
        }

    }


}
