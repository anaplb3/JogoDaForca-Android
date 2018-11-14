package com.example.anaplb.appalpha;

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

import com.example.anaplb.appalpha.Som.Som;
import com.example.anaplb.appalpha.tratamento.TratandoPalavra;

public class Forca extends AppCompatActivity {
    Integer audio;
    Integer idImagem;
    Som som;
    String chute;
    TratandoPalavra tratandoPalavra;
    int erros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forca);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        som = new Som();

        Intent it = getIntent();
        String underscore = it.getStringExtra("under");
        String palavra = it.getStringExtra("palavra");
        idImagem = it.getIntExtra("img", 0);
        erros = it.getIntExtra("erros", 0);

        audio = it.getIntExtra("som", 0);

        tratandoPalavra = new TratandoPalavra(palavra);
        tratandoPalavra.setUnderscore(underscore);

        Log.i("unders", underscore);

        ImageView forca = findViewById(R.id.img_forca);
        CuidandoDaTela cuidandoDaForca = new CuidandoDaTela(forca);

        cuidandoDaForca.mudandoForca(erros);

        TextView txtUnderscore = findViewById(R.id.txt_underscore);
        txtUnderscore.setText(underscore);

        ImageView imgPalavra = findViewById(R.id.img_palavra);
        imgPalavra.setImageResource(idImagem);

    }

    public void escutandoPalavra(View v) {
        som.playSound(getApplicationContext(), audio);
    }

    public void pegandoResposta(View v) {
        Intent it;
        EditText txt = findViewById(R.id.txt_chute);
        chute = txt.getText().toString().toLowerCase();

        if (chute.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Você não pode chutar nada!", Toast.LENGTH_LONG).show();
            it = new Intent(this, Forca.class);
            it = colocandoEmIntent(it);
            startActivity(it);
            finish();
        }


        this.erros += tratandoPalavra.contandoErros(chute.charAt(0));

        if (this.erros == 6) {
            it = new Intent(this, FinalActivity.class);
            it.putExtra("ganhou", false);
        } else if (tratandoPalavra.checandoSeAcertouPalavra()) {
            it = new Intent(this, FinalActivity.class);
            it.putExtra("ganhou", true);
        } else {
            it = new Intent(this, Forca.class);
            it = colocandoEmIntent(it);
        }

        startActivity(it);
        finish();
    }

    public Intent colocandoEmIntent(Intent it) {
        it.putExtra("erros", erros);
        it.putExtra("palavra", tratandoPalavra.getPalavra());
        it.putExtra("som", audio);
        it.putExtra("img", idImagem);
        it.putExtra("under", tratandoPalavra.getUnderscore());

        return it;
    }

}
