package bestelling;

import java.util.Formatter;
import java.util.Locale;

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
    	
        formatter.format("%1$-26S %2$6d %3$9d", product.getNaam(), hoeveelheid, product.getPrijsPerEenheid());
        
        return formatter.toString();
    }

    public Product getProduct() {
        return product;
    }

    public int getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(int hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }
    
    public int getBTWBedrag() {
    	return hoeveelheid * product.getBTWBedrag();
    }
}
