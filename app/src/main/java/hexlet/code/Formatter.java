package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

//import java.util.List;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String chooseFormat(Map<String, Map<String, Object>> data, String format, List<String> keys)
        throws JsonProcessingException {
        return switch (format) {
            case "plain" -> Plain.format(data, keys);
            case "json" -> Json.format(data, keys);
            case "stylish" -> Stylish.format(data, keys);
            default -> throw new Error("Unknown format: " + format + "!");
        };
    }
}
