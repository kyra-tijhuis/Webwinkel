package product;

import bestelling.IBezorgbaar;

public class Voedingsmiddel extends Product implements IBezorgbaar {

	private String naam;
	private int prijs;
	private Eenheid eenheid;
	private Bezorging bezorging;
	
	public Voedingsmiddel(String naam, int prijs, int voorraad, Eenheid eenheid) {
		super(naam, prijs, voorraad, eenheid);
		this.btw = 6;
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

	@Override
	public Bezorging getBezorging() {
		return bezorging;
	}

	@Override
	public void setBezorging(Bezorging b) {
		this.bezorging = b;		
	}

}
