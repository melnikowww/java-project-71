package formatters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Collection;

public class Plain {
    public static String format(Map<String, List<Object>> data) {
        List<String> sortedKeys = new ArrayList<>(data.keySet());
        Collections.sort(sortedKeys);
        List<String> result = new ArrayList<>();
        if (data.isEmpty()) {
            return "\n\n";
        }
        result.add("");
        for (String item: sortedKeys) {
            if (data.get(item).get(1).toString().isEmpty()) {
                result.add("Property '" + item + "' was removed");
            }

            if (!data.get(item).get(0).equals(data.get(item).get(1)) && !data.get(item).get(0).toString().isEmpty()
                && !data.get(item).get(1).toString().isEmpty()) {
                result.add("Property '" + item + "' was updated. From " + makeString(data.get(item).get(0)) + " to "
                    + makeString(data.get(item).get(1)));
            }

            if (data.get(item).get(0).toString().isEmpty()) {
                result.add("Property '" + item + "' was added with value: " + makeString(data.get(item).get(1)));
            }
        }
        result.add("");
        return String.join("\n", result);
    }

    public static String makeString(Object it) {
        if (it instanceof Collection<?> || it instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (it instanceof String && !it.equals("null")) {
            return "'" + it + "'";
        } else {
            return it.toString();
        }
    }
}
