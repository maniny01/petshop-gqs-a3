package br.com.petshop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClienteTest {

    private Cliente cliente() {
        return new Cliente(1, "Maria Silva", "(31) 99999-1234", "maria@email.com");
    }

    // ----- construtor -----
    @Test
    void construtorCriaClienteValido() {
        Cliente c = cliente();
        assertEquals(1, c.getId());
        assertEquals("Maria Silva", c.getNome());
        assertEquals("31999991234", c.getTelefone());
        assertEquals("maria@email.com", c.getEmail());
    }

    @Test
    void construtorAceitaNomeComUmCaractere() {
        assertEquals("A", new Cliente(2, "A", "3133334444", "a@b.c").getNome());
    }

    @Test
    void construtorRejeitaNomeNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente(1, null, "3133334444", "a@b.c"));
    }

    // ----- setNome -----
    @Test
    void setNomeAlteraNome() {
        Cliente c = cliente();
        c.setNome("João");
        assertEquals("João", c.getNome());
    }

    @Test
    void setNomeRemoveEspacosNasPontas() {
        Cliente c = cliente();
        c.setNome("  Ana  ");
        assertEquals("Ana", c.getNome());
    }

    @Test
    void setNomeRejeitaTextoEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> cliente().setNome("   "));
    }

    // ----- setTelefone -----
    @Test
    void setTelefoneAceitaOnzeDigitos() {
        Cliente c = cliente();
        c.setTelefone("31988887777");
        assertEquals("31988887777", c.getTelefone());
    }

    @Test
    void setTelefoneAceitaDezDigitosLimiteInferior() {
        Cliente c = cliente();
        c.setTelefone("3133334444");
        assertEquals("3133334444", c.getTelefone());
    }

    @Test
    void setTelefoneRejeitaNoveDigitos() {
        assertThrows(IllegalArgumentException.class, () -> cliente().setTelefone("999991234"));
    }

    @Test
    void setTelefoneRejeitaDozeDigitosLimiteSuperior() {
        assertThrows(IllegalArgumentException.class, () -> cliente().setTelefone("319999912345"));
    }

    @Test
    void setTelefoneRejeitaLetras() {
        assertThrows(IllegalArgumentException.class, () -> cliente().setTelefone("telefone"));
    }

    // ----- setEmail -----
    @Test
    void setEmailAceitaEmailValido() {
        Cliente c = cliente();
        c.setEmail("novo@dominio.com.br");
        assertEquals("novo@dominio.com.br", c.getEmail());
    }

    @Test
    void setEmailAceitaMenorEmailPossivel() {
        Cliente c = cliente();
        c.setEmail("a@b.c");
        assertEquals("a@b.c", c.getEmail());
    }

    @Test
    void setEmailRejeitaSemArroba() {
        assertThrows(IllegalArgumentException.class, () -> cliente().setEmail("semarroba.com"));
    }

    @Test
    void setEmailRejeitaNulo() {
        assertThrows(IllegalArgumentException.class, () -> cliente().setEmail(null));
    }
}
