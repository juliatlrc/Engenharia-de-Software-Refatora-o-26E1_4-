# E-commerce Refatorado — DR4 TP3

Refatoração completa do sistema de geração de faturas e envio de e-mails,
aplicando boas práticas de design orientado a objetos em Java.

---

## Estrutura do Projeto

```
ecommerce-refatorado/
├── pom.xml
└── src/
    ├── main/java/com/ecommerce/
    │   ├── App.java                      ← Ponto de entrada
    │   ├── Client.java                   ← Dados do cliente encapsulados
    │   ├── Item.java                     ← Substitui as listas paralelas
    │   ├── Order.java                    ← Domínio principal
    │   ├── InvoicePrinter.java           ← Responsabilidade de impressão
    │   ├── DiscountPolicy.java           ← Interface (Strategy)
    │   ├── PercentageDiscountPolicy.java ← Implementação padrão de desconto
    │   ├── EmailService.java             ← Interface de e-mail
    │   └── ConsoleEmailService.java      ← Implementação console
    └── test/java/com/ecommerce/
        └── AllTests.java                 ← Testes JUnit 5
```

---

## Refatorações Aplicadas

| # | Ação | Resultado |
|---|------|-----------|
| 1 | Encapsular registros e coleções | Todos os atributos são `private`; lista de itens exposta como `unmodifiableList` |
| 2 | Substituir primitivos por objetos | Classe `Item` substitui as três listas paralelas |
| 3 | Ocultar delegados | `EmailService` injetado em `Order`; chamada interna e oculta |
| 4 | Mover funções entre classes | Cálculos em `Order`; impressão em `InvoicePrinter` |
| 5 | Reposicionar campos | `Client` encapsula nome e e-mail |
| 6 | Substituir código embutido por funções | `printHeader()`, `printItems()`, `printTotals()`, `buildConfirmationMessage()` |
| 7 | Remover código morto | `DiscountPolicy` virou interface útil; sem atributos públicos ou listas inseguras |

---

## Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.8+

### Compilar e rodar
```bash
mvn clean package
java -jar target/ecommerce-refatorado.jar
```

### Rodar os testes
```bash
mvn test
```

### Saída esperada
```
──────────────────────────────────────
           FATURA DE PEDIDO           
Cliente : João
E-mail  : joao@email.com
──────────────────────────────────────
ITENS:
  Notebook              1x R$  3500,00  =  R$   3500,00
  Mouse                 2x R$    80,00  =  R$    160,00
  Teclado               1x R$   150,00  =  R$    150,00
──────────────────────────────────────
  Subtotal :             R$   3810,00
  Desconto :           - R$    381,00
  TOTAL    :             R$   3429,00
──────────────────────────────────────
──────────────────────────────────────
📧 E-mail enviado
Para    : joao@email.com
Mensagem: Olá, João! Seu pedido foi recebido. Total: R$3429,00. Obrigado pela compra!
──────────────────────────────────────
```

---

## Diagrama de Classes (simplificado)

```
App
 └─creates─► Client
 └─creates─► PercentageDiscountPolicy ──implements──► DiscountPolicy
 └─creates─► ConsoleEmailService      ──implements──► EmailService
 └─creates─► Order ◄──────────────────────────────────────────────
               │ uses DiscountPolicy (injected)
               │ uses EmailService   (injected)
               │ aggregates ──► Item (0..*)
               │
 └─creates─► InvoicePrinter ──uses──► Order
```

---

## Decisões de Design

- **Strategy Pattern** em `DiscountPolicy`: novas regras de desconto (por valor mínimo, cupom, VIP) são adicionadas sem tocar em `Order`.
- **Injeção de dependência manual**: sem framework; `EmailService` e `DiscountPolicy` entram pelo construtor de `Order`, facilitando testes com mocks.
- **Imutabilidade**: `Client` e `Item` têm todos os campos `final`; `Order` expõe itens apenas via `Collections.unmodifiableList`.
- **Validação defensiva**: construtores rejeitam dados inválidos com `IllegalArgumentException` e mensagens claras.
