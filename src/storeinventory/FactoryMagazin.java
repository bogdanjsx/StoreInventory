package storeinventory;

public class FactoryMagazin {
	
	// construieste un magazin de tipul parametrului tip
	public static Magazin buildMagazin(String tip, String nume) {
		Magazin magazin = null;
		switch(tip) {
		case "MiniMarket":
			magazin = new MiniMarket(nume);
			break;
		case "MediumMarket":
			magazin = new MediumMarket(nume);
			break;
		case "HyperMarket":
			magazin = new HyperMarket(nume);
			break;
		default:
			break;
		}	
		return magazin;
	}
}
