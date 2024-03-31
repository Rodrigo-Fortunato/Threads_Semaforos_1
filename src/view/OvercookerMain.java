package view;

import controller.OvercookedThread;

public class OvercookerMain {
    public static void main(String[] args) {

        for (int id=1; id <=10; id++) {
            OvercookedThread overcooked;
            if (id % 2==0) {
                overcooked = new OvercookedThread(id, "Lasanha a Bolonhesa", 0.6, 1.2);
            }else {
                overcooked = new OvercookedThread(id, "Sopa de Cebola", 0.5, 0.8);
            }
            overcooked.start();
        }
    }
}
