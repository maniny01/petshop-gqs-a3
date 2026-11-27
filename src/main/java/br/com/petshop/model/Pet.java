package br.com.petshop.model;

import static br.com.petshop.util.Validacao.exigirTexto;

public class Pet implements Identificavel {

    private final int id;
    private String nome;
    private Porte porte;
    private int idade;

    public Pet(int id, String nome, Porte porte, int idade) {
        this.id = id;
        setNome(nome);
        setPorte(porte);
        setIdade(idade);
    }

    @Override
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Porte getPorte() {
        return porte;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        this.nome = exigirTexto("Nome do pet", nome);
    }

    public void setPorte(Porte porte) {
        if (porte == null) {
            throw new IllegalArgumentException("Porte é obrigatório");
        }
        this.porte = porte;
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa");
        }
        this.idade = idade;
    }
}