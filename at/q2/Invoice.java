public class Invoice {

    private final String clientName;
    private final String clientEmail;
    private final double amount;
    private final InvoiceType type;

    public Invoice(String clientName, String clientEmail,
                   double amount, InvoiceType type) {
        this.clientName  = clientName;
        this.clientEmail = clientEmail;
        this.amount      = amount;
        this.type        = type;
    }

    public void process() {
        if (!isValidEmail(clientEmail)) {
            System.out.println("Email invalido. Falha no envio.");
            return;
        }
        printInvoice();
        sendByEmail();
    }

    private void printInvoice() {
        System.out.println("--- NOTA FISCAL ---");
        System.out.println("Cliente: " + clientName);
        System.out.println("Valor: R$ " + amount);
        System.out.println("Tipo: " + describeType());
        System.out.println("-------------------");
    }

    private void sendByEmail() {
        String nota = buildInvoiceText();
        System.out.println("Enviando para: " + clientEmail);
        System.out.println("Conteudo:\n" + nota);
    }

    /**
     * Único lugar onde o mapeamento tipo → descrição é definido.
     * Elimina a duplicação do código original onde a mesma cadeia
     * if/else aparecia duas vezes no método process().
     */
    private String describeType() {
        return switch (type) {
            case SIMPLE -> "Simples";
            case TAXED  -> "Com imposto";
        };
    }

    private String buildInvoiceText() {
        return "--- NOTA FISCAL ---\n"
             + "Cliente: " + clientName + "\n"
             + "Valor: R$ " + amount + "\n"
             + "Tipo: " + describeType() + "\n"
             + "-------------------";
    }

    private static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}
