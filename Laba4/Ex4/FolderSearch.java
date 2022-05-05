package Ex4;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSearch extends RecursiveTask<List<String>> {
    private final Folder folder;
    private final List<String> findedKeys;

    FolderSearch(Folder folder, List<String> searchedWords) {
        super();
        this.folder = folder;
        this.findedKeys = searchedWords;
    }

    @Override
    protected List<String> compute() {
        List<RecursiveTask<List<String>>> forkjoin = new LinkedList<>();

        for (Folder subFolder : folder.getFolders()) {
            FolderSearch text = new FolderSearch(subFolder, findedKeys);
            forkjoin.add(text);
            text.fork();
        }
        for (Document document : folder.getDocuments()) {
            DocumentSearch text = new DocumentSearch(document, findedKeys);
            forkjoin.add(text);
            text.fork();
        }

        List<String> result = new ArrayList<>();
        for (RecursiveTask<List<String>> text : forkjoin) {
            List<String> textResult = text.join();
            if (textResult == null) {
                continue;
            }
            result.addAll(textResult);
        }
        return result;
    }
}
