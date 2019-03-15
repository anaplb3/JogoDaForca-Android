package com.example.anaplb.appalpha.cronometro;

import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

/*
Classe que implementa o cronômetro para a contagem de pontos
 */
public class Cronometro {
    private Chronometer cronometro;
    private boolean isStart;

    public Cronometro(View txt) {
        cronometro = (Chronometer) txt;

        cronometro.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                cronometro = chronometer;
            }
        });
    }

    /*
    Começa a contagem do cronômetro
     */
    public void comecandoCronometro() {
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();
    }

    /*
    Para o cronômetro e retorna o tempo
     */
    public double parandoCronometroEPegandoTempo() {
        cronometro.stop();

        String tempo = (String) cronometro.getText();
        String tempo_form = tempo.replace(":", ".");

        return Double.parseDouble(tempo_form);

    }



}
