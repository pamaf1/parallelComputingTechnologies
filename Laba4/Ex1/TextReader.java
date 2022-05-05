package Ex1;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;


public class TextReader {
    public static List<String> getListOfWordsFromText(String text) {
        return Arrays.asList(text.trim().split("(\\s|\\p{Punct})+"));
    }

    public static List<String> getWordsFromText(String fileName) throws IOException {
        StringBuilder wordBuild = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(s -> wordBuild.append(s).append("\n"));
        }
        return TextReader.getListOfWordsFromText(wordBuild.toString());
    }

}
