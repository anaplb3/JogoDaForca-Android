package com.example.anaplb.appalpha.tratamento;

import android.util.Log;

/**
 * Classe que faz as modificações no underscore e verifica acertos ou erros
 */
public class TratandoPalavra {
    private final int CHUTE_ERRADO = 0;
    private String palavra;
    private String underscore;

    public TratandoPalavra(String palavra) {
        this.palavra = palavra;
    }

    public void setUnderscore() {
        this.underscore = deixandoEmUnderscore();

    }

    public void setUnderscore(String under) {
        this.underscore = under;
    }

    /**
     * Deixa a palavra em underscore
     * @return a palavra em forma de underscore
     */
    private String deixandoEmUnderscore() {
        StringBuilder s = new StringBuilder(palavra);
        StringBuilder newS = new StringBuilder("");

        for (int i = 0; i < s.length(); i++) {
            newS.append("_");
        }

        return newS.toString();
    }


    /**
     * Verifica se o chute do usuário foi certo ou errado
     * @param chute chute do usuário
     * @return um int indicando se houve acerto ou erro
     */
    private int checandoSeAcertou(String chute) {
        int chute_certo = 1;
        int resultado;

        String under = novaPalavra(chute.charAt(0));

        // Se o underscore atual for diferente do novo, significa que o usuário acertou
        if (!underscore.equals(under)) {

            Log.i("checando", "entrou");
            underscore = under;
            resultado = chute_certo;

        } else {
            resultado = CHUTE_ERRADO;
        }

        return resultado;
    }


    private char[] arrayDeChars(String underscore) {
        char[] vetor = new char[underscore.length()];

        for (int i = 0; i < underscore.length(); i++) {
            vetor[i] = underscore.charAt(i);
        }

        return vetor;
    }

    /**
     * Verifica se o usuário acertou a palavra
     * @return um boolean indicando se acertou ou não
     */
    public boolean checandoSeAcertouPalavra() {

        return palavra.equals(underscore);

    }

    /**
     * Verifica se o chute se encontra na palavra. Caso sim, a adiciona no underscore
     * @param chute letra que o usuário chutou
     * @return o underscore modificado
     */
    private String novaPalavra(char chute) {
        char[] vetor = arrayDeChars(underscore);

        for (int i = 0; i < palavra.length(); i++) {
            if (chute == palavra.charAt(i)) {
                vetor[i] = palavra.charAt(i);
            }
        }

        return new String(vetor);
    }

    /**
     * Verifica se a letra já foi chutada antes
     * @param chute letra que o usuário chutou
     * @return boolean indicando se já foi chutada antes
     */
    private boolean checandoSeJaExiste(char chute) {
        char[] vetor = arrayDeChars(underscore);

        for (int i = 0; i < underscore.length(); i++) {
            if (chute == underscore.charAt(i)) {
                vetor[i] = underscore.charAt(i);
                return true;
            }
        }

        return false;
    }

    /**
     * Verifica se o chute do usuário foi vazio, se ele já chutou aquela letra ou se o chute foi errado
     * @param chute letra chutada pelo usuário
     * @return um inteiro indicando se o chute foi vazio, já existente ou errado
     */
    public int contandoErros(String chute) {
        int erros = 0;

        int CHUTE_VAZIO = 2;
        //Verificando se chute é vazio
        if (chute.isEmpty()) {

            return CHUTE_VAZIO;

        } else {
            //Verificando se letra já foi chutada
            if (checandoSeJaExiste(chute.charAt(0))) {

                return -1;

              //Verificando se o chute foi errado;
            } else if (checandoSeAcertou(chute) == CHUTE_ERRADO) {

                erros ++;

            }
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
