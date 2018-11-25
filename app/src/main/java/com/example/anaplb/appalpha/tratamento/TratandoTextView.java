package com.example.anaplb.appalpha.tratamento;

import android.view.View;
import android.widget.TextView;

import com.example.anaplb.appalpha.transformandopalavra.Opcoes;

import java.util.ArrayList;
import java.util.Random;

public class TratandoTextView {
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private TextView txt5;
    private TextView txt6;
    private TextView txt7;
    private TextView txt8;
    private TextView txt9;
    private TextView txt10;
    private TextView txt11;
    private TextView txt12;
    private ArrayList<Character> opcoes;
    private Random gerador;
    private int numero_de_opcoes;

    public TratandoTextView(TextView txt1, TextView txt2, TextView txt3, TextView txt4, TextView txt5, TextView txt6, TextView txt7, TextView txt8, TextView txt9, TextView txt10, TextView txt11, TextView txt12, String palavra) {
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.txt3 = txt3;
        this.txt4 = txt4;
        this.txt5 = txt5;
        this.txt6 = txt6;
        this.txt7 = txt7;
        this.txt8 = txt8;
        this.txt9 = txt9;
        this.txt10 = txt10;
        this.txt11 = txt11;
        this.txt12 = txt12;
        Opcoes op = new Opcoes(palavra);
        opcoes = op.todasAsOpcoes();
        gerador = new Random();
        numero_de_opcoes = 12;

    }

    public void setandoTextView() {

        txt1.setText(pegandoLetra());
        txt2.setText(pegandoLetra());
        txt3.setText(pegandoLetra());
        txt4.setText(pegandoLetra());
        txt5.setText(pegandoLetra());
        txt6.setText(pegandoLetra());
        txt7.setText(pegandoLetra());
        txt8.setText(pegandoLetra());
        txt9.setText(pegandoLetra());
        txt10.setText(pegandoLetra());
        txt11.setText(pegandoLetra());
        txt12.setText(pegandoLetra());

    }


    private char pegandoLetra() {
        char letra = opcoes.get(gerador.nextInt(numero_de_opcoes));

        opcoes.remove(letra);
        numero_de_opcoes -= 1;

        return letra;

    }




}
