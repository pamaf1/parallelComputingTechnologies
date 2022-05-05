package Ex4;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class WordCounter {

    public static String[] splitWords(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    public static Long meetingCount(Document document, List<String> findedKeyWord) {
        long count = 0;
        for (String line : document.getRows()) {
            for (String key : splitWords(line)) {
                if (findedKeyWord.contains(key)) {
                    count = count + 1;
                }
            }
        }
        return count;
    }

    private final ForkJoinPool forkJoinPool =
            new ForkJoinPool(Runtime.getRuntime().availableProcessors());

    public List<String> meetingCountFork(Folder folder, List<String> searchedWords) {
        return forkJoinPool.invoke(new FolderSearch(folder, searchedWords));
    }

}
