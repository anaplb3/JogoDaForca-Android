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
    public void checandoSeAcertouPalavra() {
    }

    @Test
    public void novaPalavra() {
    }

    @Test
    public void contandoErros() {
    }
}