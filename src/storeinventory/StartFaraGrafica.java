package storeinventory;

import java.io.IOException;

public class StartFaraGrafica {
	public static void main(String[] args) throws IOException{
		OperatiiFisiere.citesteProduse("fisiere/produse.txt");
		OperatiiFisiere.citesteTaxe("fisiere/taxe.txt");
		OperatiiFisiere.citesteMagazine("fisiere/facturi.txt");
		OperatiiFisiere.scrieDate("fisiere/out.txt");
	}
}
