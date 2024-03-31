package controller;


import java.util.concurrent.Semaphore;

public class Servidor extends Thread {

    private final int id;
    private static final Semaphore semaphore = new Semaphore(1);
    int tranMin;
    int calcMin;
    int calcMax;

    public Servidor(int id, int calcMin, int calcMax,int tranMin) {
        this.id = id;

        this.calcMin = calcMin;
        this.calcMax = calcMax;
        this.tranMin = tranMin;
    }

    @Override
    public void run() {


        calculos();
        transacao();
    }

    private void calculos() {


        int tempo = (int) ((Math.random() * calcMax) + calcMax);
        System.out.println("Realizando Calculos. " +" Thread "+ id);
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }

    private void transacao() {

        try {
            semaphore.acquire();
            int tempo = (int) (Math.random() + tranMin);
            System.out.println("Realizando Transações do Banco de Dados." +" Thread "+ id);
            sleep(tempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }

    }

}
