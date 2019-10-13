package br.dcx.appalpha.model;

import com.example.anaplb.appalpha.R;

import java.util.ArrayList;


public class VocabularioComida extends Vocabulario {
    private static VocabularioComida voc;

    public static VocabularioComida getInstance() {
        if(voc == null) {
            voc = new VocabularioComida();
        }

        return voc;
    }


    public ArrayList<String> retornandoNomes() {
        ArrayList<String> nomes = new ArrayList<>();

        nomes.add("arroz");
        nomes.add("batata");
        nomes.add("bolo");
        nomes.add("carne");
        nomes.add("feijao");
        nomes.add("macarrao");
        nomes.add("ovo");
        nomes.add("peixe");
        nomes.add("sorvete");
        nomes.add("tomate");

        return nomes;
    }

    public ArrayList<Integer> retornandoImagens() {
        ArrayList<Integer> imgs = new ArrayList<>();

        imgs.add(R.drawable.arroz);
        imgs.add(R.drawable.batata);
        imgs.add(R.drawable.bolo);
        imgs.add(R.drawable.carne);
        imgs.add(R.drawable.feijao);
        imgs.add(R.drawable.macarrao);
        imgs.add(R.drawable.ovo);
        imgs.add(R.drawable.peixe);
        imgs.add(R.drawable.sorvete);
        imgs.add(R.drawable.tomate);

        return imgs;
    }

    @Override
    public ArrayList<Integer> retornandoAudios() {
        ArrayList<Integer> audio = new ArrayList<>();

        audio.add(R.raw.arroz);
        audio.add(R.raw.batata);
        audio.add(R.raw.bolo);
        audio.add( R.raw.carne);
        audio.add(R.raw.feijao);
        audio.add(R.raw.macarrao);
        audio.add(R.raw.ovo);
        audio.add(R.raw.peixe);
        audio.add(R.raw.sorvete);
        audio.add(R.raw.tomate);

        return audio;

    }

}
