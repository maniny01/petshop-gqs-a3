package br.com.petshop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendaTest {

    private final Servico banho = new Servico(1, "Banho", 50.0);
    private final Servico tosa = new Servico(2, "Tosa", 80.0);

    // ----- adicionarItem -----
    @Test
    void adicionaItemValido() {
        Venda venda = new Venda();
        venda.adicionarItem(new ItemVenda(banho, 2));

        assertEquals(1, venda.getItens().size());
    }

    @Test
    void adicionaVariosItens() {
        Venda venda = new Venda();
        venda.adicionarItem(new ItemVenda(banho, 1));
        venda.adicionarItem(new ItemVenda(tosa, 1));

        assertEquals(2, venda.getItens().size());
    }

    @Test
    void adicionarItemRejeitaNulo() {
        Venda venda = new Venda();

        assertThrows(IllegalArgumentException.class,
                () -> venda.adicionarItem(null));
    }

    // ----- getSubtotal -----
    @Test
    void calculaSubtotalDosItens() {
        Venda venda = new Venda();
        venda.adicionarItem(new ItemVenda(banho, 2));
        venda.adicionarItem(new ItemVenda(tosa, 1));

        assertEquals(180.0, venda.getSubtotal(), 0.001);
    }

    @Test
    void subtotalDeVendaVaziaEZero() {
        Venda venda = new Venda();

        assertEquals(0.0, venda.getSubtotal(), 0.001);
    }

    @Test
    void subtotalConsideraQuantidade() {
        Venda venda = new Venda();
        venda.adicionarItem(new ItemVenda(banho, 10));

        assertEquals(500.0, venda.getSubtotal(), 0.001);
    }

    // ----- desconto e total -----
    @Test
    void calculaTotalComDesconto() {
        Venda venda = new Venda();
        venda.adicionarItem(new ItemVenda(banho, 2));
        venda.setDesconto(10.0);

        assertEquals(90.0, venda.getTotal(), 0.001);
    }

    @Test
    void aceitaDescontoZero() {
        Venda venda = new Venda();
        venda.adicionarItem(new ItemVenda(banho, 1));
        venda.setDesconto(0.0);

        assertEquals(50.0, venda.getTotal(), 0.001);
    }

    @Test
    void rejeitaDescontoAcimaDeCem() {
        Venda venda = new Venda();

        assertThrows(IllegalArgumentException.class,
                () -> venda.setDesconto(101.0));
    }

    @Test
    void rejeitaDescontoNegativo() {
        Venda venda = new Venda();

        assertThrows(IllegalArgumentException.class,
                () -> venda.setDesconto(-1.0));
    }
}