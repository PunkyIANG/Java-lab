

public class Carte {
    public float pret = -1f;
    public String denumire = "---";
    public String autor = "---";
    public int nrCopii = 0;

    public static int nrCarti = 0;
    public int state[] = new int[nrCopii];
    
    public Carte() {}

    public Carte(float pret, String denumire, String autor, int nrCopii, int state[]) {
        try {
            setPret(pret);
            setDenumire(denumire);
            setAutor(autor);
            nrCarti++;

            if (nrCopii == state.length) {
                setStateArray(state);
            } else {
                throw new CarteException(nrCopii, state.length);
            }
        } catch (CarteException e) {
            e.Handle();
        }
    }

    public Carte(Carte copy) {
        this.pret = copy.getPret();
        this.denumire = copy.getDenumire();
        this.autor = copy.getAutor();
        this.nrCopii = copy.getNrCopii();
        nrCarti++;
        this.state = copy.getStateArray();
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) throws CarteException {
        if (pret < 0f) {
            throw new CarteException(pret);
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
        return nrCopii;
    }

    public void setNrCopii(int nrCopii) {

        try {
            if (nrCopii < 0) {
                throw new CarteException(nrCopii);
            }

            if (nrCopii == this.nrCopii) {
                System.out.println("WARNING: nrcopii == this.nrCopii, no changes");
                return;
            }

            int minCount = nrCopii < this.nrCopii ? nrCopii : this.nrCopii;

            int temp[] = state.clone();

            state = new int[nrCopii];
            this.nrCopii = nrCopii;

            for (int i = 0; i < minCount; i++) {
                setState(i, temp[i]);
            }
        } catch (CarteException e) {
            e.Handle();
        }
    }

    public static int getNrCarti() {
        return nrCarti;
    }

    public int getState(int id) {
        if (id >= 0 && id < state.length) {
            return state[id];
        }

        System.out.println("Shit's fucked man");
        System.out.println("Requested invalid id at ***.getState");
        return -1;
    }

    public void setState(int id, int state) {
        try {
            try {
                if (id >= 0 && id < this.state.length) {
                    if (state > 10 || state < 0) {
                        throw new CarteException(state, true);
                    }
                    this.state[id] = state;
                } else {
                    System.out.println("Set invalid id at ***.setState");
                    throw new ArrayIndexOutOfBoundsException();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CarteException(e);
            }
        } catch (CarteException e) {
            e.Handle();
        }
    }

    public int[] getStateArray() {
        return state.clone();
    }

    public void setStateArray(int[] state) {
        nrCopii = state.length;

        for (int i = 0; i < state.length; i++) {
            setState(i, state[i]);
        }
        this.state = state.clone();
    }

    public void Print() {
        System.out.println("Pret: " + pret);
        System.out.println("Denumire: " + denumire);
        System.out.println("Autor: " + autor);
        System.out.println("Nr copii: " + nrCopii);
        System.out.print("Status: ");

        for (int i = 0; i < state.length; i++) {
            System.out.print(state[i] + " ");
        }

        System.out.println();
    }

    public void Edit() {
        System.out.print("Dati pretul: ");
        try {
            setPret(Helper.InputFloatLimit(0f));
        } catch (CarteException e) {
            e.Handle();
        }

        System.out.print("Dati denumirea: ");
        setDenumire(Helper.InputString());

        System.out.print("Dati autorul: ");
        setAutor(Helper.InputString());

        System.out.print("Dati nr. copii: ");
        setNrCopii(Helper.InputIntLimit(0));

        for (int i = 0; i < state.length; i++) {
            System.out.println("Dati starea cartii " + i + ": ");
            setState(i, Helper.InputIntLimit(0, 10));
        }
    }

    public void Randomize() {
        String titles[] = { "bandit of tomorrow", "lion of despair", "wives of the mountain", "spies with strength",
                "armies and defenders", "traitors and girls", "fate of gold", "root of the eclipse",
                "belonging to the king", "smiles in my family" };

        String names[] = { "Harper Gear", "Alex Robinson", "Brett Blade", "Franky Joyce", "Caden Martin", "Jody Ramone",
                "Jude Day", "Caden Brandon", "Vic Money", "Quinn Davis" };

        pret = Helper.randomizer.nextFloat() * 100;
        denumire = titles[Helper.randomizer.nextInt(titles.length)];
        autor = names[Helper.randomizer.nextInt(names.length)];
        nrCopii = Helper.randomizer.nextInt(10);
        state = new int[nrCopii];

        for (int i = 0; i < state.length; i++) {
            state[i] = Helper.randomizer.nextInt(10) + 1;
        }
    }

    public float PretTotal() {
        return pret * nrCopii;
    }

    public float GetIntrebuintare() {
        float result = 0f;

        for (int i = 0; i < state.length; i++) {
            result += state[i];
        }

        if (state.length != 0) {
            result /= state.length;
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
