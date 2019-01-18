package com.example.anaplb.appalpha.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anaplb.appalpha.CuidandoDaTela;
import com.example.anaplb.appalpha.R;
import com.example.anaplb.appalpha.Som.Som;
import com.example.anaplb.appalpha.desafios.Progresso;
import com.example.anaplb.appalpha.model.Vocabulario;
import com.example.anaplb.appalpha.tratamento.TratandoPalavra;


public class ForcaActivity extends AppCompatActivity {
    final int CHUTE_NULO = 2;
    final int CHUTE_VALIDO = 1;
    final int CHUTE_REPETIDO = -1;
    final int QTD_MAX_ERROS = 6;
    CuidandoDaTela cuidandoDaForca;
    Integer audio;
    Integer idImagem;
    Som som;
    String chute;
    TratandoPalavra tratandoPalavra;
    int erros;
    int progresso;
    String palavra;
    Vocabulario vocabulario;
    int pontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forca);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        som = new Som();
        Log.i("check", "check");


        //Pegando dados do Tema
        Intent it = getIntent();
        String underscore = it.getStringExtra("under");
        palavra = it.getStringExtra("palavra");
        idImagem = it.getIntExtra("img", 0);
        erros = it.getIntExtra("erros", 0);
        audio = it.getIntExtra("som", 0);
        progresso = it.getIntExtra("progresso", 0);
        vocabulario = (Vocabulario) it.getSerializableExtra("objeto");
        pontuacao = it.getIntExtra("pontuacao", 0);

        // Setando o underscore no objeto para que ele possa ser modificado ao longo do jogo
        tratandoPalavra = new TratandoPalavra(palavra);
        tratandoPalavra.setUnderscore(underscore);

        // Setando o underscore no TextView da tela
        TextView txtUnderscore = findViewById(R.id.txt_underscore);
        txtUnderscore.setText(underscore);

        // Setando o ImageView da forca no objeto para modificação ao longo do jogo
        ImageView img_forca = findViewById(R.id.img_forca);
        cuidandoDaForca = new CuidandoDaTela(img_forca);

        // Setando imagem da palavra
        ImageView imgPalavra = findViewById(R.id.img_palavra);
        imgPalavra.setImageResource(idImagem);

    }

    /**
     * Atualiza os dados da tela como a img da forca e o TextView com o underscore
     * @param v view
     */
    public void atualizandoInformacoes(View v) {
        // Pegando a resposta e verificando se houve erro
        pegandoResposta();

        // Atualiza a imagem da forca de acordo com a quantidade de erros
        setandoAForca();

        // Setando o text view com o novo underscore
        setandoTxtUnderscore(tratandoPalavra.getUnderscore());

        verificandoSeOJogoAcabou();
    }

    /**
     * Atualiza a imagem da forca
     */
    private void setandoAForca() {
        cuidandoDaForca.mudandoForca(erros);
    }

    /**
     * Verifica se a quantidade máxima de erros foi atingida ou se o usuário já acertou a palavra
     */
    private void verificandoSeOJogoAcabou() {
        Intent it = new Intent(this, ProgressoActivity.class);

        if (this.erros == QTD_MAX_ERROS) {

            it.putExtra("progresso", progresso +=1 );
            it.putExtra("palavraUsada", palavra);
            it.putExtra("objeto", vocabulario);
            startEmActivity(it);
        } else if (verificandoSeJaAcertou()) {

            it.putExtra("pontuacao", pontuacao += 1);
            it.putExtra("progresso", progresso +=1 );
            it.putExtra("palavraUsada", palavra);
            it.putExtra("objeto", vocabulario);
            startEmActivity(it);
        }
    }

    /**
     * Seta o text view com o underscore da palavra
     *
     * @param underscore underscore da palavra
     */
    private void setandoTxtUnderscore(String underscore) {
        TextView txtUnderscore = findViewById(R.id.txt_underscore);
        txtUnderscore.setText(underscore);
    }

    /**
     * Toca o som da palavra
     *
     * @param v view
     */
    public void escutandoPalavra(View v) {
        som.playSound(getApplicationContext(), audio);
    }

    /**
     * Pega a resposta do usuário e verifica se houve acerto, resposta nulo ou repetida. A partir disso contabiliza a quantidade de erros
     */
    public void pegandoResposta() {
        EditText txt = findViewById(R.id.txt_chute);
        chute = txt.getText().toString().toLowerCase();

        int res = tratandoPalavra.contandoErros(chute);

        if (res == CHUTE_NULO) {
            Toast.makeText(getApplicationContext(), "Você não pode chutar nada!", Toast.LENGTH_LONG).show();

        } else if (res == CHUTE_VALIDO) {
            this.erros += res;

        } else if (res == CHUTE_REPETIDO) {
            Toast.makeText(getApplicationContext(), "Você já chutou essa letra!", Toast.LENGTH_LONG).show();

        }

        txt.getText().clear();

    }

    /**
     * Verifica se o usuário já acertou a palavra
     * @return um boolean informando de se acertou ou não
     */
    private boolean verificandoSeJaAcertou() {

        return tratandoPalavra.checandoSeAcertouPalavra();
    }

    /**
     * Método que dá start em activities
     * @param it Intent que será dado o start
     */
    private void startEmActivity(Intent it) {
        startActivity(it);
        finish();
    }

}
