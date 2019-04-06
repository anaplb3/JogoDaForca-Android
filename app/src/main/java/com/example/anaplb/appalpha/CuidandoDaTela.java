package com.example.anaplb.appalpha;

import android.content.Context;
import android.widget.ImageView;

import com.example.anaplb.appalpha.config.ImageDownsize;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Responsável por ir atualizando a //forca durante o jogo
 */
public class CuidandoDaTela {
    private SoftReference<ImageView> forca;
    private WeakReference<Context> context;

    public CuidandoDaTela(ImageView forca, Context context) {

        this.forca = new SoftReference<>(forca);
        this.context = new WeakReference<>(context);
    }

    public void mudandoForca(int erro) {

        ImageDownsize downsize = new ImageDownsize(forca, context);

        switch (erro) {
            case 0:
                downsize.setIdImagem(R.drawable.erro0);
                //forca.setImageResource(R.drawable.erro0);
                break;

            case 1:
                downsize.setIdImagem(R.drawable.erro1);
                //forca.setImageResource(R.drawable.erro1);
                break;

            case 2:
                downsize.setIdImagem(R.drawable.erro2);
                //forca.setImageResource(R.drawable.erro2);
                break;

            case 3:
                downsize.setIdImagem(R.drawable.erro3);
                //forca.setImageResource(R.drawable.erro3);
                break;

            case 4:
                downsize.setIdImagem(R.drawable.erro4);
                //forca.setImageResource(R.drawable.erro4);
                break;

            case 5:
                downsize.setIdImagem(R.drawable.erro5);
                //forca.setImageResource(R.drawable.erro5);
                break;

            case 6:
                downsize.setIdImagem(R.drawable.erro6);
                //forca.setImageResource(R.drawable.erro6);
                break;
        }

        downsize.execute();
    }

}
