package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Carte defaultCarte = new Carte();
        Carte customCarte = new Carte(150f, "The Catcher in the Rye", "J. D. Salinger", new ArrayList<CarteCopie>(5));
        for (int i = 0; i < 5; i++) {
            customCarte.addState(new CarteCopie());
        }

        Carte copyCarte = new Carte(customCarte);

        ArrayList<Carte> biblioteca = new ArrayList<>(5);

        biblioteca.add(defaultCarte);
        biblioteca.add(customCarte);
        biblioteca.add(copyCarte);
        biblioteca.add(new Carte());
        biblioteca.add(new Carte());

        customCarte.setState(1, new CarteCopie(1, false));

        biblioteca.set(3, Helper.gson.fromJson(
                Helper.gson.toJson(biblioteca.get(1)),
                Carte.class
        ));

        System.out.println("Diferenta de pret dintre 0 si 1: " + Carte.ComparePrice(biblioteca.get(0), biblioteca.get(1)));
        System.out.println("Diferenta de pret dintre 2 si 3: " + Carte.ComparePrice(biblioteca.get(2), biblioteca.get(3)));
        System.out.println("Diferenta de intrebuintare dintre 0 si 1: " + biblioteca.get(0).CompareIntrebuintare(biblioteca.get(1)));
        System.out.println("Diferenta de intrebuintare dintre 2 si 3: " + biblioteca.get(2).CompareIntrebuintare(biblioteca.get(3)));
        System.out.println();


        float totalPrice = 0f;
        for (Carte carte : biblioteca) {
            totalPrice += carte.PretTotal();
        }

        System.out.println("Pret total: " + totalPrice);
        System.out.println();

        Carte mostUsed = null;  //nu folosim new Carte() ca sa nu marim nrCarti cu inca o unitate
        for (Carte carte : biblioteca) {
            if (mostUsed == null || mostUsed.CompareIntrebuintare(carte) < 0) {
                mostUsed = carte;
            }
        }

        for (int i = 0; i < biblioteca.size(); i++) {
            if (mostUsed == null || mostUsed.CompareIntrebuintare(biblioteca.get(i)) < 0) {
                mostUsed = biblioteca.get(i);
            }
        }

        System.out.println("Cea mai intrebuintata carte: ");
        mostUsed.Print();
        System.out.println();

        System.out.println("Nr de carti create: " + Carte.getNrCarti());

        for (Carte carte : biblioteca) {
            //biblioteca[i].SaveToFile("books/" + biblioteca[i].getDenumire() + ".txt");
            Helper.FileWrite(carte.getDenumire() + ".txt", Helper.gson.toJson(carte));
        }
    }
}
