package br.ufpb.dcx.appalpha.model;


public class Recordista implements Comparable<Recordista>{
    private String nome;
    private double pontuacao;

    public Recordista(String nome, double pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    @Override
    public int compareTo(Recordista outroRecordista) {
        if(this.pontuacao > outroRecordista.getPontuacao()) {
            return -1;
        }

        if(this.pontuacao < outroRecordista.getPontuacao()) {
            return 1;
        }

        return 0;
    }
}
