package br.ufpb.dcx.appalpha.control.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import br.ufpb.dcx.appalpha.R;

public class ImageLoadUtil {
    private static ImageLoadUtil instance;

    private ImageLoadUtil(){}

    public static ImageLoadUtil getInstance(){
        if(instance == null){
            instance = new ImageLoadUtil();
        }

        return instance;
    }

    public void loadImage(String imageUrl, ImageView themeImageLeft, Context context){
        DiskCacheStrategy diskCacheStrategy = new DiskCacheStrategy() {
            @Override
            public boolean isDataCacheable(DataSource dataSource) {
                return true;
            }

            @Override
            public boolean isResourceCacheable(boolean isFromAlternateCacheKey, DataSource dataSource, EncodeStrategy encodeStrategy) {
                return true;
            }

            @Override
            public boolean decodeCachedResource() {
                return true;
            }

            @Override
            public boolean decodeCachedData() {
                return true;
            }
        };

        int erroImg = -1;
        try{
            erroImg = Integer.parseInt(imageUrl);
        }catch(NumberFormatException e){
            erroImg = R.drawable.no_image;
        }

        Glide.with(context)
                .load(imageUrl)
                .error(erroImg)
                .diskCacheStrategy(diskCacheStrategy)
                .into(themeImageLeft);
    }
}
