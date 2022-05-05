package Ex2.help;

public class Matrix {
    public double[][] matrix;
    private final int size0;
    private final int size1;

    public Matrix(int size0, int size1) {
        this.matrix = new double[size0][size1];
        this.size0 = size0;
        this.size1 = size1;
    }

    public void print() {
        for (int i = 0; i < this.size0; i++) {
            for (int j = 0; j < this.size1; j++) {
                System.out.printf("%10.1f", this.matrix[i][j]);
            }
            System.out.println();
        }
    }

    public double[] getRow(int index) {
        return this.matrix[index];
    }

    public int getSize0() {
        return this.size0;
    }

    public int getSize1() {
        return this.size1;
    }

    public void generateRandom() {
        for (int i = 0; i < this.size0; i++) {
            for (int j = 0; j < this.size1; j++) {
                this.matrix[i][j] = Math.random();
            }
        }
    }
}
