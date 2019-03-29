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
import com.example.anaplb.appalpha.Som.Som;
import com.example.anaplb.appalpha.desafios.Progresso;
import com.example.anaplb.appalpha.model.Vocabulario;
import com.example.anaplb.appalpha.tratamento.TratandoPalavra;

import java.util.ArrayList;

public class tela_progresso_Activity extends AppCompatActivity {
    private String palavra;
    private String underscore;
    private int idSom;
    private int idImagem;
    private int progresso;
    private TratandoPalavra tratandoPalavra;

    private ArrayList<String> palavrasUsadas;
    private Vocabulario vocab;
    private double tempo;
    private int somaErros;

    private char[] letras;

    private void mainThread() {

        final Thread atualizando = new Thread() {

            @Override
            public void run() {
                try {

                    while(!isInterrupted()) {

                        for(final char letra: letras) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            final Runnable update = new Runnable() {
                                @Override
                                public void run() {
                                    atualizandoGeral(letra);
                                }
                            };

                            runOnUiThread(update);
                        }

                        break;
                    }
                } catch (Exception e) {
                    Log.i("erro", e.getMessage());
                }
            }
        };

        atualizando.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_progresso_);

        // Pegando informações do desafio anterior
        informacoesDesafioAnterior();

        setandoLetras();

        setandoImagem();

        tocando_som_da_palavra();

        setandoUnderscore();

        mainThread();

    }

    public void atualizandoGeral(char letra) {
        underscore = novaPalavra(letra);
        setandoTXT(underscore);
        atualizandoUnderscore();
    }


    public void setandoUnderscore() {
        tratandoPalavra = new TratandoPalavra(palavra);

        underscore = tratandoPalavra.deixandoEmUnderscore();
    }

    public void atualizandoUnderscore() {
        tratandoPalavra.setUnderscore(underscore);
    }

    public void setandoLetras() {
        letras = new char[palavra.length()];
        for(int i = 0; i < palavra.length(); i++) {
            letras[i] = palavra.charAt(i);
        }
    }

    private char[] arrayDeChars(String underscore) {
        char[] vetor = new char[underscore.length()];

        for (int i = 0; i < underscore.length(); i++) {
            vetor[i] = underscore.charAt(i);
        }

        return vetor;
    }


    public String novaPalavra(char letra) {
        char[] vetor = arrayDeChars(underscore);

        Log.i("novaPalavra antes", new String(vetor));

        Log.i("length da palavra ", palavra);
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == letra) {

                if(underscore.charAt(i) == letra) {
                    continue;
                }
                vetor[i] = palavra.charAt(i);
                break;
            }

        }

        Log.i("novaPalavra depois", new String(vetor));

        return new String(vetor);
    }

    public void informacoesDesafioAnterior() {
        Intent it = getIntent();
        palavra = it.getStringExtra("ultimaPalavra");
        idSom = it.getIntExtra("ultimoSom", 0);
        idImagem = it.getIntExtra("ultimaImg", 0);

        palavrasUsadas = it.getStringArrayListExtra("palavrasUsadas");
        vocab = (Vocabulario) it.getSerializableExtra("objeto");
        tempo = it.getDoubleExtra("tempo", 0);
        somaErros = it.getIntExtra("somaErros", 0);
        progresso = it.getIntExtra("progresso", progresso);

    }

    public void setandoImagem() {
        ImageView img_desafio = findViewById(R.id.img_desafio);
        img_desafio.setImageResource(idImagem);
    }

    public void tocando_som_da_palavra() {
        Som som = Som.getInstance();

        som.playSound(getApplicationContext(), idSom);
    }

    public void setandoTXT(String underscore) {
        TextView txt = findViewById(R.id.txt_underscore);

        txt.setText(underscore);

        Log.i("set ", "setooou");
    }

    public void voltandoParaDesafio() {

        Progresso progress = new Progresso(vocab, palavrasUsadas);

        CuidandoDeTudo cdt = progress.procurandoNovaPalavra();

        Intent it = new Intent(getApplicationContext(), ForcaActivity.class);
        it = cdt.colocandoEmIntent(it);

        it.putExtra("erros", 0);
        it.putExtra("palavrasUsadas", palavrasUsadas);
        it.putExtra("progresso", progresso);
        it.putExtra("objeto", vocab);
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
     *
     * @param v View do botão
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