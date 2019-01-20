package com.example.anaplb.appalpha.model;


import com.example.anaplb.appalpha.R;

import java.util.ArrayList;


public class VocabularioFruta extends Vocabulario {
    private static VocabularioFruta voc;

    public static VocabularioFruta getInstance() {
        if(voc == null) {
            voc = new VocabularioFruta();
        }

        return voc;
    }

    public ArrayList<String> retornandoNomes() {
        ArrayList<String> nomes = new ArrayList<>();

        nomes.add("abacate");
        nomes.add("abacaxi");
        nomes.add("banana");
        nomes.add("goiaba");
        nomes.add("manga");
        nomes.add("melancia");
        nomes.add("morango");
        nomes.add("pera");
        nomes.add("uva");

        return nomes;
    }

    public ArrayList<Integer> retornandoImagens() {
        ArrayList<Integer> imgs = new ArrayList<>();

        imgs.add(R.drawable.abacate);
        imgs.add(R.drawable.abacaxi);
        imgs.add(R.drawable.banana);
        imgs.add(R.drawable.goiaba);
        imgs.add(R.drawable.manga);
        imgs.add(R.drawable.melancia);
        imgs.add(R.drawable.morango);
        imgs.add(R.drawable.pera);
        imgs.add(R.drawable.uva);

        return imgs;
    }

    @Override
    public ArrayList<Integer> retornandoAudios() {
        ArrayList<Integer> audio = new ArrayList<>();

        audio.add(R.raw.abacate);
        audio.add(R.raw.abacaxi);
        audio.add(R.raw.banana);
        audio.add( R.raw.goiaba);
        audio.add(R.raw.manga);
        audio.add(R.raw.melancia);
        audio.add(R.raw.morango);
        audio.add(R.raw.pera);
        audio.add(R.raw.uva);

        return audio;

    }

}
