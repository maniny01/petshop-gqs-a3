package br.com.petshop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemVendaTest {

    private final Servico banho = new Servico(1, "Banho", 50.0);

    // ----- construtor -----
    @Test
    void construtorCriaItemValido() {
        ItemVenda i = new ItemVenda(banho, 2);
        assertSame(banho, i.getServico());
        assertEquals(2, i.getQuantidade());
    }

    @Test
    void construtorAceitaQuantidadeMinimaUm() {
        assertEquals(1, new ItemVenda(banho, 1).getQuantidade());
    }

    @Test
    void construtorRejeitaQuantidadeZero() {
        assertThrows(IllegalArgumentException.class, () -> new ItemVenda(banho, 0));
    }

    @Test
    void construtorRejeitaServicoNulo() {
        assertThrows(IllegalArgumentException.class, () -> new ItemVenda(null, 1));
    }

    // ----- getSubtotal -----
    @Test
    void getSubtotalMultiplicaValorPelaQuantidade() {
        assertEquals(100.0, new ItemVenda(banho, 2).getSubtotal(), 0.001);
    }

    @Test
    void getSubtotalComQuantidadeUmEIgualAoValor() {
        assertEquals(50.0, new ItemVenda(banho, 1).getSubtotal(), 0.001);
    }

    @Test
    void getSubtotalComQuantidadeGrande() {
        assertEquals(50000.0, new ItemVenda(banho, 1000).getSubtotal(), 0.001);
    }
}
