package com.example.anaplb.appalpha.desafios;

import com.example.anaplb.appalpha.CuidandoDeTudo;
import com.example.anaplb.appalpha.model.Vocabulario;

/**
 * Classe responsável por procurar uma nova palavra para o desafio
 */
public class Progresso {
    Vocabulario vocab;
    String palavraUsada;

    public Progresso(Vocabulario v, String palavraUsada) {
        this.vocab = v;
        this.palavraUsada = palavraUsada;
    }


    public CuidandoDeTudo procurandoNovaPalavra() {

        CuidandoDeTudo cdt = new CuidandoDeTudo(vocab.getPalavras(), vocab.getAudios(), vocab.getImgs());

        if(cdt.getPalavra().equals(palavraUsada)) {
            while( cdt.getPalavra().equals(palavraUsada) ) {

                cdt = new CuidandoDeTudo(vocab.getPalavras(), vocab.getAudios(), vocab.getImgs());
            }
        }

        return cdt;
    }




}
