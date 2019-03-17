package com.example.anaplb.appalpha.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anaplb.appalpha.model.Recordista;

import java.util.ArrayList;
import java.util.Collections;

public class Recordes implements RecordesDao{
    private SQLiteDatabase escreve;
    private SQLiteDatabase ler;

    public Recordes(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public void cadastrarNovoRecorde(double pontuacao, String nome) {
        ContentValues cv = new ContentValues();

        try {
            cv.put("nome", nome);
            cv.put("pontuacao", pontuacao);

            escreve.insert(DbHelper.nomeTabela, null, cv);
            Log.i("insert info", "os dados entraram");
        } catch(Exception e) {
            Log.i("insert info", "deu merda hein "+ e.getMessage());
        }
    }

    @Override
    public ArrayList<Recordista> getRecordistas() {
        ArrayList<Recordista> recordistas = new ArrayList<>();
        String nome;
        double pontuacao;
        Recordista r1;

        Cursor cursor = ler.rawQuery("SELECT nome, pontuacao FROM " +DbHelper.nomeTabela, null);

         // Pegando indices da tabela
        int indiceNome = cursor.getColumnIndex("nome");
        int indicePontuacao = cursor.getColumnIndex("pontuacao");


        if(cursor.moveToFirst() && cursor.getCount() >= 1) {
            Log.i("info insert", "entrou no if");
            do {
                nome = cursor.getString(indiceNome);
                pontuacao = cursor.getDouble(indicePontuacao);

                r1 = new Recordista(nome, pontuacao);
                recordistas.add(r1);
            } while(cursor.moveToNext());
        }

        Collections.sort(recordistas);

        cursor.close();

        return recordistas;
    }
}
