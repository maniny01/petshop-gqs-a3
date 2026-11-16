# petshop-gqs-a3
# Sistema de Apoio a Pet Shop e Veterinária

Trabalho prático final da disciplina de Gestão e Qualidade de Software (A3)[span_1](start_span)[span_1](end_span).

---

## 1. Tema Escolhido

* **Tema:** PetShop / Veterinária[span_2](start_span)[span_2](end_span)[span_3](start_span)[span_3](end_span)
* **Descrição do Sistema:** Sistema de apoio à gestão de um pet shop e clínica veterinária, permitindo a gestão completa de clientes (CRUD), gestão do catálogo de serviços oferecidos (ex.: banho, tosa, vacinações) e gestão financeira/vendas associadas aos atendimentos prestados[span_4](start_span)[span_4](end_span)[span_5](start_span)[span_5](end_span).

---

## 2. Integrantes do Grupo

* Diego Manini[span_6](start_span)[span_6](end_span)
* Yago Siqueira[span_7](start_span)[span_7](end_span)
* Diogo Marx Lopes[span_8](start_span)[span_8](end_span)

---

## 3. Tecnologias Utilizadas

* **Linguagem:** Java 17[span_9](start_span)[span_9](end_span)
* **Gestor de Dependências e Build:** Apache Maven
* **Framework de Testes:** JUnit 5 (JUnit Jupiter)[span_10](start_span)[span_10](end_span)
* **Controlo de Versões:** Git / GitHub[span_11](start_span)[span_11](end_span)

---

## 4. Estrutura do Projeto e Classes

O sistema foi modelado sob o paradigma de programação orientada a objetos com encapsulamento rigoroso e operações de CRUD[span_12](start_span)[span_12](end_span)[span_13](start_span)[span_13](end_span):

1. `Cliente`: Gestão dos dados cadastrais dos tutores[span_14](start_span)[span_14](end_span).
2. `Pet`: Registo dos animais associados aos clientes tutores.
3. `Servico`: Catálogo de procedimentos disponíveis (banho, tosa, vacinas, consultas)[span_15](start_span)[span_15](end_span).
4. `Agendamento`: Controlo de horários, datas e estados de marcação de atendimentos.
5. `ItemVenda`: Itens individuais e serviços discriminados numa ordem de serviço/venda[span_16](start_span)[span_16](end_span).
6. `Venda`: Fechamento financeiro, cálculo de totais, descontos e métodos de pagamento[span_17](start_span)[span_17](end_span).

---

## 5. Metodologia de Desenvolvimento (TDD e Git)

O desenvolvimento seguiu rigorosamente o ciclo de **Test-Driven Development (TDD)** e boas práticas de versionamento[span_18](start_span)[span_18](end_span)[span_19](start_span)[span_19](end_span):

* **Ciclo RED → GREEN → REFACTOR:** Cada funcionalidade e método possui o teste implementado previamente, com histórico de commits refletindo o padrão exigido (`RED: ...`, `GREEN: ...`, `REFACTOR: ...`)[span_20](start_span)[span_20](end_span).
* **Cobertura de Testes:** Ao menos 3 testes unitários com JUnit 5 para cada método (cenário normal, valor limite e cenário de erro/exceção)[span_21](start_span)[span_21](end_span).
* **Branches e Colaboração:** Cada funcionalidade foi desenvolvida em branch própria (`feature/...`) e integrada à `main` somente com os testes aprovados[span_22](start_span)[span_22](end_span).

---

## 6. Como Compilar e Executar os Testes

### Pré-requisitos
* Java JDK 17 instalado e configurado nas variáveis de ambiente.
* Apache Maven instalado.

### Compilação do Projeto
Para descarregar as dependências e compilar o código-fonte:
```bash
mvn compile
