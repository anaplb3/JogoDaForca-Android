package br.ufpb.dcx.appalpha.view.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufpb.dcx.appalpha.R;
import br.ufpb.dcx.appalpha.control.ChallengeFacade;
import br.ufpb.dcx.appalpha.control.config.ButtonDelay;
import br.ufpb.dcx.appalpha.control.util.SomUtil;

public class ProgressActivity extends AppCompatActivity {
    private TextView txt;
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
                            try {
                                Thread.sleep(millis);
                            } catch (InterruptedException e) {
                                Log.i("for try", "entrou no catch");
                                break;
                            }

                            final Runnable update = new Runnable() {
                                @Override
                                public void run() {
                                    playLetterSong(letra);
                                    atualizandoGeral(letra);
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

        setUnderscore();

        setandoLetras();

        setChallengeImage();

        mudouActivity = false;

        SomUtil.getInstance().playSound(getApplicationContext(), Integer.parseInt(ChallengeFacade.getInstance().getCurrentChallenge().getSoundUrl()));

        thread_atualizando_letras();

    }

    /**
     * Quando a activity é destruida também destroi o objeto do media player
     */
    protected void onDestroy() {
        super.onDestroy();
        atualizando.interrupt();
        SomUtil.getInstance().stopSound();

    }

    /**
     * Procura o audio da letra com base na letra passada como parâmetro e toca esse audio
     *
     * @param letra Letra que será tocada
     */
    public void playLetterSong(char letra) {

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

        SomUtil.getInstance().playSound(getApplicationContext(), idSom);
    }

    /**
     * Seta o textView com o underscore da palavra
     */
    public void setUnderscore() {
        ChallengeFacade.getInstance().setUnderscore();
    }

    /**
     * Atualiza o underscore atual com o novo
     *
     * @param letra letra que vai ser adicionada ao underscore
     */
    public void atualizandoGeral(char letra) {
        String newUnderscore = novaPalavra(letra);
        setTextViewWord(newUnderscore);
        atualizandoUnderscore(newUnderscore);
    }

    /**
     * Seta o underscore no objeto tratando palavra para que
     * na próxima vez que ele gerar o underscore novo ele tenha o mais atualizado
     */
    public void atualizandoUnderscore(String underscore) {
        ChallengeFacade.getInstance().setUnderscore(underscore);
    }

    /**
     * Quebra a palavra do desafio em um array de char,
     * para que ele possa adicionar um a um no underscore
     */
    public void setandoLetras() {
        letras = new char[ChallengeFacade.getInstance().getCurrentChallenge().getWord().length()];
        for (int i = 0; i < ChallengeFacade.getInstance().getCurrentChallenge().getWord().length(); i++) {
            letras[i] = ChallengeFacade.getInstance().getCurrentChallenge().getWord().charAt(i);
        }
    }

    public String novaPalavra(char letra) {
        char[] vetor = ChallengeFacade.getInstance().getUnderscore().toCharArray();

        for (int i = 0; i < ChallengeFacade.getInstance().getCurrentChallenge().getWord().length(); i++) {
            if (ChallengeFacade.getInstance().getCurrentChallenge().getWord().charAt(i) == letra) {

                if (ChallengeFacade.getInstance().getUnderscore().charAt(i) == letra) {
                    continue;
                }
                vetor[i] = ChallengeFacade.getInstance().getCurrentChallenge().getWord().charAt(i);
                break;
            }

        }

        return new String(vetor);
    }

    public void goToTheNextChallenge() {
        mudouActivity = true;
        Intent it = new Intent(getApplicationContext(), ForcaActivity.class);
        ChallengeFacade.getInstance().nextChallenge();
        startActivity(it);
        finish();
    }

    /**
     * Redireciona para a activity da pontuação final
     */
    public void goToTheFinalActivity() {
        mudouActivity = true;
        Intent it = new Intent(getApplicationContext(), FinalActivity.class);
        startActivity(it);

    }

    /**
     * Verifica se os desafios acabaram, se sim manda para a tela de pontuação, se não manda para a tela da forca
     *
     * @param v View do botão
     */
    public void goToTheNextActivityByCondiction(View v) {
        // Testa se o botão foi clicado mais de uma vez em um intervalo de 1 segundo
        if(ButtonDelay.delay(1000)) {
            if (ChallengeFacade.getInstance().getProgressCount() == ChallengeFacade.getInstance().getChallenges().size()) {
                goToTheFinalActivity();
            } else {
                goToTheNextChallenge();
            }
        }
        finish();
    }

    public void setChallengeImage() {
        ImageView img_desafio = findViewById(R.id.img_desafio);
        img_desafio.setImageResource(Integer.parseInt(ChallengeFacade.getInstance().getCurrentChallenge().getImageUrl()));
    }

    public void setTextViewWord(String underscore) {
        txt = findViewById(R.id.txt_underscore);
        txt.setText(underscore);
    }

}