package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.HashMap;

import java.util.Collections;

import java.util.LinkedHashMap;


import static hexlet.code.formatters.Util.makeMap;
//import static hexlet.code.formatters.Util.nullToNoNull;


public class GenDiff {
    public static Map<String, Map<String, Object>> genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        Map<String, Map<String, Object>> resultMap = new LinkedHashMap<>();

        List<String> keys = new ArrayList<>(data1.keySet());

        for (String item: data2.keySet()) {
            if (!keys.contains(item)) {
                keys.add(item);
            }
        }
        Collections.sort(keys);

        for (String item: keys) {
            if (!data1.containsKey(item)) {
                resultMap.put(item, makeMap("added", item, data2));
            } else if (data1.containsKey(item) && data2.containsKey(item)
                && data1.get(item) != null && (data1.get(item)).equals(data2.get(item))) {
                resultMap.put(item, makeMap("unchanged", item, data2));
            } else if (data1.containsKey(item) && data2.containsKey(item)
                && (data1.get(item) == null || !(data1.get(item)).equals(data2.get(item)))) {
                resultMap.put(item, makeMap(item, data1, data2));
            } else if (!data2.containsKey(item)) {
                resultMap.put(item, makeMap("removed", item, data1));
            }
        }
        return resultMap;
    }
}
