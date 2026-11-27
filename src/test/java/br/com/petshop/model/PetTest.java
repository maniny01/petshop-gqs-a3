package br.com.petshop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PetTest {

    private Pet pet() {
        return new Pet(1, "Rex", Porte.MEDIO, 5);
    }

    // ----- construtor -----
    @Test
    void construtorCriaPetValido() {
        Pet p = pet();
        assertEquals(1, p.getId());
        assertEquals("Rex", p.getNome());
        assertEquals(Porte.MEDIO, p.getPorte());
        assertEquals(5, p.getIdade());
    }

    @Test
    void construtorAceitaIdadeZero() {
        Pet p = new Pet(2, "Luna", Porte.PEQUENO, 0);
        assertEquals(0, p.getIdade());
    }

    @Test
    void construtorRejeitaNomeNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pet(1, null, Porte.MEDIO, 5));
    }

    // ----- setNome -----
    @Test
    void setNomeAlteraNome() {
        Pet p = pet();
        p.setNome("Thor");
        assertEquals("Thor", p.getNome());
    }

    @Test
    void setNomeAceitaUmCaractere() {
        Pet p = pet();
        p.setNome("A");
        assertEquals("A", p.getNome());
    }

    @Test
    void setNomeRejeitaNulo() {
        assertThrows(IllegalArgumentException.class, () -> pet().setNome(null));
    }

    // ----- setPorte -----
    @Test
    void setPorteAlteraPorte() {
        Pet p = pet();
        p.setPorte(Porte.GRANDE);
        assertEquals(Porte.GRANDE, p.getPorte());
    }

    @Test
    void setPorteAceitaPortePequeno() {
        Pet p = pet();
        p.setPorte(Porte.PEQUENO);
        assertEquals(Porte.PEQUENO, p.getPorte());
    }

    @Test
    void setPorteRejeitaNulo() {
        assertThrows(IllegalArgumentException.class, () -> pet().setPorte(null));
    }

    // ----- setIdade -----
    @Test
    void setIdadeAlteraIdade() {
        Pet p = pet();
        p.setIdade(10);
        assertEquals(10, p.getIdade());
    }

    @Test
    void setIdadeAceitaZero() {
        Pet p = pet();
        p.setIdade(0);
        assertEquals(0, p.getIdade());
    }

    @Test
    void setIdadeRejeitaNegativo() {
        assertThrows(IllegalArgumentException.class, () -> pet().setIdade(-1));
    }
}