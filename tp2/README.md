# Build Pipeline Refactoring Kata – DR4 TP2

## Sobre o Projeto Original

O [BuildPipeline-Refactoring-Kata](https://github.com/emilybache/BuildPipeline-Refactoring-Kata)
simula um pipeline de CI/CD com as seguintes etapas: executar testes, fazer deploy e
enviar um e-mail de sumário. O código original (`Pipeline.java`) tem ~80 linhas com:

- Bloco `if/else` duplicado integralmente (com e sem e-mail)
- Múltiplas responsabilidades no método `run()`
- Variáveis booleanas sem clareza de intenção
- Nenhum teste automatizado

## Estrutura do Projeto

```
build-pipeline/
├── pom.xml
├── README.md
└── src/
    ├── main/java/pipeline/
    │   ├── Config.java              (interface original)
    │   ├── Emailer.java             (interface original)
    │   ├── Logger.java              (interface original)
    │   ├── Project.java             (interface original)
    │   ├── Pipeline.java            (código original – preservado)
    │   └── PipelineRefactored.java  (versão refatorada – todos os exercícios)
    └── test/java/pipeline/
        └── PipelineTest.java        (8 testes JUnit 5)
```

## Melhorias Realizadas

### Exercício 1 – Verificação Inicial e Testes
- Criados 8 testes JUnit 5 cobrindo todos os caminhos do código original
- Testes escritos com doubles manuais (sem frameworks externos)

### Exercício 2 – Reestruturando Métodos Complexos
- `run()` decompostos em: `runTests()`, `deployProject()`, `sendSummaryEmail()`
- Bloco duplicado de ~40 linhas eliminado

### Exercício 3 – Expressividade com Variáveis
- `testsPassed` e `deploySuccessful` nomeiam claramente o resultado de cada etapa
- Condição `project.runTests().equals("success")` extraída com nome expressivo

### Exercício 4 – Encapsulamento
- Lógica de notificação isolada em `sendSummaryEmail(boolean, boolean)`
- Dependências injetadas via construtor (imutáveis, sem setters públicos)

### Exercício 5 – Reorganização
- Classe com única responsabilidade: orquestrar as etapas do pipeline
- Cada etapa tem método dedicado, testável individualmente

## Como Executar

```bash
mvn test
# Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
```

## Repositório Base

https://github.com/emilybache/BuildPipeline-Refactoring-Kata
