package br.com.petshop.util;

import br.com.petshop.model.Identificavel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RepositorioMemoria<T extends Identificavel> {

    private final Map<Integer, T> registros = new LinkedHashMap<>();

    public void salvar(T entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("Entidade não pode ser nula.");
        }
        int id = entidade.getId();
        if (registros.containsKey(id)) {
            throw new IllegalArgumentException("Já existe registro com o ID: " + id);
        }
        registros.put(id, entidade);
    }

    public Optional<T> buscarPorId(int id) {
        return Optional.ofNullable(registros.get(id));
    }

    public void atualizar(T entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("Entidade não pode ser nula.");
        }
        int id = entidade.getId();
        if (!registros.containsKey(id)) {
            throw new IllegalArgumentException("Registro não encontrado para o ID: " + id);
        }
        registros.put(id, entidade);
    }

    public void excluir(int id) {
        if (!registros.containsKey(id)) {
            throw new IllegalArgumentException("Registro não encontrado para o ID: " + id);
        }
        registros.remove(id);
    }

    public List<T> listar() {
        return new ArrayList<>(registros.values());
    }

    public int proximoId() {
        int maiorId = 0;
        for (Integer id : registros.keySet()) {
            if (id > maiorId) {
                maiorId = id;
            }
        }
        return maiorId + 1;
    }
}