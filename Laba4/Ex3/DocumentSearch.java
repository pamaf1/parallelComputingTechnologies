package Ex3;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class DocumentSearch extends RecursiveTask<Set<String>>{
    private final Document document;

    DocumentSearch(Document document) {
        super();
        this.document = document;
    }

    @Override
    protected Set<String> compute() {
        return WordCounter.getFullText(document);
    }
}
