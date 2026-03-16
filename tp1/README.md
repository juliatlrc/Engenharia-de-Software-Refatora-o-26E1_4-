# Gilded Rose Refactoring Kata – DR4 TP1

## Estrutura do Projeto

```
gilded-rose/
├── pom.xml
└── src/
    ├── main/java/com/gildedrose/
    │   ├── Item.java                  # Classe original (não modificada)
    │   ├── ItemUpdater.java           # Interface – Exercício 2
    │   ├── AgedBrieUpdater.java       # Exercício 2
    │   ├── SulfurasUpdater.java       # Exercício 2
    │   ├── BackstagePassUpdater.java  # Exercício 2
    │   ├── DefaultUpdater.java        # Exercício 2
    │   ├── ConjuredItemUpdater.java   # Exercício 3
    │   └── GildedRose.java            # Classe principal refatorada
    └── test/java/com/gildedrose/
        └── GildedRoseTest.java        # 9 testes JUnit 5 – Exercício 4
```

## Como executar

**Pré-requisitos:** Java 11+ e Maven 3.6+

```bash
# Compilar e rodar todos os testes
mvn test

# Saída esperada:
# Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
# BUILD SUCCESS
```

## Resumo dos Exercícios

| # | Exercício | Técnica | Princípio SOLID |
|---|-----------|---------|-----------------|
| 1 | Extração de Métodos | Decomposição de método longo | SRP (parcial) |
| 2 | Modularização | Strategy Pattern + Map | SRP + OCP |
| 3 | Item Conjurado | Nova classe sem alterar existentes | OCP |
| 4 | Testes JUnit 5 | Arrange-Act-Assert, 9 testes | Segurança na refatoração |
| 5 | Avaliação de Design | Revisão OCP / SRP / LSP | OCP + SRP + LSP |

## Repositório base

https://github.com/emilybache/GildedRose-Refactoring-Kata
