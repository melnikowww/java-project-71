package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

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
    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest1(String format) throws Exception {
        String filePath1 = "src/test/resources/file1." + format;
        String filePath2 = "src/test/resources/file2." + format;

        assertThat(Differ.generate(filePath1, filePath2))
            .isEqualTo(resultStylish);

        assertThat(Differ.generate(filePath1, filePath2, "stylish"))
            .isEqualTo(resultStylish);

        assertThat(Differ.generate(filePath1, filePath2, "plain"))
            .isEqualTo(resultPlain);

        String actualJson = Differ.generate(filePath1, filePath2, "json");
        assertThat(actualJson).isEqualTo(resultJson);
    }
}


