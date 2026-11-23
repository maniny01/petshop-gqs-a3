package br.com.petshop.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidacaoTest {

    // ----- exigirTexto -----
    @Test
    void exigirTextoDevolveTextoSemEspacos() {
        assertEquals("abc", Validacao.exigirTexto("Campo", "  abc "));
    }

    @Test
    void exigirTextoAceitaUmCaractere() {
        assertEquals("a", Validacao.exigirTexto("Campo", "a"));
    }

    @Test
    void exigirTextoRejeitaNuloEEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> Validacao.exigirTexto("Campo", null));
        assertThrows(IllegalArgumentException.class, () -> Validacao.exigirTexto("Campo", "  "));
    }

    @Test
    void exigirTextoInformaONomeDoCampoNaMensagem() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> Validacao.exigirTexto("Nome", ""));
        assertTrue(e.getMessage().contains("Nome"));
    }

    // ----- exigirPositivo -----
    @Test
    void exigirPositivoAceitaValorPositivo() {
        assertEquals(10.5, Validacao.exigirPositivo("Valor", 10.5));
    }

    @Test
    void exigirPositivoAceitaMenorValorProximoDeZero() {
        assertEquals(0.01, Validacao.exigirPositivo("Valor", 0.01));
    }

    @Test
    void exigirPositivoRejeitaZero() {
        assertThrows(IllegalArgumentException.class, () -> Validacao.exigirPositivo("Valor", 0));
    }

    @Test
    void exigirPositivoRejeitaNegativo() {
        assertThrows(IllegalArgumentException.class, () -> Validacao.exigirPositivo("Valor", -5));
    }

    // ----- exigirIntervalo -----
    @Test
    void exigirIntervaloAceitaValorNoMeio() {
        assertEquals(5, Validacao.exigirIntervalo("Idade", 5, 0, 10));
    }

    @Test
    void exigirIntervaloAceitaLimitesInferiorESuperior() {
        assertEquals(0, Validacao.exigirIntervalo("Idade", 0, 0, 10));
        assertEquals(10, Validacao.exigirIntervalo("Idade", 10, 0, 10));
    }

    @Test
    void exigirIntervaloRejeitaAbaixoDoMinimo() {
        assertThrows(IllegalArgumentException.class, () -> Validacao.exigirIntervalo("Idade", -1, 0, 10));
    }

    @Test
    void exigirIntervaloRejeitaAcimaDoMaximo() {
        assertThrows(IllegalArgumentException.class, () -> Validacao.exigirIntervalo("Idade", 11, 0, 10));
    }
}
