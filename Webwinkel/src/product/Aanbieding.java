package product;

/**
 * Created by Kyra on 19/03/2016.
 */
public class Aanbieding extends DefaultProduct {

    private int korting; // in procenten

    public Aanbieding(String naam, int prijs, int korting, int voorraad, Eenheid eenheid) {
        super(naam, prijs, voorraad, eenheid);
        this.korting = korting;
    }

    public Aanbieding(String naam, int prijs, int korting, int voorraad) {
        super(naam, prijs, voorraad);
        this.korting = korting;
    }

    public Aanbieding(String naam, int prijs, int korting, Eenheid eenheid) {
        super(naam, prijs, eenheid);
        this.korting = korting;
    }

    @Override
    public int getPrijs(int hoeveelheid, boolean inclusief) {
        int gewonePrijs = super.getPrijs(hoeveelheid, inclusief);
        int kortingsBedrag = (gewonePrijs * korting)/100;
        return gewonePrijs - kortingsBedrag;
    }

    @Override
    public int getPrijs(int hoeveelheid) {
        return getPrijs(hoeveelheid, true);
    }
}
