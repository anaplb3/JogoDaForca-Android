package br.ufpb.dcx.appalpha.control;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe responsável por escolher uma palavra aleatória do tema escolhido pelo usuário
 */
public class Escolhendo {
    private ArrayList<String> palavras;
    private ArrayList<Integer> audios;
    private ArrayList<Integer> imgs;
    private int indice;

    public Escolhendo(ArrayList<String> palavras, ArrayList<Integer> audios, ArrayList<Integer> imgs) {
        this.palavras = palavras;
        this.audios = audios;
        this.imgs = imgs;

        Random gerador = new Random();
        this.indice = gerador.nextInt(palavras.size());
    }


    public String palavra() {
        return palavras.get(indice);
    }

    public Integer audio() {
        return audios.get(indice);
    }

    public Integer imagem() {
        return imgs.get(indice);
    }

}
