package com.example.anaplb.appalpha.tratamento;

import android.util.Log;


public class VazioException {
    String underscore;
    String palavra;
    Integer idImagem;
    Integer idAudio;
    int erros;

    public VazioException(String under, String pv, Integer idImg, Integer idAudio, int erros) {
        this.underscore = under;
        this.palavra = pv;
        this.idImagem = idImg;
        this.idAudio = idAudio;
        this.erros = erros;
    }

    public void mostrandoGeral() {
        Log.i("vazioExcep underscore", underscore);
        Log.i("vazioExcep palavra", palavra);
        Log.i("vazioExcep imagem", ""+idImagem);
        Log.i("vazioExcep som", ""+idAudio);
        Log.i("vazioExcep erro", ""+erros);
    }

}
