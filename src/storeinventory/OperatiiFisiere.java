package storeinventory;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

class ComparatorMagazinSumaTotala implements Comparator<Magazin> {
	
	// compara cele 2 magazine dupa tipul lor, apoi dupa suma totala fara taxe
	@Override
	public int compare(Magazin magazin1, Magazin magazin2) {
		
		if(magazin1.tip().equals(magazin2.tip())) {
			if(magazin1.getTotalFaraTaxe() < magazin2.getTotalFaraTaxe())
				return -1;
			else if(magazin1.getTotalFaraTaxe() > magazin2.getTotalFaraTaxe())
				return 1;
			return 0;
		}
		else if(magazin1.tip() == "HyperMarket")
			return 1;
		else if(magazin1.tip() == "MiniMarket")
			return -1;
		else if(magazin2.tip() == "HyperMarket")
			return -1;
		return 1;
	}
}

class ComparatorFacturaSumaTotala implements Comparator<Factura> {

	// compara 2 facturi dupa suma totala fara taxe
	@Override
	public int compare(Factura factura1, Factura factura2) {
		if(factura1.getTotalFaraTaxe() < factura2.getTotalFaraTaxe())
			return -1;
		if(factura1.getTotalFaraTaxe() > factura2.getTotalFaraTaxe())
			return 1;
		return 0;
	}
	
}

public class OperatiiFisiere {
	// citeste datele din fisierul produse.txt si retine preturile lor
	// precum si o lista cu toate categoriile existente
	public static void citesteProduse(String numeFisier) throws IOException {
		BufferedReader read = null;
		try {
			read = new BufferedReader(new FileReader(numeFisier));
		} catch (FileNotFoundException e) {
			System.out.println("Fisierul nu exista!");
			e.printStackTrace();
		}
		
		List<Produs> produse = new LinkedList<>();
		List<String> tari = new LinkedList<>();
		List<String> categorii = new LinkedList<>();
		
		String linie = read.readLine();
		String[] cuvinte = linie.split("\\s+");
		
		// citesc prima linie si retin tarile
		for(int i = 2; i < cuvinte.length; ++i)
			tari.add(cuvinte[i]);
		
		// citesc restul liniilor si retin fiecare produs
		while((linie = read.readLine()) != null) {
			// impartim linia in cuvinte, dupa spatii
			cuvinte = linie.split("\\s+");
			String nume = cuvinte[0];
			String categorie = cuvinte[1];
			categorii.add(categorie);
			for(int i = 2; i < cuvinte.length; ++i) {
				// adaugam cate un produs nou pentru fiecare tara de origine
				Produs produs = new Produs(nume);
				produs.setCategorie(categorie);
				produs.setTaraOrigine(tari.get(i - 2));
				if(Double.parseDouble(cuvinte[i]) == 0)
					continue;
				produs.setPret(Double.parseDouble(cuvinte[i]));
				produse.add(produs);
			}
		}

		Gestiune.getInstanta().setProduse(produse);
		Gestiune.getInstanta().setTari(tari);
		Gestiune.getInstanta().setCategorii(categorii);
		read.close();
	}
	
	// citeste datele din fisierul taxe.txt si retine toate taxele
	// pentru produse, precum si lista tuturor tarilor si a categoriilor
	public static void citesteTaxe(String numeFisier) throws IOException {
		BufferedReader read = null;
		try {
			read = new BufferedReader(new FileReader(numeFisier));
		} catch (FileNotFoundException e) {
			System.out.println("Fisierul nu exista!");
			e.printStackTrace();
		}
		
		Map<String, Map<String, Double>> taxe = new HashMap<>();
		
		// citim tarile si le retinem in Gestiune
		List<String> tari = new LinkedList<>();
		String linie = read.readLine();
		// impartim linia in cuvinte, dupa spatii
		String[] cuvinte = linie.split("\\s+");
		for(int i = 1; i < cuvinte.length; i++)
			tari.add(cuvinte[i]);
		
		// citim toate categoriile si taxele aferente
		List<String> categorii = new LinkedList<>();
		while((linie = read.readLine()) != null) {
			// impartim linia in cuvinte, dupa spatii
			cuvinte = linie.split("\\s+");
			categorii.add(cuvinte[0]);
			for(int i = 1; i < cuvinte.length; ++i) {
				Map<String, Double> taxaCategorie = taxe.get(cuvinte[0]);
				// daca nu am introdus nicio taxa pana acum, alocam memorie
				if(taxaCategorie == null)
					taxaCategorie = new HashMap<>();
				taxaCategorie.put(tari.get(i - 1), Double.parseDouble(cuvinte[i]));
				taxe.put(cuvinte[0], taxaCategorie);
			}
		}
		Gestiune.getInstanta().setTaxe(taxe);
		Gestiune.getInstanta().setCategorii(categorii);
		read.close();
	}
	
