package stripAlg;
import help.Matrix;
import java.util.concurrent.Callable;

public class StripAlgRun implements Callable<double[]>{
    private final double[] row;
    private Matrix second;
    private double[] result;

    public StripAlgRun(double[] row, Matrix second) {
        this.row = row;
        this.second = second;
        this.result = new double[second.getSize1()];
    }

    @Override
    public double[] call() {
        for (int j = 0; j < second.getSize1(); j++) {
            for (int i = 0; i < row.length; i++) {
                result[j] += row[i] * second.matrix[i][j];
            }
        }
        return this.result;
    }
}
