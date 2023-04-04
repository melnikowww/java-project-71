package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
//import java.util.List;
import java.util.Map;

public class Parser {
    public static Map<String, Object> makeMap(Path path) throws Exception {
        String content = Files.readString(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<>() { });
    }
    public static Object makeItem(Path path, String key) throws Exception {
        if (makeMap(path).get(key) != null) {
            return makeMap(path).get(key);
        } else {
            return "null";
        }
    }
    public static String makeJson(Map<String, Map<String, Object>> data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(data);
    }
}
