package stripAlg;
import Laba2.HelpMat;
import help.Matrix;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class StripAlg implements HelpMat{
    Matrix first;
    Matrix second;
    private final int nThread;

    public StripAlg(Matrix first, Matrix second, int nThread) {
        this.first = first;
        this.second = second;
        this.nThread = nThread;
    }

    @Override
    public Matrix multiply() {
        Matrix Result = new Matrix(first.getSize0(), second.getSize1());
        Result = make(Result);

        return Result;
    }

    public Matrix make(Matrix Result) {
        ExecutorService executor = Executors.newFixedThreadPool(this.nThread);

        List<Future<double[]>> list = new ArrayList<>();

        for (int i = 0; i < first.getSize0(); i++) {
            Callable<double[]> worker = new StripAlgRun(first.getRow(i), second);
            Future<double[]> submit = executor.submit(worker);
            list.add(submit);
        }

        for (int i = 0; i < list.size(); i++) {
            try {
                Result.matrix[i] = list.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        return Result;
    }
}
