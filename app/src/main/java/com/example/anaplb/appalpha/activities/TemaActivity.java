package com.example.anaplb.appalpha.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.anaplb.appalpha.CuidandoDeTudo;
import com.example.anaplb.appalpha.R;
import com.example.anaplb.appalpha.Som.Som;
import com.example.anaplb.appalpha.TemaFactory;
import com.example.anaplb.appalpha.model.Vocabulario;


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


    public void botaoEscolha(ImageView img_button) {
        Intent intent = new Intent(TemaActivity.this, ForcaActivity.class);

        Log.i("botao", "botaoEscolha");

        TemaFactory factory = new TemaFactory();

        Vocabulario p = factory.pegandoPalavra(img_button);

        facade = new CuidandoDeTudo(p.getPalavras(), p.getAudios(), p.getImgs());
        Log.i("size", ""+p.retornandoNomes().size());


        intent = facade.colocandoEmIntent(intent);
        intent.putExtra("pontuacao", 0);
        intent.putExtra("erros", 0);
        intent.putExtra("progresso", 0);
        intent.putExtra("objeto", p);
        startActivity(intent);


    }

    public void imageButtonCidade(View v) {

        som.playSound(getApplicationContext(), R.raw.cidade);
        ImageView img = findViewById(R.id.img_cidade);
        botaoEscolha(img);
    }

    public void imageButtonNatureza(View v) {
        som.playSound(getApplicationContext(), R.raw.natureza);
        ImageView img = findViewById(R.id.img_natureza);
        botaoEscolha(img);
    }

    public void imageButtonComida(View v) {

        som.playSound(getApplicationContext(), R.raw.comida);
        ImageView img = findViewById(R.id.img_comida);
        botaoEscolha(img);
    }

    public void imageButtonCozinha(View v) {
        som.playSound(getApplicationContext(), R.raw.cozinha);
        ImageView img = findViewById(R.id.img_cozinha);
        botaoEscolha(img);
    }

    public void imageButtonCor(View v) {

        som.playSound(getApplicationContext(), R.raw.cores);
        ImageView img = findViewById(R.id.img_cores);
        botaoEscolha(img);
    }

    public void imageButtonFruta(View v) {

        som.playSound(getApplicationContext(), R.raw.frutas);
        ImageView img = findViewById(R.id.img_frutas);
        botaoEscolha(img);
    }

}
