package com.example.anaplb.appalpha.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anaplb.appalpha.R;

public class FinalActivity extends AppCompatActivity {
    private static final int pontuacaoInicial = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent it = getIntent();

        TextView txt = findViewById(R.id.textView);
        ImageView img = findViewById(R.id.imageView9);

        // Pegando informações da activity anterior
        double tempo = it.getDoubleExtra("tempo", 0);
        double pontuacaoFinal = retornaPontuacao(tempo, it.getIntExtra("somaErros", 0));

        pontuacao(img, pontuacaoFinal);

        txt.setText(String.format("Sua pontuação final foi: %s", pontuacaoFinal));

    }

    /**
     * Diminui da pontuação inicial o tempo e os erros do usuário
     * @param tempo tempo que ele levou para terminar os desafios
     * @param erros erros que o usuário cometeu durante os desafios
     * @return pontuação final do usuário
     */
    public double retornaPontuacao(double tempo, int erros) {

        return pontuacaoInicial - ( (erros * 10) + (tempo * 100) );
    }

    /**
     * Dependendo da pontuação do usuário mostra uma imagem com as estrelas que ele ganhou
     * @param img imageview que vai ser setada com uma das imagens das estrelas
     * @param pontuacao pontuação do usuário
     */
    public void pontuacao(ImageView img, double pontuacao) {

        if(pontuacao < 0) {
            img.setImageResource(R.drawable.zero);
        } else if(pontuacao <= 200.0) {
            img.setImageResource(R.drawable.um);
        } else if(pontuacao <= 400.0) {
            img.setImageResource(R.drawable.dois);
        } else if(pontuacao <= 600.0) {
            img.setImageResource(R.drawable.tres);
        } else if(pontuacao <= 800.0) {
            img.setImageResource(R.drawable.quatro);
        } else {
            img.setImageResource(R.drawable.cinco);
        }
    }

    public void jogarNovamente(View v) {
        Intent it = new Intent(getApplicationContext(), TemaActivity.class);
        startActivity(it);
        finish();
    }

    public void sair(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
