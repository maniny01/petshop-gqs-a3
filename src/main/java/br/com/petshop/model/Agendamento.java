
package br.com.petshop.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

public class Agendamento implements Identificavel {

    private final int id;
    private final Pet pet;
    private final Servico servico;
    private LocalDateTime dataHora;
    private StatusAgendamento status;

    public Agendamento(int id, Pet pet, Servico servico, LocalDateTime dataHora) {
        if (pet == null) {
            throw new IllegalArgumentException("Pet é obrigatório");
        }

        if (servico == null) {
            throw new IllegalArgumentException("Serviço é obrigatório");
        }

        if (dataHora == null) {
            throw new IllegalArgumentException("Data e hora são obrigatórias");
        }

        this.id = id;
        this.pet = pet;
        this.servico = servico;
        this.dataHora = dataHora;
        this.status = StatusAgendamento.PENDENTE;
    }

    @Override
    public int getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    public Servico getServico() {
        return servico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void confirmar() {
        if (status != StatusAgendamento.PENDENTE) {
            throw new IllegalStateException("Somente agendamentos pendentes podem ser confirmados");
        }
        status = StatusAgendamento.CONFIRMADO;
    }

    public void concluir() {
        if (status != StatusAgendamento.CONFIRMADO) {
            throw new IllegalStateException("Somente agendamentos confirmados podem ser concluídos");
        }
        status = StatusAgendamento.CONCLUIDO;
    }

    public void cancelar() {
        if (status == StatusAgendamento.CONCLUIDO ||
                status == StatusAgendamento.CANCELADO) {
            throw new IllegalStateException("Agendamento não pode ser cancelado");
        }
        status = StatusAgendamento.CANCELADO;
    }

    public void reagendar(LocalDateTime novaDataHora) {
        if (status == StatusAgendamento.CONCLUIDO ||
                status == StatusAgendamento.CANCELADO) {
            throw new IllegalStateException("Agendamento não pode ser reagendado");
        }

        if (novaDataHora == null) {
            throw new IllegalArgumentException("Nova data e hora são obrigatórias");
        }

        this.dataHora = novaDataHora;
    }


    @Test
    void naoDeveConfirmarAgendamentoJaConfirmado() {
        Agendamento agendamento = criarAgendamento();
        agendamento.confirmar();

        assertThrows(IllegalStateException.class,
                agendamento::confirmar);
    }

    @Test
    void naoDeveConcluirAgendamentoPendente() {
        Agendamento agendamento = criarAgendamento();

        assertThrows(IllegalStateException.class,
                agendamento::concluir);
    }

    @Test
    void naoDeveCancelarAgendamentoConcluido() {
        Agendamento agendamento = criarAgendamento();
        agendamento.confirmar();
        agendamento.concluir();

        assertThrows(IllegalStateException.class,
                agendamento::cancelar);
    }

    @Test
    void naoDeveReagendarAgendamentoCancelado() {
        Agendamento agendamento = criarAgendamento();
        agendamento.cancelar();

        assertThrows(IllegalStateException.class,
                () -> agendamento.reagendar(
                        LocalDateTime.of(2026, 12, 20, 10, 0)));
    }

    @Test
    void naoDeveReagendarComDataNula() {
        Agendamento agendamento = criarAgendamento();

        assertThrows(IllegalArgumentException.class,
                () -> agendamento.reagendar(null));
    }


    @Test
    void naoDeveConfirmarAgendamentoJaConfirmado() {
        Agendamento agendamento = criarAgendamento();
        agendamento.confirmar();

        assertThrows(IllegalStateException.class,
                agendamento::confirmar);
    }

    @Test
    void naoDeveConcluirAgendamentoPendente() {
        Agendamento agendamento = criarAgendamento();

        assertThrows(IllegalStateException.class,
                agendamento::concluir);
    }

    @Test
    void naoDeveCancelarAgendamentoConcluido() {
        Agendamento agendamento = criarAgendamento();
        agendamento.confirmar();
        agendamento.concluir();

        assertThrows(IllegalStateException.class,
                agendamento::cancelar);
    }

    @Test
    void naoDeveReagendarAgendamentoCancelado() {
        Agendamento agendamento = criarAgendamento();
        agendamento.cancelar();

        assertThrows(IllegalStateException.class,
                () -> agendamento.reagendar(
                        LocalDateTime.of(2026, 12, 20, 10, 0)));
    }

    @Test
    void naoDeveReagendarComDataNula() {
        Agendamento agendamento = criarAgendamento();

        assertThrows(IllegalArgumentException.class,
                () -> agendamento.reagendar(null));
    }
}