package com.example.anaplb.appalpha.config;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ImageDownsize extends AsyncTask<Void, Void, Bitmap> {
    private int idImagem;
    private SoftReference<ImageView> imageView;
    private WeakReference<Context> context;

    public ImageDownsize(int idImagem, ImageView imgView, Context context) {
        this.idImagem = idImagem;
        this.imageView = new SoftReference<>(imgView);
        this.context = new WeakReference<>(context);
    }

    public ImageDownsize(SoftReference<ImageView> imageView, WeakReference<Context> context) {
        this.imageView = imageView;
        this.context = context;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {

        try {

            int targetW = imageView.get().getWidth();
            int targetH = imageView.get().getHeight();

            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inJustDecodeBounds = true;

            BitmapFactory.decodeResource(context.get().getResources(), idImagem, options);

            int photoW = options.outWidth;
            int photoH = options.outHeight;

            int scaleFactor = 1;
            if( (targetW > 0) || (targetH > 0) ) {
                scaleFactor = Math.min(photoW/targetW, photoH/targetH);
            }

            options.inJustDecodeBounds = false;
            options.inSampleSize = scaleFactor;

            return BitmapFactory.decodeResource(context.get().getResources(), idImagem, options);

        } catch(Exception e) {
            Log.i("erro", "não sei o que foi mas não deu certo");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        if(imageView != null) {
            ImageView img = imageView.get();

            if(img != null) {
                img.setImageBitmap(bitmap);
            } else {
                Log.i("erro", "tá null");
            }
        } else {

            Log.i("erro", "tá null essa bagaça");
        }

    }
}
