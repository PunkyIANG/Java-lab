public class Main {
    public static final Object mainLock = new Object();


    public static void main(String[] args) {
        MatrixThread.PrintMatrix();

        for (int i = 0; i < MatrixThread.rows; i++)
            (new MatrixThread(i, 3)).start();

        synchronized (mainLock) {
            try {
                mainLock.wait();
            } catch (InterruptedException e) {}
        }

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) { }

//        MatrixThread.PrintMatrix();
    }
}
