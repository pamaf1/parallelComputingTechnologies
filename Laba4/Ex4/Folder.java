package Ex4;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Folder {
    private final List<Folder> Folders;
    private final List<Document> documents;

    Folder(List<Folder> Folders, List<Document> documents) {
        this.Folders = Folders;
        this.documents = documents;
    }

    List<Folder> getFolders() {
        return this.Folders;
    }

    List<Document> getDocuments() {
        return this.documents;
    }

    public static Folder fromDirectory(File dir) throws IOException {
        List<Document> documents = new LinkedList<>();
        List<Folder> Folders = new LinkedList<>();
        for (File entry : Objects.requireNonNull(dir.listFiles())) {
            if (entry.isDirectory()) {
                Folders.add(Folder.fromDirectory(entry));
            } else {
                documents.add(Document.fromFile(entry));
            }
        }
        return new Folder(Folders, documents);
    }
}
