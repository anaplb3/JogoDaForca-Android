package br.ufpb.dcx.appalpha.control;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

import br.ufpb.dcx.appalpha.control.util.Som;

/*
Classe que implementa o cronômetro para a contagem de pontos
 */
public class Cronometro {
    private Chronometer cronometro;
    private Context context;

    public Cronometro(View txt, Context context) {
        this.cronometro = (Chronometer) txt;
        this.context = context;

        cronometro.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                cronometro = chronometer;

                String tempo = (String) cronometro.getText();

                if(verificarHoraDaDica(tempo)) {
                    darDicaSonora();
                }
            }
        });
    }

    private boolean verificarHoraDaDica(String tempo) {
        boolean hora_chegou = false;
        String tempo_formatado = tempo.replace(":", ".");

        double tempo_final = Double.parseDouble(tempo_formatado);

        int t = (int) tempo_final;


        if(t != 0) {

            if(tempo_final % 2 == 0) {
                hora_chegou = true;
            }
        }

        return hora_chegou;
    }

    private void darDicaSonora() {
        Som som = Som.getInstance();
        som.playSound(context, Integer.parseInt(ChallengeFacade.getInstance().getCurrentChallenge().getSoundUrl()));
    }

    /*
    Começa a contagem do cronômetro
     */
    public void comecarCronometro() {
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
