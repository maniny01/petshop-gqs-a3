package br.com.petshop.model;

import static br.com.petshop.util.Validacao.exigirPositivo;
import static br.com.petshop.util.Validacao.exigirTexto;

/** Item do catálogo: banho, tosa, vacina, consulta etc. */
public class Servico implements Identificavel {
    private final int id;
    private String nome;
    private double valor;

    public Servico(int id, String nome, double valor) {
        this.id = id;
        setNome(nome);
        setValor(valor);
    }

    @Override
    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getValor() { return valor; }

    public void setNome(String nome) {
        this.nome = exigirTexto("Nome do serviço", nome);
    }

    public void setValor(double valor) {
        this.valor = exigirPositivo("Valor", valor);
    }
}
