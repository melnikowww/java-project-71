package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String fpath1, String fpath2, String format) throws Exception {
        Path path1 = makePath(fpath1);
        Path path2 = makePath(fpath2);
        String extension1 = getDataFormat(fpath1);
        String extension2 = getDataFormat(fpath2);

        Map<String, Object> dataMap1 = Parser.parse(Files.readString(path1), extension1);
        Map<String, Object> dataMap2 = Parser.parse(Files.readString(path2), extension2);

        Map<String, Map<String, Object>> resultMap = GenDiff.genDiff(dataMap1, dataMap2);
        return Formatter.chooseFormat(resultMap, format);
    }
    public static String generate(String fpath1, String fpath2) throws Exception {
        return generate(fpath1, fpath2, "stylish");
    }
    public static Path makePath(String fpath) throws Exception {
        Path path = Paths.get(fpath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist!");
        }
        return path;
    }
    public static String getDataFormat(String path) {
        int index = path.lastIndexOf(".");
        return index > 0 ? path.substring(index + 1) : "";
    }
}
