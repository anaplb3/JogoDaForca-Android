package com.example.anaplb.appalpha.tratamento;

import android.util.Log;

public class TratandoPalavra {
    private String palavra;
    private String underscore;
    private int erros;

    public TratandoPalavra(String palavra) {
        this.palavra = palavra;
        this.erros = 0;
    }

    public void setUnderscore() {
        this.underscore = deixandoEmUnderscore();

    }

    public void setUnderscore(String under) {
        this.underscore = under;
    }

    private String deixandoEmUnderscore() {
        StringBuilder s = new StringBuilder(palavra);
        StringBuilder newS = new StringBuilder("");

        for(int i = 0; i < s.length(); i++) {
            newS.append("_");
        }

        return newS.toString();
    }


    private boolean checandoSeAcertou(char chute) {
        String under = novaPalavra(chute);
        if(!underscore.equals(under)) {

            Log.i("checando", "entrou");
            underscore = under;
            return true;

        } else {
            return false;

        }
    }


    private char[] arrayDeChars(String underscore) {
        char[] vetor = new char[underscore.length()];

        for(int i = 0; i < underscore.length(); i++) {
            vetor[i] = underscore.charAt(i);
        }

        return vetor;
    }

    public boolean checandoSeAcertouPalavra() {

        return palavra.equals(underscore);

    }

    private String novaPalavra(char chute) {
        char[] vetor = arrayDeChars(underscore);

        for(int i = 0; i < palavra.length(); i++) {
            if(chute == palavra.charAt(i)) {
                vetor[i] = palavra.charAt(i);
            }
        }

        return new String(vetor);
    }

    public int contandoErros(char chute) {
        if(!checandoSeAcertou(chute)) {
            erros ++;
        }

        return erros;
    }

    public String getPalavra() {
        return palavra;
    }

    public String getUnderscore() {
        return underscore;
    }
}
