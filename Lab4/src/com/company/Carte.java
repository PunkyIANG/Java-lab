package com.company;

import java.util.*;

class Carte {
    private float pret;
    private String denumire;
    private String autor;

    private static int nrCarti = 0;
    private ArrayList<CarteCopie> state;

    public Carte() {
        pret = -1f;
        denumire = "---";
        autor = "---";

        nrCarti++;
        state = new ArrayList<CarteCopie>();
    }

    public Carte(float pret, String denumire, String autor, ArrayList<CarteCopie> state) {
        this.pret = pret;
        this.denumire = denumire;
        this.autor = autor;
        nrCarti++;

        this.state = (ArrayList) state.clone();
    }

    public Carte(Carte copy) {
        this.pret = copy.getPret();
        this.denumire = copy.getDenumire();
        this.autor = copy.getAutor();
        nrCarti++;
        this.state = copy.getStateArrayList();
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        if (pret < 0f) {
            System.out.println("ERROR: negative price given at setPret");
            throw new RuntimeException();
        }
        this.pret = pret;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNrCopii() {
        return state.size();
    }

    public void setNrCopii(int nrCopii) {
        if (nrCopii < 0) {
            System.out.println("ERROR: negative nrCopii given");
            throw new RuntimeException();
        }

        if (nrCopii == this.state.size()) {
            System.out.println("WARNING: nrcopii == this.nrCopii, no changes");
            return;
        }

        for (int i = state.size(); i < nrCopii; i++) {
            state.add(new CarteCopie());
        }

        while (state.size() > nrCopii) {
            state.remove(state.size() - 1);
        }

//        int minCount = nrCopii < this.nrCopii ? nrCopii : this.nrCopii;
//
//        int temp[] = new int[this.nrCopii];
//
//        for (int i = 0; i < this.nrCopii; i++) {
//            temp[i] = state[i];
//        }
//
//        state = new int[nrCopii];
//        this.nrCopii = nrCopii;
//
//        for (int i = 0; i < minCount; i++) {
//            state[i] = temp[i];
//        }
    }

    public static int getNrCarti() {
        return nrCarti;
    }

    public CarteCopie getState(int id) {
        if (id >= 0 && id < state.size()) {
            return state.get(id);
        }

        System.out.println("Shit's fucked man");
        System.out.println("Requested invalid id at ***.getState");
        return null;
    }

    public void setState(int id, CarteCopie state) {
        if (id >= 0 && id < this.state.size()) {
            this.state.set(id, state);
        } else if (id < 0) {
            System.out.println("Set invalid id at ***.setState");
            throw new IndexOutOfBoundsException();
        } else {
            Helper.println("WARNING: set CarteCopie at too big position, adding to end of list");
            this.state.add(state);
        }
    }

    public void addState(CarteCopie state) {
        this.state.add(state);
    }

    public ArrayList<CarteCopie> getStateArrayList() {
        return (ArrayList<CarteCopie>) state.clone();
    }

    public void setStateArrayList(ArrayList<CarteCopie> state) {
        this.state = (ArrayList<CarteCopie>)state.clone();
    }

    public void Print() {
        System.out.println("Pret: " + pret);
        System.out.println("Denumire: " + denumire);
        System.out.println("Autor: " + autor);
        System.out.println("Nr copii: " + state.size());
        System.out.println("Status: ");

        for (CarteCopie copie : state) {
            copie.Print();
        }

        System.out.println();
    }

    public void Edit() {
        System.out.print("Dati pretul: ");

        boolean success = false;
        do {
            try {
                setPret(Helper.InputFloat());
                success = true;
            } catch (Exception e) {
                System.out.println(e);
                success = false;
            }
        } while (!success);

        System.out.print("Dati denumirea: ");
        setDenumire(Helper.InputString());

        System.out.print("Dati autorul: ");
        setAutor(Helper.InputString());

        System.out.print("Dati nr. copii: ");

        success = false;
        do {
            try {
                setNrCopii(Helper.InputInt());
                success = true;
            } catch (Exception e) {
                System.out.println(e);
                success = false;
            }
        } while (!success);

        for (int i = 0; i < state.size(); i++) {
            setState(i, new CarteCopie());
            state.get(i).Edit();
        }
    }

    public void Randomize() {
        String titles[] = { "bandit of tomorrow", "lion of despair", "wives of the mountain", "spies with strength",
                "armies and defenders", "traitors and girls", "fate of gold", "root of the eclipse",
                "belonging to the king", "smiles in my family" };

        String names[] = { "Harper Gear", "Alex Robinson", "Brett Blade", "Franky Joyce", "Caden Martin", "Jody Ramone",
                "Jude Day", "Caden Brandon", "Vic Money", "Quinn Davis" };
        Random randomizer = new Random();

        pret = randomizer.nextFloat() * 100;
        denumire = titles[randomizer.nextInt(titles.length)];
        autor = names[randomizer.nextInt(names.length)];
        int nrCopii = randomizer.nextInt(10);
        state = new ArrayList<CarteCopie>(nrCopii);

        for (int i = 0; i < state.size(); i++) {
            state.set(i, new CarteCopie());
        }
    }

    public float PretTotal() {
        return pret * state.size();
    }

    public float GetIntrebuintare() {
        float result = 0f;

        for (int i = 0; i < state.size(); i++) {
            result += state.get(i).getState();
        }

        if (state.size() != 0) {
            result /= state.size();
        }
        return result;
    }

    public float CompareIntrebuintare(Carte other) {
        return this.GetIntrebuintare() - other.GetIntrebuintare();
    }

    public static float ComparePrice(Carte first, Carte second) {
        return first.PretTotal() - second.PretTotal();
    }
}
