package Ex3;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class FolderSearch extends RecursiveTask<Set<String>>{
    private final Folder folder;

    FolderSearch(Folder folder) {
        super();
        this.folder = folder;
    }

    @Override
    protected Set<String> compute() {
        Set<String> result;
        List<RecursiveTask<Set<String>>> forks = new LinkedList<>();

        for (Folder subFolder : folder.getFolders()) {
            FolderSearch task = new FolderSearch(subFolder);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocuments()) {
            DocumentSearch task = new DocumentSearch(document);
            forks.add(task);
            task.fork();
        }

        if (forks.size() == 0) return new HashSet<>();

        result = forks.get(0).join();
        forks.remove(0);
        for (RecursiveTask<Set<String>> task : forks) {
            result.retainAll(task.join());
        }

        return result;
    }
}
