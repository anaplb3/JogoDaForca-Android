package com.example.anaplb.appalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.anaplb.appalpha.Som.Som;
import com.example.anaplb.appalpha.model.Vocabulario;

import java.util.zip.Inflater;

public class TemaActivity extends AppCompatActivity {
    Som som;
    CuidandoDeTudo facade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);

        getLayoutInflater().inflate(R.layout.activity_tema, null);
        som = new Som();

    }

    public void botaoEscolha(Button b) {
        Log.i("botao", "botaoEscolha");

        TemaFactory factory = new TemaFactory();

        Vocabulario p = factory.pegandoObjeto(b);
        facade = new CuidandoDeTudo(p.getPalavras(), p.getAudios(), p.getImgs());
        Log.i("size", ""+p.retornandoNomes().size());



        Intent intent = new Intent(TemaActivity.this, Forca.class);
        intent = facade.colocandoEmIntent(intent);
        intent.putExtra("erros", 0);
        startActivity(intent);

    }

    public void botaoCidade(View v) {
        Log.i("botao", "botaoCidade");
        Button b = findViewById(R.id.button);
        botaoEscolha(b);
    }

    public void botaoNatureza(View v) {
        Log.i("botao", "botaoNatureza");

        Button b = findViewById(R.id.button6);
        botaoEscolha(b);
    }

    public void botaoCozinha(View v) {
        Log.i("botao", "botaoCozinha");

        Button b = findViewById(R.id.button4);
        botaoEscolha(b);
    }

    public void botaoCor(View v) {
        Log.i("botao", "botaoCor");

        Button b = findViewById(R.id.button3);
        botaoEscolha(b);
    }

    public void botaoFruta(View v) {
        Log.i("botao", "botaoFruta");

        Button b = findViewById(R.id.button5);
        botaoEscolha(b);
    }

    public void botaoComida(View v) {
        Log.i("botao", "botaoComida");

        Button b = findViewById(R.id.button2);
        botaoEscolha(b);
    }

    public void imageButtonCidade(View v) {
        som.playSound(getApplicationContext(), R.raw.cidade);
    }

    public void imageButtonNatureza(View v) {
        som.playSound(getApplicationContext(), R.raw.natureza);
    }

    public void imageButtonComida(View v) {
        som.playSound(getApplicationContext(), R.raw.comida);
    }

    public void imageButtonCozinha(View v) {
        som.playSound(getApplicationContext(), R.raw.cozinha);
    }

    public void imageButtonCor(View v) {
        som.playSound(getApplicationContext(), R.raw.cores);
    }

    public void imageButtonFruta(View v) {
        som.playSound(getApplicationContext(), R.raw.frutas);
    }

}
