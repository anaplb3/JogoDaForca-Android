package com.example.anaplb.appalpha.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.anaplb.appalpha.CuidandoDeTudo;
import com.example.anaplb.appalpha.R;
import com.example.anaplb.appalpha.Som.Som;
import com.example.anaplb.appalpha.TemaFactory;
import com.example.anaplb.appalpha.config.ButtonDelay;
import com.example.anaplb.appalpha.config.VisualizadorImagem;
import com.example.anaplb.appalpha.model.Vocabulario;

import java.util.ArrayList;


public class TemaActivity extends AppCompatActivity {
    Som som;
    CuidandoDeTudo facade;
    int idSom;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);

        getLayoutInflater().inflate(R.layout.activity_tema, null);
        som = new Som();
        colocandoImagens();

    }

    public void botaoEscolha(ImageView img_button) {

        som.playSound(getApplicationContext(), idSom);
        int millis = som.getDuracao();

        Log.i("millis tema", ""+millis);

        intent = new Intent(TemaActivity.this, ForcaActivity.class);

        Log.i("botao", "botaoEscolha");

        TemaFactory factory = new TemaFactory();

        Vocabulario p = factory.pegandoPalavra(img_button);

        facade = new CuidandoDeTudo(p.getPalavras(), p.getAudios(), p.getImgs());
        Log.i("size", ""+p.retornandoNomes().size());

        ArrayList<String> palavrasUsadas = new ArrayList<>();

        intent = facade.colocandoEmIntent(intent);
        intent.putExtra("palavrasUsadas", palavrasUsadas);
        intent.putExtra("erros", 0);
        intent.putExtra("progresso", 0);
        intent.putExtra("tempo", 0.0);
        intent.putExtra("objeto", p);
        intent.putExtra("somaErros", 0);


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                som.stopSound();
                if(ButtonDelay.testClique(1000)) {
                    startActivity(intent);
                }

            }
        }, millis);

    }

    public void colocandoImagens() {
        VisualizadorImagem visu = new VisualizadorImagem();

        ImageView imgCidade = findViewById(R.id.img_cidade);
        ImageView imgNatureza = findViewById(R.id.img_natureza);
        ImageView imgComida = findViewById(R.id.img_comida);
        ImageView imgCozinha = findViewById(R.id.img_cozinha);
        ImageView imgCores = findViewById(R.id.img_cores);
        ImageView imgFrutas = findViewById(R.id.img_frutas);
        ImageView imgTemas = findViewById(R.id.imgTemas);

        visu.setandoImagem(imgCidade, R.drawable.cidade, getApplicationContext());
        visu.setandoImagem(imgNatureza, R.drawable.natureza, getApplicationContext());
        visu.setandoImagem(imgComida, R.drawable.comida, getApplicationContext());
        visu.setandoImagem(imgCozinha, R.drawable.cozinha, getApplicationContext());
        visu.setandoImagem(imgCores, R.drawable.cores, getApplicationContext());
        visu.setandoImagem(imgFrutas, R.drawable.frutas, getApplicationContext());
        visu.setandoImagem(imgTemas, R.drawable.temas, getApplicationContext());

    }

    public void imageButtonCidade(View v) {
        idSom = R.raw.cidade;

        ImageView img = findViewById(R.id.img_cidade);
        botaoEscolha(img);
    }

    public void imageButtonNatureza(View v) {

        idSom = R.raw.natureza;

        ImageView img = findViewById(R.id.img_natureza);
        botaoEscolha(img);
    }

    public void imageButtonComida(View v) {

        idSom = R.raw.comida;

        ImageView img = findViewById(R.id.img_comida);
        botaoEscolha(img);
    }

    public void imageButtonCozinha(View v) {

        idSom = R.raw.cozinha;

        ImageView img = findViewById(R.id.img_cozinha);
        botaoEscolha(img);
    }

    public void imageButtonCor(View v) {

        idSom = R.raw.cores;

        ImageView img = findViewById(R.id.img_cores);
        botaoEscolha(img);
    }

    public void imageButtonFruta(View v) {

        idSom = R.raw.frutas;

        ImageView img = findViewById(R.id.img_frutas);
        botaoEscolha(img);
    }

}
