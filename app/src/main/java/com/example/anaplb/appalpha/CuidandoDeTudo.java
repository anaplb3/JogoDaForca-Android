package com.example.anaplb.appalpha;

import android.content.Intent;

import com.example.anaplb.appalpha.tratamento.TratandoPalavra;

import java.util.ArrayList;

public class CuidandoDeTudo {
    Escolhendo escolhendo;
    TratandoPalavra tratando;

    public CuidandoDeTudo(ArrayList<String> palavras, ArrayList<Integer> audios, ArrayList<Integer> imgs) {
        escolhendo = new Escolhendo(palavras, audios, imgs);
        tratando = new TratandoPalavra(escolhendo.palavra());
        tratando.setUnderscore();
    }

    public Intent colocandoEmIntent(Intent it) {

        it.putExtra("palavra", getPalavra());
        it.putExtra("under", getUnderscore());
        it.putExtra("img", getImagem());
        it.putExtra("som", getSom());

        return it;
    }

    public String getPalavra() {
        return escolhendo.palavra();
    }

    public String getUnderscore() {
        return tratando.getUnderscore();
    }

    public Integer getImagem() {
        return escolhendo.imagem();
    }

    public Integer getSom() {
        return escolhendo.audio();
    }
}
