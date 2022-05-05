package Ex4;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DocumentSearch extends RecursiveTask<List<String>> {
    private final Document document;
    private final List<String> findedWords;

    DocumentSearch(Document document, List<String> findedWords) {
        super();
        this.document = document;
        this.findedWords = findedWords;
    }

    @Override
    protected List<String> compute() {
        return WordCounter.meetingCount(document, findedWords) == 0
                ? null
                : Collections.singletonList(document.pathName);
    }
}
