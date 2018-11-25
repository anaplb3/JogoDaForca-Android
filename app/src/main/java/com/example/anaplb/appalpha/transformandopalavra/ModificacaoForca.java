package com.example.anaplb.appalpha.transformandopalavra;

import java.util.ArrayList;

public class ModificacaoForca extends ModificandoPalavras{

    public ModificacaoForca(ArrayList<String> palavras) {
        super(palavras);
        underscores();
    }

    @Override
    public String verificandoSeLetraExiste(String unders, String palavra, char chute) {
        char[] vetor = arrayDeChars(unders, 0);

        for(int i = 0; i < palavra.length(); i++) {
            if(chute == palavra.charAt(i)) {
                vetor[i] = palavra.charAt(i);
            }

        }

        return new String(vetor);
    }

    private void underscores() {
        ArrayList<String> unders = new ArrayList<>();

        for(String s: palavras) {

            unders.add(new String(arrayDeChars(s, 1)));
        }

        this.palavrasModificadas = unders;
    }

}
