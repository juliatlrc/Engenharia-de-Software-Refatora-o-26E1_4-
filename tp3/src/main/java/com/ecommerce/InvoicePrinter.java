package com.ecommerce;

/**
 * Responsável exclusivamente por formatar e imprimir a fatura de um pedido.
 * Separa a lógica de apresentação da lógica de negócio em Order.
 */
public class InvoicePrinter {

    private final Order order;

    public InvoicePrinter(Order order) {
        if (order == null) throw new IllegalArgumentException("Order não pode ser nulo.");
        this.order = order;
    }

    /** Ponto de entrada: imprime a fatura completa no console. */
    public void print() {
        printSeparator();
        printHeader();
        printSeparator();
        printItems();
        printSeparator();
        printTotals();
        printSeparator();
    }

    private void printHeader() {
        System.out.println("           FATURA DE PEDIDO           ");
        System.out.println("Cliente : " + order.getClient().getName());
        System.out.println("E-mail  : " + order.getClient().getEmail());
    }

    private void printItems() {
        System.out.println("ITENS:");
        for (Item item : order.getItems()) {
            System.out.printf("  %-20s %2dx R$%8.2f  =  R$%9.2f%n",
                item.getProduct(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getSubtotal());
        }
    }

    private void printTotals() {
        System.out.printf("  Subtotal :             R$%9.2f%n", order.calculateSubtotal());
        System.out.printf("  Desconto :           - R$%9.2f%n", order.calculateDiscount());
        System.out.printf("  TOTAL    :             R$%9.2f%n", order.calculateTotal());
    }

    private void printSeparator() {
        System.out.println("──────────────────────────────────────");
    }
}
