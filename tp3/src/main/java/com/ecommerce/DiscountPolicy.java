package com.ecommerce;

/**
 * Contrato para políticas de desconto.
 * Permite trocar a estratégia de desconto sem alterar Order (Strategy Pattern).
 */
public interface DiscountPolicy {

    /**
     * Calcula o valor de desconto sobre o subtotal informado.
     *
     * @param subtotal valor bruto dos itens
     * @return valor a ser descontado (nunca negativo)
     */
    double calculate(double subtotal);
}
