public abstract class Document {

    private final String title;

    public Document(String title) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Titulo do documento nao pode ser vazio.");
        this.title = title;
    }

    public String getTitle() { return title; }

    /**
     * Cada subclasse implementa seu próprio comportamento de impressão.
     * Não existe mais cadeia if/else — o polimorfismo resolve o desvio
     * para o método correto em tempo de execução.
     */
    public abstract void print();
}
