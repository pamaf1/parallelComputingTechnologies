package Ex3;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class WordCounter {

    public static String[] splitWords(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    public static Set<String> getFullText(Document document) {
        Set<String> fullText = new HashSet<>();
        for (String line : document.getRows()) {
            fullText.addAll(Arrays.asList(splitWords(line)));
        }
        return fullText;
    }

    private final ForkJoinPool forkJoinPool =
            new ForkJoinPool(Runtime.getRuntime().availableProcessors());

    public Set<String> getCommonWords(Folder folder) {
        return forkJoinPool.invoke(new FolderSearch(folder));
    }

}
