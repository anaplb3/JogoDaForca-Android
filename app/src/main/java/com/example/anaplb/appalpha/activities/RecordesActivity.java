package com.example.anaplb.appalpha.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.anaplb.appalpha.R;
import com.example.anaplb.appalpha.dbhelper.Recordes;
import com.example.anaplb.appalpha.model.Recordista;

import java.util.ArrayList;

public class RecordesActivity extends AppCompatActivity {
    Recordes recorde;

    TextView primeiroNome;
    TextView segundoNome;
    TextView terceiroNome;
    TextView quartoNome;
    TextView quintoNome;

    TextView primeiroLugar;
    TextView segundoLugar;
    TextView terceiroLugar;
    TextView quartoLugar;
    TextView quintoLugar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordes);

        pegandoTxt();

        recorde = new Recordes(getApplicationContext());

        setandoDados();
    }

    public void setandoDados() {
        ArrayList<Recordista> recordistas = recorde.getRecordistas();

        for(int i = 0; i < recordistas.size(); i++) {

            switch (i) {

                case 0:
                    gravandoDadosNoRecorde(primeiroNome, recordistas.get(i).getNome());
                    gravandoDadosNoRecorde(primeiroLugar, ""+recordistas.get(i).getPontuacao());
                    break;

                case 1:
                    gravandoDadosNoRecorde(segundoNome, recordistas.get(i).getNome());
                    gravandoDadosNoRecorde(segundoLugar, ""+recordistas.get(i).getPontuacao());
                    break;

                case 2:
                    gravandoDadosNoRecorde(terceiroNome, recordistas.get(i).getNome());
                    gravandoDadosNoRecorde(terceiroLugar, ""+recordistas.get(i).getPontuacao());
                    break;

                case 3:
                    gravandoDadosNoRecorde(quartoNome, recordistas.get(i).getNome());
                    gravandoDadosNoRecorde(quartoLugar, ""+recordistas.get(i).getPontuacao());
                    break;

                case 4:
                    gravandoDadosNoRecorde(quintoNome, recordistas.get(i).getNome());
                    gravandoDadosNoRecorde(quintoLugar, ""+recordistas.get(i).getPontuacao());
                    break;
            }

        }
    }

    public void voltandoParaMenu(View v) {
        Intent it = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(it);
    }

    public void gravandoDadosNoRecorde(TextView txt, String dado) {
        txt.setText(dado);
    }

    public void pegandoTxt() {
        primeiroNome = findViewById(R.id.primeiro_nome);
        segundoNome = findViewById(R.id.segundo_nome);
        terceiroNome = findViewById(R.id.terceiro_nome);
        quartoNome = findViewById(R.id.quarto_nome);
        quintoNome = findViewById(R.id.quinto_nome);

        primeiroLugar = findViewById(R.id.primeiro_lugar);
        segundoLugar = findViewById(R.id.segundo_lugar);
        terceiroLugar = findViewById(R.id.terceiro_lugar);
        quartoLugar = findViewById(R.id.quarto_lugar);
        quintoLugar = findViewById(R.id.quinto_lugar);
    }
}
