import java.util.Random;
import java.io.*;

//TODO: rewrite (or replace) nrCopii so it is equal to state.length 

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
        nrCopii = -1;

        nrCarti++;
        state = new int[0];
    }

    public Carte(float pret, String denumire, String autor, int nrCopii, int state[]) {
        this.pret = pret;
        this.denumire = denumire;
        this.autor = autor;
        this.nrCopii = nrCopii;

        nrCarti++;
        this.state = state;
    }

    public Carte(Carte copy) {
        this.pret = copy.getPret();
        this.denumire = copy.getDenumire();
        this.autor = copy.getAutor();
        this.nrCopii = copy.getNrCopii();
        nrCarti++;
        this.state = copy.state.clone();
    }

    public Carte(String path) {
        try {
            BufferedReader box = new BufferedReader(new FileReader(path));

            pret = (Float.valueOf(box.readLine())).floatValue();
            denumire = box.readLine();
            autor = box.readLine();
            nrCopii = (Integer.valueOf(box.readLine())).intValue();
            state = new int[(Integer.valueOf(box.readLine())).intValue()];

            for (int i = 0; i < state.length; i++) {
                state[i] = (Integer.valueOf(box.readLine())).intValue();
            }
            nrCarti++;

            box.close();
        } catch (Exception e) {
            System.out.println("Shit's fucked man");
            System.out.println(e.getMessage());
        }
    }

    public String Serialize() {
        String result = "";
        result += pret + "\n";
        result += denumire + "\n";
        result += autor + "\n";
        result += nrCopii + "\n";
        result += state.length + "\n";
        for (int i = 0; i < state.length; i++) {
            result += state[i] + "\n";
        }

        return result;
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
            System.out.println(e.getMessage());
        }
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
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
        this.nrCopii = nrCopii;
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
            this.state[id] = state;
        } else {
            System.out.println("Shit's fucked man");
            System.out.println("Set invalid id at ***.setState");    
        }
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
        pret = Helper.InputInt();

        System.out.print("Dati denumirea: ");
        denumire = Helper.InputString();

        System.out.print("Dati autorul: ");
        autor = Helper.InputString();

        System.out.print("Dati nr. copii: ");
        nrCopii = Helper.InputInt();

        System.out.println("Dati starea cator carti este verificata");
        state = new int[Helper.InputInt()];

        for (int i = 0; i < state.length; i++) {
            System.out.println("Dati starea cartii " + i + ": ");
            state[i] = Helper.InputInt();
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
        nrCopii = randomizer.nextInt(1000);
        state = new int[randomizer.nextInt(10)];

        for (int i = 0; i < state.length; i++) {
            state[i] = randomizer.nextInt(10);
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
