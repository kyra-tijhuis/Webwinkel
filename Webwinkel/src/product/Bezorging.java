package product;

public class Bezorging extends Product {
	
	public Bezorging(String naam, int prijs) {
		super(naam, prijs, 0);
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
		return 0;
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
	public void haalUitVoorraad(int aantal) throws NegatieveVoorraadException {
		// bezorging is altijd op voorraad
	}

	

}