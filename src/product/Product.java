package product;

/**
 * Created by Kyra on 19/03/2016.
 */
public abstract class Product {

    protected String naam;
    protected int prijs;
    protected Eenheid eenheid;
    protected int btw;
    protected int voorraad;

    protected Product(String naam, int prijs, int voorraad, Eenheid eenheid) {
        this.naam = naam;
        this.prijs = prijs;
        this.voorraad = voorraad;
        this.eenheid = eenheid;
    }

    protected Product(String naam, int prijs, int voorraad) {
        this(naam, prijs, voorraad, Eenheid.STUK);
    }

    protected Product(String naam, int prijs, Eenheid eenheid) {
        this(naam, prijs, 0, eenheid);
    }

    public abstract String getNaam();

    public abstract int getPrijsPerEenheid();

    public int getPrijs() {
        return getPrijsPerEenheid();
    }

    public int getPrijs(int hoeveelheid) {
        return getPrijs(hoeveelheid, true);
    }

    public int getPrijs(int hoeveelheid, boolean inclusief) {
        int prijs = getPrijsPerEenheid();
        if (inclusief) {
            prijs += (prijs * getBTWPercentage()) / 100;
        }
        return prijs * hoeveelheid;
    }

    public abstract int getBTWPercentage();

    public abstract Eenheid getEenheid();

    public abstract int getVoorraad();

    public final void setVoorraad(int voorraad) throws NegatieveVoorraadException {
        if (voorraad<0) {
            throw new NegatieveVoorraadException("Geen negatieve voorraad mogelijk");
        } else {
            this.voorraad = voorraad;
        }
    }

    public void haalUitVoorraad(int aantal) throws NegatieveVoorraadException {
        setVoorraad(getVoorraad() - aantal);
    }

    public String toString() {
        return getNaam() + ": " + getVoorraad() + " " + getEenheid();
    }

    public String printInfo(boolean btw) {
        return getNaam() + ": " + getVoorraad() + " " + getEenheid() + ", prijs: " + getPrijs(1, btw);
    }
}

