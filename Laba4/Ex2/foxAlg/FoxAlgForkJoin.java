package Ex2.foxAlg;
import java.util.HashMap;
import java.util.concurrent.RecursiveTask;
import Ex2.help.Matrix;

public class FoxAlgForkJoin extends RecursiveTask<HashMap<String, Object>> {
    private final Matrix first;
    private final Matrix second;

    private final int I;
    private final int J;

    public FoxAlgForkJoin(Matrix first, Matrix second, int I, int J) {
        this.first = first;
        this.second = second;

        this.I = I;
        this.J = J;
    }

    private Matrix multiplyBlock() {
        Matrix blockRes = new Matrix(first.getSize1(), second.getSize0());
        for (int i = 0; i < first.getSize0(); i++) {
            for (int j = 0; j < second.getSize1(); j++) {
                for (int k = 0; k < first.getSize1(); k++) {
                    blockRes.matrix[i][j] += first.matrix[i][k] * second.matrix[k][j];
                }
            }
        }
        return blockRes;
    }

    @Override
    protected HashMap<String, Object> compute() {
        Matrix blockRes = multiplyBlock();

        HashMap<String, Object> result = new HashMap<>();
        result.put("blockRes", blockRes);
        result.put("stepI", I);
        result.put("stepJ", J);

        return result;
    }
}
