package com.example.anaplb.appalpha.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anaplb.appalpha.CuidandoDeTudo;
import com.example.anaplb.appalpha.R;
import com.example.anaplb.appalpha.desafios.CuidandoTelaProgresso;
import com.example.anaplb.appalpha.desafios.Progresso;
import com.example.anaplb.appalpha.model.Vocabulario;

public class ProgressoActivity extends AppCompatActivity {
    int progresso;
    String palavraUsada;
    Vocabulario vocab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progresso);


        //Pegando informações da activity anterior
        Intent it = getIntent();
        progresso = it.getIntExtra("progresso", 0);
        palavraUsada = it.getStringExtra("palavraUsada");
        vocab = (Vocabulario) it.getSerializableExtra("objeto");

        Log.i("progresso", "" + progresso);

        //Alterando imagem e texto de progresso
        ImageView imagemProgresso = findViewById(R.id.imageView);
        TextView txt = findViewById(R.id.textView5);

        CuidandoTelaProgresso tela = new CuidandoTelaProgresso(imagemProgresso, txt);
        tela.atualizandoProgresso(progresso);


    }

    /**
     * Pega a nova palavra, coloca ela e seus dados (audio e imagem) em uma intent e envia para a activity da forca
     */
    public void voltandoParaDesafio() {

        Progresso progress = new Progresso(vocab, palavraUsada);

        CuidandoDeTudo cdt = progress.procurandoNovaPalavra();

        Intent it = new Intent(getApplicationContext(), ForcaActivity.class);
        it = cdt.colocandoEmIntent(it);

        it.putExtra("erros", 0);
        it.putExtra("progresso", progresso);
        it.putExtra("objeto", vocab);

        startActivity(it);
    }

    /**
     * Redireciona para a activity da pontuação
     */
    public void indoParaPontuacao() {

        Intent it = new Intent(getApplicationContext(), FinalActivity.class);
        startActivity(it);

    }

    /**
     * Verifica se os desafios acabaram, se sim manda para a tela de pontuação, se não manda para a tela da forca
     * @param v
     */
    public void startAction(View v) {

        if (progresso == 5) {
            indoParaPontuacao();
        } else {
            voltandoParaDesafio();
        }

        finish();

    }

}
