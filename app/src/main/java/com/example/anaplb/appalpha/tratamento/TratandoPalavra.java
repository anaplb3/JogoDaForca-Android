package com.example.anaplb.appalpha.tratamento;

/**
 * Classe que faz as modificações no underscore e verifica acertos ou erros
 */
public class TratandoPalavra {
    public final int CHUTE_ERRADO = 0;
    public final int CHUTE_CERTO = 1;
    private String palavra;
    private String underscoreAtual;

    public TratandoPalavra(String palavra) {
        this.palavra = palavra;
        this.underscoreAtual = deixandoEmUnderscore();
    }

    public void setUnderscore() {
        this.underscoreAtual = deixandoEmUnderscore();

    }

    //setNovoUnderscore
    public void setUnderscoreAtual(String under) {
        this.underscoreAtual = under;
    }

    /**
     * Deixa a palavra em underscore
     * @return a palavra em forma de underscore
     */
    public String deixandoEmUnderscore() {
        StringBuilder palavraEmUnderscore = new StringBuilder();

        for (int i = 0; i < palavra.length(); i++) {
            palavraEmUnderscore.append("_");
        }

        return palavraEmUnderscore.toString();
    }


    /**
     * Verifica se o chute do usuário foi certo ou errado
     * @param chute chute do usuário
     * @return um int indicando se houve acerto ou erro
     */
    public int checandoSeAcertou(String chute) {

        String underscoreAposOChute = novaPalavra(chute.charAt(0));

        if (underscoreAtual.equals(underscoreAposOChute)) {
            return CHUTE_ERRADO;
        } else {
            underscoreAtual = underscoreAposOChute;
            return CHUTE_CERTO;
        }
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

        return palavra.equals(underscoreAtual);

    }

    /**
     * Verifica se o chute se encontra na palavra. Caso sim, a adiciona no underscore
     * @param chute letra que o usuário chutou
     * @return o underscore modificado
     */
    public String novaPalavra(char chute) {
        char[] vetor = arrayDeChars(underscoreAtual);

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

        for (int i = 0; i < underscoreAtual.length(); i++) {
            if (chute == underscoreAtual.charAt(i)) {
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


        if(checandoSeJaExiste(chute.charAt(0))) {
            return -1;
        } else if (checandoSeAcertou(chute) == CHUTE_ERRADO){
            erros ++;
        }

        return erros;

    }

    public String getUnderscoreAtual() {
        return underscoreAtual;
    }

    public String getPalavra(){
        return this.palavra;
    }
}
