package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String fpath1, String fpath2, String format) throws Exception {
        Path path1 = makePath(fpath1);
        Path path2 = makePath(fpath2);

        Map<String, Object> dataMap1 = Parser.makeMap(path1);
        Map<String, Object> dataMap2 = Parser.makeMap(path2);

        List<String> keys = new ArrayList<>(dataMap1.keySet());
        for (String item: dataMap2.keySet()) {
            if (!keys.contains(item)) {
                keys.add(item);
            }
        }

        Collections.sort(keys);
        Map<String, Map<String, Object>> resultMap = GenDiff.genDiff(keys, dataMap1, path1, dataMap2, path2);
        return Formatter.chooseFormat(resultMap, format, keys);
    }
    public static Path makePath(String fpath) throws Exception {
        Path path = Paths.get(fpath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist!");
        }
        return path;
    }
}
