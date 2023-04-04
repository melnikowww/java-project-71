package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;
//import java.util.Collections;

public class Plain {
    public static String format(Map<String, Map<String, Object>> data, List<String> keys) {
        List<String> result = new ArrayList<>();

        for (String property: keys) {
            if (data.get(property).containsKey("removed") && data.get(property).containsKey("added")) {
                result.add("Property '" + property + "' was updated. From "
                    + makeString(data.get(property).get("removed"))
                    + " to " + makeString(data.get(property).get("added")));
            }

            if (data.get(property).containsKey("removed") && !data.get(property).containsKey("added")) {
                result.add("Property '" + property + "' was removed");
            }

            if (!data.get(property).containsKey("removed") && data.get(property).containsKey("added")) {
                result.add("Property '" + property + "' was added with value: "
                    + makeString(data.get(property).get("added")));
            }
        }

        return String.join("\n", result);
    }

    public static String makeString(Object it) {
        if (it instanceof Collection<?> || it instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (it instanceof String && !it.equals("null")) {
            return "'" + it + "'";
        }
        if (it == null) {
            return "null";
        } else {
            return it.toString();
        }
    }
}
