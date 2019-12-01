package br.ufpb.dcx.appalpha.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.ufpb.dcx.appalpha.R;
import br.ufpb.dcx.appalpha.control.service.RecordsService;
import br.ufpb.dcx.appalpha.model.bean.Record;

import java.util.ArrayList;

public class RecordesActivity extends AppCompatActivity {
    RecordsService recorde;

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
        setContentView(R.layout.activity_recorde);

        pegandoTxt();

        recorde = RecordsService.getInstance(getApplicationContext());

        setandoDados();
    }

    public void setandoDados() {
        ArrayList<Record> records = recorde.getRecordistas();

        for(int i = 0; i < records.size(); i++) {

            switch (i) {

                case 0:
                    gravandoDadosNoRecorde(primeiroNome, records.get(i).getNome());
                    gravandoDadosNoRecorde(primeiroLugar, ""+ records.get(i).getPontuacao());
                    break;

                case 1:
                    gravandoDadosNoRecorde(segundoNome, records.get(i).getNome());
                    gravandoDadosNoRecorde(segundoLugar, ""+ records.get(i).getPontuacao());
                    break;

                case 2:
                    gravandoDadosNoRecorde(terceiroNome, records.get(i).getNome());
                    gravandoDadosNoRecorde(terceiroLugar, ""+ records.get(i).getPontuacao());
                    break;

                case 3:
                    gravandoDadosNoRecorde(quartoNome, records.get(i).getNome());
                    gravandoDadosNoRecorde(quartoLugar, ""+ records.get(i).getPontuacao());
                    break;

                case 4:
                    gravandoDadosNoRecorde(quintoNome, records.get(i).getNome());
                    gravandoDadosNoRecorde(quintoLugar, ""+ records.get(i).getPontuacao());
                    break;
            }

        }
    }

    public void voltandoParaMenu(View v) {
        finish();
    }

    public void gravandoDadosNoRecorde(TextView txt, String dado) {
        txt.setText(dado);
    }

    public void pegandoTxt() {
        primeiroNome = findViewById(R.id.nome_um);
        segundoNome = findViewById(R.id.nome_dois);
        terceiroNome = findViewById(R.id.nome_tres);
        quartoNome = findViewById(R.id.nome_quatro);
        quintoNome = findViewById(R.id.nome_cinco);

        primeiroLugar = findViewById(R.id.pontuacao_um);
        segundoLugar = findViewById(R.id.pontuacao_dois);
        terceiroLugar = findViewById(R.id.pontuacao_tres);
        quartoLugar = findViewById(R.id.pontuacao_quatro);
        quintoLugar = findViewById(R.id.pontuacao_cinco);
    }
}
