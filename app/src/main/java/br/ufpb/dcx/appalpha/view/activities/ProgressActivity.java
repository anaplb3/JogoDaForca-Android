package br.ufpb.dcx.appalpha.view.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufpb.dcx.appalpha.R;
import br.ufpb.dcx.appalpha.control.ChallengeFacade;
import br.ufpb.dcx.appalpha.control.config.ButtonDelay;
import br.ufpb.dcx.appalpha.control.util.SomUtil;

public class ProgressActivity extends AppCompatActivity {
    private final String TAG = "ProgressActivity";
    private TextView txt;
    private boolean mudouActivity;
    private int millis = 2500;
    private char[] letras;
    private Thread leitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_progresso_);

        setUnderscore();

        setLetters();

        setChallengeImage();

        mudouActivity = false;

        SomUtil.getInstance().playSound(getApplicationContext(), Integer.parseInt(ChallengeFacade.getInstance().getCurrentChallenge().getSoundUrl()));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                readLetterByLetter();
            }
        }, SomUtil.getInstance().getDuracao());
    }

    private void readLetterByLetter() {
        leitor = new Thread() {

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
                                    updateUnderscoreInTextViewAndFacade(letra);
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

        leitor.start();

    }

    /**
     * Quando a activity é destruida também destroi o objeto do media player
     */
    protected void onDestroy() {
        super.onDestroy();
        //leitor.interrupt();
        SomUtil.getInstance().stopSound();

    }

    /**
     * Procura o audio da letra com base na letra passada como parâmetro e toca esse audio
     *
     * @param letra Letra que será tocada
     */
    public void playLetterSong(char letra) {

        int idSom = 0;

        switch (letra) {

            case 'a':
                idSom = R.raw.letraa;
                break;

            case 'b':
                idSom = R.raw.letrab;
                break;

            case 'c':
                idSom = R.raw.letrac;
                break;

            case 'd':
                idSom = R.raw.letrad;
                break;

            case 'e':
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
    public void updateUnderscoreInTextViewAndFacade(char letra) {
        String newUnderscore = updateUnderscore(letra);
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
    public void setLetters() {
        letras = new char[ChallengeFacade.getInstance().getCurrentChallenge().getWord().length()];
        for (int i = 0; i < ChallengeFacade.getInstance().getCurrentChallenge().getWord().length(); i++) {
            letras[i] = ChallengeFacade.getInstance().getCurrentChallenge().getWord().charAt(i);
        }
    }

    public String updateUnderscore(char letra) {
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
            Log.i(TAG,"pg"+ChallengeFacade.getInstance().getProgressCount());
            Log.i(TAG,"size"+ChallengeFacade.getInstance().getChallenges().size());
            if (ChallengeFacade.getInstance().getProgressCount() == ChallengeFacade.getInstance().getChallenges().size()-1) {
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