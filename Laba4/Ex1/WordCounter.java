package Ex1;
import java.util.List;
import java.util.HashMap;


public class WordCounter {
    public static HashMap<String, Integer> counting(List<String> words) {
        HashMap<String, Integer> countOfWord = new HashMap<>();

        for (String word : words) {
            countOfWord.putIfAbsent(word, word.length());
        }

        return countOfWord;
    }
}
