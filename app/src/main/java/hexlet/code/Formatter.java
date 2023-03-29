package hexlet.code;

import formatters.Json;
import formatters.Plain;
import formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String chooseFormat(Map<String, List<Object>> data, String format) {
        return switch (format) {
            case "plain" -> Plain.format(data);
            case "json" -> Json.format(data);
            default -> Stylish.format(data);
        };
    }
}
