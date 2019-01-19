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


    public CuidandoDeTudo procurandoNovaPalavra() {

        CuidandoDeTudo cdt = new CuidandoDeTudo(vocab.getPalavras(), vocab.getAudios(), vocab.getImgs());

        for(String palavraUsada: palavrasUsadas) {

            if(cdt.getPalavra().equals(palavraUsada)) {

                while( cdt.getPalavra().equals(palavraUsada) ) {

                    cdt = new CuidandoDeTudo(vocab.getPalavras(), vocab.getAudios(), vocab.getImgs());
                }
            }
        }

        return cdt;

    }

}
