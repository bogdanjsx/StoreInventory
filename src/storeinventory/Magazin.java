package storeinventory;

import java.util.LinkedList;
import java.util.List;

public abstract class Magazin implements IMagazin {
	private String nume;
	private List<Factura> facturi;
	
	// constructori
	public Magazin() {
		facturi = new LinkedList<>();
	}
	
	public Magazin(String nume) {
		this();
		this.nume = nume;
	}
	
	// getteri
	public String getNume() {
		return nume;
	}
	
	public List<Factura> getFacturi() {
		return facturi;
	}
	
	// returneaza ultima factura din lista de facturi
	public Factura getUltimaFactura() {
		return facturi.get(facturi.size()  - 1);
	}
	
	// adauga factura primita ca parametru la lista de facturi
	public void addFactura(Factura factura) {
		facturi.add(factura);
	}
	
	// returneaza suma totala a magazinului fara taxe
	@Override
	public double getTotalFaraTaxe() {
		double rezultat = 0;
		for(Factura factura : facturi)
			rezultat += factura.getTotalFaraTaxe();
		return rezultat;
	}

	// returneaza suma totala a magazinului cu taxe
	@Override
	public double getTotalCuTaxe() {
		double rezultat = 0;
		for(Factura factura : facturi)
			rezultat += factura.getTotalCuTaxe();
		return rezultat;
	}

	// returneaza suma totala a magazinului cu scutiri de taxe
	@Override
	public double getTotalCuTaxeScutite() {
		return getTotalCuTaxe() * (100 - calculScutiriTaxe()) / 100;
	}

	// returneaza suma totala pentru o tara fara taxe
	@Override
	public double getTotalTaraFaraTaxe(String tara) {
		double rezultat = 0;
		for(Factura factura : facturi)
			rezultat += factura.getTotalTaraFaraTaxe(tara);
		return rezultat;
	}

	// returneaza suma totala pentru o tara cu taxe
	@Override
	public double getTotalTaraCuTaxe(String tara) {
		double rezultat = 0;
		for(Factura factura : facturi)
			rezultat += factura.getTotalTaraCuTaxe(tara);
		return rezultat;
	}

	// returneaza suma totala pentru o tara cu scutiri de taxe
	@Override
	public double getTotalTaraCuTaxeScutite(String tara) {
		return getTotalTaraCuTaxe(tara) * (100 - calculScutiriTaxe()) / 100;
	}
	
	// returneaza suma totala pentru o categorie cu taxe
	public double getTotalCategorieCuTaxe(String categorie) {
		double rezultat = 0;
		for(Factura factura : facturi)
			rezultat += factura.getTotalCategorieCuTaxe(categorie);
		return rezultat;
	}
	
	// returneaza procentul de taxe scutite
	abstract public double calculScutiriTaxe();
	
	// returneaza un string cu tipul magazinului
	abstract public String tip();
	
	// toString
	public String toString() {
		return nume + "\n" + facturi;
	}

}
