package Ex4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Document {
    private final List<String> rows;
    public final String pathName;

    Document(List<String> lines, String documentName) {
        this.rows = lines;
        this.pathName = documentName;
    }

    List<String> getRows() {
        return this.rows;
    }

    static Document fromFile(File file) throws IOException {
        List<String> rows = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String row = reader.readLine();
            while (row != null) {
                rows.add(row);
                row = reader.readLine();
            }
        }
        return new Document(rows, file.getAbsolutePath());
    }
}
