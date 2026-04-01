public class ValueClassifier {

    /**
     * Classifica um valor inteiro e imprime a categoria correspondente.
     * Responsabilidade única: orquestrar a classificação e exibir o resultado.
     */
    public void classify(int value) {
        String category = determineCategory(value);
        System.out.println(category);
    }

    /**
     * Determina a categoria do valor conforme as regras de negócio.
     * Extraído como método separado para facilitar testes e reuso.
     */
    private String determineCategory(int value) {
        if (value == -9999) return "CASO RARO";
        if (value > 10)     return "ALTO";
        if (value == 10)    return "MEDIO";
        return "BAIXO";
    }
}
