package com.example.anaplb.appalpha.dbhelper;

import com.example.anaplb.appalpha.model.Recordista;

import java.util.ArrayList;

public interface RecordesDao {

    void cadastrarNovoRecorde(double pontuacao, String nome);
    ArrayList<Recordista> getRecordistas();
}
