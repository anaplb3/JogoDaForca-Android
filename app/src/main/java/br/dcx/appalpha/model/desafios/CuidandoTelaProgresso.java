package br.dcx.appalpha.model.desafios;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anaplb.appalpha.R;
import br.dcx.appalpha.control.Som.Som;

public class CuidandoTelaProgresso {
    ImageView imgProgresso;
    TextView txt;
    Som som;

    public CuidandoTelaProgresso(ImageView iv, TextView txt) {
        this.imgProgresso = iv;
        this.txt = txt;
        this.som = new Som();
    }

    public void atualizandoProgresso(int progresso, Context context) {

        switch (progresso) {


            case 1:
                imgProgresso.setImageResource(R.drawable.progresso1);
                txt.setText("Bom começo! Vamos para o próximo?");
                som.playSound(context, R.raw.bomcomeco);
                break;

            case 2:
                imgProgresso.setImageResource(R.drawable.progresso2);
                txt.setText("Mais um pra conta! Parabéns!");
                som.playSound(context, R.raw.conta);

                break;

            case 3:
                imgProgresso.setImageResource(R.drawable.progresso3);
                txt.setText("Falta pouco hein?");
                som.playSound(context, R.raw.faltapouco);

                break;

            case 4:
                imgProgresso.setImageResource(R.drawable.progresso4);
                txt.setText("Tá quase lá!");
                som.playSound(context, R.raw.quasela);

                break;

            case 5:
                imgProgresso.setImageResource(R.drawable.progresso5);
                txt.setText("Acabou! Vamos olhar a pontuação?");
                som.playSound(context, R.raw.pontuacao);

                break;
        }

    }


}
