public class PriceCalculator {

    private static final int    STANDARD_CUSTOMER = 1;
    private static final int    PREMIUM_CUSTOMER  = 2;

    private static final double STANDARD_DISCOUNT = 0.10;
    private static final double PREMIUM_DISCOUNT  = 0.15;
    private static final double HOLIDAY_BONUS     = 0.05;
    private static final double NO_DISCOUNT       = 0.00;

    /**
     * Calcula o preço final de um produto considerando o tipo de cliente
     * e se a compra ocorre em um feriado.
     *
     * @param basePrice    preço base do produto
     * @param customerType tipo do cliente (1 = padrão, 2 = premium)
     * @param holiday      true se for feriado
     * @return preço final com descontos aplicados
     */
    public double calculatePrice(double basePrice,
                                 int customerType,
                                 boolean holiday) {
        double baseDiscount    = getBaseDiscount(customerType);
        double holidayDiscount = holiday ? HOLIDAY_BONUS : NO_DISCOUNT;
        double totalDiscount   = baseDiscount + holidayDiscount;

        return basePrice * (1 - totalDiscount);
    }

    private double getBaseDiscount(int customerType) {
        if (customerType == STANDARD_CUSTOMER) return STANDARD_DISCOUNT;
        if (customerType == PREMIUM_CUSTOMER)  return PREMIUM_DISCOUNT;
        return NO_DISCOUNT;
    }
}
