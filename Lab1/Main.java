public class Main {
    public static void main(String[] args) {
        Carte defaultCarte = new Carte();
        Carte customCarte = new Carte(150f, "The Catcher in the Rye", "J. D. Salinger", 1000, new int[] {5,7,3,4,1});
        Carte copyCarte = new Carte(customCarte);

        Carte biblioteca[] = new Carte[] {
            defaultCarte,
            customCarte,
            copyCarte,
            new Carte(),
            new Carte()
        };

        customCarte.setState(1, 0);
        biblioteca[3].Randomize();
        biblioteca[4].Randomize();

        for (int i = 0; i < biblioteca.length; i++) {
            biblioteca[i].Print();
            System.out.println();
        }

        System.out.println("Diferenta de pret dintre 0 si 1: " + Carte.ComparePrice(biblioteca[0], biblioteca[1]));
        System.out.println("Diferenta de pret dintre 2 si 3: " + Carte.ComparePrice(biblioteca[2], biblioteca[3]));
        System.out.println("Diferenta de intrebuintare dintre 0 si 1: " + biblioteca[0].CompareIntrebuintare(biblioteca[1]));
        System.out.println("Diferenta de intrebuintare dintre 2 si 3: " + biblioteca[2].CompareIntrebuintare(biblioteca[3]));
        System.out.println();


        float totalPrice = 0f;
        for (int i = 0; i < biblioteca.length; i++) {
            totalPrice += biblioteca[i].PretTotal();
        }

        System.out.println("Pret total: " + totalPrice);
        System.out.println();

        Carte mostUsed = new Carte();
        for (int i = 0; i < biblioteca.length; i++) {
            if (mostUsed.CompareIntrebuintare(biblioteca[i]) < 0) {
                mostUsed = biblioteca[i];
            }
        }
        System.out.println("Cea mai intrebuintata carte: ");
        mostUsed.Print();
        System.out.println();

        System.out.println("Nr de carti create: " + Carte.getNrCarti());

        for (int i = 0; i < biblioteca.length; i++) {
            biblioteca[i].SaveToFile("books/" + biblioteca[i].getDenumire() + ".txt");
        }
    }
}
