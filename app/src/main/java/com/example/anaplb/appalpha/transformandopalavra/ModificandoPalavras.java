package com.example.anaplb.appalpha.transformandopalavra;

import java.util.ArrayList;

public abstract class ModificandoPalavras {
    ArrayList<String> palavras;
    ArrayList<String> palavrasModificadas;

    public ModificandoPalavras(ArrayList<String> palavras) {
        this.palavras = palavras;
    }

     char[] arrayDeChars(String palavra, int id) {
        char[] vetor = new char[palavra.length()];

        if(id == 1) {
            for(int i = 0; i < palavra.length(); i++) {
                vetor[i] = '_';
            }

            return vetor;

        } else {
            for(int i = 0; i < palavra.length(); i++) {
                vetor[i] = palavra.charAt(i);
            }

            return vetor;
        }
    }

    public abstract String verificandoSeLetraExiste(String unders, String palavra, char chute);


    public ArrayList<String> getPalavrasModificadas() {
        return palavrasModificadas;
    }
}
