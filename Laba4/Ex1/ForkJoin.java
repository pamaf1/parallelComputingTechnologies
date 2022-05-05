package Ex1;
import java.util.*;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinTask;


public class ForkJoin extends RecursiveTask<HashMap<String, Integer>> {
    private final List<String> words;
    private final HashMap<String, Integer> countOfWord = new HashMap<>();
    private static final int limit = 1000;

    public ForkJoin(List<String> words) {
        this.words = words;
    }

    @Override
    protected HashMap<String, Integer> compute() {
        if (this.words.size() > limit) {
            ForkJoinTask.invokeAll(subText()).stream().map(ForkJoinTask::join).flatMap(map -> map.entrySet().stream())
                    .forEach(entry -> this.countOfWord.putIfAbsent(entry.getKey(), entry.getValue()));
            return this.countOfWord;
        } else {
            return counting(words);
        }
    }

    private Collection<ForkJoin> subText() {
        List<ForkJoin> dividedText = new ArrayList<>();
        dividedText.add(new ForkJoin(words.subList(0, words.size() / 2)));
        dividedText.add(new ForkJoin(words.subList(words.size() / 2, words.size())));
        return dividedText;
    }

    private HashMap<String, Integer> counting(List<String> words) {
        for (String word : words) {
            this.countOfWord.putIfAbsent(word, word.length());
        }
        return this.countOfWord;
    }
}
