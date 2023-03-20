package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void diffEmptyTest() throws Exception {
        String filepath1 = "src/test/resources/emptyFile.json";
        assertEquals("{\n\n}", Differ.generate(filepath1, filepath1));
    }
    @Test
    public void diffNotEmptyTest() throws Exception {
        String filepath1 = "src/test/resources/notEmptyFile1.json";
        String filepath2 = "src/test/resources/notEmptyFile2.json";
        assertEquals("{\n   follow: false\n"
            + " - host: hexlet.io\n"
            + " + host: hexlet.iooo\n"
            + "   proxy: 123.234.53.22\n"
            + "   timeout: 50\n}", Differ.generate(filepath1, filepath2));
    }
    @Test
    public void diffNotEmptyTest1() throws Exception {
        String filepath1 = "src/test/resources/emptyFile.json";
        String filepath2 = "src/test/resources/notEmptyFile1.json";
        assertEquals("{\n"
            + " + follow: false\n"
            + " + host: hexlet.ioo\n"
            + " + proxy: 123.234.53.22\n"
            + " + timeout: 50\n"
            + "}", Differ.generate(filepath1, filepath2));
    }
}


