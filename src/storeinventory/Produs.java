package storeinventory;

public class Produs {
	private String nume;
	private String categorie;
	private String taraOrigine;
	private double pret;
	
	// constructori
	public Produs() {
	}
	
	public Produs(String nume) {
		this.nume = nume;
	}
	
	// setteri
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	public void setTaraOrigine(String taraOrigine) {
		this.taraOrigine = taraOrigine;
	}
	
	public void setPret(double pret) {
		this.pret = pret;
	}
	
	// getteri
	public String getNume() {
		return nume;
	}
	
	public String getCategorie() {
		return categorie;
	}
	
	public String getTaraOrigine() {
		return taraOrigine;
	}
	
	public double getPret() {
		return pret;
	}
	
	// toString
	public String toString() {
		return nume + " " + categorie + " " + taraOrigine + " " + pret;
	}

}
