package br.com.petshop.app;

import br.com.petshop.model.Agendamento;
import br.com.petshop.model.Cliente;
import br.com.petshop.model.Identificavel;
import br.com.petshop.model.ItemVenda;
import br.com.petshop.model.Pet;
import br.com.petshop.model.Porte;
import br.com.petshop.model.Servico;
import br.com.petshop.model.Venda;
import br.com.petshop.util.RepositorioMemoria;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** Interface de console para demonstrar o CRUD do sistema PetShop. */
public class Main {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final Scanner in;
    private final RepositorioMemoria<Cliente> clientes = new RepositorioMemoria<>();
    private final RepositorioMemoria<Pet> pets = new RepositorioMemoria<>();
    private final RepositorioMemoria<Servico> servicos = new RepositorioMemoria<>();
    private final RepositorioMemoria<Agendamento> agendamentos = new RepositorioMemoria<>();
    /** Venda não tem id próprio: guardamos a lista e o cliente de cada venda. */
    private final List<Venda> vendas = new ArrayList<>();
    private final Map<Venda, Cliente> clienteDaVenda = new LinkedHashMap<>();

    Main(Scanner in) {
        this.in = in;
    }

    public static void main(String[] args) {
        new Main(new Scanner(System.in)).executar();
    }

    private void executar() {
        System.out.println("=== Sistema PetShop & Clínica Veterinária ===");
        boolean rodando = true;
        while (rodando) {
            System.out.println("\n1) Clientes  2) Pets  3) Serviços  4) Agendamentos  5) Vendas  0) Sair");
            switch (lerTexto("Opção")) {
                case "1" -> menuClientes();
                case "2" -> menuPets();
                case "3" -> menuServicos();
                case "4" -> menuAgendamentos();
                case "5" -> menuVendas();
                case "0" -> rodando = false;
                default -> System.out.println("Opção inválida.");
            }
        }
        System.out.println("Até logo!");
    }

    // ---------------- Clientes ----------------
    private void menuClientes() {
        System.out.println("\n[Clientes] 1) Cadastrar  2) Listar  3) Alterar  4) Excluir");
        tentar(() -> {
            switch (lerTexto("Opção")) {
                case "1" -> {
                    Cliente c = new Cliente(clientes.proximoId(), lerTexto("Nome"),
                            lerTexto("Telefone"), lerTexto("Email"));
                    clientes.salvar(c);
                    System.out.println("Cliente cadastrado com id " + c.getId());
                }
                case "2" -> listar(clientes.listar(), c -> String.format("#%d %s | %s | %s",
                        c.getId(), c.getNome(), c.getTelefone(), c.getEmail()));
                case "3" -> {
                    Cliente c = buscar(clientes, lerInt("Id do cliente"));
                    c.setNome(lerTexto("Novo nome"));
                    c.setTelefone(lerTexto("Novo telefone"));
                    c.setEmail(lerTexto("Novo email"));
                    clientes.atualizar(c);
                    System.out.println("Cliente alterado.");
                }
                case "4" -> {
                    clientes.excluir(lerInt("Id do cliente"));
                    System.out.println("Cliente excluído.");
                }
                default -> System.out.println("Opção inválida.");
            }
        });
    }

    // ---------------- Pets ----------------
    private void menuPets() {
        System.out.println("\n[Pets] 1) Cadastrar  2) Listar  3) Alterar  4) Excluir");
        tentar(() -> {
            switch (lerTexto("Opção")) {
                case "1" -> {
                    Pet p = new Pet(pets.proximoId(), lerTexto("Nome"), lerPorte(), lerInt("Idade"));
                    pets.salvar(p);
                    System.out.println("Pet cadastrado com id " + p.getId());
                }
                case "2" -> listar(pets.listar(), p -> String.format("#%d %s (%s, %d anos)",
                        p.getId(), p.getNome(), p.getPorte(), p.getIdade()));
                case "3" -> {
                    Pet p = buscar(pets, lerInt("Id do pet"));
                    p.setNome(lerTexto("Novo nome"));
                    p.setPorte(lerPorte());
                    p.setIdade(lerInt("Nova idade"));
                    pets.atualizar(p);
                    System.out.println("Pet alterado.");
                }
                case "4" -> {
                    pets.excluir(lerInt("Id do pet"));
                    System.out.println("Pet excluído.");
                }
                default -> System.out.println("Opção inválida.");
            }
        });
    }

    // ---------------- Serviços ----------------
    private void menuServicos() {
        System.out.println("\n[Serviços] 1) Cadastrar  2) Listar  3) Alterar  4) Excluir");
        tentar(() -> {
            switch (lerTexto("Opção")) {
                case "1" -> {
                    Servico s = new Servico(servicos.proximoId(), lerTexto("Nome"), lerDouble("Valor"));
                    servicos.salvar(s);
                    System.out.println("Serviço cadastrado com id " + s.getId());
                }
                case "2" -> listar(servicos.listar(), s -> String.format("#%d %s - R$ %.2f",
                        s.getId(), s.getNome(), s.getValor()));
                case "3" -> {
                    Servico s = buscar(servicos, lerInt("Id do serviço"));
                    s.setNome(lerTexto("Novo nome"));
                    s.setValor(lerDouble("Novo valor"));
                    servicos.atualizar(s);
                    System.out.println("Serviço alterado.");
                }
                case "4" -> {
                    servicos.excluir(lerInt("Id do serviço"));
                    System.out.println("Serviço excluído.");
                }
                default -> System.out.println("Opção inválida.");
            }
        });
    }

