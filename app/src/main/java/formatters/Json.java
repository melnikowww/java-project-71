package formatters;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Collection;

public class Json {
    public static String format(Map<String, List<Object>> data) {
        List<String> sortedKeys = new ArrayList<>(data.keySet());
        Collections.sort(sortedKeys);
        List<String> result = new ArrayList<>();
        if (data.isEmpty()) {
            return "{\n\n}";
        }
        for (String item: sortedKeys) {
            if (Stylish.makeString(data, item, 1).isEmpty()) {
//                result.add(" ".repeat(2) + "\"" + item + "\"" + ": " + makeItem(data.get(item).get(0)));
                result.add(" ".repeat(2) + "\"" + item + "\"" + ": " + makeJson(data.get(item).get(0), null));
            }
            if (!Stylish.makeString(data, item, 0).equals(Stylish.makeString(data, item, 1))
                && !Stylish.makeString(data, item, 0).isEmpty()
                && !Stylish.makeString(data, item, 1).isEmpty()) {

                result.add(" ".repeat(2) + "\"" + item + "\""  + ": "
                    + makeJson(data.get(item).get(0), data.get(item).get(1)));

            } else {
                if (Stylish.makeString(data, item, 0).equals(Stylish.makeString(data, item, 1))) {
                    result.add(" ".repeat(2) + "\"" + item + "\""  + ": " + makeItem(data.get(item).get(0)));
                }
            }
            if (Stylish.makeString(data, item, 0).isEmpty()) {
//                result.add(" ".repeat(2) + "\"" + item + "\"" + ": " + makeItem(data.get(item).get(1)));
                result.add(" ".repeat(2) + "\"" + item + "\"" + ": " + makeJson(null, data.get(item).get(1)));
            }
        }
        return "{\n" + String.join(",\n", result) + "\n}";
    }
    public static Object makeItem(Object it) {
        if (it instanceof Collection<?> || it instanceof Map<?, ?>) {
            return it;
        }
        if (it instanceof String && !it.equals("null")) {
            return "\"" + it + "\"";
        } else {
            return it.toString();
        }
    }
    public static String makeJson(Object first, Object second) {
        if (first == null) {
            return "{\n" + " ".repeat(4) +  "\"" + "added" + "\": "
                + makeItem(second) + "\n" + " ".repeat(2) + "}";
        }
        if (second == null) {
            return "{\n" + " ".repeat(4) +  "\"" + "removed" + "\": "
                + makeItem(first) + "\n" + " ".repeat(2) + "}";
        }
        return "{\n" + " ".repeat(4) + "\"" + "removed" + "\": " + makeItem(first) + ",\n"
            + " ".repeat(4) +  "\"" + "added" + "\": " + makeItem(second) + "\n" + " ".repeat(2) + "}";
    }
}
