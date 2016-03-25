package product;

public class Bezorging extends Product {

	private String naam;
	private int prijs;
	private Eenheid eenheid;

	public Bezorging(String naam, int prijs) {
		super(naam, prijs, 0);
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
	public String toString() {
		return getNaam() + ", prijs: " + getPrijsPerEenheid();
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
		return 0;
	}

	@Override
	public Eenheid getEenheid() {
		return eenheid;
	}

	@Override
    protected void setEenheid(Eenheid eenheid) {
		// bezorging heeft altijd de eenheid STUK
	}

	@Override
	public int getVoorraad() {
		return voorraad;
	}

	@Override
	public void haalUitVoorraad(int aantal) throws NegatieveVoorraadException {
		// bezorging is altijd op voorraad
	}

}