    // ---------------- Agendamentos ----------------
    private void menuAgendamentos() {
        System.out.println("\n[Agendamentos] 1) Agendar  2) Listar  3) Confirmar  4) Concluir"
                + "  5) Cancelar  6) Reagendar  7) Excluir");
        tentar(() -> {
            switch (lerTexto("Opção")) {
                case "1" -> {
                    Agendamento a = new Agendamento(agendamentos.proximoId(),
                            buscar(pets, lerInt("Id do pet")), buscar(servicos, lerInt("Id do serviço")),
                            lerDataHora());
                    agendamentos.salvar(a);
                    System.out.println("Agendamento criado com id " + a.getId());
                }
                case "2" -> listar(agendamentos.listar(), a -> String.format("#%d %s - %s em %s [%s]",
                        a.getId(), a.getPet().getNome(), a.getServico().getNome(),
                        a.getDataHora().format(FORMATO), a.getStatus()));
                case "3" -> buscar(agendamentos, lerInt("Id")).confirmar();
                case "4" -> buscar(agendamentos, lerInt("Id")).concluir();
                case "5" -> buscar(agendamentos, lerInt("Id")).cancelar();
                case "6" -> buscar(agendamentos, lerInt("Id")).reagendar(lerDataHora());
                case "7" -> agendamentos.excluir(lerInt("Id"));
                default -> System.out.println("Opção inválida.");
            }
        });
    }

    // ---------------- Vendas ----------------
    private void menuVendas() {
        System.out.println("\n[Vendas] 1) Nova venda  2) Adicionar item  3) Aplicar desconto"
                + "  4) Listar  5) Excluir");
        tentar(() -> {
            switch (lerTexto("Opção")) {
                case "1" -> {
                    Cliente c = buscar(clientes, lerInt("Id do cliente"));
                    Venda v = new Venda();
                    vendas.add(v);
                    clienteDaVenda.put(v, c);
                    System.out.println("Venda criada com número " + vendas.size());
                }
                case "2" -> buscarVenda(lerInt("Número da venda")).adicionarItem(
                        new ItemVenda(buscar(servicos, lerInt("Id do serviço")), lerInt("Quantidade")));
                case "3" -> buscarVenda(lerInt("Número da venda")).setDesconto(lerDouble("Desconto (%)"));
                case "4" -> {
                    if (vendas.isEmpty()) {
                        System.out.println("(nenhum registro)");
                    }
                    for (int i = 0; i < vendas.size(); i++) {
                        Venda v = vendas.get(i);
                        System.out.printf("#%d %s | itens: %d | subtotal R$ %.2f | desconto %.1f%% | total R$ %.2f%n",
                                i + 1, clienteDaVenda.get(v).getNome(), v.getItens().size(),
                                v.getSubtotal(), v.getDesconto(), v.getTotal());
                    }
                }
                case "5" -> {
                    Venda v = buscarVenda(lerInt("Número da venda"));
                    vendas.remove(v);
                    clienteDaVenda.remove(v);
                    System.out.println("Venda excluída.");
                }
                default -> System.out.println("Opção inválida.");
            }
        });
    }

    // ---------------- Utilidades ----------------
    private Venda buscarVenda(int numero) {
        if (numero < 1 || numero > vendas.size()) {
            throw new IllegalArgumentException("Venda não encontrada: " + numero);
        }
        return vendas.get(numero - 1);
    }

    private <T> void listar(List<T> lista, java.util.function.Function<T, String> formato) {
        if (lista.isEmpty()) {
            System.out.println("(nenhum registro)");
        }
        lista.forEach(item -> System.out.println(formato.apply(item)));
    }

    private <T extends Identificavel> T buscar(RepositorioMemoria<T> repo, int id) {
        return repo.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Registro não encontrado: " + id));
    }

    private void tentar(Runnable acao) {
        try {
            acao.run();
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private String lerTexto(String rotulo) {
        System.out.print(rotulo + ": ");
        return in.hasNextLine() ? in.nextLine().trim() : "0";
    }

    private int lerInt(String rotulo) {
        try {
            return Integer.parseInt(lerTexto(rotulo));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Informe um número inteiro");
        }
    }

    private double lerDouble(String rotulo) {
        try {
            return Double.parseDouble(lerTexto(rotulo).replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Informe um número válido");
        }
    }

    private Porte lerPorte() {
        try {
            return Porte.valueOf(lerTexto("Porte (PEQUENO, MEDIO, GRANDE)").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Porte inválido");
        }
    }

    private LocalDateTime lerDataHora() {
        try {
            return LocalDateTime.parse(lerTexto("Data e hora (dd/MM/yyyy HH:mm)"), FORMATO);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data/hora inválida");
        }
    }
}
