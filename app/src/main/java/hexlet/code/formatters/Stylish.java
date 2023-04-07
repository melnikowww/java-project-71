package hexlet.code.formatters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import static hexlet.code.formatters.Util.makeStylishString;

public class Stylish {
    public static String format(Map<String, Map<String, Object>> data) {
        List<String> result = new ArrayList<>();
        if (data.isEmpty()) {
            return "{\n\n}";
        }
        result.add("{");
        for (String property: data.keySet()) {
            if (data.get(property).containsKey("removed") && !data.get(property).containsKey("added")) {
                result.add(" ".repeat(2) + "- " + property + ": "
                    + data.get(property).get("removed"));
            }
            if (!data.get(property).containsKey("removed") && data.get(property).containsKey("added")) {
                result.add(" ".repeat(2) + "+ " + property + ": "
                    + data.get(property).get("added"));
            }
            if (data.get(property).containsKey("unchanged")) {
                result.add(" ".repeat(4) + property + ": "
                    + data.get(property).get("unchanged"));
            }
            if (data.get(property).containsKey("removed") && data.get(property).containsKey("added")) {
                result.add(" ".repeat(2) + "- " + property + ": "
                    + data.get(property).get("removed"));
                result.add(" ".repeat(2) + "+ " + property + ": "
                    + data.get(property).get("added"));
            }
        }
        result.add("}");
        return String.join("\n", result);
    }
}
