import help.Matrix;
import stripAlg.*;
import foxAlg.*;
import java.util.HashMap;
import java.util.Map;

public class Run {
    public static void main(String[] args) {
        RunEx();
//        sizeMatrixExperiment();
//        threadExperiment();
    }

    public static void RunEx(){
        int size0 = 1000;
        int size1 = 1000;

        Matrix first = new Matrix(size0, size1);
        Matrix second = new Matrix(size0, size1);

        first.generateRandom();
        second.generateRandom();
        int nThread = Runtime.getRuntime().availableProcessors();

        StripAlg sa = new StripAlg(first, second , nThread);
        FoxAlg fa = new FoxAlg(first, second, nThread);

        long currentTime = System.nanoTime();
        Matrix res = sa.multiply();
        currentTime = System.nanoTime() - currentTime;

//        print matrix      \\

//        res.print();
        System.out.println("Striped Algorithm worked with time: " + currentTime / 1_000_000);

        currentTime = System.nanoTime();
        res = fa.multiply();
        currentTime = System.nanoTime() - currentTime;

//        res.print();
        System.out.println("Fox Algorithm worked with time: " + currentTime / 1_000_000);


    }

    public static void sizeMatrixExperiment() {
        int nThread = Runtime.getRuntime().availableProcessors();

        int[] sizesArray = new int[] {10, 100, 1000, 2000};
        Map<Integer, Long> resultTimeStrip = new HashMap<>();
        Map<Integer, Long> resultTimeFox = new HashMap<>();

        for (int size : sizesArray) {
            Matrix first = new Matrix(size, size);
            Matrix second = new Matrix(size, size);

            first.generateRandom();
            second.generateRandom();

            StripAlg sa = new StripAlg(first, second, nThread);
            long temporary = 0;
            long currTime = System.nanoTime();
            Matrix res = sa.multiply();
            temporary += System.nanoTime() - currTime;
            resultTimeStrip.put(size, temporary / 1_000_000);

            FoxAlg fa = new FoxAlg(first, second, nThread);
            temporary = 0;
            currTime = System.nanoTime();
            res = fa.multiply();
            temporary += System.nanoTime() - currTime;
            resultTimeFox.put(size, temporary / 1_000_000);
        }

        System.out.printf("Size of matrix:");
        for (int size : sizesArray) {
            System.out.print(size);
            System.out.print(" ");
        }

        System.out.println();

        System.out.printf("Striped Algorithm worked with time:");
        for (int size : sizesArray) {
            System.out.print(resultTimeStrip.get(size));
            System.out.print(" ");
        }

        System.out.println();

        System.out.print("Fox Algorithm worked with time:");
        for (int size : sizesArray) {
            System.out.print(resultTimeFox.get(size));
            System.out.print(" ");
        }

    }

    public static void threadExperiment() {
        int size0 = 1000;
        int size1 = 1000;

        int[] nStriped = new int[] {5, 10, 15, 20, 25, 50};
        int[] nFox = new int[] {5, 10, 15, 20, 25, 50};
        Map<Integer, Long> resultTimeStrip = new HashMap<>();
        Map<Integer, Long> resultTimeFox = new HashMap<>();

        Matrix first = new Matrix(size0, size1);
        Matrix second = new Matrix(size0, size1);

        first.generateRandom();
        second.generateRandom();

        for (int nThread : nStriped) {
            StripAlg sa = new StripAlg(first, second, nThread);
            long temporary = 0;
            long currTime = System.nanoTime();
            Matrix res = sa.multiply();
            temporary += System.nanoTime() - currTime;
            resultTimeStrip.put(nThread, temporary / 1_000_000);
        }

        for (int nThread : nFox) {
            FoxAlg fa = new FoxAlg(first, second, nThread);
            long temporary = 0;
            long currTime = System.nanoTime();
            Matrix res = fa.multiply();
            temporary += System.nanoTime() - currTime;
            resultTimeFox.put(nThread, temporary / 1_000_000);
        }

        System.out.print("Number of thread Stripped:");
        for (int size : nStriped) {
            System.out.print(size);
            System.out.print(" ");
        }

        System.out.println();

        System.out.print("Time Stripped:");
        for (int size : nStriped) {
            System.out.print(resultTimeStrip.get(size));
            System.out.print(" ");
        }
        System.out.println("\n");

        System.out.print("Number of thread Fox:");
        for (int size : nFox) {
            System.out.print(size);
            System.out.print(" ");
        }

        System.out.println();

        System.out.print("Time Fox:");
        for (int key : nFox) {
            System.out.print(resultTimeFox.get(key));
            System.out.print(" ");
        }
    }

}
