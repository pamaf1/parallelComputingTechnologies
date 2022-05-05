package Ex2.foxAlg;
import Ex2.help.Matrix;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

public class FoxAlg extends RecursiveTask<Matrix>{
    Matrix first;
    Matrix second;
    Matrix res;
    private final int step;
    private int nThread;
    private final int[][] I;
    private final int[][] J;

    public FoxAlg(Matrix first, Matrix second, int nThread) {
        this.first = first;
        this.second = second;
        this.res = new Matrix(first.getSize0(), second.getSize1());
        this.nThread = nThread;

        if (!(first.getSize0() == first.getSize1() & second.getSize0() == second.getSize1()
                & first.getSize0() == second.getSize0())) {
            try {
                throw new Exception("");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        this.nThread = Math.min(this.nThread, first.getSize0());
        this.nThread = findNearestDivider(this.nThread, first.getSize0());
        this.step = first.getSize0() / this.nThread;

        this.I = new int[this.nThread][this.nThread];
        this.J = new int[this.nThread][this.nThread];

        checking();
    }

    private int findNearestDivider(int s, int p) {
        int i = s;
        while (i > 1) {
            if (p % i == 0) break;
            if (i >= s) {
                i++;
            } else {
                i--;
            }
            if (i > Math.sqrt(p)) i = Math.min(s, p / s) - 1;
        }

        return i >= s ? i : i != 0 ? p / i : p;
    }

    public void checking() {
        int stepI = 0;
        for (int i = 0; i < nThread; i++) {
            int stepJ = 0;
            for (int j = 0; j < nThread; j++) {
                I[i][j] = stepI;
                J[i][j] = stepJ;
                stepJ += this.step;
            }
            stepI += this.step;
        }
    }

    private Matrix copied(Matrix matrix, int i, int j, int size) {
        Matrix block = new Matrix(size, size);
        for (int k = 0; k < size; k++) {
            System.arraycopy(matrix.matrix[k + i], j, block.matrix[k], 0, size);
        }
        return block;
    }

    @Override
    protected Matrix compute() {
        List<RecursiveTask<HashMap<String, Object>>> tasks = new ArrayList<>();

        for (int l = 0; l < nThread; l++) {
            for (int i = 0; i < nThread; i++) {
                for (int j = 0; j < nThread; j++) {
                    int stepI0 = I[i][j];
                    int stepJ0 = J[i][j];

                    int stepI1 = I[i][(i + l) % nThread];
                    int stepJ1 = J[i][(i + l) % nThread];

                    int stepI2 = I[(i + l) % nThread][j];
                    int stepJ2 = J[(i + l) % nThread][j];

                    FoxAlgForkJoin task =
                            new FoxAlgForkJoin(
                                    copied(first, stepI1, stepJ1, step),
                                    copied(second, stepI2, stepJ2, step),
                                    stepI0,
                                    stepJ0);

                    tasks.add(task);
                    task.fork();
                }
            }
        }

        for (RecursiveTask<HashMap<String, Object>> task : tasks) {
            HashMap<String, Object> r = task.join();

            Matrix blockRes = (Matrix) r.get("blockRes");
            int stepI = (int) r.get("stepI");
            int stepJ = (int) r.get("stepJ");

            for (int i = 0; i < blockRes.getSize0(); i++) {
                for (int j = 0; j < blockRes.getSize1(); j++) {
                    res.matrix[i + stepI][j + stepJ] += blockRes.matrix[i][j];
                }
            }
        }

        return this.res;
    }

}
