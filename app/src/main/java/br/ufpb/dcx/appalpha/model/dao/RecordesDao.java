package br.ufpb.dcx.appalpha.model.dao;

import br.ufpb.dcx.appalpha.model.bean.Record;

import java.util.ArrayList;

public interface RecordesDao {

    void cadastrarNovoRecorde(double pontuacao, String nome);
    ArrayList<Record> getRecordistas();
}
