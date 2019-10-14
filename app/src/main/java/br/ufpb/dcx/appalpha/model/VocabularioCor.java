package br.ufpb.dcx.appalpha.model;


import br.ufpb.dcx.appalpha.R;

import java.util.ArrayList;


public class VocabularioCor extends Vocabulario{
    private static VocabularioCor voc;

    public static VocabularioCor getInstance() {
        if(voc == null) {
            voc = new VocabularioCor();
        }

        return voc;
    }

    public ArrayList<String> retornandoNomes() {
        ArrayList<String> nomes = new ArrayList<>();

        nomes.add("amarelo");
        nomes.add("azul");
        nomes.add("branco");
        nomes.add("laranja");
        nomes.add("marrom");
        nomes.add("preto");
        nomes.add("rosa");
        nomes.add("roxo");
        nomes.add("verde");
        nomes.add("vermelho");

        return nomes;
    }

    public ArrayList<Integer> retornandoImagens() {
        ArrayList<Integer> imgs = new ArrayList<>();

        imgs.add(R.drawable.amarelo);
        imgs.add(R.drawable.azul);
        imgs.add(R.drawable.branco);
        imgs.add(R.drawable.laranja);
        imgs.add(R.drawable.marrom);
        imgs.add(R.drawable.preto);
        imgs.add(R.drawable.rosa);
        imgs.add(R.drawable.purple);
        imgs.add(R.drawable.verde);
        imgs.add(R.drawable.vermelho);

        return imgs;
    }

    @Override
    public ArrayList<Integer> retornandoAudios() {
        ArrayList<Integer> audio = new ArrayList<>();

        audio.add(R.raw.amarelo);
        audio.add(R.raw.azul);
        audio.add(R.raw.branco);
        audio.add( R.raw.laranja);
        audio.add(R.raw.marrom);
        audio.add(R.raw.preto);
        audio.add(R.raw.rosa);
        audio.add(R.raw.roxo);
        audio.add(R.raw.verde);
        audio.add(R.raw.vermelho);

        return audio;

    }

}
