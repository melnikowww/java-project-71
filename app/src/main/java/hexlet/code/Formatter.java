package hexlet.code;

import formatters.Plain;
import formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String chooseFormat(Map<String, List<Object>> data, String format) {
        switch (format) {
            case "plain" :
                return Plain.format(data);
            default:
                return Stylish.format(data);
        }
    }
}
