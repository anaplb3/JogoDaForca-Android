package br.ufpb.dcx.appalpha.model;

import com.example.anaplb.appalpha.R;

import java.util.ArrayList;

public class VocabularioNatureza extends Vocabulario {
    private static VocabularioNatureza voc;

    public static VocabularioNatureza getInstance() {
        if(voc == null) {
            voc = new VocabularioNatureza();
        }

        return voc;
    }

    public ArrayList<String> retornandoNomes() {
        ArrayList<String> nomes = new ArrayList<>();

        nomes.add("abelha");
        nomes.add("arvore");
        nomes.add("bode");
        nomes.add("flor");
        nomes.add("galo");
        nomes.add("gato");
        nomes.add("lua");
        nomes.add("nuvem");
        nomes.add("praia");
        nomes.add("rio");
        nomes.add("sol");
        nomes.add("vaca");

        return nomes;
    }

    public ArrayList<Integer> retornandoImagens() {
        ArrayList<Integer> imgs = new ArrayList<>();

        imgs.add(R.drawable.abelha);
        imgs.add(R.drawable.arvore);
        imgs.add(R.drawable.bode);
        imgs.add(R.drawable.flor);
        imgs.add(R.drawable.galo);
        imgs.add(R.drawable.gato);
        imgs.add(R.drawable.moon);
        imgs.add(R.drawable.nuvem);
        imgs.add(R.drawable.praia);
        imgs.add(R.drawable.rio);
        imgs.add(R.drawable.sol);
        imgs.add(R.drawable.vaca);

        return imgs;
    }

    @Override
    public ArrayList<Integer> retornandoAudios() {
        ArrayList<Integer> audio = new ArrayList<>();

        audio.add(R.raw.abelha);
        audio.add(R.raw.arvore);
        audio.add(R.raw.bode);
        audio.add( R.raw.flor);
        audio.add(R.raw.galo);
        audio.add(R.raw.gato);
        audio.add(R.raw.lua);
        audio.add(R.raw.nuvem);
        audio.add(R.raw.praia);
        audio.add(R.raw.rio);
        audio.add(R.raw.sol);
        audio.add(R.raw.vaca);

        return audio;

    }

}
