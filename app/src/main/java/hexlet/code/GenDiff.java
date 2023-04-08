package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Collections;

import java.util.LinkedHashMap;



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
    public static Map<String, Object> makeMap(String status, String property, Map<String, Object> data) {
        Map<String, Object> result = new HashMap<>();
        switch (status) {
            case "added" -> result.put("added", data.get(property));
            case "removed" -> result.put("removed", data.get(property));
            case "unchanged" -> result.put("unchanged", data.get(property));
            default -> System.out.println("Unknown operation");
        }
        return result;
    }
    public static Map<String, Object> makeMap(String property,
                                              Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("removed", data1.get(property));
        result.put("added", data2.get(property));
        return result;
    }
}
