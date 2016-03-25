package product;

/**
 * Created by Kyra on 19/03/2016.
 */
public class DefaultProduct extends Product {

    private String naam;
    private int prijs;
    private Eenheid eenheid;

    public DefaultProduct(String naam, int prijs, int voorraad, Eenheid eenheid) {
        super(naam, prijs, voorraad, eenheid);
        this.btw = 21;
    }

    public DefaultProduct(String naam, int prijs, int voorraad) {
        super(naam, prijs, voorraad);
        this.btw = 21;
    }

    public DefaultProduct(String naam, int prijs, Eenheid eenheid) {
        super(naam, prijs, eenheid);
        this.btw = 21;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public int getPrijsPerEenheid() {
        return prijs;
    }

    @Override
    public void setPrijsPerEenheid(int prijs) {
        this.prijs = prijs;
    }

    @Override
    public int getBTWPercentage() {
        return btw;
    }

    @Override
    public Eenheid getEenheid() {
        return eenheid;
    }

    @Override
    protected void setEenheid(Eenheid eenheid) {
        this.eenheid = eenheid;
    }

    @Override
    public int getVoorraad() {
        return voorraad;
    }

}
