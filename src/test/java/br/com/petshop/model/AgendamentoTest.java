
package br.com.petshop.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoTest {

    private Agendamento criarAgendamento() {
        return new Agendamento(
                1,
                new Pet(1, "Rex", Porte.MEDIO, 5),
                new Servico(1, "Banho", 50.0),
                LocalDateTime.of(2026, 12, 10, 14, 0)
        );
    }

    @Test
    void deveCriarAgendamentoComStatusPendente() {
        assertEquals(StatusAgendamento.PENDENTE,
                criarAgendamento().getStatus());
    }

    @Test
    void deveRejeitarPetNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Agendamento(1, null,
                        new Servico(1, "Banho", 50.0),
                        LocalDateTime.of(2026, 12, 10, 14, 0)));
    }

    @Test
    void deveRejeitarServicoNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Agendamento(1,
                        new Pet(1, "Rex", Porte.MEDIO, 5),
                        null,
                        LocalDateTime.of(2026, 12, 10, 14, 0)));
    }

    @Test
    void deveConfirmarAgendamento() {
        Agendamento agendamento = criarAgendamento();
        agendamento.confirmar();

        assertEquals(StatusAgendamento.CONFIRMADO,
                agendamento.getStatus());
    }

    @Test
    void deveConcluirAgendamento() {
        Agendamento agendamento = criarAgendamento();
        agendamento.confirmar();
        agendamento.concluir();

        assertEquals(StatusAgendamento.CONCLUIDO,
                agendamento.getStatus());
    }

    @Test
    void deveCancelarAgendamento() {
        Agendamento agendamento = criarAgendamento();
        agendamento.cancelar();

        assertEquals(StatusAgendamento.CANCELADO,
                agendamento.getStatus());
    }

    @Test
    void deveReagendarAgendamento() {
        Agendamento agendamento = criarAgendamento();
        LocalDateTime novaData =
                LocalDateTime.of(2026, 12, 15, 15, 0);

        agendamento.reagendar(novaData);

        assertEquals(novaData, agendamento.getDataHora());
    }


    @Test
void deveImpedirConfirmarAgendamentoJaConfirmado() {
    Agendamento agendamento = criarAgendamento();
    agendamento.confirmar();

    assertThrows(IllegalStateException.class,
            agendamento::confirmar);
}

@Test
void deveImpedirConcluirAgendamentoPendente() {
    Agendamento agendamento = criarAgendamento();

    assertThrows(IllegalStateException.class,
            agendamento::concluir);
}

@Test
void deveImpedirCancelarAgendamentoConcluido() {
    Agendamento agendamento = criarAgendamento();
    agendamento.confirmar();
    agendamento.concluir();

    assertThrows(IllegalStateException.class,
            agendamento::cancelar);
}

@Test
void deveImpedirReagendarComDataNula() {
    Agendamento agendamento = criarAgendamento();

    assertThrows(IllegalArgumentException.class,
            () -> agendamento.reagendar(null));
    }
}