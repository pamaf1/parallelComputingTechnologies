package foxAlg;
import help.Matrix;

public class FoxAlgRun extends Thread{
    private final Matrix first;
    private final Matrix second;
    private final Matrix res;
    private final int I;
    private final int J;

    public FoxAlgRun(Matrix first, Matrix second, Matrix res, int I, int J) {
        this.first = first;
        this.second = second;
        this.res = res;
        this.I = I;
        this.J = J;
    }

    @Override
    public void run() {
        Matrix endRes = multiplyBlock();

        for (int i = 0; i < endRes.getSize0(); i++) {
            for (int j = 0; j < endRes.getSize1(); j++) {
                res.matrix[i + I][j + J] += endRes.matrix[i][j];
            }
        }

    }

    private Matrix multiplyBlock() {
        Matrix endRes = new Matrix(first.getSize1(), second.getSize0());
        for (int i = 0; i < first.getSize0(); i++) {
            for (int j = 0; j < second.getSize1(); j++) {
                for (int k = 0; k < first.getSize1(); k++) {
                    endRes.matrix[i][j] += first.matrix[i][k] * second.matrix[k][j];
                }
            }
        }
        return endRes;
    }
}
