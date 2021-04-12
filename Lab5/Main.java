
class Main{
    public static void main(String[] args) {
        DoubleMatrix.PrintMatrix();

        double x = 0;

        for (int j = 0; j < DoubleMatrix.cols; j++) 
            (new DoubleMatrix(j, x)).start();

    }
}