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

        List<String> sortedKeys = new ArrayList<>(makeMap(fpath1).keySet());
        for (String item:makeMap(fpath2).keySet()) {
            if (!sortedKeys.contains(item)) {
                sortedKeys.add(item);
            }
        }
        Collections.sort(sortedKeys);

        List<String> result = new ArrayList<>();
        for (String item: sortedKeys) {
            if (!makeMap(fpath2).containsKey(item)) {
                result.add(" - " + item + ": " + makeMap(fpath1).get(item));
            } else {
                if (makeMap(fpath1).containsKey(item)) {
                    if (!makeMap(fpath1).get(item).equals(makeMap(fpath2).get(item))) {
                        result.add(" - " + item + ": " + makeMap(fpath1).get(item) + "\n" + " + " + item + ": " + makeMap(fpath2).get(item));
                    } else {
                        result.add("   " + item + ": " + makeMap(fpath2).get(item));
                    }
                }
            }
            if (!makeMap(fpath1).containsKey(item)) {
                result.add(" + " + item + ": " + makeMap(fpath2).get(item));
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


