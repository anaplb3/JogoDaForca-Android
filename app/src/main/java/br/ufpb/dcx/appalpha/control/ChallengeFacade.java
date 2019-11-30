package br.ufpb.dcx.appalpha.control;

import android.util.Log;

import java.util.List;

import br.ufpb.dcx.appalpha.model.bean.Challenge;
import br.ufpb.dcx.appalpha.model.bean.Theme;

public class ChallengeFacade {
    public static final int ATTEMPT_ACEPTED = 0;
    public static int ATTEMPT_REJECTED = 1;
    public static int ATTEMPT_EXISTS = 2;
    private static ChallengeFacade instance;
    private List<Challenge> challenges;
    private Challenge currentChallenge;
    private Theme selectedTheme;
    private int erroCount;
    private int progressCount;
    private double time;
    private int sumError;
    private String underscore;


    private ChallengeFacade(){}

    public static ChallengeFacade getInstance(){
        if(instance == null){
            instance = new ChallengeFacade();
        }

        return instance;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void init(List<Challenge> challenges, Theme selectedTheme) {
        this.challenges = challenges;
        this.currentChallenge = challenges.get(0);
        this.selectedTheme = selectedTheme;
        this.erroCount = 0;
        this.progressCount = 0;
        this.time = 0.0;
        this.sumError = 0;
        setUnderscore();

    }

    public Theme getSelectedTheme() {
        return selectedTheme;
    }

    public Challenge getCurrentChallenge() {
        return currentChallenge;
    }

    public void nextChallenge(){
        try{
            this.currentChallenge = challenges.get(challenges.indexOf(currentChallenge) + 1);
            this.progressCount++;
            this.sumError += this.erroCount;
            this.erroCount = 0;
        }catch (IndexOutOfBoundsException e){
            this.currentChallenge = null;
        }
    }

    public void setCurrentChallenge(Challenge currentChallenge) {
        this.currentChallenge = currentChallenge;
    }

    public void increaseErro(){
        this.erroCount++;
    }

    /**
     * Deixa a palavra em underscore
     */
    public void setUnderscore() {
        StringBuilder s = new StringBuilder(currentChallenge.getWord());
        StringBuilder newS = new StringBuilder("");

        for (int i = 0; i < s.length(); i++) {
            newS.append("_");
        }

        this.underscore = newS.toString();
    }

    /**
     * Verifica se o chute do usuário foi certo ou errado
     * @param letter chute do usuário
     * @return um int indicando se houve acerto ou erro
     */
    public int checkAttempt(String letter) {
        int resultado;

        String under = updateWordByAttemp(letter.charAt(0));

        // Se o underscore atual for diferente do novo, significa que o usuário acertou
        if (!underscore.equals(under)) {
            underscore = under;
            resultado = ATTEMPT_ACEPTED;

        } else {
            resultado = ATTEMPT_REJECTED;
        }

        return resultado;
    }

    /**
     * Verifica se o chute se encontra na palavra. Caso sim, a adiciona no underscore
     * @param letter letra que o usuário chutou
     * @return o underscore modificado
     */
    public String updateWordByAttemp(char letter) {
        char[] vetor = underscore.toCharArray();

        for (int i = 0; i < this.currentChallenge.getWord().length(); i++) {
            if (letter == this.currentChallenge.getWord().charAt(i)) {
                vetor[i] = this.currentChallenge.getWord().charAt(i);
            }
        }

        return new String(vetor);
    }

    /**
     * Verifica se a letra já foi chutada antes
     * @param letter letra que o usuário chutou
     * @return boolean indicando se já foi chutada antes
     */
    private boolean checkAttempExists(char letter) {
        for (int i = 0; i < underscore.length(); i++) {
            if (letter == underscore.charAt(i)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Verifica se o usuário acertou a palavra, se o sublinhado estiver por completo igual a palavra (sem traços) é porque a palavra foi completa.
     * @return um boolean indicando se acertou ou não
     */
    public boolean checkWordAccepted() {
        return this.currentChallenge.getWord().equals(underscore);
    }

    /**
     * Verifica se o chute do usuário foi vazio, se ele já chutou aquela letra ou se o chute foi errado
     * @param letter letra chutada pelo usuário
     * @return um inteiro indicando se o chute foi vazio, já existente ou errado
     */
    public int getAttempResult(String letter) {

        if(checkAttempExists(letter.charAt(0))) {
            return ATTEMPT_EXISTS;
        } else if (checkAttempt(letter) == ATTEMPT_REJECTED){
            increaseErro();
            return ATTEMPT_REJECTED;
        }else{
            return ATTEMPT_ACEPTED;
        }

    }

    public String getUnderscore() {
        return underscore;
    }

    public int getProgressCount() {
        return progressCount;
    }

    public void setProgressCount(int progressCount) {
        this.progressCount = progressCount;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void increaseTime(double time){
        this.time += time;
    }

    public int getErroCount() {
        return erroCount;
    }

}
