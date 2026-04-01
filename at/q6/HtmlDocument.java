public class HtmlDocument extends Document {

    public HtmlDocument(String title) {
        super(title);
    }

    @Override
    public void print() {
        System.out.println("Printing HTML: " + getTitle());
    }
}
