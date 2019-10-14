package br.ufpb.dcx.appalpha.model;



import br.ufpb.dcx.appalpha.R;

import java.util.ArrayList;

public class VocabularioCidade extends Vocabulario{
    private static VocabularioCidade voc;

    public static VocabularioCidade getInstance() {
        if(voc == null) {
            voc = new VocabularioCidade();
        }

        return voc;
    }

    public ArrayList<String> retornandoNomes() {
        ArrayList<String> nomes = new ArrayList<>();

        nomes.add("aeroporto");
        nomes.add("escola");
        nomes.add("hospital");
        nomes.add("igreja");
        nomes.add("museu");
        nomes.add("praia");
        nomes.add("restaurante");
        nomes.add("shopping");
        nomes.add("zoologico");

        return nomes;
    }

    public ArrayList<Integer> retornandoImagens() {
        ArrayList<Integer> imgs = new ArrayList<>();


        imgs.add(R.drawable.aeroporto);
        imgs.add(R.drawable.escola);
        imgs.add(R.drawable.hospital);
        imgs.add(R.drawable.igreja);
        imgs.add(R.drawable.museu);
        imgs.add(R.drawable.praia);
        imgs.add(R.drawable.rest);
        imgs.add(R.drawable.shopping);
        imgs.add(R.drawable.zoologico);

        return imgs;
    }

    @Override
    public ArrayList<Integer> retornandoAudios() {
        ArrayList<Integer> audio = new ArrayList<>();

        audio.add(R.raw.aeroporto);
        audio.add(R.raw.escola);
        audio.add(R.raw.hospital);
        audio.add( R.raw.igreja);
        audio.add(R.raw.museu);
        audio.add(R.raw.praia);
        audio.add(R.raw.restaurante);
        audio.add(R.raw.shopping);
        audio.add(R.raw.zoo);

        return audio;

    }


}
