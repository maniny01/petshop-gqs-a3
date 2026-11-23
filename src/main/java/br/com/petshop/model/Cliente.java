package br.com.petshop.model;

import static br.com.petshop.util.Validacao.exigirTexto;

/** Tutor dos animais atendidos no PetShop. */
public class Cliente implements Identificavel {
    private final int id;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(int id, String nome, String telefone, String email) {
        this.id = id;
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
    }

    @Override
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }

    public void setNome(String nome) {
        this.nome = exigirTexto("Nome", nome);
    }

    /** Aceita máscara; guarda apenas os dígitos (10 ou 11). */
    public void setTelefone(String telefone) {
        String digitos = exigirTexto("Telefone", telefone).replaceAll("\\D", "");
        if (digitos.length() < 10 || digitos.length() > 11) {
            throw new IllegalArgumentException("Telefone deve ter 10 ou 11 dígitos");
        }
        this.telefone = digitos;
    }

    public void setEmail(String email) {
        String valor = exigirTexto("Email", email);
        if (!valor.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = valor;
    }
}
