package storeinventory;

public class MediumMarket extends Magazin {

	// constructori
	public MediumMarket() {
		super();
	}
	
	public MediumMarket(String nume) {
		super(nume);
	}
	
	// returneaza procentul de taxe scutite
	@Override
	public double calculScutiriTaxe() {
	// daca exista o categorie pentru care suma totala
	// depaseste 50% din suma totala, scutirea este 5%
	for(String categorie : Gestiune.getInstanta().getCategorii())
			if((getTotalCuTaxe() * 50 / 100) < getTotalCategorieCuTaxe(categorie))
				return 5;
	return 0;
	}

	// returneaza tipul magazinului
	@Override
	public String tip() {
		return "MediumMarket";
	}
}
