package hexlet.code;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Differ {
    public static String generate(String fpath1, String fpath2, String format) throws Exception {

        List<String> keys = new ArrayList<>(Parser.makeMap(fpath1).keySet());
        for (String item:Parser.makeMap(fpath2).keySet()) {
            if (!keys.contains(item)) {
                keys.add(item);
            }
        }
        Map<String, List<String>> resultMap = new HashMap<>();

        for (String item: keys) {
            if (!Parser.makeMap(fpath2).containsKey(item)) {
                resultMap.put(item, List.of(Parser.makeItem(fpath1, item), ""));
            } else {
                if (Parser.makeMap(fpath1).containsKey(item)) {
                    resultMap.put(item, List.of(Parser.makeItem(fpath1, item), Parser.makeItem(fpath2, item)));
                }
            }
            if (!Parser.makeMap(fpath1).containsKey(item)) {
                resultMap.put(item, List.of("", Parser.makeItem(fpath2, item)));
            }
        }
        String res;
        switch (format) {
            default:
                res = Stylish.format(resultMap);
        }
        return res;
    }
}


