package com.example.anaplb.appalpha.model;

import com.example.anaplb.appalpha.transformandopalavra.ModificandoPalavras;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Vocabulario implements Serializable{
    protected ArrayList<String> palavras;
    protected ArrayList<Integer> imgs;
    protected ArrayList<Integer> audios;
    protected ModificandoPalavras mp;

    Vocabulario() {
        povoandoAtributos();
    }

    void povoandoAtributos() {
        setPalavras();
        setImgs();
        setAudios();
    }

    public void setModificador(ModificandoPalavras mp) {
        this.mp = mp;
    }

    public ModificandoPalavras getModificador() {
        return mp;
    }

    private void setPalavras() {
        this.palavras = retornandoNomes();
    }

    private void setImgs() {
        this.imgs = retornandoImagens();
    }

    private void setAudios() {
        this.audios = retornandoAudios();
    }

    public ArrayList<String> getPalavras() {
        return palavras;
    }

    public ArrayList<Integer> getImgs() {
        return imgs;
    }

    public ArrayList<Integer> getAudios() {
        return audios;
    }

    public abstract ArrayList<String> retornandoNomes();

    public abstract ArrayList<Integer> retornandoImagens();

    public abstract ArrayList<Integer> retornandoAudios();

}
