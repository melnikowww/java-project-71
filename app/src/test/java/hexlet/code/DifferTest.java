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
            + "  - setting2: 200\n"
            + "  + setting2: 201\n"
            + "    setting3: true\n"
            + "}", Differ.generate(filepath1, filepath2, "stylish"));
    }

    @Test
    public void plainFormatterTest() throws Exception {
        String filepath1 = "src/test/resources/notEmpty1.yml";
        String filepath2 = "src/test/resources/notEmpty2.yml";
        assertEquals("\n"
                + "Property 'chars1' was updated. From [complex value] to [complex value]\n"
                + "Property 'numb2' was added with value: [complex value]\n"
                + "Property 'numbers1' was removed\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Some_value'\n"
                + "Property 'setting2' was updated. From 200 to 201\n",
            Differ.generate(filepath1, filepath2, "plain"));
    }

    @Test
    public void plainFormatterTestEmpty() throws Exception {
        String filepath1 = "src/test/resources/empty.yml";
        assertEquals("\n"
               + "\n", Differ.generate(filepath1, filepath1, "plain"));
    }
    @Test
    public void jsonFormatterTestEmpty() throws Exception {
        String filepath1 = "src/test/resources/empty.yml";
        assertEquals("{\n"
            + "\n}", Differ.generate(filepath1, filepath1, "json"));
    }
    @Test
    public void jsonFormatterTest() throws Exception {
        String filepath1 = "src/test/resources/notEmptyFile1.json";
        String filepath2 = "src/test/resources/notEmptyFile2.json";
        assertEquals("{\n"
            + "  \"follow\": {\n"
            + "    \"removed\": false\n"
            + "  },\n"
            + "  \"host\": {\n"
            + "    \"removed\": \"hexlet.io\",\n"
            + "    \"added\": \"hexlet.iooo\"\n"
            + "  },\n"
            + "  \"proxy\": \"123.234.53.22\",\n"
            + "  \"timeout\": {\n"
            + "    \"added\": 50\n"
            + "  }\n"
            + "}", Differ.generate(filepath1, filepath2, "json"));
    }
}


