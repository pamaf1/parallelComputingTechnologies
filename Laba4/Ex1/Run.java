package Ex1;
import java.util.concurrent.ForkJoinPool;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Run {
    public static void main(String[] args) throws IOException {
        List<String> words = TextReader.getWordsFromText("files/text.txt");

        System.out.printf("Number of words in text: %d\n", words.size());

        long currentTime = System.nanoTime();
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        HashMap<String, Integer> result = pool.submit(new ForkJoin(words)).join();
        long currentTimeForkJoin = System.nanoTime() - currentTime;

        System.out.printf("Number of unique words in text: %d\n", result.keySet().size());
        System.out.printf(
                "Average length of word: %f\n",
                result.values().stream().mapToDouble(i -> i).sum()
                        / result.values().stream().mapToDouble(i -> i).count());

        System.out.printf("Execution time (ForkJoin): %d\n", currentTimeForkJoin);

        currentTime = System.nanoTime();
        result = WordCounter.counting(words);
        long currentTimeWithoutFork = System.nanoTime() - currentTime;

        System.out.printf("Execution time without ForkJoin: %d\n", currentTimeWithoutFork);

        System.out.printf("SpeedUp = %.2f\n", (double) currentTimeWithoutFork / currentTimeForkJoin);
    }
}
