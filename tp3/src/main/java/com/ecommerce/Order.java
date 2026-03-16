package com.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa um pedido de e-commerce.
 *
 * Responsabilidades:
 *  - Gerenciar a coleção de itens de forma encapsulada
 *  - Calcular subtotal, desconto e total
 *  - Delegar o envio de e-mail ao serviço injetado (sem acoplamento direto)
 */
public class Order {

    private final Client         client;
    private final List<Item>     items = new ArrayList<>();
    private final DiscountPolicy discountPolicy;
    private final EmailService   emailService;

    public Order(Client client, DiscountPolicy discountPolicy, EmailService emailService) {
        if (client         == null) throw new IllegalArgumentException("Client não pode ser nulo.");
        if (discountPolicy == null) throw new IllegalArgumentException("DiscountPolicy não pode ser nula.");
        if (emailService   == null) throw new IllegalArgumentException("EmailService não pode ser nulo.");
        this.client         = client;
        this.discountPolicy = discountPolicy;
        this.emailService   = emailService;
    }

    // ── Mutação controlada ────────────────────────────────────────────────────

    public void addItem(Item item) {
        if (item == null) throw new IllegalArgumentException("Item não pode ser nulo.");
        items.add(item);
    }

    // ── Consultas ─────────────────────────────────────────────────────────────

    public Client getClient() { return client; }

    /** Retorna os itens como lista imutável para evitar modificações externas. */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double calculateSubtotal() {
        return items.stream().mapToDouble(Item::getSubtotal).sum();
    }

    public double calculateDiscount() {
        return discountPolicy.calculate(calculateSubtotal());
    }

    public double calculateTotal() {
        return calculateSubtotal() - calculateDiscount();
    }

    // ── Notificação ───────────────────────────────────────────────────────────

    /** Envia e-mail de confirmação ao cliente, ocultando o delegate EmailService. */
    public void sendConfirmationEmail() {
        String message = buildConfirmationMessage();
        emailService.send(client.getEmail(), message);
    }

    private String buildConfirmationMessage() {
        return String.format(
            "Olá, %s! Seu pedido foi recebido. Total: R$%.2f. Obrigado pela compra!",
            client.getName(), calculateTotal()
        );
    }
}
