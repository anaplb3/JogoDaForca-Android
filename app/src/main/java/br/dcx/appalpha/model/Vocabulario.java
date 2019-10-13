package br.dcx.appalpha.model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Vocabulario implements Serializable {
    protected ArrayList<String> palavras;
    protected ArrayList<Integer> imgs;
    protected ArrayList<Integer> audios;

    Vocabulario() {
        povoandoAtributos();
    }

    void povoandoAtributos() {
        setPalavras();
        setImgs();
        setAudios();
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
