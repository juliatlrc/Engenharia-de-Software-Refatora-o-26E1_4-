package com.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Client client;
    private DiscountPolicy tenPercentDiscount;
    private EmailService   captureEmailService;
    private List<String>   sentEmails;

    @BeforeEach
    void setUp() {
        client             = new Client("João", "joao@email.com");
        tenPercentDiscount = new PercentageDiscountPolicy(0.10);
        sentEmails         = new ArrayList<>();
        // Mock de EmailService: captura os e-mails enviados
        captureEmailService = (to, message) -> sentEmails.add(to + "|" + message);
    }

    // ── Cálculos ──────────────────────────────────────────────────────────────

    @Test
    void subtotalShouldSumAllItemSubtotals() {
        Order order = buildOrder();
        // Notebook: 3500 * 1 = 3500 | Mouse: 80 * 2 = 160
        assertEquals(3660.0, order.calculateSubtotal(), 0.001);
    }

    @Test
    void discountShouldBeTenPercentOfSubtotal() {
        Order order = buildOrder();
        assertEquals(366.0, order.calculateDiscount(), 0.001);
    }

    @Test
    void totalShouldBeSubtotalMinusDiscount() {
        Order order = buildOrder();
        assertEquals(3294.0, order.calculateTotal(), 0.001);
    }

    @Test
    void emptyOrderShouldHaveZeroTotal() {
        Order order = new Order(client, tenPercentDiscount, captureEmailService);
        assertEquals(0.0, order.calculateTotal(), 0.001);
    }

    // ── E-mail ────────────────────────────────────────────────────────────────

    @Test
    void sendConfirmationEmailShouldTargetClientEmail() {
        Order order = buildOrder();
        order.sendConfirmationEmail();

        assertEquals(1, sentEmails.size());
        assertTrue(sentEmails.get(0).startsWith("joao@email.com|"));
    }

    @Test
    void sendConfirmationEmailShouldContainClientName() {
        Order order = buildOrder();
        order.sendConfirmationEmail();

        assertTrue(sentEmails.get(0).contains("João"));
    }

    // ── Encapsulamento ────────────────────────────────────────────────────────

    @Test
    void itemsListShouldBeUnmodifiable() {
        Order order = buildOrder();
        assertThrows(UnsupportedOperationException.class, () -> order.getItems().clear());
    }

    // ── Validações ────────────────────────────────────────────────────────────

    @Test
    void orderShouldRejectNullClient() {
        assertThrows(IllegalArgumentException.class,
            () -> new Order(null, tenPercentDiscount, captureEmailService));
    }

    @Test
    void orderShouldRejectNullItem() {
        Order order = new Order(client, tenPercentDiscount, captureEmailService);
        assertThrows(IllegalArgumentException.class, () -> order.addItem(null));
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private Order buildOrder() {
        Order order = new Order(client, tenPercentDiscount, captureEmailService);
        order.addItem(new Item("Notebook", 3500.0, 1));
        order.addItem(new Item("Mouse",      80.0, 2));
        return order;
    }
}


class ItemTest {

    @Test
    void subtotalShouldMultiplyPriceByQuantity() {
        Item item = new Item("Mouse", 80.0, 2);
        assertEquals(160.0, item.getSubtotal(), 0.001);
    }

    @Test
    void itemShouldRejectNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Item("X", -1.0, 1));
    }

    @Test
    void itemShouldRejectZeroQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new Item("X", 10.0, 0));
    }

    @Test
    void itemShouldRejectBlankProduct() {
        assertThrows(IllegalArgumentException.class, () -> new Item("  ", 10.0, 1));
    }
}


class ClientTest {

    @Test
    void clientShouldStoreNameAndEmail() {
        Client c = new Client("João", "joao@email.com");
        assertEquals("João",            c.getName());
        assertEquals("joao@email.com",  c.getEmail());
    }

    @Test
    void clientShouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Client("", "joao@email.com"));
    }

    @Test
    void clientShouldRejectBlankEmail() {
        assertThrows(IllegalArgumentException.class, () -> new Client("João", ""));
    }
}


class PercentageDiscountPolicyTest {

    @Test
    void shouldCalculateTenPercentDiscount() {
        DiscountPolicy policy = new PercentageDiscountPolicy(0.10);
        assertEquals(100.0, policy.calculate(1000.0), 0.001);
    }

    @Test
    void zeroRateShouldReturnNoDiscount() {
        DiscountPolicy policy = new PercentageDiscountPolicy(0.0);
        assertEquals(0.0, policy.calculate(500.0), 0.001);
    }

    @Test
    void shouldRejectRateAboveOne() {
        assertThrows(IllegalArgumentException.class, () -> new PercentageDiscountPolicy(1.5));
    }

    @Test
    void shouldRejectNegativeRate() {
        assertThrows(IllegalArgumentException.class, () -> new PercentageDiscountPolicy(-0.1));
    }
}
