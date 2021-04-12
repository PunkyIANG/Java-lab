public class DoubleMatrix extends Thread {
    static double Matrix[][];
    static int rows, cols, globalGreaterThanX;
    int threadId;
    double x;


    static {
        rows = Helper.randomizer.nextInt(6) + 5;
        cols = Helper.randomizer.nextInt(6) + 5;
        Matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                Matrix[i][j] = Helper.randomizer.nextDouble() * 20 - 10;
    }

    static void PrintMatrix() {
        for (int i = 0; i < rows; i++)  {
            for (int j = 0; j < cols; j++) {
                Helper.print(Matrix[i][j] + " ");
            }

            Helper.println("");
        }
    }

    public DoubleMatrix(int id, double x) {
        this.threadId = id;
        this.x = x;
    }

    public void run() {
        int localGreaterThanX = 0;

        for (int i = 0; i < rows; i++)
            if (Matrix[i][threadId] > x)
                localGreaterThanX++;

        Helper.println("Thread " + threadId + " finished with result " + localGreaterThanX);

        SumToGlobalGreaterThanX(localGreaterThanX);
    }

    private static synchronized void SumToGlobalGreaterThanX(int x) {
        globalGreaterThanX += x;
    }
}
