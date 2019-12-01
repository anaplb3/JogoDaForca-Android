package br.ufpb.dcx.appalpha.model.bean;


public class Record implements Comparable<Record>{
    private String nome;
    private double pontuacao;

    public Record(String nome, double pontuacao) {
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
    public int compareTo(Record outroRecord) {
        if(this.pontuacao > outroRecord.getPontuacao()) {
            return -1;
        }

        if(this.pontuacao < outroRecord.getPontuacao()) {
            return 1;
        }

        return 0;
    }
}
