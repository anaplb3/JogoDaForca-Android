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

    public void setUnderscoreAtual(String underscoreAtualizado) {
        this.underscoreAtual = underscoreAtualizado;
    }

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

        String underscoreAposOChute = novoUnderscoreAposChute(chute.charAt(0));

        if (underscoreAtual.equals(underscoreAposOChute)) {
            return CHUTE_ERRADO;
        } else {
            underscoreAtual = underscoreAposOChute;
            return CHUTE_CERTO;
        }
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
    public String novoUnderscoreAposChute(char chute) {
        StringBuilder novoUnderscore = new StringBuilder(underscoreAtual);

        for (int indexNaPalavra = 0; indexNaPalavra < palavra.length(); indexNaPalavra++) {
            if (chute == palavra.charAt(indexNaPalavra)) {
                novoUnderscore.setCharAt(indexNaPalavra, palavra.charAt(indexNaPalavra));
            }
        }

        return novoUnderscore.toString();
    }

    /**
     * Verifica se a letra já foi chutada antes
     * @param chute letra que o usuário chutou
     * @return boolean indicando se já foi chutada antes
     */
    public boolean checandoSeJaExiste(char chute) {

        for (int indexEmUnderscore = 0; indexEmUnderscore < underscoreAtual.length(); indexEmUnderscore++) {
            if (chute == underscoreAtual.charAt(indexEmUnderscore)) {
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
