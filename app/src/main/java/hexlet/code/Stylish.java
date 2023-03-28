package hexlet.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(Map<String, List<String>> data) {
        List<String> sortedKeys = new ArrayList<>(data.keySet());
        Collections.sort(sortedKeys);
        List<String> result = new ArrayList<>();
        if (data.isEmpty()) {
            return "{\n\n}";
        }

        result.add("{");
        for (String item: sortedKeys) {
            if (data.get(item).get(1).isEmpty()) {
                result.add(" ".repeat(2) + "- " + item + ": " + data.get(item).get(0));
            }
            if (!data.get(item).get(0).equals(data.get(item).get(1)) && !data.get(item).get(0).isEmpty()
                && !data.get(item).get(1).isEmpty()) {
                result.add(" ".repeat(2) + "- " + item + ": " + data.get(item).get(0));
                result.add(" ".repeat(2) + "+ " + item + ": " + data.get(item).get(1));
            } else {
                if (data.get(item).get(0).equals(data.get(item).get(1))) {
                    result.add(" ".repeat(4) + item + ": " + data.get(item).get(0));
                }
            }
            if (data.get(item).get(0).isEmpty()) {
                result.add(" ".repeat(2) + "+ " + item + ": " + data.get(item).get(1));
            }
        }
        result.add("}");
        return String.join("\n", result);
    }
}
