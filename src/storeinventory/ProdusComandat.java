package storeinventory;

public class ProdusComandat {
	private Produs produs;
	private double taxa;
	private int cantitate;

	// constructori
	public ProdusComandat() {
	}
	
	public ProdusComandat(Produs produs) {
		setProdus(produs);
	}
	
	// setteri
	public void setProdus(Produs produs) {
		this.produs = produs;
	}
	
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	
	// getteri
	public Produs getProdus() {
		return produs;
	}
	
	public double getTaxa() {
		return taxa;
	}
	
	public double getCantitate() {
		return cantitate;
	}
	
	// toString
	public String toString() {
		return produs + " cu taxa " + taxa + ", cantitate " + cantitate;
	}
}
