package br.ufpb.dcx.appalpha.model.desafios;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufpb.dcx.appalpha.R;
import br.ufpb.dcx.appalpha.control.util.SomUtil;

public class CuidandoTelaProgresso {
    ImageView imgProgresso;
    TextView txt;

    public CuidandoTelaProgresso(ImageView iv, TextView txt) {
        this.imgProgresso = iv;
        this.txt = txt;
    }

    public void atualizandoProgresso(int progresso, Context context) {

        switch (progresso) {


            case 1:
                imgProgresso.setImageResource(R.drawable.progresso1);
                txt.setText("Bom começo! Vamos para o próximo?");
                SomUtil.getInstance().playSound(context, R.raw.bomcomeco);
                break;

            case 2:
                imgProgresso.setImageResource(R.drawable.progresso2);
                txt.setText("Mais um pra conta! Parabéns!");
                SomUtil.getInstance().playSound(context, R.raw.conta);

                break;

            case 3:
                imgProgresso.setImageResource(R.drawable.progresso3);
                txt.setText("Falta pouco hein?");
                SomUtil.getInstance().playSound(context, R.raw.faltapouco);

                break;

            case 4:
                imgProgresso.setImageResource(R.drawable.progresso4);
                txt.setText("Tá quase lá!");
                SomUtil.getInstance().playSound(context, R.raw.quasela);

                break;

            case 5:
                imgProgresso.setImageResource(R.drawable.progresso5);
                txt.setText("Acabou! Vamos olhar a pontuação?");
                SomUtil.getInstance().playSound(context, R.raw.pontuacao);

                break;
        }

    }


}
