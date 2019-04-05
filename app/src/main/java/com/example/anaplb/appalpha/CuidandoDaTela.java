package com.example.anaplb.appalpha;

import android.widget.ImageView;

import com.example.anaplb.appalpha.config.ImageDownsize;

/**
 * Responsável por ir atualizando a forca durante o jogo
 */
public class CuidandoDaTela {
    private ImageView forca;

    public CuidandoDaTela(ImageView forca) {
        this.forca = forca;
    }



    public void mudandoForca(int erro) {
        switch (erro) {
            case 0:
                forca.setImageResource(R.drawable.erro0);
                break;

            case 1:
                forca.setImageResource(R.drawable.erro1);
                break;

            case 2:
                forca.setImageResource(R.drawable.erro2);
                break;

            case 3:
                forca.setImageResource(R.drawable.erro3);
                break;

            case 4:
                forca.setImageResource(R.drawable.erro4);
                break;

            case 5:
                forca.setImageResource(R.drawable.erro5);
                break;

            case 6:
                forca.setImageResource(R.drawable.erro6);
                break;
        }

    }

}
