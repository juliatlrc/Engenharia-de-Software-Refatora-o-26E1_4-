import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class ValueClassifierTest {

    private String captureOutput(int value) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        new ValueClassifier().classify(value);
        System.setOut(original);
        return baos.toString().trim();
    }

    @Test
    void shouldPrintALTO_whenValueIsAboveTen() {
        assertEquals("ALTO", captureOutput(15));
        assertEquals("ALTO", captureOutput(11));
    }

    @Test
    void shouldPrintMEDIO_whenValueIsExactlyTen() {
        assertEquals("MEDIO", captureOutput(10));
    }

    @Test
    void shouldPrintBAIXO_whenValueIsBelowTen() {
        assertEquals("BAIXO", captureOutput(5));
        assertEquals("BAIXO", captureOutput(0));
        assertEquals("BAIXO", captureOutput(-1));
    }

    @Test
    void shouldPrintCASORAR0_whenValueIsNegative9999() {
        assertEquals("CASO RARO", captureOutput(-9999));
    }
}
