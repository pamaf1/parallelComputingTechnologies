package Ex2;
import java.util.concurrent.ForkJoinPool;
import Ex2.help.Matrix;
import Ex2.foxAlg.*;

public class Run {
    public static void main(String[] args) {
        RunEx();
    }

    public static void RunEx() {
        int size0 = 1000;
        int size1 = 1000;

        Matrix first = new Matrix(size0, size1);
        Matrix second = new Matrix(size0, size1);

        first.generateRandom();
        second.generateRandom();

        int nThread = Runtime.getRuntime().availableProcessors();

        long currentTime = System.nanoTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool(nThread);
        Matrix res = forkJoinPool.invoke(new FoxAlg(first, second, 6));
        currentTime = System.nanoTime() - currentTime;

        System.out.println("Execution time Fox Algorithm (from laba2): " + 521);
        System.out.println("Execution time FoxForkJoin Algorithm: " + currentTime / 1_000_000);
        System.out.println("SpeedUp FoxForkJoin: " + (double) 521 / (currentTime / 1_000_000));
        }
}

