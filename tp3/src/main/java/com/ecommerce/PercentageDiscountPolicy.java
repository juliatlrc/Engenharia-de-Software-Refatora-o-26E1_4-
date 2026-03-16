package com.ecommerce;

/**
 * Implementação padrão de desconto: percentual fixo sobre o subtotal.
 */
public class PercentageDiscountPolicy implements DiscountPolicy {

    private final double rate;

    /**
     * @param rate taxa de desconto entre 0.0 (0%) e 1.0 (100%)
     */
    public PercentageDiscountPolicy(double rate) {
        if (rate < 0 || rate > 1) throw new IllegalArgumentException("Taxa de desconto deve estar entre 0 e 1.");
        this.rate = rate;
    }

    @Override
    public double calculate(double subtotal) {
        return subtotal * rate;
    }

    public double getRate() { return rate; }
}
