package com.example.anaplb.appalpha.transformandopalavra;

import java.util.ArrayList;

public class ModificacaoCompletar extends ModificandoPalavras{
    RemovedorDeLetras rm;

    public ModificacaoCompletar(ArrayList<String> palavras) {
        super(palavras);
        rm = new RemovedorDeLetras();
        removendoLetras();
    }

    @Override
    public String verificandoSeLetraExiste(String vazio, String palavra, char letraRetirada) {
        char[] vetor = arrayDeChars(palavra, 0);

        for(int i = 0; i < palavra.length(); i++) {
            if(letraRetirada == palavra.charAt(i)) {
                vetor[i] = '_';
                break;
            }

        }

        return new String(vetor);
    }


    private void removendoLetras() {
        ArrayList<String> mds = new ArrayList<>();

        for(String s: this.palavras) {

            mds.add(verificandoSeLetraExiste(null, s, rm.procurandoLetra(s)));

        }

        this.palavrasModificadas = mds;

    }


}
