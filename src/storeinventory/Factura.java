package storeinventory;

import java.util.LinkedList;
import java.util.List;

public class Factura {
	private String nume;
	private List<ProdusComandat> produse;
	
	// constructori
	public Factura() {
		produse = new LinkedList<>();
	}
	
	public Factura(String nume) {
		this();
		this.nume = nume;
	}
	
	// setteri
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	// getteri
	public String getNume() {
		return nume;
	}
	
	public List<ProdusComandat> getProduse() {
		return produse;
	}
	
	// adauga un produs la lista de produse a facturii
	public void addProdus(ProdusComandat produs) {
		produse.add(produs);
	}
	
	// returneaza suma totala a facturii fara taxe
	public double getTotalFaraTaxe() {
		double rezultat = 0;
		for(ProdusComandat produs : produse)
			rezultat = rezultat + produs.getCantitate() * produs.getProdus().getPret();
		return rezultat;
	}

	// returneaza suma totala a facturii cu taxe
	public double getTotalCuTaxe() {
		double rezultat = 0;
		for(ProdusComandat produs : produse)
			rezultat = rezultat + produs.getCantitate() * produs.getProdus().getPret()
			* (produs.getTaxa() + 100) / 100;
		return rezultat;
	}

	// returneaza totalul taxelor
	public double getTaxe() {
		return getTotalCuTaxe() - getTotalFaraTaxe();
	}
	
	// returneaza suma totala fara taxe pentru tara primita ca parametru
	public double getTotalTaraFaraTaxe(String tara) {
		double rezultat = 0;
		for(ProdusComandat produs : produse)
			if(produs.getProdus().getTaraOrigine().equals(tara))
				rezultat = rezultat + produs.getCantitate() * produs.getProdus().getPret();
		return rezultat;
	}
	
	// returneaza suma totala cu taxe pentru tara primita ca parametru
	public double getTotalTaraCuTaxe(String tara) {
		double rezultat = 0;
		for(ProdusComandat produs : produse)
			if(produs.getProdus().getTaraOrigine().equals(tara))
				rezultat = rezultat + produs.getCantitate() * produs.getProdus().getPret()
				* (produs.getTaxa() + 100) / 100;;
		return rezultat;
	}
	
	// returneaza suma totala cu taxe pentru categoria primita ca parametru
	public double getTotalCategorieCuTaxe(String categorie) {
		double rezultat = 0;
		for(ProdusComandat produs : produse)
			if(produs.getProdus().getCategorie().equals(categorie))
				rezultat = rezultat + produs.getCantitate() * produs.getProdus().getPret()
				* (produs.getTaxa() + 100) / 100;;
		return rezultat;
	}
	
	// returneaza totalul taxelor pentru o tara
	public double getTaxeTara(String tara) {
		return getTotalTaraCuTaxe(tara) - getTotalTaraFaraTaxe(tara);
	}
	
	// toString
	public String toString() {
		String rezultat = nume + ":\n";
		for(ProdusComandat produs : produse)
			rezultat = rezultat + "\n" + produs;
		return rezultat;
	}	
}
