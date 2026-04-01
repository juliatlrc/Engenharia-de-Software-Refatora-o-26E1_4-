public class WordDocument extends Document {

    public WordDocument(String title) {
        super(title);
    }

    @Override
    public void print() {
        System.out.println("Printing Word: " + getTitle());
    }
}
