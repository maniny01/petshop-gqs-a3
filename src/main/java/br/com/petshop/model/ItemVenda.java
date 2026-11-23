package br.com.petshop.model;

/** Linha de uma venda: um serviço e a quantidade realizada. */
public class ItemVenda {
    private final Servico servico;
    private final int quantidade;

    public ItemVenda(Servico servico, int quantidade) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço é obrigatório");
        }
        if (quantidade < 1) {
            throw new IllegalArgumentException("Quantidade deve ser pelo menos 1");
        }
        this.servico = servico;
        this.quantidade = quantidade;
    }

    public Servico getServico() { return servico; }
    public int getQuantidade() { return quantidade; }

    public double getSubtotal() {
        return servico.getValor() * quantidade;
    }
}
