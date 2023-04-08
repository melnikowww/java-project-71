package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class Plain {
    public static String format(Map<String, Map<String, Object>> data) {

        List<String> result = new ArrayList<>();

        for (String property: data.keySet()) {
            if (data.get(property).containsKey("removed") && data.get(property).containsKey("added")) {
                result.add("Property '" + property + "' was updated. From "
                    + makePlainString(data.get(property).get("removed"))
                    + " to " + makePlainString(data.get(property).get("added")));
            } else if (data.get(property).containsKey("removed") && !data.get(property).containsKey("added")) {
                result.add("Property '" + property + "' was removed");
            } else if (!data.get(property).containsKey("removed") && data.get(property).containsKey("added")) {
                result.add("Property '" + property + "' was added with value: "
                    + makePlainString(data.get(property).get("added")));
            }
        }
        return String.join("\n", result);
    }
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
}
