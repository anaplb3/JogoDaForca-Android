package com.example.anaplb.appalpha;

import android.widget.Button;

import com.example.anaplb.appalpha.model.Vocabulario;
import com.example.anaplb.appalpha.model.VocabularioCidade;
import com.example.anaplb.appalpha.model.VocabularioComida;
import com.example.anaplb.appalpha.model.VocabularioCor;
import com.example.anaplb.appalpha.model.VocabularioCozinha;
import com.example.anaplb.appalpha.model.VocabularioFruta;
import com.example.anaplb.appalpha.model.VocabularioNatureza;

public class TemaFactory {

    public Vocabulario pegandoObjeto(Button button) {
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

}
