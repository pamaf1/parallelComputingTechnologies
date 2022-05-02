package foxAlg;

import Laba2.HelpMat;
import help.Matrix;
import java.util.ArrayList;
import java.util.concurrent.*;

public class FoxAlg implements HelpMat {
    Matrix first;
    Matrix second;
    private int nThread;

    public FoxAlg(Matrix first, Matrix second, int nThread) {
        this.first = first;
        this.second = second;
        this.nThread = nThread;
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

    @Override
    public Matrix multiply() {
        Matrix res = new Matrix(first.getSize0(), second.getSize1());

        if (!(first.getSize0() == first.getSize1()
                & second.getSize0() == second.getSize1()
                & first.getSize0() == first.getSize0())) {
            try {
                throw new Exception("");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        this.nThread = Math.min(this.nThread, first.getSize0());
        this.nThread = findNearestDivider(this.nThread, first.getSize0());
        int step = first.getSize0() / this.nThread;

        ExecutorService exec = Executors.newFixedThreadPool(this.nThread);
        ArrayList<Future> threads = new ArrayList<>();

        int[][] matrixSizes1 = new int[nThread][nThread];
        int[][] matrixSizes2 = new int[nThread][nThread];

        int I = 0;
        for (int i = 0; i < nThread; i++) {
            int J = 0;
            for (int j = 0; j < nThread; j++) {
                matrixSizes1[i][j] = I;
                matrixSizes2[i][j] = J;
                J += step;
            }
            I += step;
        }

        for (int l = 0; l < nThread; l++) {
            for (int i = 0; i < nThread; i++) {
                for (int j = 0; j < nThread; j++) {
                    int I0 = matrixSizes1[i][j];
                    int J0 = matrixSizes2[i][j];

                    int I1 = matrixSizes1[i][(i + l) % nThread];
                    int J1 = matrixSizes2[i][(i + l) % nThread];

                    int I2 = matrixSizes1[(i + l) % nThread][j];
                    int J2 = matrixSizes2[(i + l) % nThread][j];

                    FoxAlgRun t = new FoxAlgRun(copied(first, I1, J1, step), copied(second, I2, J2, step), res, I0, J0);
                    threads.add(exec.submit(t));
                }
            }
        }

        for (Future mapFuture : threads) {
            try {
                mapFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        exec.shutdown();

        return res;
    }

    private Matrix copied(Matrix matrix, int i, int j, int size) {
        Matrix block = new Matrix(size, size);
        for (int k = 0; k < size; k++) {
            System.arraycopy(matrix.matrix[k + i], j, block.matrix[k], 0, size);
        }
        return block;
    }
}
