package hexlet.code;

import java.nio.file.Path;
//import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class GenDiff {
    public static Map<String, Map<String, Object>> genDiff(List<String> keys, Map<String, Object> data1, Path path1,
                                                    Map<String, Object> data2, Path path2) throws Exception {

        Map<String, Map<String, Object>> resultMap = new HashMap<>();

        for (String item: keys) {
            if (!data1.containsKey(item)) {
                resultMap.put(item, Map.of("added", Parser.makeItem(path2, item)));
            }
            if (!data2.containsKey(item)) {
                resultMap.put(item, Map.of("removed", Parser.makeItem(path1, item)));
            }
            if (data1.containsKey(item) && data2.containsKey(item)) {
                if (!(noNull(data1.get(item))).equals(noNull(data2.get(item)))) {
                    resultMap.put(item, Map.of("removed", Parser.makeItem(path1, item),
                        "added", Parser.makeItem(path2, item)));
                } else {
                    resultMap.put(item, Map.of("unchanged", Parser.makeItem(path2, item)));
                }
            }
        }

        return resultMap;
    }
    public static Object noNull(Object data) {
        return Objects.requireNonNullElse(data, "null");
    }
}
