package storeinventory;

public class HyperMarket extends Magazin {

	// constructori
	public HyperMarket() {
		super();
	}
	
	public HyperMarket(String nume) {
		super(nume);
	}
	
	// returneaza procentul de taxe scutite
	@Override
	public double calculScutiriTaxe() {
		// daca exista o factura ce depaseste 10% din suma
		// totala, procentul scutirilor este 10%
		for(Factura factura : getFacturi())
			if((getTotalCuTaxe() * 10 / 100) < factura.getTotalCuTaxe())
				return 1;
		return 0;
	}

	// returneaza tipul magazinului
	@Override
	public String tip() {
		return "HyperMarket";
	}
}
