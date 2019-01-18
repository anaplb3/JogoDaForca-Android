package com.example.anaplb.appalpha.desafios;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.anaplb.appalpha.R;

public class CuidandoTelaProgresso {
    ImageView imgProgresso;
    TextView txt;

    public CuidandoTelaProgresso(ImageView iv, TextView txt) {
        this.imgProgresso = iv;
        this.txt = txt;
    }

    public void atualizandoProgresso(int progresso) {

        switch (progresso) {


            case 1:
                imgProgresso.setImageResource(R.drawable.progresso1);
                txt.setText("Bom começo! Vamos para o próximo!");
                break;

            case 2:
                imgProgresso.setImageResource(R.drawable.progresso2);
                txt.setText("Mais um pra conta! Partiu o próximo?");
                break;

            case 3:
                imgProgresso.setImageResource(R.drawable.progresso3);
                txt.setText("Falta pouco hein?");
                break;

            case 4:
                imgProgresso.setImageResource(R.drawable.progresso4);
                txt.setText("Tá quase lá!");
                break;

            case 5:
                imgProgresso.setBackgroundResource(R.drawable.progresso5);
                txt.setText("Acabou! Vamo olhar a pontuação?");
                break;
        }

    }


}
