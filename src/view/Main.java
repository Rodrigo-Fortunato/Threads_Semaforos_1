package view;

import controller.Servidor;

public class Main {
    public static void main(String[] args) {

        for (int id=0; id < 21;id++) {
            if (id % 3 == 1) {
                Servidor servidor = new Servidor(id,200,801,1000);
                servidor.start();

            } else if (id % 3 == 2) {
                Servidor servidor = new Servidor(id,500,1001,1500);
                servidor.start();

            } else {
                Servidor servidor = new Servidor(id,1000,1001,1500);
                servidor.start();

            }



        }




    }
}
