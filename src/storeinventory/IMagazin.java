package storeinventory;

public interface IMagazin {
	// returneaza suma totala fara taxe
	public double getTotalFaraTaxe();
	
	// returneaza suma totala cu taxe
	public double getTotalCuTaxe();
	
	// returneaza suma totala dupa scutirea de taxe
	public double getTotalCuTaxeScutite();
	
	// returneaza suma totala fara taxe pentru o tara
	public double getTotalTaraFaraTaxe(String tara);
	
	// returneaza suma totala cu taxe pentru o tara
	public double getTotalTaraCuTaxe(String tara);
	
	// returneaza suma totala cu taxe pentru o categorie de produse
	public double getTotalCategorieCuTaxe(String categorie);
	
	// returneaza suma totala dupa scutirea de taxe pentru o tara
	public double getTotalTaraCuTaxeScutite(String tara);
	
	// returneaza procentajul de taxe scutite
	public double calculScutiriTaxe();
}
