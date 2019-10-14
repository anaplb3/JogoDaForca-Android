package br.ufpb.dcx.appalpha.control;

import android.widget.ImageView;

import br.ufpb.dcx.appalpha.R;

import br.ufpb.dcx.appalpha.model.Vocabulario;
import br.ufpb.dcx.appalpha.model.VocabularioCidade;
import br.ufpb.dcx.appalpha.model.VocabularioComida;
import br.ufpb.dcx.appalpha.model.VocabularioCor;
import br.ufpb.dcx.appalpha.model.VocabularioCozinha;
import br.ufpb.dcx.appalpha.model.VocabularioFruta;
import br.ufpb.dcx.appalpha.model.VocabularioNatureza;

/**
 * Classe que retorna o objeto-tema dependendo da escolha do usuário
 */
public class TemaFactory {

    /**
     * A partir do id do image view que o usuário escolheu, retorna um objeto com o tema correspondente
     * @param img tema escolhido
     * @return um objeto Vocabulário do tema escolhido pelo jogador

    public Vocabulario pegandoPalavra(ImageView img) {
        Vocabulario p = null;

        switch(img.getId()) {

            case(R.id.img_cidade):
                p =  VocabularioCidade.getInstance();
                break;

            case(R.id.img_comida):
                p = VocabularioComida.getInstance();
                break;

            case(R.id.img_cores):
                p =  VocabularioCor.getInstance();
                break;

            case(R.id.img_cozinha):
                p =  VocabularioCozinha.getInstance();
                break;

            case(R.id.img_frutas):
                p =  VocabularioFruta.getInstance();
                break;

            case(R.id.img_natureza):
                p =  VocabularioNatureza.getInstance();
                break;
        }

        return p;
    }*/

}
