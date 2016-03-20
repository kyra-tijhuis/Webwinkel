package bestelling;

import product.Product;

/**
 * Created by Kyra on 19/03/2016.
 */
class ProductBestelling {

    private Product product;
    private int hoeveelheid;
    private Winkelwagen winkelwagen;
//    private boolean btw;

    public ProductBestelling(Product product, int hoeveelheid, Winkelwagen winkelwagen) {
        this.product = product;
        this.hoeveelheid = hoeveelheid;
        this.winkelwagen = winkelwagen;
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
        String result = "";
        result = product.getNaam() + ": " + hoeveelheid + " " + product.getEenheid()
                + " à " + product.getPrijsPerEenheid() + " -> " + (getPrijs(winkelwagen.getBTW()));
        return result;
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
}
