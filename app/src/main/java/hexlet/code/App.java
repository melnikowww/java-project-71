package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
    mixinStandardHelpOptions = true,
    description = "Compares two configuration files and shows a difference.",
    headerHeading = "\n")
class App implements Callable<Integer> {

    @Parameters(paramLabel = "filepath1", index = "0", description = "path to first file.")
    private String filepath1;
    @Parameters(paramLabel = "filepath2", index = "1", description = "path to second file.")
    private String filepath2;

    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]")
    File archive;
    @Override
    public Integer call() throws Exception {
        try {
            System.out.println(Differ.generate(filepath1, filepath2));
        } catch (Exception e) {
            System.out.println("Нет файлика то такого...");
        }

        return 0;
    }
    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
