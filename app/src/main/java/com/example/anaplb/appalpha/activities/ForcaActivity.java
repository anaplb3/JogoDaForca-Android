package com.example.anaplb.appalpha.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anaplb.appalpha.CuidandoDaTela;
import com.example.anaplb.appalpha.R;
import com.example.anaplb.appalpha.Som.Som;
import com.example.anaplb.appalpha.cronometro.Cronometro;
import com.example.anaplb.appalpha.log.LogManagerExtStor;
import com.example.anaplb.appalpha.model.Vocabulario;
import com.example.anaplb.appalpha.tratamento.TratandoPalavra;

import java.util.ArrayList;


public class ForcaActivity extends AppCompatActivity {

    final int CHUTE_CERTO = 0;
    final int CHUTE_ERRADO = 1;
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
    ArrayList<String> palavrasUsadas;
    Cronometro cronometro;
    double tempo;
    int somaErros;
    private LogManagerExtStor logManagerExt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forca);

        som = new Som();

        //Pegando dados do Tema
        Intent it = getIntent();

        String underscore = it.getStringExtra("under");
        palavra = it.getStringExtra("palavra");
        idImagem = it.getIntExtra("img", 0);
        erros = it.getIntExtra("erros", 0);
        somaErros = it.getIntExtra("somaErros", 0);
        audio = it.getIntExtra("som", 0);
        progresso = it.getIntExtra("progresso", 0);
        vocabulario = (Vocabulario) it.getSerializableExtra("objeto");
        palavrasUsadas = it.getStringArrayListExtra("palavrasUsadas");
        tempo = it.getDoubleExtra("tempo", 0);

        // Setando o underscore no objeto para que ele possa ser modificado ao longo do jogo
        tratandoPalavra = new TratandoPalavra(palavra);
        tratandoPalavra.setUnderscore(underscore);

        // Setando o underscore no TextView da tela
        TextView txtUnderscore = findViewById(R.id.txt_underscore);
        txtUnderscore.setText(dandoEspacos(underscore));

        // Setando o ImageView da forca no objeto para modificação ao longo do jogo
        ImageView img_forca = findViewById(R.id.img_forca);
        cuidandoDaForca = new CuidandoDaTela(img_forca);

        // Setando imagem da palavra
        ImageView imgPalavra = findViewById(R.id.img_palavra);
        imgPalavra.setImageResource(idImagem);

        // Iniciando cronômetro
        cronometro = new Cronometro(findViewById(R.id.cronometro), getApplicationContext(), audio);
        cronometro.comecandoCronometro();

        //Iniciando o gerenciador de Logs para memória interna
        //this.logManagerExt = new LogManagerExtStor(getApplicationContext());

    }

    protected void onDestroy() {
        super.onDestroy();
        som.stopSound();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //this.logManagerExt.saveLogInFile();
    }

    /**
     * Verifica se a letra que o jogador clicou é a correta, caso sim, a cor do botão mudará para verde, caso não, mudará para vermelho.
     * @param letraClicada a letra que o usuário chutou
     * @param btnClicado botão correspondente a essa letra
     */
    public void feedbackColorButtonLeter(String letraClicada, Button btnClicado) {
        int resultado = tratandoPalavra.contandoErros(letraClicada);

        if (resultado == CHUTE_CERTO) {
            btnClicado.setBackgroundResource(R.drawable.greem_rounded_backgroud);
        } else if (resultado == CHUTE_REPETIDO) {
            Toast.makeText(getApplicationContext(), "Você já chutou essa letra!", Toast.LENGTH_SHORT).show();
        } else { // Chute errado
            btnClicado.setBackgroundResource(R.drawable.red_rounded_backgroud);
            Log.i("Json-Log", tratandoPalavra.getPalavra()+ " - " + letraClicada);
            //this.logManagerExt.addNewErro(tratandoPalavra.getPalavra(), letraClicada); //Adicionando caso de erro ao arquivo JSON
        }
    }

    /**
     * Atualiza os dados da tela como a img da forca e o TextView com o underscore
     * @param letraClicada letra que o usuário chutou
     * @param btnClicado botão correspondente a letra
     */
    public void atualizandoInformacoes(String letraClicada, Button btnClicado) {

        // Mudando cor do botão
        feedbackColorButtonLeter(letraClicada, btnClicado);

        // Pegando a resposta e verificando se houve erro
        pegandoResposta(letraClicada);

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
        Intent it = new Intent(this, tela_progresso_Activity.class);


        if (this.erros == QTD_MAX_ERROS || verificandoSeJaAcertou()) {
            tempo += cronometro.parandoCronometroEPegandoTempo();

            Log.i("tempo do if", ""+tempo);

            palavrasUsadas.add(palavra);
            it.putExtra("progresso", progresso += 1);
            it.putExtra("palavrasUsadas", palavrasUsadas);
            it.putExtra("objeto", vocabulario);
            it.putExtra("somaErros", somaErros+= erros);
            it.putExtra("tempo", tempo);

            it.putExtra("ultimoSom", audio);
            it.putExtra("ultimaPalavra", palavra);
            it.putExtra("ultimaImg", idImagem);
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
        txtUnderscore.setText(dandoEspacos(underscore));
    }

    /**
     * Dá um espaço entre as letras para o usuário poder ver quantas letras a palavra tem
     * @param underscore underscore da palavra a ser acertada
     * @return o underscore com as letras espaçadas
     */
    private String dandoEspacos(String underscore) {
        StringBuilder novaString = new StringBuilder();

        for(int i = 0; i < underscore.length(); i++) {
            novaString.append(underscore.charAt(i)) ;
            novaString.append(" ");
        }

        return novaString.toString();
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
    public void pegandoResposta(String letraClicada) {
        chute = letraClicada.toLowerCase();

        int res = tratandoPalavra.contandoErros(chute);

        Log.i("bug underscore", tratandoPalavra.getUnderscore());

        Log.i("bug", "" + res);

        // Verifica se o usuário errou o chute para poder adicionar nos erros e mudar a imagem da forca
        if (res == CHUTE_ERRADO) {
            this.erros += res;
        }

    }

    /**
     * Verifica se o usuário já acertou a palavra
     *
     * @return um boolean informando de se acertou ou não
     */
    private boolean verificandoSeJaAcertou() {

        return tratandoPalavra.checandoSeAcertouPalavra();
    }

    /**
     * Método que dá start em activities
     *
     * @param it Intent que será dado o start
     */
    private void startEmActivity(Intent it) {
        startActivity(it);
        finish();
    }

    /***
     * Evento de click em letra do teclado. A letra clicada e o botão são enviados para serem analizados e tratados de acordo com o desafio.
     * @param view Botão clicado
     */
    public void letterClick(View view){
        Button btClick = (Button) view;
        atualizandoInformacoes(btClick.getText().toString().toLowerCase(), btClick);
        Log.i("Botão clicado:", btClick.getText().toString());
    }

}
