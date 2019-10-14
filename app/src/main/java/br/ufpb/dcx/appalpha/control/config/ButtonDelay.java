package br.ufpb.dcx.appalpha.control.config;

import android.os.Handler;

public class ButtonDelay {
    private static int clique = 0;
    private static ButtonDelay buttonDelay = null;

    public static ButtonDelay getInstance() {
        if(buttonDelay == null) {
            buttonDelay = new ButtonDelay();
        }

        return buttonDelay;
    }

    /**
     * Método que impede que o clique duplo do botão abra duas activities ao mesmo tempo
     * @param ms milisegundos em que o botão foi clicado
     * @return true caso o botão tenha sido tocado mais de uma vez, e false caso não
     */
    public static boolean testClique(int ms) {

        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                clique = 0;
            }
        };

        if (clique == 0) {
            clique = 1;
            handler.postDelayed(r, ms);
            return true;
        }
        handler.postDelayed(r, 1000);
        return false;
    }
}
