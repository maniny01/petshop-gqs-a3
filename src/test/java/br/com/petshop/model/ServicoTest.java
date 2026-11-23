package br.com.petshop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ServicoTest {

    private Servico servico() {
        return new Servico(1, "Banho", 50.0);
    }

    // ----- construtor -----
    @Test
    void construtorCriaServicoValido() {
        Servico s = servico();
        assertEquals(1, s.getId());
        assertEquals("Banho", s.getNome());
        assertEquals(50.0, s.getValor(), 0.001);
    }

    @Test
    void construtorAceitaValorMinimo() {
        assertEquals(0.01, new Servico(2, "Brinde", 0.01).getValor(), 0.0001);
    }

    @Test
    void construtorRejeitaValorZero() {
        assertThrows(IllegalArgumentException.class, () -> new Servico(1, "Banho", 0));
    }

    // ----- setNome -----
    @Test
    void setNomeAlteraNome() {
        Servico s = servico();
        s.setNome("Tosa");
        assertEquals("Tosa", s.getNome());
    }

    @Test
    void setNomeAceitaUmCaractere() {
        Servico s = servico();
        s.setNome("T");
        assertEquals("T", s.getNome());
    }

    @Test
    void setNomeRejeitaNulo() {
        assertThrows(IllegalArgumentException.class, () -> servico().setNome(null));
    }

    // ----- setValor -----
    @Test
    void setValorAlteraValor() {
        Servico s = servico();
        s.setValor(75.9);
        assertEquals(75.9, s.getValor(), 0.001);
    }

    @Test
    void setValorAceitaCentavo() {
        Servico s = servico();
        s.setValor(0.01);
        assertEquals(0.01, s.getValor(), 0.0001);
    }

    @Test
    void setValorRejeitaNegativo() {
        assertThrows(IllegalArgumentException.class, () -> servico().setValor(-10));
    }
}
