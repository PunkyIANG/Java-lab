package com.company;

public class CarteCopie {
    private int state;
    private boolean hasAutograph;

    public CarteCopie() {
        state = Helper.randomizer.nextInt(10) + 1;
        hasAutograph = Helper.randomizer.nextInt(10) >= 7 ? true : false;
    }

    public CarteCopie(int state, boolean hasAutograph) {
        if (state < 1 || state > 10) {
            Helper.println("ERROR: incorrect value given to SetState: " + state + ", should be in [1,10]");
            throw new RuntimeException();
        }

        this.state = state;
        this.hasAutograph = hasAutograph;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if (state < 1 || state > 10) {
            Helper.println("ERROR: incorrect value given to SetState: " + state + ", should be in [1,10]");
            throw new RuntimeException();
        }

        this.state = state;
    }

    public boolean getHasAutograph() {
        return hasAutograph;
    }

    public void setHasAutograph(boolean hasAutograph) {
        this.hasAutograph = hasAutograph;
    }

    public void Edit() {
        boolean success = false;
        do {
            try {
                Helper.println("Dati starea cartii: ");
                state = Helper.InputIntLimit(1, 10);
                Helper.println("Are autograf? (true/false): ");
                hasAutograph = Helper.InputBoolean();
                success = true;
            } catch (Exception e) {
                System.out.println(e);
                success = false;
            }
        } while (!success);
    }

    public void Print() {
        String result = "Carte de starea " + state;

        if (hasAutograph)
            result += " cu autograful autorului";

        Helper.println(result);
    }
}
