package com.example.anaplb.appalpha.desafios;

import com.example.anaplb.appalpha.CuidandoDeTudo;
import com.example.anaplb.appalpha.model.Vocabulario;

import java.util.ArrayList;

/**
 * Classe responsável por procurar uma nova palavra para o desafio
 */
public class Progresso {
    private Vocabulario vocab;
    private ArrayList<String> palavrasUsadas;

    public Progresso(Vocabulario v, ArrayList<String> palavrasUsadas) {
        this.vocab = v;
        this.palavrasUsadas = palavrasUsadas;
    }

    /**
     * Compara a palavra escolhida para o próximo desafio com o array de palavras usadas, caso ela já tenha sido utilizada escolhe outra
     *
     * @return Um objeto com palavra, audio e imagem escolhido
     */
    public CuidandoDeTudo procurandoNovaPalavra() {

        CuidandoDeTudo cdt = new CuidandoDeTudo(vocab.getPalavras(), vocab.getAudios(), vocab.getImgs());

        if (palavrasUsadas.contains(cdt.getPalavra())) {

            while (palavrasUsadas.contains(cdt.getPalavra())) {

                cdt = new CuidandoDeTudo(vocab.getPalavras(), vocab.getAudios(), vocab.getImgs());

            }
        }

        return cdt;

    }

}
