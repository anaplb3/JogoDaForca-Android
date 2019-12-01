package br.ufpb.dcx.appalpha.control.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class SomUtil {
    private static SomUtil somUtil;
    private MediaPlayer mediaPlayer;
    private int duracao;

    public static SomUtil getInstance() {
        if(somUtil == null) {
            somUtil = new SomUtil();
        }

        return somUtil;
    }

    private SomUtil(){}

    public synchronized void playSound(Context context, int songId) {

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
