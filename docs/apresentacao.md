# Apresentação — Sistema Pet Shop & Clínica Veterinária

Trabalho A3 de **Gestão e Qualidade de Software**.
Integrantes: Diego Manini, Yago Siqueira e Diogo Marx Lopes.

---

## 1. Capa
Sistema Pet Shop & Clínica Veterinária — CRUD em Java com TDD, JUnit 5 e Git.

## 2. O tema
Um sistema para o dia a dia do pet shop: cadastros (clientes, pets e serviços), agenda de atendimentos e vendas com desconto. Escopo: CRUD completo, orientado a objetos, com testes unitários.

## 3. Domínio: seis classes, atributos privados
- `Cliente`: ID, nome, telefone e e-mail do tutor.
- `Pet`: ID, nome, porte (enum) e idade.
- `Servico`: nome e valor positivo obrigatório.
- `Agendamento`: pet, serviço, data/hora e status.
- `ItemVenda`: serviço e quantidade, com subtotal.
- `Venda`: itens, desconto de 0 a 100% e total.

Apoio: `Validacao` (regras reutilizáveis) e `RepositorioMemoria<T>` (CRUD genérico).

## 4. Diagrama de classes
O diagrama completo (Mermaid) está no [README](../README.md#diagrama-de-classes).

- Agenda: Pet → Agendamento ← Servico
- Venda: Venda → ItemVenda → Servico
- Cliente, Pet, Servico e Agendamento implementam `Identificavel`; o `RepositorioMemoria<T>` guarda qualquer `Identificavel`.

## 5. Demonstração do sistema
```bash
mvn compile exec:java
```
Menu de console: Clientes, Pets, Serviços, Agendamentos e Vendas. Fluxo: cadastrar cliente, pet e serviço; agendar e confirmar; criar venda com itens e desconto; alterar e excluir um registro.

## 6. Testes
```bash
mvn test
```
Mais de 100 testes JUnit 5, com três casos por método: normal, limite e erro.

## 7. TDD na prática (RED → GREEN → REFACTOR)
Exemplo no `RepositorioMemoria`:
- **RED** 734394d: teste salvarGuardaEntidade (o teste vem primeiro e falha)
- **GREEN** 6711127: implementa RepositorioMemoria (código mínimo para passar)
- **REFACTOR** 626f24e: extrai validação e simplifica proximoId (testes continuam verdes)

Também: Venda (25281b9 RED, f4a283c GREEN) e Cliente (REFACTOR para usar Validacao).
Para ver o histórico: `git log --oneline --graph`

## 8. Organização no Git
Uma branch por funcionalidade, integrada à `main` por pull request.

| Integrante | Branches e classes | GitHub |
| --- | --- | --- |
| Diego Manini | cliente, validacao, servico, item-venda, menu-console, docs/readme | maniny01 |
| Yago Siqueira | RepositorioMemoria (ciclos RED, GREEN, REFACTOR) | faculdadecabuloso-eng |
| Diogo Marx Lopes | pet, venda, agendamento | DiogoMarx8349 |

Professor `reinaldocm-prof` como colaborador do repositório.

## 9. Aprendizados
- Testar primeiro obriga a pensar no comportamento e nos casos de erro.
- Integrar cedo: um erro de merge deixou testes dentro de uma classe e quebrou a compilação; rodar o build antes do merge evita isso.
- Validacao e RepositorioMemoria evitam repetir regras e CRUD.

## 10. Como rodar
```bash
git clone https://github.com/maniny01/petshop-gqs-a3.git
cd petshop-gqs-a3
mvn clean test
mvn compile exec:java
```
Requisitos: Java 17 e Maven.
