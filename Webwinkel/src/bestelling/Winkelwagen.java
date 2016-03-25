package bestelling;

import product.Bezorging;
import product.NegatieveVoorraadException;
import product.Product;

import java.util.ArrayList;

/**
 * Created by Kyra on 19/03/2016.
 */
public class Winkelwagen {

    private ArrayList<ProductBestelling> bestelling = new ArrayList<>();
    private boolean btw = true;
    private boolean bezorgbaar;
    private Bezorging bezorging;

    public Winkelwagen(boolean btw) {
        this.btw = btw;
    }

    public boolean getBTW() {
        return btw;
    }

    public void setBTW(boolean btwInclusief) {
        this.btw = btwInclusief;
    }

    public void resetWinkelwagen() {
        bestelling.clear();
    }

    int getTotaalPrijs(boolean btw) {
        int result = 0;
        for (ProductBestelling pb : bestelling) {
            result += pb.getPrijs(btw);
        }
        return result;
    }

    public void bestel(Product p, int hoeveelheid) {
        ProductBestelling productBestelling = null;
        for (ProductBestelling pb : bestelling) {
            if (pb.productEquals(p)) {
                productBestelling = pb;
            }
        }
        if (productBestelling==null) { // product is niet in bestelling aanwezig
            try {
                p.haalUitVoorraad(hoeveelheid);
                ProductBestelling pb = new ProductBestelling(p, hoeveelheid, this);
                bestelling.add(pb);
            } catch (NegatieveVoorraadException e) {
                e.printStackTrace();
            }
        } else {
            productBestelling.veranderBestelling(hoeveelheid + productBestelling.getHoeveelheid());
            displayWinkelwagen();
        }
    }

    public boolean verwijderBestelling(int index) {
        if (index>bestelling.size()) {
            return false;
        } else {
            ProductBestelling teVerwijderen = bestelling.get(index-1);
            teVerwijderen.veranderBestelling(0);
            bestelling.remove(index-1);
            displayWinkelwagen();
            return true;
        }
    }

    public boolean veranderBestelling(int index, int hoeveelheid) {
        if (index>bestelling.size()) {
            return false;
        } else {
            bestelling.get(index-1).veranderBestelling(hoeveelheid);
            displayWinkelwagen();
            return true;
        }
    }
    
    public void bezorgWinkelwagen(Bezorging bezorging) {
        this.bezorging = bezorging;
        int counter = 0;
        boolean teBezorgen = false;
    	for (ProductBestelling pb : bestelling) {
            pb.setBezorging(bezorging);
            if (pb.getBezorging()!=null) {
                teBezorgen = true;
                counter++;
            }
    	}
        if (teBezorgen) {
            bezorgbaar = true;
            System.out.println(counter + " producten die " + bezorging.getNaam() + " bezorgd worden");
            System.out.println((bestelling.size()-counter) + " producten die niet bezorgd kunnen worden");
        } else {
            bezorgbaar = false;
            System.out.println("Geen producten in de winkelwagen die bezorgd kunnen worden");
        }
    }
    
    public void bestelWinkelwagen() {
    	
    }

    public void displayWinkelwagen() {
    	System.out.println("Product                         Aantal     Prijs      BTW   Totaalprijs");
    	System.out.println("-----------------------------------------------------------------------");
        int index = 1;
        int btwTotaal = 0;
        int btwBedrag = 0;
        if (bestelling.size()>0) {
            for (ProductBestelling pb : bestelling) {
            	if (btw) {
            		btwBedrag = pb.getBTWBedrag();
            	}            	
                System.out.printf("%3d  %s %8d %13d%n", index, pb.toString(), btwBedrag, pb.getPrijs(btw));
                index++;
                btwTotaal += btwBedrag;
            }
            System.out.println("-----------------------------------------------------------------------");
            int prijs = getTotaalPrijs(false);
            System.out.printf("Subtotalen                              %8d %8d %13d%n", prijs, btwTotaal, prijs+btwTotaal);
            int totaalprijs = prijs+btwTotaal;
            if (bezorgbaar) {
                totaalprijs += bezorging.getPrijsPerEenheid();
                System.out.printf("Bezorging: %-51s %8d%n", bezorging.getNaam(),bezorging.getPrijsPerEenheid());
            }
            System.out.printf("Totaal                                                         %8d%n", totaalprijs);
        } else {
            System.out.println("Winkelwagen is leeg");
        }

    }
}
