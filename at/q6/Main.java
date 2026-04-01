import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Instancia três documentos de tipos diferentes usando as subclasses
        List<Document> documents = List.of(
            new PdfDocument("Relatorio Anual"),
            new HtmlDocument("Pagina Inicial"),
            new WordDocument("Ata de Reuniao")
        );

        // O polimorfismo resolve automaticamente qual print() chamar
        // sem nenhum if/else ou verificação de tipo
        for (Document doc : documents) {
            doc.print();
        }

        // Saída esperada:
        // Printing PDF: Relatorio Anual
        // Printing HTML: Pagina Inicial
        // Printing Word: Ata de Reuniao
    }
}
