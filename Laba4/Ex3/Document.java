package Ex3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Document {
    public final String pathName;
    private final List<String> rows;


    Document(List<String> rows, String pathName) {
        this.rows = rows;
        this.pathName = pathName;
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
