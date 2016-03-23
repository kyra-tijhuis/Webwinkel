package product;

import bestelling.IBezorgbaar;

public class Voedingsmiddel extends Product implements IBezorgbaar {

	Bezorging bezorging;
	
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

	@Override
	public Bezorging getBezorging() {
		return bezorging;
	}

	@Override
	public void setBezorging(Bezorging b) {
		this.bezorging = b;		
	}

}
