package hexlet.code.formatters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(Map<String, Map<String, Object>> data) {
        int smallSpace = 2;
        int tab = 4;
        List<String> result = new ArrayList<>();
        if (data.isEmpty()) {
            return "{\n\n}";
        }
        result.add("{");
        for (String property: data.keySet()) {
            if (data.get(property).containsKey("removed") && !data.get(property).containsKey("added")) {
                result.add(" ".repeat(smallSpace) + "- " + property + ": "
                    + data.get(property).get("removed"));
            }
            if (!data.get(property).containsKey("removed") && data.get(property).containsKey("added")) {
                result.add(" ".repeat(smallSpace) + "+ " + property + ": "
                    + data.get(property).get("added"));
            }
            if (data.get(property).containsKey("unchanged")) {
                result.add(" ".repeat(tab) + property + ": "
                    + data.get(property).get("unchanged"));
            }
            if (data.get(property).containsKey("removed") && data.get(property).containsKey("added")) {
                result.add(" ".repeat(smallSpace) + "- " + property + ": "
                    + data.get(property).get("removed"));
                result.add(" ".repeat(tab) + "+ " + property + ": "
                    + data.get(property).get("added"));
            }
        }
        result.add("}");
        return String.join("\n", result);
    }
}
