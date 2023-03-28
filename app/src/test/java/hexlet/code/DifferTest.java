package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void diffEmptyTestYAML() throws Exception {
        String filepath1 = "src/test/resources/empty.yml";
        assertEquals("{\n\n}", Differ.generate(filepath1, filepath1, "stylish"));
    }
    @Test
    public void diffNotEmptyTest1YAML() throws Exception {
        String filepath1 = "src/test/resources/notEmpty1.yml";
        String filepath2 = "src/test/resources/notEmpty2.yml";
        assertEquals("{\n"
            + "  - chars1: [a, b, c]\n"
            + "  + chars1: [a, b, d]\n"
            + "    default: null\n"
            + "  + numb2: [22, 33, 44, 55]\n"
            + "  - numbers1: [1, 2, 3, 4]\n"
            + "  - setting1: Some value\n"
            + "  + setting1: Some_value\n"
            + "    setting2: 200\n"
            + "    setting3: true\n"
            + "}", Differ.generate(filepath1, filepath2, "stylish"));
    }
    @Test
    public void parsingTest() throws Exception {
        String filepath1 = "src/test/resources/file1.yml";
        assertEquals("Some value", Parser.makeItem(filepath1, "setting1"));
        assertEquals("200", Parser.makeItem(filepath1, "setting2"));
        assertEquals("true", Parser.makeItem(filepath1, "setting3"));
        assertEquals("[1, 2, 3, 4]", Parser.makeItem(filepath1, "numbers1"));
        assertEquals("null", Parser.makeItem(filepath1, "default"));
    }

}


