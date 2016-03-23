package product;

public class Voedingsmiddel extends Product {

	public Voedingsmiddel(String naam, int prijs, int voorraad, Eenheid eenheid) {
		super(naam, prijs, voorraad, eenheid);
		this.btw = 6;
	}

	@Override
	public String getNaam() {
		return naam;
	}

	@Override
	public int getPrijsPerEenheid() {
		return prijs;
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
	public int getVoorraad() {
		return voorraad;
	}

}
