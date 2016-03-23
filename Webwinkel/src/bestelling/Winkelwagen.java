package bestelling;

import product.NegatieveVoorraadException;
import product.Product;

import java.util.ArrayList;

/**
 * Created by Kyra on 19/03/2016.
 */
public class Winkelwagen {

    private ArrayList<ProductBestelling> bestelling;
    private boolean btw = true;

    public Winkelwagen(boolean btw) {
        bestelling = new ArrayList<>();
        this.btw = btw;
    }

    public boolean getBTW() {
        return btw;
    }

    public void setBTW(boolean btwInclusief) {
        this.btw = btwInclusief;
        // TODO recalc prices
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
            if (pb.getProduct().equals(p)) {
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
            veranderBestelling(p, hoeveelheid + productBestelling.getHoeveelheid());
        }
    }

    public boolean verwijderBestelling(Product p) {
        ProductBestelling teVerwijderen = null;
        for (ProductBestelling pb : bestelling) {
            if (pb.getProduct().equals(p)) {
                Product product = pb.getProduct();
                try {
                    product.setVoorraad(product.getVoorraad() + pb.getHoeveelheid());
                    teVerwijderen = pb;
                } catch (NegatieveVoorraadException e) {
                    e.printStackTrace();
                }
            }
        }
        bestelling.remove(bestelling.indexOf(teVerwijderen));
        return !(teVerwijderen==null);
    }

    public boolean verwijderBestelling(int index) {
        if (index>bestelling.size()) {
            return false;
        } else {
            return verwijderBestelling(bestelling.get(index-1).getProduct());
        }
    }

    public boolean veranderBestelling(Product p, int hoeveelheid) {
        boolean result = false;
        for (ProductBestelling pb : bestelling) {
            if (pb.getProduct().equals(p)) {
                result = true;
                Product product = pb.getProduct();
                try {
                    product.setVoorraad(product.getVoorraad() + pb.getHoeveelheid());
                    pb.setHoeveelheid(hoeveelheid);
                    product.haalUitVoorraad(hoeveelheid);
                } catch (NegatieveVoorraadException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public boolean veranderBestelling(int index, int hoeveelheid) {
        if (index>bestelling.size()) {
            return false;
        } else {
            return veranderBestelling(bestelling.get(index-1).getProduct(), hoeveelheid);
        }
    }

    public void displayWinkelwagen() {
    	System.out.println("Product                         Aantal     Prijs      BTW");
    	System.out.println("---------------------------------------------------------");
        int index = 1;
        int btwTotaal = 0;
        int btwBedrag = 0;
        if (bestelling.size()>0) {
            for (ProductBestelling pb : bestelling) {
            	if (btw) {
            		btwBedrag = pb.getBTWBedrag();
            	}            	
                System.out.printf("%3d  %s %8d %n", index, pb.toString(), btwBedrag);
                index++;
                btwTotaal += btwBedrag;
            }
            System.out.println("---------------------------------------------------------");
            System.out.printf("Subtotalen                              %8d %8d", getTotaalPrijs(false), btwTotaal);
        } else {
            System.out.println("Winkelwagen is leeg");
        }

    }
}
