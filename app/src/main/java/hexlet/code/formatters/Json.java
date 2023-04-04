package hexlet.code.formatters;


import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Parser;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
public class Json {
    public static String format(Map<String, Map<String, Object>> data, List<String> keys)
        throws JsonProcessingException {
        Map<String, Map<String, Object>> result = new HashMap<>();
        for (String property: keys) {
            result.put(property, data.get(property));
        }
        return Parser.makeJson(result);
    }
}
