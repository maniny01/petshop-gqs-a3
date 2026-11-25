package br.com.petshop.util;

import br.com.petshop.model.Identificavel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repositório genérico em memória indexado pelo identificador da entidade.
 *
 * @param <T> tipo da entidade que implementa {@link Identificavel}
 */
public class RepositorioMemoria<T extends Identificavel> {

    private final Map<Integer, T> registros = new LinkedHashMap<>();

    /**
     * Salva uma nova entidade no repositório.
     *
     * @param entidade entidade a ser armazenada
     * @throws IllegalArgumentException se a entidade for nula ou já existir registro com o mesmo ID
     */
    public void salvar(T entidade) {
        validarEntidadeNaoNula(entidade);
        int id = entidade.getId();
        if (registros.containsKey(id)) {
            throw new IllegalArgumentException("Já existe registro com o ID: " + id);
        }
        registros.put(id, entidade);
    }

    /**
     * Busca uma entidade pelo seu ID.
     *
     * @param id identificador do registro
     * @return {@link Optional} contendo a entidade, ou vazio se não encontrado
     */
    public Optional<T> buscarPorId(int id) {
        return Optional.ofNullable(registros.get(id));
    }

    /**
     * Atualiza uma entidade já existente no repositório.
     *
     * @param entidade entidade com os dados atualizados e mesmo ID
     * @throws IllegalArgumentException se a entidade for nula ou o ID não existir no repositório
     */
    public void atualizar(T entidade) {
        validarEntidadeNaoNula(entidade);
        int id = entidade.getId();
        if (!registros.containsKey(id)) {
            throw new IllegalArgumentException("Registro não encontrado para o ID: " + id);
        }
        registros.put(id, entidade);
    }

    /**
     * Remove uma entidade do repositório pelo ID.
     *
     * @param id identificador do registro a ser removido
     * @throws IllegalArgumentException se não existir registro com o ID informado
     */
    public void excluir(int id) {
        if (!registros.containsKey(id)) {
            throw new IllegalArgumentException("Registro não encontrado para o ID: " + id);
        }
        registros.remove(id);
    }

    /**
     * Retorna uma cópia da lista de entidades na ordem de inserção.
     *
     * @return lista com as entidades armazenadas
     */
    public List<T> listar() {
        return new ArrayList<>(registros.values());
    }

    /**
     * Retorna a quantidade atual de registros armazenados.
     *
     * @return total de entidades no repositório
     */
    public int contar() {
        return registros.size();
    }

    /**
     * Calcula o próximo ID disponível com base no maior ID atual.
     *
     * @return maior ID existente mais 1, ou 1 se o repositório estiver vazio
     */
    public int proximoId() {
        return registros.keySet().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0) + 1;
    }

    private void validarEntidadeNaoNula(T entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("Entidade não pode ser nula.");
        }
    }
}