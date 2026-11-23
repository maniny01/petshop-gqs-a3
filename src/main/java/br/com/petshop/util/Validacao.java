package br.com.petshop.util;

/** Regras de validação reutilizadas pelas classes de domínio. */
public final class Validacao {

    private Validacao() {
    }

    /** Exige texto não nulo e não vazio; devolve o texto sem espaços nas pontas. */
    public static String exigirTexto(String campo, String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " é obrigatório");
        }
        return valor.trim();
    }

    /** Exige valor estritamente maior que zero. */
    public static double exigirPositivo(String campo, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " deve ser maior que zero");
        }
        return valor;
    }

    /** Exige valor dentro do intervalo fechado [minimo, maximo]. */
    public static double exigirIntervalo(String campo, double valor, double minimo, double maximo) {
        if (valor < minimo || valor > maximo) {
            throw new IllegalArgumentException(
                    campo + " deve estar entre " + minimo + " e " + maximo);
        }
        return valor;
    }
}
