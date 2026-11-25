package br.com.petshop.util;

import static org.junit.jupiter.api.Assertions.*;

import br.com.petshop.model.Identificavel;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RepositorioMemoriaTest {

    private static class Coisa implements Identificavel {
        private final int id;
        private final String nome;

        Coisa(int id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        @Override
        public int getId() { return id; }
        String getNome() { return nome; }
    }

    private RepositorioMemoria<Coisa> repo;

    @BeforeEach
    void preparar() {
        repo = new RepositorioMemoria<>();
    }

    // ----- salvar -----
    @Test
    void salvarGuardaEntidade() {
        repo.salvar(new Coisa(1, "a"));
        assertEquals(1, repo.listar().size());
    }

    @Test
    void salvarAceitaIdZero() {
        repo.salvar(new Coisa(0, "zero"));
        assertTrue(repo.buscarPorId(0).isPresent());
    }

    @Test
    void salvarRejeitaIdDuplicado() {
        repo.salvar(new Coisa(1, "a"));
        assertThrows(IllegalArgumentException.class, () -> repo.salvar(new Coisa(1, "b")));
    }

    @Test
    void salvarRejeitaNulo() {
        assertThrows(IllegalArgumentException.class, () -> repo.salvar(null));
    }

    // ----- buscarPorId -----
    @Test
    void buscarPorIdEncontraEntidade() {
        repo.salvar(new Coisa(7, "sete"));
        assertEquals("sete", repo.buscarPorId(7).get().getNome());
    }

    @Test
    void buscarPorIdNoRepositorioVazioDevolveVazio() {
        assertTrue(repo.buscarPorId(1).isEmpty());
    }

    @Test
    void buscarPorIdInexistenteDevolveVazio() {
        repo.salvar(new Coisa(1, "a"));
        assertTrue(repo.buscarPorId(99).isEmpty());
        assertTrue(repo.buscarPorId(-1).isEmpty());
    }

    // ----- listar -----
    @Test
    void listarDevolveNaOrdemDeCadastro() {
        repo.salvar(new Coisa(2, "b"));
        repo.salvar(new Coisa(1, "a"));
        List<Coisa> lista = repo.listar();
        assertEquals(2, lista.get(0).getId());
        assertEquals(1, lista.get(1).getId());
    }

    @Test
    void listarRepositorioVazioDevolveListaVazia() {
        assertTrue(repo.listar().isEmpty());
    }

    @Test
    void listarDevolveCopiaQueNaoAlteraORepositorio() {
        repo.salvar(new Coisa(1, "a"));
        repo.listar().clear();
        assertEquals(1, repo.listar().size());
    }

    // ----- atualizar -----
    @Test
    void atualizarSubstituiRegistro() {
        repo.salvar(new Coisa(1, "antigo"));
        repo.atualizar(new Coisa(1, "novo"));
        assertEquals("novo", repo.buscarPorId(1).get().getNome());
    }

    @Test
    void atualizarNaoAlteraQuantidadeDeRegistros() {
        repo.salvar(new Coisa(1, "a"));
        repo.atualizar(new Coisa(1, "b"));
        assertEquals(1, repo.listar().size());
    }

    @Test
    void atualizarRejeitaRegistroInexistente() {
        assertThrows(IllegalArgumentException.class, () -> repo.atualizar(new Coisa(5, "x")));
    }

    @Test
    void atualizarRejeitaNulo() {
        assertThrows(IllegalArgumentException.class, () -> repo.atualizar(null));
    }

    // ----- excluir -----
    @Test
    void excluirRemoveRegistro() {
        repo.salvar(new Coisa(1, "a"));
        repo.excluir(1);
        assertTrue(repo.buscarPorId(1).isEmpty());
    }

    @Test
    void excluirUnicoRegistroDeixaRepositorioVazio() {
        repo.salvar(new Coisa(1, "a"));
        repo.excluir(1);
        assertTrue(repo.listar().isEmpty());
    }

    @Test
    void excluirRejeitaIdInexistente() {
        assertThrows(IllegalArgumentException.class, () -> repo.excluir(42));
    }

    // ----- proximoId -----
    @Test
    void proximoIdComRepositorioVazioEUm() {
        assertEquals(1, repo.proximoId());
    }

    @Test
    void proximoIdEMaiorIdMaisUm() {
        repo.salvar(new Coisa(1, "a"));
        repo.salvar(new Coisa(2, "b"));
        assertEquals(3, repo.proximoId());
    }

    @Test
    void proximoIdComIdsEsparsosUsaOMaior() {
        repo.salvar(new Coisa(10, "a"));
        repo.salvar(new Coisa(3, "b"));
        assertEquals(11, repo.proximoId());
    }
    // ----- bordas e contar -----
    @Test
    void proximoIdAposExcluirMaiorIdMantemBaseCorreta() {
        repo.salvar(new Coisa(10, "a"));
        repo.salvar(new Coisa(3, "b"));
        repo.excluir(10);
        assertEquals(4, repo.proximoId());
    }

    @Test
    void contarRefleteQuantidadeDeRegistros() {
        assertEquals(0, repo.contar());
        repo.salvar(new Coisa(1, "a"));
        repo.salvar(new Coisa(2, "b"));
        assertEquals(2, repo.contar());
        repo.excluir(1);
        assertEquals(1, repo.contar());
    }
}