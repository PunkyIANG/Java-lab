public class Main{
    public static void main(String[] args) {
        DoubleMatrix.PrintMatrix();

        Helper.print("X: ");
        double x = Helper.InputFloat(); //extends to double

        for (int j = 0; j < DoubleMatrix.cols; j++)
            (new DoubleMatrix(j, x)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }

        Helper.println("Elements greater than " + x + ": " + DoubleMatrix.globalGreaterThanX);

    }
}