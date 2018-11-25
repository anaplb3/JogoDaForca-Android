package com.example.anaplb.appalpha.transformandopalavra;

import java.util.ArrayList;
import java.util.Random;

public class Opcoes {
    private char[] alfabeto = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private String palavra;
    private ArrayList<Character> opcoes;
    private final int numero_de_opcoes = 12;
    private Random gerador;

    public Opcoes(String palavra) {
        this.palavra = palavra;
        this.opcoes = new ArrayList<>();
        gerador = new Random();
    }

    private void letrasDaPalavra() {

        for(int i = 0; i < palavra.length(); i++) {
            opcoes.add(palavra.charAt(i));
        }

    }

    public ArrayList<Character> todasAsOpcoes() {
        letrasDaPalavra();
        int resto = numero_de_opcoes - opcoes.size();

        for(int i = 0; i < resto; i++) {
            char letraEscolhida;

            do {
                letraEscolhida = alfabeto[gerador.nextInt(resto)];

            } while(!letraJaEscolhida(letraEscolhida));

            opcoes.add(letraEscolhida);
        }

        return opcoes;
    }

    private boolean letraJaEscolhida(char letra) {

        for(char c: opcoes) {
            if(letra == c) {
                return true;
            }
        }

        return false;
    }

}
