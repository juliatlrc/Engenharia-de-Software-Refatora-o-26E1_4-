package com.ecommerce;

/**
 * Representa um item de pedido: produto, preço unitário e quantidade.
 * Substitui as três listas paralelas do código original (products, prices, quantities).
 */
public class Item {

    private final String product;
    private final double unitPrice;
    private final int    quantity;

    public Item(String product, double unitPrice, int quantity) {
        if (product == null || product.isBlank()) throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        if (unitPrice < 0) throw new IllegalArgumentException("Preço unitário não pode ser negativo.");
        if (quantity  < 1) throw new IllegalArgumentException("Quantidade deve ser pelo menos 1.");
        this.product   = product;
        this.unitPrice = unitPrice;
        this.quantity  = quantity;
    }

    public String getProduct()   { return product; }
    public double getUnitPrice() { return unitPrice; }
    public int    getQuantity()  { return quantity; }

    /** Calcula o subtotal deste item (unitPrice × quantity). */
    public double getSubtotal()  { return unitPrice * quantity; }

    @Override
    public String toString() {
        return String.format("%dx %s - R$%.2f (subtotal: R$%.2f)",
                quantity, product, unitPrice, getSubtotal());
    }
}
