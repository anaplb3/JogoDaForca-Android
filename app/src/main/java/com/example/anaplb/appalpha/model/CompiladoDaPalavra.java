package com.example.anaplb.appalpha.model;

import java.util.ArrayList;

public class CompiladoDaPalavra {
    ArrayList<String> palavra;
    ArrayList<String> palavraModificada;
    ArrayList<Integer> audios;
    ArrayList<Integer> imgs;

    public CompiladoDaPalavra(Vocabulario v) {
        this.palavra = v.getPalavras();
        this.palavraModificada = v.getModificador().getPalavrasModificadas();
        this.audios = v.getAudios();
        this.imgs = v.getImgs();
    }

    public ArrayList<String> getPalavra() {
        return palavra;
    }

    public ArrayList<String> getPalavraModificada() {
        return palavraModificada;
    }

    public ArrayList<Integer> getAudios() {
        return audios;
    }

    public ArrayList<Integer> getImgs() {
        return imgs;
    }
}
