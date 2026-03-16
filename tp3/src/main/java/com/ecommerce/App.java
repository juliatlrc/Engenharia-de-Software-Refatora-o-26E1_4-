package com.ecommerce;

/**
 * Ponto de entrada da aplicação.
 * Demonstra o uso do sistema refatorado com injeção de dependências manual.
 */
public class App {

    public static void main(String[] args) {

        // 1. Cria o cliente
        Client client = new Client("João", "joao@email.com");

        // 2. Define a política de desconto (10%)
        DiscountPolicy discountPolicy = new PercentageDiscountPolicy(0.10);

        // 3. Define o serviço de e-mail (console — troque por SMTP em produção)
        EmailService emailService = new ConsoleEmailService();

        // 4. Cria o pedido injetando as dependências
        Order order = new Order(client, discountPolicy, emailService);
        order.addItem(new Item("Notebook", 3500.0, 1));
        order.addItem(new Item("Mouse",      80.0, 2));
        order.addItem(new Item("Teclado",   150.0, 1));

        // 5. Imprime a fatura
        InvoicePrinter printer = new InvoicePrinter(order);
        printer.print();

        // 6. Envia e-mail de confirmação
        order.sendConfirmationEmail();
    }
}
