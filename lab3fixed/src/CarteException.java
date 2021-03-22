

public class CarteException extends Exception {
    private float wrongPret = 0f;
    private int wrongNrCopii = 3;
    private int wrongState = 5;
    private int wrongStateArrayLength = -1;
    private Object standardException;

    public CarteException(float wrongPret) {
        this.wrongPret = wrongPret;
    }

    public CarteException(int wrongNrCopii) {
        this.wrongNrCopii = wrongNrCopii;
    }

    public CarteException(int wrongState, boolean unusedParam) {
        //unusedParam is ofc just to get a different param signature
        this.wrongState = wrongState;
    }

    public CarteException(int wrongNrCopii, int wrongStateArrayLength) {
        this.wrongNrCopii = wrongNrCopii;
        this.wrongStateArrayLength = wrongStateArrayLength;
    }

    public CarteException(Object standardException) {
        this.standardException = standardException;
    }

    public void Handle() {
        if (wrongPret < 0f) {
            Helper.println("pret should be >= 0f");
        }

        if (wrongNrCopii < 0 && wrongStateArrayLength == -1) {
            Helper.println("nrCopii should be >= 0");
        }

        if (wrongState > 10 || wrongState < 0) {
            Helper.println("state should be between 0 and 10 inclusively");
        }

        if (wrongNrCopii != wrongStateArrayLength && wrongStateArrayLength != -1) {
            Helper.println("nrCopii and state.length should be the same");
        }

        if (standardException instanceof ArrayIndexOutOfBoundsException) {
            Helper.println("accessed index out of bounds of state");
            Helper.println(standardException.toString());
        }
    }

}
