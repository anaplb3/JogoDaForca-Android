package com.example.anaplb.appalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.anaplb.appalpha.Som.Som;
import com.example.anaplb.appalpha.model.Vocabulario;

public class theme_Activity extends AppCompatActivity {
    Som som;
    CuidandoDeTudo facade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_);

        getLayoutInflater().inflate(R.layout.activity_tema, null);
        som = new Som();
    }

    private void botaoEscolha(Button b) {
        Intent intent = new Intent(this, Forca.class);

        Log.i("botao", "botaoEscolha");

        TemaFactory factory = new TemaFactory();

        Vocabulario p = factory.pegandoPalavra(b);

        facade = new CuidandoDeTudo(p.getPalavras(), p.getAudios(), p.getImgs());
        Log.i("size", ""+p.retornandoNomes().size());


        intent = facade.colocandoEmIntent(intent);
        intent.putExtra("erros", 0);
        startActivity(intent);


    }

    public void botaoCidade(View v) {
        Log.i("botao", "botaoCidade");

        som.playSound(getApplicationContext(), R.raw.cidade);
        Button b = findViewById(R.id.button);
        botaoEscolha(b);
    }

    public void botaoNatureza(View v) {
        Log.i("botao", "botaoNatureza");

        som.playSound(getApplicationContext(), R.raw.natureza);
        Button b = findViewById(R.id.button6);
        botaoEscolha(b);
    }

    public void botaoCozinha(View v) {
        Log.i("botao", "botaoCozinha");

        som.playSound(getApplicationContext(), R.raw.cozinha);
        Button b = findViewById(R.id.button4);
        botaoEscolha(b);
    }

    public void botaoCor(View v) {
        Log.i("botao", "botaoCor");

        som.playSound(getApplicationContext(), R.raw.cores);
        Button b = findViewById(R.id.button3);
        botaoEscolha(b);
    }

    public void botaoFruta(View v) {
        Log.i("botao", "botaoFruta");

        som.playSound(getApplicationContext(), R.raw.frutas);
        Button b = findViewById(R.id.button5);
        botaoEscolha(b);
    }

    public void botaoComida(View v) {
        Log.i("botao", "botaoComida");

        som.playSound(getApplicationContext(), R.raw.comida);
        Button b = findViewById(R.id.button2);
        botaoEscolha(b);
    }

}
