package storeinventory;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JTextPane;

public class TabStatistici extends JPanel {
	private static final long serialVersionUID = 1L;

	public TabStatistici() {
		setLayout(new GridLayout(2, 2, 10, 10));
		
		// fontul pe care il vom folosi
		Font font = new Font("Serif", Font.HANGING_BASELINE, 12);
		
		// Magazinul cu cele mai mari vanzari
		DecimalFormat zecimale = new DecimalFormat("#.###");
		JTextPane textMaxim = new JTextPane();
		Magazin magazinMaxim = Gestiune.getInstanta().getMagazinMaxim();
		textMaxim.setText("Magazinul cu cele mai mari vanzari:\n" +
				magazinMaxim.getNume() + "\nTotal: " 
				+ zecimale.format(magazinMaxim.getTotalFaraTaxe())
				+ " " + zecimale.format(magazinMaxim.getTotalCuTaxe())
				+ " " + zecimale.format(magazinMaxim.getTotalCuTaxeScutite()));
		textMaxim.setEditable(false);
		textMaxim.setFont(font);
		JScrollPane scrollMaxim = new JScrollPane(textMaxim);
		add(scrollMaxim);
		
		// Magazinul cu cele mai mari vanzari pentru fiecare tara
		JTextPane textMaximTari = new JTextPane();
		String textFinal = "Magazinul cu cele mai mari vanzari pentru fiecare tara:\n";
		for(String tara : Gestiune.getInstanta().getTari()) {
			magazinMaxim = Gestiune.getInstanta().getMagazinMaximTara(tara);
			textFinal += tara + ": " + magazinMaxim.getNume() + "\nTotal: " 
					+ zecimale.format(magazinMaxim.getTotalFaraTaxe())
					+ " " + zecimale.format(magazinMaxim.getTotalCuTaxe())
					+ " " + zecimale.format(magazinMaxim.getTotalCuTaxeScutite())
					+ '\n';
		}
		textMaximTari.setText(textFinal);
		textMaximTari.setFont(font);
		JScrollPane scrollMaximTari = new JScrollPane(textMaximTari);
		add(scrollMaximTari);
		
		// Magazinul cu cele mai mari vanzari pentru fiecare categorie
		JTextPane textMaximCategorii = new JTextPane();
		textFinal = "Magazinul cu cele mai mari vanzari pentru fiecare categorie:\n";
		for(String categorie : Gestiune.getInstanta().getCategorii()) {
			magazinMaxim = Gestiune.getInstanta().getMagazinMaximCategorie(categorie);
			textFinal += categorie + ": " + magazinMaxim.getNume() + "\nTotal: " 
					+ zecimale.format(magazinMaxim.getTotalFaraTaxe())
					+ " " + zecimale.format(magazinMaxim.getTotalCuTaxe())
					+ " " + zecimale.format(magazinMaxim.getTotalCuTaxeScutite())
					+ '\n';
		}
		textMaximCategorii.setText(textFinal);
		textMaximCategorii.setFont(font);
		JScrollPane scrollMaximCategorii = new JScrollPane(textMaximCategorii);
		add(scrollMaximCategorii);
		
		// factura cu cea mai mare suma
		JTextPane textMaximFactura = new JTextPane();
		Factura facturaMaxima = Gestiune.getInstanta().getFacturaMaxima();
		textMaximFactura.setText("Factura cu suma cea mai mare:\n" +
				facturaMaxima.getNume() + "\nTotal: " 
				+ zecimale.format(facturaMaxima.getTotalFaraTaxe())
				+ " " + zecimale.format(facturaMaxima.getTotalCuTaxe()));
		textMaximFactura.setFont(font);
		JScrollPane scrollMaximFactura = new JScrollPane(textMaximFactura);
		add(scrollMaximFactura);
	}
}
