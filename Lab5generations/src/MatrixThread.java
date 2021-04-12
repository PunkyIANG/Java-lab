public class MatrixThread extends Thread{
    static int Matrix[][];
    static int rows, cols, finishedThreadCount;
    int threadIdRow;
    int generations;

    private static final Object threadCounterLock = new Object();
    private static final Object waitNotifyLock = new Object();

    static {
        rows = Helper.randomizer.nextInt(6) + 5;
        cols = Helper.randomizer.nextInt(6) + 5;
        Matrix = new int[rows][cols];

        Matrix[rows/2][cols/2] = 1;
    }

    static void PrintMatrix() {
        for (int i = 0; i < rows; i++)  {
            for (int j = 0; j < cols; j++) {
                Helper.print(Matrix[i][j] + " ");
            }

            Helper.println("");
        }
    }

    public MatrixThread(int id, int generations) {
        this.threadIdRow = id;
        this.generations = generations;
    }

    public void run() {
        while (generations != 0) {
            Helper.println("Started reading");

            int localResult[] = new int[cols];

            for (int j = 0; j < cols; j++) {
                int cellResult = Matrix[threadIdRow][j];

                if (threadIdRow + 1 < rows) {
                    if (j - 1 >= 0)
                        cellResult += Matrix[threadIdRow + 1][j - 1];

                    if (j + 1 < cols)
                        cellResult += Matrix[threadIdRow + 1][j + 1];

                    cellResult += Matrix[threadIdRow + 1][j];
                }

                if (threadIdRow - 1 >= 0) {
                    if (j - 1 >= 0)
                        cellResult += Matrix[threadIdRow - 1][j - 1];

                    if (j + 1 < cols)
                        cellResult += Matrix[threadIdRow - 1][j + 1];

                    cellResult += Matrix[threadIdRow - 1][j];
                }

                if (j - 1 >= 0)
                    cellResult += Matrix[threadIdRow][j - 1];

                if (j + 1 < cols)
                    cellResult += Matrix[threadIdRow][j + 1];

                localResult[j] = cellResult;
            }

            Helper.println("Finished reading");

            IncrementFinishedThreadCount();

            if (GetFinishedThreadCount() != rows) {
                synchronized (waitNotifyLock) {
                    try {
                        waitNotifyLock.wait();
                    } catch (InterruptedException e) {}
                }
            } else {
                synchronized (waitNotifyLock) {
                    waitNotifyLock.notifyAll();
                }
            }

            Helper.println("Started writing");

            for (int j = 0; j < cols; j++) {
                Matrix[threadIdRow][j] = localResult[j];
            }

            Helper.println("Finished writing");

            DecrementFinishedThreadCount();

            if (GetFinishedThreadCount() != 0) {
                synchronized (waitNotifyLock) {
                    try {
                        waitNotifyLock.wait();
                    } catch (InterruptedException e) {}
                }
            } else {
                synchronized (waitNotifyLock) {
                    PrintMatrix();

                    waitNotifyLock.notifyAll();
                }
            }



            generations--;
        }

        synchronized (Main.mainLock) {
            Main.mainLock.notifyAll();
        }
    }

    public static void IncrementFinishedThreadCount() {
        synchronized (threadCounterLock) {
            finishedThreadCount++;
        }
    }

    public static void DecrementFinishedThreadCount() {
        synchronized (threadCounterLock) {
            finishedThreadCount--;
        }
    }

    public static int GetFinishedThreadCount() {
        synchronized (threadCounterLock) {
            return finishedThreadCount;
        }
    }


}
