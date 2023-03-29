package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Path makePath(String fpath) throws Exception {
        Path path = Paths.get(fpath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist!");
        }
        return path;
    }
    public static Map<String, Object> makeMap(String path) throws Exception {
        String content = Files.readString(makePath(path));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<>() { });
    }
    public static Object makeItem(String path, String key) throws Exception {
        if (makeMap(path).get(key) != null) {
            return makeMap(path).get(key);
        } else {
            return "null";
        }
    }
}
