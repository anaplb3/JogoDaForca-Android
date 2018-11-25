package com.example.anaplb.appalpha.transformandopalavra;

import java.util.Random;

public class RemovedorDeLetras {
    private char[] alfabeto = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private Random gerador = new Random();


    public char procurandoLetra(String palavra) {
        int indice;
        char letra = ' ';
        boolean achou = false;

        do {
           indice = gerador.nextInt(alfabeto.length);

           for(int i = 0; i < palavra.length(); i++) {
               if(palavra.charAt(i) == alfabeto[indice]) {
                   letra = alfabeto[indice];
                   achou = true;
                   break;
               }
           }

        } while(!achou);

        return letra;
    }

}
