package formatters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(Map<String, List<Object>> data) {
        List<String> sortedKeys = new ArrayList<>(data.keySet());
        Collections.sort(sortedKeys);
        List<String> result = new ArrayList<>();
        if (data.isEmpty()) {
            return "{\n\n}";
        }
        result.add("{");
        for (String item: sortedKeys) {
            if (makeString(data, item, 1).isEmpty()) {
                result.add(" ".repeat(2) + "- " + item + ": " + data.get(item).get(0));
            }
            if (!makeString(data, item, 0).equals(makeString(data, item, 1))
                && !makeString(data, item, 0).isEmpty() && !makeString(data, item, 1).isEmpty()) {
                result.add(" ".repeat(2) + "- " + item + ": " + makeString(data, item, 0));
                result.add(" ".repeat(2) + "+ " + item + ": " + makeString(data, item, 1));
            } else {
                if (makeString(data, item, 0).equals(makeString(data, item, 1))) {
                    result.add(" ".repeat(4) + item + ": " + data.get(item).get(0));
                }
            }
            if (makeString(data, item, 0).isEmpty()) {
                result.add(" ".repeat(2) + "+ " + item + ": " + data.get(item).get(1));
            }
        }
        result.add("}");
        return String.join("\n", result);
    }
    public static String makeString(Map<String, List<Object>> it, String item, int key) {
        return it.get(item).get(key).toString();
    }
}
