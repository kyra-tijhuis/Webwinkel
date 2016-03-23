package bestelling;

import java.awt.font.ImageGraphicAttribute;
import java.util.Formatter;
import java.util.Locale;

import product.Bezorging;
import product.NegatieveVoorraadException;
import product.Product;

/**
 * Created by Kyra on 19/03/2016.
 */
class ProductBestelling {

    private Product product;
    private int hoeveelheid;
    
    public ProductBestelling(Product product, int hoeveelheid, Winkelwagen winkelwagen) {
        this.product = product;
        this.hoeveelheid = hoeveelheid;
    }

    public ProductBestelling(Product product, Winkelwagen winkelwagen) {
        this(product, 1, winkelwagen);
    }

    public String getProductNaam() {
        return product.getNaam();
    }

    public int getPrijs(boolean inclusief) {
        return product.getPrijs(hoeveelheid, inclusief);
    }

    public String toString(){
    	Formatter formatter = new Formatter(new StringBuilder(), Locale.getDefault());
    	
        formatter.format("%1$-26S %2$6d %3$9d", product.getNaam(), hoeveelheid, getPrijs(false));
        
        return formatter.toString();
    }

    public boolean productEquals(Product p) {
        return product.equals(p);
    }

//    public Product getProduct() {
//        return product;
//    } // TODO niet netjes, aangezien andere klasse product variables kan veranderen

    public int getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(int hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }
    
    public int getBTWBedrag() {
    	return hoeveelheid * product.getBTWBedrag();
    }

    public Bezorging getBezorging() {
        if (IBezorgbaar.class.isAssignableFrom(product.getClass())) {
            IBezorgbaar bezorgbaar = (IBezorgbaar) product;
            return bezorgbaar.getBezorging();
        } else {
            return null;
        }
    }

    public void setBezorging(Bezorging bezorging) {
        if (IBezorgbaar.class.isAssignableFrom(product.getClass())) {
            IBezorgbaar bezorgbaar = (IBezorgbaar) product;
            bezorgbaar.setBezorging(bezorging);
        }
    }

    public void veranderBestelling(int hoeveelheid) {
        try {
            product.setVoorraad(product.getVoorraad() + getHoeveelheid());
            product.haalUitVoorraad(hoeveelheid);
            setHoeveelheid(hoeveelheid);
        } catch (NegatieveVoorraadException e) {
            System.out.println("Negatieve voorraad is niet mogelijk");
        }
    }

}