	// citeste datele din fisierul facturi.txt si retine toate magazinele
	// impreuna cu facturile aferente lor
	public static void citesteMagazine(String numeFisier) throws IOException {
		BufferedReader read = null;
		try {
			read = new BufferedReader(new FileReader(numeFisier));
		} catch (FileNotFoundException e) {
			System.out.println("Fisierul nu exista!");
			e.printStackTrace();
		}
		
		List<Magazin> magazine = new LinkedList<>();
		
		String linie;
		while((linie = read.readLine()) != null) {
			// impartim linia in cuvinte, dupa caracterele ' ' si ':'
			String[] cuvinte = linie.split("[\\s\\:]+");
			
			// daca linia incepe cu "Magazin", adaugam un magazin nou
			if(linie.startsWith("Magazin"))
				magazine.add(FactoryMagazin.buildMagazin(cuvinte[1], cuvinte[2]));
			
			// daca linia incepe cu "Factura", adaugam
			// o factura noua la ultimul magazinarg0
			else if(linie.startsWith("Factura")) {
				Magazin magazin = magazine.remove(magazine.size() - 1);
				magazin.addFactura(new Factura(cuvinte[0]));
				magazine.add(magazin);
				// sar peste linia descriptiva
				read.readLine();
			}
			
			// altfel, adaugam un produs nou la ultima factura a ultimului magazin
			else if(linie.isEmpty() == false) {
				Magazin magazin = magazine.remove(magazine.size() - 1);
				// setam proprietatile produsului din datele citite anterior
				ProdusComandat produs = new ProdusComandat(
						Gestiune.getInstanta().cautaProdus(cuvinte[0], cuvinte[1]));
				produs.setCantitate(Integer.parseInt(cuvinte[2]));
				produs.setTaxa(Gestiune.getInstanta().getTaxe().get(produs.getProdus()
								.getCategorie()).get(produs.getProdus().getTaraOrigine()));
				magazin.getUltimaFactura().addProdus(produs);
				magazine.add(magazin);
			}
		}
		Gestiune.getInstanta().setMagazine(magazine);
		read.close();
	}
	
	// scrie datele magazinelor in formatul corect, in fisierul primit parametru
	public static void scrieDate(String numeFisier) throws IOException {
		BufferedWriter write = new BufferedWriter(new FileWriter(numeFisier));
		
		// format pentru afisarea numerelor cu 3 zecimale
		DecimalFormat zecimale = new DecimalFormat("#.###");
		List<Magazin> magazineOrdonat = Gestiune.getInstanta().getMagazine();
		Collections.sort(magazineOrdonat, new ComparatorMagazinSumaTotala());
		String tipActual = "";
		for(Magazin magazin : magazineOrdonat) {
			// trec la un nou tip de magazine
			if(tipActual.equals(magazin.tip()) == false) {
					write.write(magazin.tip() + '\n');
					tipActual = magazin.tip();
			}
			List<Factura> facturiOrdonat = magazin.getFacturi();
			Collections.sort(facturiOrdonat, new ComparatorFacturaSumaTotala());
			// scriu numele magazinului
			write.write(magazin.getNume() + "\n\n");
			// scriu cele 3 sume totale
			write.write("Total " + zecimale.format(magazin.getTotalFaraTaxe()) + " " +
					zecimale.format(magazin.getTotalCuTaxe()) + " " +
					zecimale.format(magazin.getTotalCuTaxeScutite()) + "\n\n");
			// scriu sumele pentru fiecare tara
			write.write("Tara" + '\n');
			List<String> tariOrdonat = Gestiune.getInstanta().getTari();
			Collections.sort(tariOrdonat);
			// scriu totalul pentru fiecare tarile
			for(String tara : tariOrdonat) {
				write.write(tara + " ");
				if(magazin.getTotalTaraFaraTaxe(tara) == 0)
					write.write("0\n");
				else
					write.write(zecimale.format(magazin.getTotalTaraFaraTaxe(tara)) + " " +
						zecimale.format(magazin.getTotalTaraCuTaxe(tara)) + " " +
						zecimale.format(magazin.getTotalTaraCuTaxeScutite(tara)) + "\n");
			}
			write.write("\n");
			// scriu toate facturile magazinului
			for(Factura factura : facturiOrdonat) {
				// scriu numele facturii
				write.write(factura.getNume() + "\n\n");
				write.write("Total " + zecimale.format(factura.getTotalFaraTaxe()) + " " +
						zecimale.format(factura.getTotalCuTaxe()) + " " + "\n\n");
				// scriu totalul pentru fiecare tara
				write.write("Tara\n");
				for(String tara : tariOrdonat) {
					write.write(tara + " ");
					if(factura.getTotalTaraFaraTaxe(tara) == 0)
						write.write("0\n");
					else
						write.write(zecimale.format(factura.getTotalTaraFaraTaxe(tara)) + " " +
							zecimale.format(factura.getTotalTaraCuTaxe(tara)) + "\n");
				}
				write.write("\n");
			}
		}
		write.close();
	}
	
	// actualizeaza fisierul cu produse dupa adaugare/stergere
	public static void updateProduse(List<Produs> produse, String numeFisier) throws IOException {
		BufferedWriter write = new BufferedWriter(new FileWriter(numeFisier));
		Gestiune.getInstanta().setProduse(produse);
		List<String> produseAdaugate = new ArrayList<>();
		write.write("Produs Categorie");
		for(String tara : Gestiune.getInstanta().getTari()) {
			// sterg o tara daca nu mai exista produse de acolo
			if(Gestiune.getInstanta().existaProduseDinTara(tara) == false) {
				Gestiune.getInstanta().removeTara(tara);
				continue;
			}
			write.write(" " + tara);
		}
		write.write("\n");
		for(Produs produs : produse) {
			// trec produsul o singura data
			if(produseAdaugate.contains(produs.getNume()))
				continue;
			produseAdaugate.add(produs.getNume());
			write.write(produs.getNume() + " " + produs.getCategorie());
			for(String tara : Gestiune.getInstanta().getTari()) {
				if(Gestiune.getInstanta().cautaProdus(
						produs.getNume(), tara) == null)
					write.write(" 0");
				else
				write.write(" " + Gestiune.getInstanta().
						cautaProdus(produs.getNume(), tara).getPret());
			}
			write.write("\n");
		}
		write.close();
	}
}
