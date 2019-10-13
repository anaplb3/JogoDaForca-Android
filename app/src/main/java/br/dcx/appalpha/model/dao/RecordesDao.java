package br.dcx.appalpha.model.dao;

import br.dcx.appalpha.model.Recordista;

import java.util.ArrayList;

public interface RecordesDao {

    void cadastrarNovoRecorde(double pontuacao, String nome);
    ArrayList<Recordista> getRecordistas();
}
