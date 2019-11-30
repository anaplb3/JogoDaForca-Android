package br.ufpb.dcx.appalpha.control.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class Som {
    private static Som som;
    private MediaPlayer mediaPlayer;
    private int duracao;

    public static Som getInstance() {
        if(som == null) {
            som = new Som();
        }

        return som;
    }

    private Som(){}

    public void playSound(Context context, int songId) {

        this.mediaPlayer = MediaPlayer.create(context, songId);
        this.duracao = mediaPlayer.getDuration();

        this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSound();
            }
        });

    }

    public int getDuracao() {
        return duracao;
    }

    public void stopSound() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (IllegalStateException e) {
                Log.e("illegal state", "provavelmente o media player não foi iniciado");
            }
        }
    }

}
