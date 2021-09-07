package com.example.anaplb.appalpha.tratamento;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TratandoPalavraTest {
    public TratandoPalavra tratandoPalavra;
    public static final String palavraParaTeste = "azul";

    @Before
    public void setUp() {
        tratandoPalavra = new TratandoPalavra(palavraParaTeste);
    }

    @Test
    public void deixandoEmUnderscore() {
        String palavraParaTesteEmUnderscore = "____";
        String resultadoDeixandoEmUnderscore = tratandoPalavra.deixandoEmUnderscore();
        assertEquals(palavraParaTesteEmUnderscore, resultadoDeixandoEmUnderscore);
    }

    @Test
    public void deveRetornarQueOChuteFoiCerto() {
        String chute = "a";
        int resultadoDoChute = tratandoPalavra.checandoSeAcertou(chute);
        assertEquals(tratandoPalavra.CHUTE_CERTO, resultadoDoChute);
    }

    @Test
    public void deveRetornarQueOChuteFoiErrado() {
        String chute = "h";
        int resultadoDoChute = tratandoPalavra.checandoSeAcertou(chute);
        assertEquals(tratandoPalavra.CHUTE_ERRADO, resultadoDoChute);
    }

    @Test
    public void deveRetornarQueOChuteFoiCertoQuandoAPrimeiraLetraEhCerta() {
        String chute = "ak";
        int resultadoDoChute = tratandoPalavra.checandoSeAcertou(chute);
        assertEquals(tratandoPalavra.CHUTE_CERTO, resultadoDoChute);
    }

    @Test
    public void deveRetornarQueOChuteFoiErradoQuandoAPrimeiraLetraEhErrada() {
        String chute = "ka";
        int resultadoDoChute = tratandoPalavra.checandoSeAcertou(chute);
        assertEquals(tratandoPalavra.CHUTE_ERRADO, resultadoDoChute);
    }

    @Test
    public void deveRetornarQueAcertouAPalavra() {
        tratandoPalavra.setUnderscoreAtual("azul");
        assertTrue(tratandoPalavra.checandoSeAcertouPalavra());
    }

    @Test
    public void deveRetornarQueNaoAcertouAPalavra() {
        tratandoPalavra.setUnderscoreAtual("azu");
        assertFalse(tratandoPalavra.checandoSeAcertouPalavra());
    }

    @Test
    public void deveRetornarUnderscoreComChuteAdicionado() {
        String underscoreAtual = "_z_l";
        String underscoreEsperado = "az_l";
        char chute = 'a';

        tratandoPalavra.setUnderscoreAtual(underscoreAtual);
        String resultadoUnderscoreAposOChute = tratandoPalavra.novoUnderscoreAposChute(chute);
        assertEquals(underscoreEsperado, resultadoUnderscoreAposOChute);
    }

    @Test
    public void deveRetornarUnderscoreSemChuteAdicionado() {
        String underscoreAtual = "_z_l";
        String underscoreEsperado = "_z_l";
        char chute = 'k';

        tratandoPalavra.setUnderscoreAtual(underscoreAtual);
        String resultadoUnderscoreAposOChute = tratandoPalavra.novoUnderscoreAposChute(chute);
        assertEquals(underscoreEsperado, resultadoUnderscoreAposOChute);
    }

    @Test
    public void contandoErros() {
    }
}