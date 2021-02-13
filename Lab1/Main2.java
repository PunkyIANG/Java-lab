public class Main2 {
    public static void main(String[] args) {

        Carte biblioteca[] = new Carte[] { new Carte("books/---.txt"), new Carte("books/The Catcher in the Rye.txt"),
                new Carte("books/bandit of tomorrow.txt"), new Carte("books/traitors and girls.txt") };

        System.out.println("Diferenta de pret dintre 0 si 1: " + Carte.ComparePrice(biblioteca[0], biblioteca[1]));
        System.out.println("Diferenta de pret dintre 2 si 3: " + Carte.ComparePrice(biblioteca[2], biblioteca[3]));
        System.out.println(
                "Diferenta de intrebuintare dintre 0 si 1: " + biblioteca[0].CompareIntrebuintare(biblioteca[1]));
        System.out.println(
                "Diferenta de intrebuintare dintre 2 si 3: " + biblioteca[2].CompareIntrebuintare(biblioteca[3]));
        System.out.println();

        float totalPrice = 0f;
        for (int i = 0; i < biblioteca.length; i++) {
            System.out.println(i);
            totalPrice += biblioteca[i].PretTotal();
        }

        System.out.println("Pret total: " + totalPrice);
        System.out.println();

        Carte mostUsed = null;  //nu folosim new Carte() ca sa nu marim nrCarti cu inca o unitate
        for (int i = 0; i < biblioteca.length; i++) {
            if (mostUsed == null || mostUsed.CompareIntrebuintare(biblioteca[i]) < 0) {
                mostUsed = biblioteca[i];
            }
        }
        System.out.println("Cea mai intrebuintata carte: ");
        mostUsed.Print();
        System.out.println();

        System.out.println("Nr de carti create: " + Carte.getNrCarti());
    }
}
