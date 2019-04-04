package com.example.anaplb.appalpha.Som;

import android.content.Context;
import android.media.MediaPlayer;

public class Som {
    private static Som som;
    private MediaPlayer mediaPlayer;

    public static Som getInstance() {
        if(som == null) {
            som = new Som();
        }

        return som;
    }


    public void playSound(Context context, int songId) {

        mediaPlayer = MediaPlayer.create(context, songId );
        mediaPlayer.start();

        if(!mediaPlayer.isPlaying()) {
            mediaPlayer.release();
        }

    }

}
