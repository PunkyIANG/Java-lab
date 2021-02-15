import java.util.Random;
import java.io.*;

class Carte {
    private float pret;
    private String denumire;
    private String autor;
    private int nrCopii;

    private static int nrCarti = 0;
    private int state[];

    public Carte() {
        pret = -1f;
        denumire = "---";
        autor = "---";
        nrCopii = 0;

        nrCarti++;
        state = new int[nrCopii];
    }

    public Carte(float pret, String denumire, String autor, int nrCopii, int state[]) {
        this.pret = pret;
        this.denumire = denumire;
        this.autor = autor;
        nrCarti++;

        if (nrCopii == state.length) {
            this.nrCopii = nrCopii;
            this.state = state;
        } else {
            System.out.println("WARNING: nrCopii != state.length, reseting to 0");
            this.nrCopii = 0;
            this.state = new int[this.nrCopii];
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

    public Carte(String path) {
        try {
            BufferedReader box = new BufferedReader(new FileReader(path));

            pret = (Float.valueOf(box.readLine())).floatValue();
            denumire = box.readLine();
            autor = box.readLine();
            nrCopii = (Integer.valueOf(box.readLine())).intValue();
            state = new int[nrCopii];

            for (int i = 0; i < nrCopii; i++) {
                state[i] = (Integer.valueOf(box.readLine())).intValue();
            }
            nrCarti++;

            box.close();
        } catch (Exception e) {
            System.out.println("Shit's fucked man");
            System.out.println(e);
        }
    }

    // TODO: deserialize

    public String Serialize() {
        String result = "";
        result += pret + "\n";
        result += denumire + "\n";
        result += autor + "\n";
        result += nrCopii + "\n";
        for (int i = 0; i < nrCopii; i++) {
            result += state[i] + "\n";
        }

        return result;
    }

    public void Deserialize(String params) {
        String[] parts = params.split("\n");

        setPret((Float.valueOf(parts[0])).floatValue());
        setDenumire(parts[1]);
        setAutor(parts[2]);
        setNrCopii((Integer.valueOf(parts[3])).intValue());

        for (int i = 0; i < nrCopii; i++) {
            setState(i, (Integer.valueOf(parts[4 + i])).intValue());
        }

        nrCarti++;
    }

    public void SaveToFile(String path) {
        try {
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            if (!file.exists()) {
                file.createNewFile();
            }

            byte b[] = Serialize().getBytes();

            fileOutputStream.write(b);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("Shit's fucked man");
            System.out.println(e);
        }
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
        return nrCopii;
    }

    public void setNrCopii(int nrCopii) {
        if (nrCopii < 0) {
            System.out.println("ERROR: negative nrCopii given");
            throw new RuntimeException();
        }

        if (nrCopii == this.nrCopii) {
            System.out.println("WARNING: nrcopii == this.nrCopii, no changes");
            return;
        }

        int minCount = nrCopii < this.nrCopii ? nrCopii : this.nrCopii;

        int temp[] = new int[this.nrCopii];

        for (int i = 0; i < this.nrCopii; i++) {
            temp[i] = state[i];
        }

        state = new int[nrCopii];
        this.nrCopii = nrCopii;

        for (int i = 0; i < minCount; i++) {
            state[i] = temp[i];
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
        if (id >= 0 && id < this.state.length) {
            if (state > 10 || state < 0) {
                System.out.println("Set invalid state at ***.setState, value must be in [0,10]");
                throw new RuntimeException();
            }
            this.state[id] = state;
        } else {
            System.out.println("Set invalid id at ***.setState");
            throw new RuntimeException();
        }
    }

    public int[] getStateArray() {
        return state.clone();
    }

    public void setStateArray(int[] state) {
        nrCopii = state.length;
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

        for (int i = 0; i < state.length; i++) {
            System.out.println("Dati starea cartii " + i + ": ");

            success = false;
            do {
                try {
                    setState(i, Helper.InputInt());
                    success = true;
                } catch (Exception e) {
                    System.out.println(e);
                    success = false;
                }
            } while (!success);

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
        nrCopii = randomizer.nextInt(10);
        state = new int[nrCopii];

        for (int i = 0; i < state.length; i++) {
            state[i] = randomizer.nextInt(10) + 1;
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
