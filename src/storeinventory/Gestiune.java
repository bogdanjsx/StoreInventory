package storeinventory;

import java.util.*;

public class Gestiune {

	private List<Produs> produse;
	private List<Magazin> magazine;
	private Map<String, Map<String, Double>> taxe;
	
	private List<String> tari;
	private List<String> categorii;

	private static Gestiune instanta;
	
	// constructori
	// protected pentru a nu putea fi apelat din afara clasei sau a
	// claselor ce o mostenesc
	protected Gestiune() {
	}
	
	// functia ce tine loc de constructor/returneaza instanta
	// folosim lazy initialization, adica instantiem clasa
	// doar cand avem nevoie de ea
	public static Gestiune getInstanta() {
		if(instanta == null)
			instanta = new Gestiune();
		return instanta;
	}
	
	// getteri
	public void setProduse(List<Produs> produse) {
		instanta.produse = produse;
	}
	
	public void setMagazine(List<Magazin> magazine) {
		instanta.magazine = magazine;
	}
	
	public void setTaxe(Map<String, Map<String, Double>> taxe) {
		instanta.taxe = taxe;
	}
	
	public void setTari(List<String> tari) {
		instanta.tari = tari;
	}
	
	public void setCategorii(List<String> categorii) {
		instanta.categorii = categorii;
	}
	
	// getteri
	public List<Produs> getProduse() {
		return produse;
	}
	
	public List<Magazin> getMagazine() {
		return magazine;
	}
	
	public Map<String, Map<String, Double>> getTaxe() {
		return taxe;
	}
	
	public List<String> getTari() {
		return tari;
	}
	
	public List<String> getCategorii() {
		return categorii;
	}
	
	// returneaza informatiile unui produs dupa numele si tara sa de origine
	public Produs cautaProdus(String nume, String taraOrigine) {
		for(Produs produs : instanta.produse)
			if(produs.getNume().equalsIgnoreCase(nume) 
			&& produs.getTaraOrigine().equalsIgnoreCase(taraOrigine))
				return produs;
		return null;
	}
	
	public Produs cautaProdus(String nume) {
		for(Produs produs : instanta.produse)
			if(produs.getNume().equalsIgnoreCase(nume))
				return produs;
		return null;
	}
	
	// returneaza magazinul cu suma cu taxe maxima
	public Magazin getMagazinMaxim() {
		Magazin rezultat = magazine.get(0);
		for(Magazin magazin : magazine)
			if(magazin.getTotalCuTaxe() > rezultat.getTotalCuTaxe())
				rezultat = magazin;
		return rezultat;
	}
	
	// returneaza magazinul cu suma cu taxe maxima dintr-o tara
	public Magazin getMagazinMaximTara(String tara) {
		Magazin rezultat = magazine.get(0);
		for(Magazin magazin : magazine)
			if(magazin.getTotalTaraCuTaxe(tara) > rezultat.getTotalTaraCuTaxe(tara))
				rezultat = magazin;
		return rezultat;
	}
	
	// returneaza magazinul cu suma cu taxe maxima dintr-o categorie
	public Magazin getMagazinMaximCategorie(String categorie) {
		Magazin rezultat = magazine.get(0);
		for(Magazin magazin : magazine)
			if(magazin.getTotalCategorieCuTaxe(categorie) >
				rezultat.getTotalCategorieCuTaxe(categorie))
				rezultat = magazin;
		return rezultat;
	}
	
	// returneaza factura cu suma cu taxe maxima
	public Factura getFacturaMaxima() {
		Factura rezultat = magazine.get(0).getUltimaFactura();
		for(Magazin magazin : magazine)
			for(Factura factura : magazin.getFacturi())
				if(factura.getTotalFaraTaxe() > rezultat.getTotalFaraTaxe())
					rezultat = factura;
		return rezultat;
	}
	
	// adauga o tara la lista tuturor tarilor
	public void addTara(String tara) {
		tari.add(tara);
	}
	
	// sterge o tara din lista tuturor tarilor
	public void removeTara(String tara) {
		tari.remove(tara);
	}
	
	// returneaza true daca exista minim un produs din tara
	public boolean existaProduseDinTara(String tara) {
		for(Produs produs : produse)
			if(produs.getTaraOrigine().equals(tara))
				return true;
		return false;
	}
	
	// toString
	public String toString() {
		return "Magazine: " + magazine;
	}
}
