package com.example.anaplb.appalpha;

import android.widget.Button;

import com.example.anaplb.appalpha.model.Vocabulario;
import com.example.anaplb.appalpha.model.VocabularioCidade;
import com.example.anaplb.appalpha.model.VocabularioComida;
import com.example.anaplb.appalpha.model.VocabularioCor;
import com.example.anaplb.appalpha.model.VocabularioCozinha;
import com.example.anaplb.appalpha.model.VocabularioFruta;
import com.example.anaplb.appalpha.model.VocabularioNatureza;
import com.example.anaplb.appalpha.transformandopalavra.ModificacaoCompletar;
import com.example.anaplb.appalpha.transformandopalavra.ModificacaoForca;
import com.example.anaplb.appalpha.transformandopalavra.ModificandoPalavras;

public class TemaFactory {

    public Vocabulario pegandoPalavra(Button button) {
        Vocabulario p = null;

        switch(button.getId()) {

            case(R.id.button):
                p =  VocabularioCidade.getInstance();
                break;

            case(R.id.button2):
                p = VocabularioComida.getInstance();
                break;

            case(R.id.button3):
                p =  VocabularioCor.getInstance();
                break;

            case(R.id.button4):
                p =  VocabularioCozinha.getInstance();
                break;

            case(R.id.button5):
                p =  VocabularioFruta.getInstance();
                break;

            case(R.id.button6):
                p =  VocabularioNatureza.getInstance();
                break;
        }

        return p;
    }

    /*public Vocabulario pegandoObjeto(Button bt, int id) {
        Vocabulario v = pegandoPalavra(bt);
        ModificandoPalavras mp = null;

        switch (id) {
            case 1:
                mp = new ModificacaoCompletar(v.getPalavras());
                break;

            case 2:
                mp = new ModificacaoForca(v.getPalavras());
                break;
        }

        v.setModificador(mp);

        return v;
    }*/

}
