package br.ufpb.dcx.appalpha.model.dao;

import br.ufpb.dcx.appalpha.model.Recordista;

import java.util.ArrayList;

public interface RecordesDao {

    void cadastrarNovoRecorde(double pontuacao, String nome);
    ArrayList<Recordista> getRecordistas();
}
