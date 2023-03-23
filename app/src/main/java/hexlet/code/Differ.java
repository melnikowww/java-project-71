package hexlet.code;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Differ {
    public static String generate(String fpath1, String fpath2) throws Exception {

        Map<String, String> map1 = makeMap(fpath1);
        Map<String, String> map2 = makeMap(fpath2);

        List<String> sortedKeys = new ArrayList<>(map1.keySet());
        for (String item:map2.keySet()) {
            if (!sortedKeys.contains(item)) {
                sortedKeys.add(item);
            }
        }
        Collections.sort(sortedKeys);

        List<String> result = new ArrayList<>();
        for (String item: sortedKeys) {
            if (!map2.containsKey(item)) {
                result.add(" - " + item + ": " + map1.get(item));
            } else {
                if (map1.containsKey(item)) {
                    if (!map1.get(item).equals(map2.get(item))) {
                        result.add(" - " + item + ": " + map1.get(item) + "\n" + " + " + item + ": " + map2.get(item));
                    } else {
                        result.add("   " + item + ": " + map2.get(item));
                    }
                }
            }
            if (!map1.containsKey(item)) {
                result.add(" + " + item + ": " + map2.get(item));
            }
        }
        return "{" + "\n" + String.join("\n", result) + "\n" + "}";
    }

    public static Path makePath(String fpath) throws Exception {
        Path path = Paths.get(fpath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist!");
        }
        return path;
    }
    public static Map<String, String> makeMap(String path) throws Exception {
        String content = Files.readString(makePath(path));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<>() { });
    }
}


