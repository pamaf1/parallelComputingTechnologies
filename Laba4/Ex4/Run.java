package Ex4;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Run {
    public static void main(String[] args) throws IOException {
        String path = "files";
        List<String> keyWords = Arrays.asList("information", "java");

        WordCounter wordCounter = new WordCounter();
        Folder folder = Folder.fromDirectory(new File(path));

        for (String documentName : wordCounter.meetingCountFork(folder, keyWords))
            System.out.println(documentName);
    }
}
