package br.ufpb.dcx.appalpha.control.service;

import android.content.Context;

import java.util.Arrays;

import br.ufpb.dcx.appalpha.R;
import br.ufpb.dcx.appalpha.model.bean.Challenge;
import br.ufpb.dcx.appalpha.model.bean.Theme;

public class MockThemes implements Runnable {
    private ThemeService service;

    public MockThemes(Context context) {
        service = ThemeService.getInstance(context);
    }

    @Override
    public void run() {
        service.insert(new Theme("comida",  Integer.toString(R.drawable.comida), Integer.toString(R.raw.comida), null), Arrays.asList(
                new Challenge("arroz", null, Integer.toString(R.raw.arroz), null, Integer.toString(R.drawable.arroz)),
                new Challenge("peixe", null, Integer.toString(R.raw.peixe), null, Integer.toString(R.drawable.peixe)),
                new Challenge("batata", null, Integer.toString(R.raw.batata), null, Integer.toString(R.drawable.batata)),
                new Challenge("carne", null, Integer.toString(R.raw.carne), null, Integer.toString(R.drawable.carne)),
                new Challenge("macarrao", null, Integer.toString(R.raw.macarrao), null, Integer.toString(R.drawable.macarrao)),
                new Challenge("bolo", null, Integer.toString(R.raw.bolo), null, Integer.toString(R.drawable.bolo)),
                new Challenge("carne", null, Integer.toString(R.raw.carne), null, Integer.toString(R.drawable.carne)),
                new Challenge("ovo", null, Integer.toString(R.raw.ovo), null, Integer.toString(R.drawable.ovo))
        ));

        service.insert(new Theme("cidade",  Integer.toString(R.drawable.cidade), Integer.toString(R.raw.cidade), null), Arrays.asList(
                new Challenge("aeroporto", null, Integer.toString(R.raw.aeroporto), null, Integer.toString(R.drawable.aeroporto)),
                new Challenge("hospital", null, Integer.toString(R.raw.hospital), null, Integer.toString(R.drawable.hospital)),
                new Challenge("igreja", null, Integer.toString(R.raw.igreja), null, Integer.toString(R.drawable.igreja)),
                new Challenge("museu", null, Integer.toString(R.raw.museu), null, Integer.toString(R.drawable.museu)),
                new Challenge("shopping", null, Integer.toString(R.raw.shopping), null, Integer.toString(R.drawable.shopping))
        ));

        service.insert(new Theme("cores",  Integer.toString(R.drawable.cores), Integer.toString(R.raw.cores), null), Arrays.asList(
                new Challenge("preto", null, Integer.toString(R.raw.preto), null, Integer.toString(R.drawable.preto)),
                new Challenge("rosa", null, Integer.toString(R.raw.rosa), null, Integer.toString(R.drawable.rosa)),
                new Challenge("vermelho", null, Integer.toString(R.raw.vermelho), null, Integer.toString(R.drawable.vermelho)),
                new Challenge("verde", null, Integer.toString(R.raw.verde), null, Integer.toString(R.drawable.verde)),
                new Challenge("marrom", null, Integer.toString(R.raw.marrom), null, Integer.toString(R.drawable.marrom))
        ));

        service.insert(new Theme("cozinha",  Integer.toString(R.drawable.cozinha), Integer.toString(R.raw.cozinha), null), Arrays.asList(
                new Challenge("faca", null, Integer.toString(R.raw.faca), null, Integer.toString(R.drawable.faca)),
                new Challenge("colher", null, Integer.toString(R.raw.colher), null, Integer.toString(R.drawable.colher)),
                new Challenge("garfo", null, Integer.toString(R.raw.garfo), null, Integer.toString(R.drawable.garfo)),
                new Challenge("fogao", null, Integer.toString(R.raw.fogao), null, Integer.toString(R.drawable.fogao)),
                new Challenge("mesa", null, Integer.toString(R.raw.mesa), null, Integer.toString(R.drawable.mesa))
        ));

        service.insert(new Theme("natureza",  Integer.toString(R.drawable.natureza), Integer.toString(R.raw.natureza), null), Arrays.asList(
                new Challenge("vaca", null, Integer.toString(R.raw.vaca), null, Integer.toString(R.drawable.vaca)),
                new Challenge("galo", null, Integer.toString(R.raw.galo), null, Integer.toString(R.drawable.galo)),
                new Challenge("bode", null, Integer.toString(R.raw.bode), null, Integer.toString(R.drawable.bode)),
                new Challenge("abelha", null, Integer.toString(R.raw.abelha), null, Integer.toString(R.drawable.abelha)),
                new Challenge("flor", null, Integer.toString(R.raw.flor), null, Integer.toString(R.drawable.flor))
        ));

        service.insert(new Theme("frutas",  Integer.toString(R.drawable.frutas), Integer.toString(R.raw.frutas), null), Arrays.asList(
                new Challenge("banana", null, Integer.toString(R.raw.banana), null, Integer.toString(R.drawable.banana)),
                new Challenge("abacate", null, Integer.toString(R.raw.abacate), null, Integer.toString(R.drawable.abacate)),
                new Challenge("abacaxi", null, Integer.toString(R.raw.abacaxi), null, Integer.toString(R.drawable.abacaxi)),
                new Challenge("manga", null, Integer.toString(R.raw.manga), null, Integer.toString(R.drawable.manga)),
                new Challenge("melancia", null, Integer.toString(R.raw.melancia), null, Integer.toString(R.drawable.melancia))
        ));
    }
}
