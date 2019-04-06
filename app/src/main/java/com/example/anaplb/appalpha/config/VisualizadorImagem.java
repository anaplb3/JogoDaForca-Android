package com.example.anaplb.appalpha.config;

import android.content.Context;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class VisualizadorImagem {
    private ImageDownsize downsize;


    public void setandoImagem(ImageView img, int idImagem, Context context) {

        ImageDownsize downsize = new ImageDownsize(idImagem, img, context);
        downsize.execute();
    }
}
