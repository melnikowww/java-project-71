package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static hexlet.code.formatters.Util.makePlainString;

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
}
