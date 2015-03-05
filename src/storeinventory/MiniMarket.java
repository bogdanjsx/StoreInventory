package storeinventory;

public class MiniMarket extends Magazin {

	// constructori
	public MiniMarket() {
		super();
	}
	
	public MiniMarket(String nume) {
		super(nume);
	}
	
	// returneaza procentul de taxe scutite
	@Override
	public double calculScutiriTaxe() {
		// daca exista o tara a carei suma depaseste 50%
		// din suma totala, scutirea este de 10%
		for(String tara : Gestiune.getInstanta().getTari())
			if((getTotalCuTaxe() * 50 / 100) < getTotalTaraCuTaxe(tara))
				return 10;
		return 0;
	}

	// returneaza tipul magazinului
	@Override
	public String tip() {
		return "MiniMarket";
	}
}
