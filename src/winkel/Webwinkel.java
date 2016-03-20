package winkel;

import bestelling.Winkelwagen;
import product.DefaultProduct;
import product.Eenheid;
import product.Aanbieding;
import product.Product;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Kyra on 19/03/2016.
 */
public class Webwinkel {

    private ArrayList<Product> producten = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    private Webwinkel() {
        producten.add(new DefaultProduct("Melk", 100, 100, Eenheid.PAK));
        producten.add(new DefaultProduct("Brood", 200, 10, Eenheid.STUK));
        producten.add(new Aanbieding("Auto", 100000, 10, 2, Eenheid.STUK));
        producten.add(new Aanbieding("Kaas", 500, 10, 5000, Eenheid.KILO));
    }

    void printAssortiment() {
        for (int i=0; i<producten.size(); i++) {
            if (producten.get(i).getVoorraad()>0) {
                System.out.println((i+1) + ") " + producten.get(i).toString());
            }
        }
    }

    private void printPrijzenBTW(boolean btw) {
        for (int i=0; i<producten.size(); i++) {
            if (producten.get(i).getVoorraad()>0) {
                System.out.println((i+1) + ") " + producten.get(i).printInfo(btw));
            }
        }
    }

    private void run() {
        final Winkelwagen winkelwagen;
        String invoer = "", cmd2 = "", cmd3 = "";

        System.out.println("Welkom bij de webwinkel");
        System.out.println("Moet u bij uw bestelling BTW betalen? (j/n)");
        String antwoord = input.next();
        if (antwoord.equalsIgnoreCase("j") || antwoord.equalsIgnoreCase("ja")) {
            winkelwagen = new Winkelwagen(true);
        } else {
            winkelwagen = new Winkelwagen(false);
        }
        printHelp();
        System.out.println("Deze producten zijn op voorraad:");
        printPrijzenBTW(winkelwagen.getBTW());

        while (input.hasNextLine()) {
            invoer = input.nextLine();
            Scanner s = new Scanner(invoer);
            if (s.hasNext()) {
                String cmd1 = s.next();
                if (s.hasNext()) {
                    cmd2 = s.next();
                    if (s.hasNext()){
                        cmd3 = s.next();
                    }
                }
                switch (cmd1) {
                    case "x":
                        System.exit(0);
                        break;
                    case "a":
                        printPrijzenBTW(winkelwagen.getBTW());
                        break;
                    case "b":
                        commandoB(winkelwagen, cmd2, cmd3);
                        break;
                    case "w":
                        winkelwagen.displayWinkelwagen();
                        break;
                    case "d":
                        commandoD(winkelwagen, cmd2);
                        break;
                    case "v":
                        commandoV(winkelwagen, cmd2, cmd3);
                        break;
                    case "r":
                        winkelwagen.resetWinkelwagen();
                        winkelwagen.displayWinkelwagen();
                        break;
                    case "btw":
                        commandoBTW(winkelwagen, cmd2);
                        break;
                    case "h":
                    default:
                        printHelp();
                        break;
                }
            }
        }
    }

    private void printHelp() {
        System.out.println("a ............................. display assortiment");
        System.out.println("b productnummer hoeveelheid ... voeg bestelling toe aan winkelwagen");
        System.out.println("w ............................. display winkelwagen");
        System.out.println("d productnummer ............... verwijder bestelling uit winkelwagen");
        System.out.println("v productnummer hoeveelheid ... verander hoeveelheid bestelling");
        System.out.println("r ............................. verwijder alle bestellingen");
        System.out.println("btw boolean ................... set BTW betaling");
        System.out.println("h ............................. hulp menu");
        System.out.println("x ............................. exit programma");
    }

    private void commandoD(Winkelwagen winkelwagen, String cmd2) {
        Integer product = Integer.parseInt(cmd2);
        if (product>0) {
            if (!winkelwagen.verwijderBestelling(product)) {
                System.out.println("Product is niet in bestelling aanwezig");
            }
        } else {
            System.out.println("Product moet worden aangeduid met een positief getal");
        }
    }

    private void commandoV(Winkelwagen winkelwagen, String cmd2, String cmd3) {
        Integer product = Integer.parseInt(cmd2);
        Integer hoeveelheid = Integer.parseInt(cmd3);
        if (!winkelwagen.veranderBestelling(product, hoeveelheid)){
            System.out.println("Product is niet in bestelling aanwezig");
        }
    }

    private void commandoBTW(Winkelwagen winkelwagen, String cmd2) {
        if (cmd2.equalsIgnoreCase("true") || cmd2.equalsIgnoreCase("false")) {
            winkelwagen.setBTW(Boolean.getBoolean(cmd2));
        } else {
            System.out.println("Het commando btw moet worden gevolgd door 'true' of 'false'");
        }
    }

    private void commandoB(Winkelwagen winkelwagen, String cmd2, String cmd3) {
        Integer product = Integer.parseInt(cmd2);
        Integer hoeveelheid = Integer.parseInt(cmd3);
        if (product==null || product < 1) {
            System.out.println("Product moet worden aangeduid met een positief getal");
        } else if (hoeveelheid==null || hoeveelheid < 0) {
            System.out.println("Hoeveeheid moet worden aangeduid met een positief getal");
        } else {
            winkelwagen.bestel(producten.get(product-1), hoeveelheid);
        }
    }

    public static void main(String[] args) {
        Webwinkel obj = new Webwinkel();
        obj.run();
    }
}
