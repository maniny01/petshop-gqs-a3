package br.com.petshop.model;

import java.util.ArrayList;
import java.util.List;

public class Venda {

    private final List<ItemVenda> itens;
    private double desconto;

    public Venda() {
        this.itens = new ArrayList<>();
        this.desconto = 0.0;
    }

    public void adicionarItem(ItemVenda item) {
        if (item == null) {
            throw new IllegalArgumentException("Item é obrigatório");
        }
        itens.add(item);
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public double getSubtotal() {
        double subtotal = 0.0;

        for (ItemVenda item : itens) {
            subtotal += item.getSubtotal();
        }

        return subtotal;
    }

    public void setDesconto(double desconto) {
        if (desconto < 0 || desconto > 100) {
            throw new IllegalArgumentException("Desconto deve estar entre 0 e 100");
        }
        this.desconto = desconto;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getTotal() {
        return getSubtotal() * (1 - desconto / 100);
    }
}