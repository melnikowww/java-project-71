package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;
    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }
    private static String readFixture(String fileName) throws IOException {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }
    @BeforeAll
    public static void beforeAll() throws Exception {
        resultPlain = readFixture("plainResult.txt");
        resultStylish = readFixture("resultStylish.txt");
        resultJson = readFixture("resultJson.txt");
    }
    @Test
    public void emptyTestYML() throws Exception {
        String filepath1 = "src/test/resources/empty.yml";
        assertEquals("{\n\n}", Differ.generate(filepath1, filepath1, "stylish"));
    }
    @Test
    public void stylishTestYML() throws Exception {
        String filepath1 = "src/test/resources/notEmpty1.yml";
        String filepath2 = "src/test/resources/notEmpty2.yml";
        assertEquals(resultStylish, Differ.generate(filepath1, filepath2, "stylish"));
    }
    @Test
    public void plainTestYML() throws Exception {
        String filepath1 = "src/test/resources/notEmpty1.yml";
        String filepath2 = "src/test/resources/notEmpty2.yml";
        assertEquals(resultPlain, Differ.generate(filepath1, filepath2, "plain"));
    }
    @Test
    public void jsonTestYML() throws Exception {
        String filepath1 = "src/test/resources/notEmpty1.yml";
        String filepath2 = "src/test/resources/notEmpty2.yml";
        assertEquals(resultJson, Differ.generate(filepath1, filepath2, "json"));
    }
    @Test
    public void defaultTestYML() throws Exception {
        String filepath1 = "src/test/resources/notEmpty1.yml";
        String filepath2 = "src/test/resources/notEmpty2.yml";
        assertEquals(resultStylish, Differ.generate(filepath1, filepath2));
    }
///////////////////////////////////////////////////////////////////////////
    @Test
    public void emptyJsonFormatterTest() throws Exception {
        String filepath1 = "src/test/resources/empty.yml";
        assertEquals("{}", Differ.generate(filepath1, filepath1, "json"));
    }
    @Test
    public void stylishTestJson() throws Exception {
        String filepath1 = "src/test/resources/notEmptyFile1.json";
        String filepath2 = "src/test/resources/notEmptyFile2.json";
        assertEquals(resultStylish, Differ.generate(filepath1, filepath2, "stylish"));
    }
    @Test
    public void plainTestJson() throws Exception {
        String filepath1 = "src/test/resources/notEmptyFile1.json";
        String filepath2 = "src/test/resources/notEmptyFile2.json";
        assertEquals(resultJson, Differ.generate(filepath1, filepath2, "json"));
    }
    @Test
    public void jsonTestJson() throws Exception {
        String filepath1 = "src/test/resources/notEmptyFile1.json";
        String filepath2 = "src/test/resources/notEmptyFile2.json";
        assertEquals(resultJson, Differ.generate(filepath1, filepath2, "json"));
    }
    @Test
    public void defaultTestJson() throws Exception {
        String filepath1 = "src/test/resources/notEmptyFile1.json";
        String filepath2 = "src/test/resources/notEmptyFile2.json";
        assertEquals(resultStylish, Differ.generate(filepath1, filepath2));
    }
}


