package br.ufpb.dcx.appalpha.view.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufpb.dcx.appalpha.R;
import br.ufpb.dcx.appalpha.model.desafios.CuidandoTelaProgresso;

import java.util.ArrayList;

public class ProgressoActivity extends AppCompatActivity {
    int progresso;
    ArrayList<String> palavrasUsadas;
    //Vocabulario vocab;
    double tempo;
    int somaErros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progresso);


        //Pegando informações da activity anterior
        Intent it = getIntent();
        progresso = it.getIntExtra("progresso", 0);
        palavrasUsadas = it.getStringArrayListExtra("palavrasUsadas");

        //vocab = (Vocabulario) it.getSerializableExtra("objeto");

        tempo = it.getDoubleExtra("tempo", 0);
        somaErros  = it.getIntExtra("somaErros", 0);
        Log.i("tempo do progresso", ""+tempo);


        //Alterando imagem, texto e som do progresso
        ImageView imagemProgresso = findViewById(R.id.imageView);
        TextView txt = findViewById(R.id.textView5);

        CuidandoTelaProgresso tela = new CuidandoTelaProgresso(imagemProgresso, txt);
        tela.atualizandoProgresso(progresso, getApplicationContext());


    }

    /**
     * Pega a nova palavra, coloca ela e seus dados (audio e imagem) em uma intent e envia para a activity da forca
     */
    public void voltandoParaDesafio() {

        //Progresso progress = new Progresso(vocab, palavrasUsadas);

       // CuidandoDeTudo cdt = progress.procurandoNovaPalavra();

        Intent it = new Intent(getApplicationContext(), ForcaActivity.class);
        //it = cdt.colocandoEmIntent(it);

        it.putExtra("erros", 0);
        it.putExtra("palavrasUsadas", palavrasUsadas);
        it.putExtra("progresso", progresso);
       // it.putExtra("objeto", vocab);
        it.putExtra("tempo", tempo);
        it.putExtra("somaErros", somaErros);

        startActivity(it);
    }

    /**
     * Redireciona para a activity da pontuação final
     */
    public void indoParaPontuacao() {

        Intent it = new Intent(getApplicationContext(), FinalActivity.class);
        it.putExtra("tempo", tempo);
        it.putExtra("somaErros", somaErros);
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
