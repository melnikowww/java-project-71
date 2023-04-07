package hexlet.code.formatters;

import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;
public class Util {
    public static String makePlainString(Object it) {
        if (it instanceof Collection<?> || it instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (it == null) {
            return "null";
        }
        if (it instanceof String) {
            return "\'" + it + "\'";
        } else {
            return it.toString();
        }
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
