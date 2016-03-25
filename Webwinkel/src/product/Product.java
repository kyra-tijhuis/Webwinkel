package product;

import bestelling.IBezorgbaar;

/**
 * Created by Kyra on 19/03/2016.
 */
public abstract class Product {

    protected int btw;
    protected int voorraad;

    protected Product(String naam, int prijs, int voorraad, Eenheid eenheid) {
        this.setNaam(naam);
        this.setPrijsPerEenheid(prijs);
        this.voorraad = voorraad;
        this.setEenheid(eenheid);
    }

    protected Product(String naam, int prijs, int voorraad) {
        this(naam, prijs, voorraad, Eenheid.STUK);
    }

    protected Product(String naam, int prijs, Eenheid eenheid) {
        this(naam, prijs, 0, eenheid);
    }

    public abstract String getNaam();

    public abstract void setNaam(String naam);

    public abstract int getPrijsPerEenheid();

    public abstract void setPrijsPerEenheid(int prijs);

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
    
    public int getBTWBedrag() {
    	return (getPrijsPerEenheid() * getBTWPercentage()) / 100;
    }

    public abstract Eenheid getEenheid();

    protected abstract void setEenheid(Eenheid eenheid);

    public abstract int getVoorraad();

    public final void setVoorraad(int voorraad) {
        if (voorraad<0) {
            throw new NegatieveVoorraadException("Geen negatieve voorraad mogelijk");
        } else {
            this.voorraad = voorraad;
        }
    }

    public void haalUitVoorraad(int aantal) {
        setVoorraad(getVoorraad() - aantal);
    }

    public String toString() {
        return getNaam() + ": " + getVoorraad() + " " + getEenheid();
    }

    public String printInfo(boolean btw) {
        return getNaam() + ": " + getVoorraad() + " " + getEenheid() + ", prijs: " + getPrijs(1, btw);
    }
}

