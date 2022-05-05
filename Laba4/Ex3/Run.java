package Ex3;
import java.io.File;
import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        String path = "files/commonWords";

        WordCounter wordCounter = new WordCounter();
        Folder folder = Folder.fromDirectory(new File(path));

        System.out.println(wordCounter.getCommonWords(folder));
    }
}
