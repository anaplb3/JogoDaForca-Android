package com.example.anaplb.appalpha.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        carregarTeclado();

    }

    /**
     * Atualiza os dados da tela como a img da forca e o TextView com o underscore
     */
    public void atualizandoInformacoes(String letraClicada) {
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
    public void pegandoResposta(String letraClicada) {
        chute = letraClicada.toLowerCase();

        int res = tratandoPalavra.contandoErros(chute);

        if (res == CHUTE_NULO) {
            Toast.makeText(getApplicationContext(), "Você não pode chutar nada!", Toast.LENGTH_LONG).show();

        } else if (res == CHUTE_VALIDO) {
            this.erros += res;

        } else if (res == CHUTE_REPETIDO) {
            Toast.makeText(getApplicationContext(), "Você já chutou essa letra!", Toast.LENGTH_LONG).show();

        }

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

    /**
     * Recupera os botões do teclado e os escuta para que a cada novo clique seja enviado a respectiva letra para ser testada
     */
    private void carregarTeclado(){
        final Button a = findViewById(R.id.A);
        final Button b = findViewById(R.id.B);
        final Button c = findViewById(R.id.C);
        final Button d = findViewById(R.id.D);
        final Button e = findViewById(R.id.E);
        final Button f = findViewById(R.id.F);
        final Button g = findViewById(R.id.G);
        final Button h = findViewById(R.id.H);
        final Button i = findViewById(R.id.I);
        final Button j = findViewById(R.id.J);
        final Button k = findViewById(R.id.K);
        final Button l = findViewById(R.id.L);
        final Button m = findViewById(R.id.M);
        final Button n = findViewById(R.id.N);
        final Button o = findViewById(R.id.O);
        final Button p = findViewById(R.id.P);
        final Button q = findViewById(R.id.Q);
        final Button r = findViewById(R.id.R);
        final Button s = findViewById(R.id.S);
        final Button t = findViewById(R.id.T);
        final Button u = findViewById(R.id.U);
        final Button v = findViewById(R.id.V);
        final Button w = findViewById(R.id.W);
        final Button x = findViewById(R.id.X);
        final Button y = findViewById(R.id.Y);
        final Button z = findViewById(R.id.Z);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("a");
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("b");
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("c");
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("d");
            }
        });

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("e");
            }
        });

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("f");
            }
        });

        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("g");
            }
        });

        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("h");
            }
        });

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("i");
            }
        });

        j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("j");
            }
        });

        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("k");
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("l");
            }
        });

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("m");
            }
        });

        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("n");
            }
        });

        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("o");
            }
        });

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("p");
            }
        });

        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("q");
            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("r");
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("s");
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("t");
            }
        });

        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("u");
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("v");
            }
        });

        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("w");
            }
        });

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("x");
            }
        });

        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("y");
            }
        });

        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizandoInformacoes("z");
            }
        });

    }

}
