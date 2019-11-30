package br.ufpb.dcx.appalpha.view.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufpb.dcx.appalpha.R;
import br.ufpb.dcx.appalpha.control.config.ButtonDelay;

import java.util.ArrayList;

public class ProgressActivity extends AppCompatActivity {
    private String palavra;
    private String underscore;
    private int idSom;
    private int idImagem;
    private int progresso;
    //private TratandoPalavra tratandoPalavra;

    private ArrayList<String> palavrasUsadas;
    //private Vocabulario vocab;
    private double tempo;
    private int somaErros;
    private TextView txt;
    private MediaPlayer primeiroMediaPlayer = null;
    private boolean mudouActivity;
    private int millis = 1900;
    private Thread atualizando;

    private char[] letras;

    private void thread_atualizando_letras() {

        atualizando = new Thread() {

            @Override
            public void run() {
                try {

                    while (!isInterrupted()) {


                        for (final char letra : letras) {

                            Log.i("mudou", "" + mudouActivity);
                            Log.i("millis for", "" + millis);

                            try {
                                Log.i("o que vem primeiro", "thread sleep");
                                Thread.sleep(millis);
                            } catch (InterruptedException e) {
                                Log.i("for try", "entrou no catch");
                                break;
                            }
                            final Runnable update = new Runnable() {
                                @Override
                                public void run() {
                                    tocando_som_da_letra(letra);

                                    atualizandoGeral(letra);

                                    Log.i("millis depois", "" + millis);

                                }
                            };

                            if (mudouActivity) {
                                Thread.currentThread().interrupt();
                            } else {
                                runOnUiThread(update);
                            }

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

        setandoUnderscore();

        setandoLetras();

        setandoImagem();

        mudouActivity = false;

        tocando_som(idSom);

        thread_atualizando_letras();

    }

    /**
     * Quando a activity é destruida também destroi o objeto do media player
     */
    protected void onDestroy() {
        super.onDestroy();
        atualizando.interrupt();
        parandoSom();

    }

    // Metodos relacionados ao som :

    /**
     * Verifica se a instancia do media player já foi destruida, caso não ele o destroi
     */
    public void parandoSom() {
        if (primeiroMediaPlayer != null) {
            try {
                primeiroMediaPlayer.stop();
                primeiroMediaPlayer.release();
                primeiroMediaPlayer = null;
            } catch (IllegalStateException e) {
                Log.e("illegal state", "provavelmente o media player não foi iniciado");
            }

        }

    }

    /**
     * Procura o audio da letra com base na letra passada como parâmetro e toca esse audio
     *
     * @param letra Letra que será tocada
     */
    public void tocando_som_da_letra(char letra) {

        int idSom = 0;

        Log.i("tocando som", "" + letra);

        switch (letra) {

            case 'a':
                Log.i("tocando som", "" + letra);
                idSom = R.raw.letraa;
                break;

            case 'b':
                Log.i("tocando som", "" + letra);
                idSom = R.raw.letrab;
                break;

            case 'c':
                Log.i("tocando som", "" + letra);
                idSom = R.raw.letrac;
                break;

            case 'd':
                Log.i("tocando som", "" + letra);
                idSom = R.raw.letrad;
                break;

            case 'e':
                Log.i("tocando som", "" + letra);
                idSom = R.raw.letrae;
                break;

            case 'f':
                idSom = R.raw.letraf;
                break;

            case 'g':
                idSom = R.raw.letrag;
                break;

            case 'h':
                idSom = R.raw.letrah;
                break;

            case 'i':
                idSom = R.raw.letrai;
                break;

            case 'j':
                idSom = R.raw.letraj;
                break;

            case 'k':
                idSom = R.raw.letrak;
                break;

            case 'l':
                idSom = R.raw.letral;
                break;

            case 'm':
                idSom = R.raw.letram;
                break;

            case 'n':
                idSom = R.raw.letran;
                break;

            case 'o':
                idSom = R.raw.letrao;
                break;

            case 'p':
                idSom = R.raw.letrap;
                break;

            case 'q':
                idSom = R.raw.letraq;
                break;

            case 'r':
                idSom = R.raw.letrar;
                break;

            case 's':
                idSom = R.raw.letras;
                break;

            case 't':
                idSom = R.raw.letrat;
                break;

            case 'u':
                idSom = R.raw.letrau;
                break;

            case 'v':
                idSom = R.raw.letrav;
                break;

            case 'w':
                idSom = R.raw.letraw;
                break;

            case 'x':
                idSom = R.raw.letrax;
                break;

            case 'y':
                idSom = R.raw.letray;
                break;

            case 'z':
                idSom = R.raw.letraz;
                break;

        }

        tocando_som(idSom);
    }

    /**
     * Usa a instância do media player para tocar o audio passado como parâmetro
     *
     * @param idSom id do audio que será tocado
     */
    public void tocando_som(int idSom) {

        primeiroMediaPlayer = MediaPlayer.create(this, idSom);

        primeiroMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Log.i("on prepared", "entrou no on prepared");
                primeiroMediaPlayer.start();
            }
        });

    }

    // Metodos relacionados ao underscore

    /**
     * Seta o textView com o underscore da palavra
     */
    public void setandoUnderscore() {
        //tratandoPalavra = new TratandoPalavra(palavra);

        //underscore = tratandoPalavra.deixandoEmUnderscore();
    }

    /**
     * Atualiza o underscore atual com o novo
     *
     * @param letra letra que vai ser adicionada ao underscore
     */
    public void atualizandoGeral(char letra) {
        underscore = novaPalavra(letra);
        setandoTXT(underscore);
        //atualizandoUnderscore();
    }

    /**
     * Seta o underscore no objeto tratando palavra para que
     * na próxima vez que ele gerar o underscore novo ele tenha o mais atualizado
     */
    //public void atualizandoUnderscore() {
    //    tratandoPalavra.setUnderscore(underscore);
    //}

    /**
     * Quebra a palavra do desafio em um array de char,
     * para que ele possa adicionar um a um no underscore
     */
    public void setandoLetras() {
        letras = new char[palavra.length()];
        for (int i = 0; i < palavra.length(); i++) {
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

                if (underscore.charAt(i) == letra) {
                    continue;
                }
                vetor[i] = palavra.charAt(i);
                break;
            }

        }

        return new String(vetor);
    }

    // Metodos relacionados a Intent

    public void informacoesDesafioAnterior() {
        Intent it = getIntent();
        palavra = it.getStringExtra("ultimaPalavra");
        idSom = it.getIntExtra("ultimoSom", 0);
        idImagem = it.getIntExtra("ultimaImg", 0);

        palavrasUsadas = it.getStringArrayListExtra("palavrasUsadas");
        //vocab = (Vocabulario) it.getSerializableExtra("objeto");
        tempo = it.getDoubleExtra("tempo", 0);
        somaErros = it.getIntExtra("somaErros", 0);
        progresso = it.getIntExtra("progresso", progresso);

    }

    public void voltandoParaDesafio() {
        mudouActivity = true;

        //Progresso progress = new Progresso(vocab, palavrasUsadas);

        //CuidandoDeTudo cdt = progress.procurandoNovaPalavra();

        Intent it = new Intent(getApplicationContext(), ForcaActivity.class);
        //it = cdt.colocandoEmIntent(it);

        it.putExtra("erros", 0);
        it.putExtra("palavrasUsadas", palavrasUsadas);
        it.putExtra("progresso", progresso);
        //it.putExtra("objeto", vocab);
        it.putExtra("tempo", tempo);
        it.putExtra("somaErros", somaErros);

        startActivity(it);
        finish();
    }

    /**
     * Redireciona para a activity da pontuação final
     */
    public void indoParaPontuacao() {

        mudouActivity = true;
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

        // Testa se o botão foi clicado mais de uma vez em um intervalo de 1 segundo
        if(ButtonDelay.testClique(1000)) {
            if (progresso == 5) {
                indoParaPontuacao();
            } else {
                voltandoParaDesafio();
            }
        }
        finish();

    }

    // Relacionados a sets

    public void setandoImagem() {
        ImageView img_desafio = findViewById(R.id.img_desafio);
        img_desafio.setImageResource(idImagem);
    }


    public void setandoTXT(String underscore) {
        txt = findViewById(R.id.txt_underscore);

        txt.setText(underscore);

        Log.i("set ", "setooou");
    }

}