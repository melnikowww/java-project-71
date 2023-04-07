package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String chooseFormat(Map<String, Map<String, Object>> data, String format)
        throws JsonProcessingException {
        return switch (format) {
            case "plain" -> Plain.format(data);
            case "json" -> Json.format(data);
            case "stylish" -> Stylish.format(data);
            default -> throw new Error("Unknown format: " + format + "!");
        };
    }
}
