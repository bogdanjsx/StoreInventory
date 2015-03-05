package storeinventory;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;

class ComparatorProduseNume implements Comparator<Produs> {
	@Override
	public int compare(Produs produs1, Produs produs2) {
		return produs1.getNume().compareToIgnoreCase(produs2.getNume());
	}
}

class ComparatorProduseTaraOrigine implements Comparator<Produs> {
	@Override
	public int compare(Produs produs1, Produs produs2) {
		return produs1.getTaraOrigine().compareToIgnoreCase(produs2.getTaraOrigine());
	}
}

class ComparatorProduseCategorie implements Comparator<Produs> {
	@Override
	public int compare(Produs produs1, Produs produs2) {
		return produs1.getCategorie().compareToIgnoreCase(produs2.getCategorie());
	}
}

public class TabGestiune extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static List<Produs> produse;
	public static DefaultListModel<Produs> modelListaProduse;
	public static JList<Produs> listaProduse;
	private JTextField textCautare;
	
	public TabGestiune() {
		// folosesc o lista pentru a afisa produsele

		try {
			StartFaraGrafica.main(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// initializam lista de produse si le ordonam
		produse = Gestiune.getInstanta().getProduse();
		Collections.sort(produse, new ComparatorProduseNume());
		modelListaProduse = new DefaultListModel<>();
		for(Produs produs : produse)
			modelListaProduse.addElement(produs);
		listaProduse = new JList<>(modelListaProduse);
		JScrollPane listScroller = new JScrollPane(listaProduse);
		listScroller.setPreferredSize(new Dimension(600, 200));
		add(listScroller);
	    
	    // grupez butoanele intr-o grupa, astfel incat
	    // numai unul este selectat
	    ButtonGroup grupa = new ButtonGroup();
	    
	    JPanel panelButoaneOrdonare = new JPanel();
	    add(panelButoaneOrdonare);
	    
	    JLabel labelOrdonare = new JLabel("Criteriu Ordonare:");
	    labelOrdonare.setFont(new Font(labelOrdonare.getFont().getName(), Font.BOLD, 15));
	    panelButoaneOrdonare.add(labelOrdonare);
	    
	    // butoane pentru selectarea tipului de ordonare
	    JRadioButton butonNume = new JRadioButton("Nume");
	    panelButoaneOrdonare.add(butonNume);
	    butonNume.setMnemonic(KeyEvent.VK_N);
	    butonNume.setActionCommand("nume");
	    butonNume.setSelected(true);
	    butonNume.addActionListener(this);
	    butonNume.setToolTipText("Ordoneaza alfabetic dupa nume");
	    grupa.add(butonNume);
	    
		JRadioButton butonTaraOrigine = new JRadioButton("Tara de origine");
		panelButoaneOrdonare.add(butonTaraOrigine);
		butonTaraOrigine.setMnemonic(KeyEvent.VK_T);
		butonTaraOrigine.setActionCommand("taraorigine");
		butonTaraOrigine.addActionListener(this);
		butonTaraOrigine.setToolTipText("Ordoneaza alfabetic dupa tara de origine");
		grupa.add(butonTaraOrigine);
		
		JRadioButton butonCategorie = new JRadioButton("Categorie");
		panelButoaneOrdonare.add(butonCategorie);
		butonCategorie.setMnemonic(KeyEvent.VK_C);
		butonCategorie.setActionCommand("categorie");
		butonCategorie.addActionListener(this);
		butonCategorie.setToolTipText("Ordoneaza alfabetic dupa categorie");
		grupa.add(butonCategorie);

		// butoane pentru adaugarea, stergerea, editarea unui produs
		JPanel panelButoaneGestiune = new JPanel();
		add(panelButoaneGestiune);
		
		JButton butonAdaugare = new JButton("Adauga produs");
		butonAdaugare.setActionCommand("adaugare");
		butonAdaugare.addActionListener(this);
		butonAdaugare.setToolTipText("Adauga un produs nou");
		butonAdaugare.setMnemonic(KeyEvent.VK_A);
		panelButoaneGestiune.add(butonAdaugare);
		
		JButton butonStergere = new JButton("Sterge produs");
		butonStergere.setActionCommand("stergere");
		butonStergere.addActionListener(this);
		butonStergere.setToolTipText("Sterge produsul selectat");
		butonStergere.setMnemonic(KeyEvent.VK_S);
		panelButoaneGestiune.add(butonStergere);
		
		JButton butonEditare = new JButton("Editeaza produs");
		butonEditare.setActionCommand("editare");
		butonEditare.addActionListener(this);
		butonEditare.setToolTipText("Editeaza produsul selectat");
		butonEditare.setMnemonic(KeyEvent.VK_E);
		panelButoaneGestiune.add(butonEditare);

		
		JButton butonSalvare = new JButton("Salveaza produse");
		butonSalvare.setActionCommand("salvare");
		butonSalvare.addActionListener(this);
		butonSalvare.setToolTipText("Salveaza produsele in fisier");
		butonSalvare.setMnemonic(KeyEvent.VK_L);
		panelButoaneGestiune.add(butonSalvare);
		
		JPanel panelCautare = new JPanel();
		add(panelCautare);
		
		textCautare = new JTextField();
		panelCautare.add(textCautare);
		textCautare.setColumns(10);
		
		JButton butonCautare = new JButton("Cauta produs");
		butonCautare.setActionCommand("cautare");
		butonCautare.addActionListener(this);
		butonCautare.setToolTipText("Cauta produse cu acest nume");
		butonCautare.setMnemonic(KeyEvent.VK_P);
		panelCautare.add(butonCautare);
		    
	}

	// in cazul apasarii butoanelor
	@Override
	public void actionPerformed(ActionEvent e) {
		String butonApasat = e.getActionCommand();
		produse.removeAll(produse);
		for(int i = 0; i < modelListaProduse.getSize(); i++)
			produse.add(modelListaProduse.getElementAt(i));
		switch (butonApasat) {
		// sortari
		case "nume":
			Collections.sort(produse, new ComparatorProduseNume());
			modelListaProduse.clear();
			for(Produs produs : produse)
				modelListaProduse.addElement(produs);
			break;
		case "taraorigine":
			Collections.sort(produse, new ComparatorProduseTaraOrigine());
			modelListaProduse.clear();
			for(Produs produs : produse)
				modelListaProduse.addElement(produs);
			break;
		case "categorie":
			Collections.sort(produse, new ComparatorProduseCategorie());
			modelListaProduse.clear();
			for(Produs produs : produse)
				modelListaProduse.addElement(produs);
			break;
		// stergere
		case "stergere":
			if(listaProduse.getSelectedIndex() < 0)
				return;
			modelListaProduse.remove(listaProduse.getSelectedIndex());
			break;
		// adaugare
		case "adaugare":
			JFrame framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
			DialogAdaugareProdus dialog = new DialogAdaugareProdus(framePrincipal, "Produs nou", null);
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);
			Produs produs = dialog.produs;
			if(produs == null)
				break;
			if(Gestiune.getInstanta().getTari().contains(produs.getTaraOrigine())
					== false)
				Gestiune.getInstanta().addTara(produs.getTaraOrigine());
			produse.add(produs);
			modelListaProduse.addElement(dialog.produs);
			break;
		// editare
		case "editare":
			if(listaProduse.getSelectedIndex() < 0)
				return;
			produs = modelListaProduse.getElementAt(listaProduse.getSelectedIndex());
			framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
			dialog = new DialogAdaugareProdus(framePrincipal, "Editare produs", produs);
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);
			Produs produsNou = dialog.produs;
			if(produsNou == null)
				break;
			if(Gestiune.getInstanta().getTari().contains(produsNou.getTaraOrigine())
					== false)
				Gestiune.getInstanta().addTara(produsNou.getTaraOrigine());
			produse.add(produsNou);
			modelListaProduse.addElement(dialog.produs);
			modelListaProduse.removeElement(produs);
			break;
		// salvare
		case "salvare":
			try {
				OperatiiFisiere.updateProduse(produse, "fisiere/produse.txt");
			} catch (IOException e1) {
				System.out.println("Exceptie!");
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,
				    "Produsele au fost salvate!", "Informatie",
				    JOptionPane.INFORMATION_MESSAGE);
			break;
		// cautare
		case "cautare":
			//daca produsul nu exista, afisam eroare
			if(Gestiune.getInstanta().cautaProdus(textCautare.getText()) == null) {
				JOptionPane.showMessageDialog(null,
					    "Produsul nu exista!", "Eroare",
					    JOptionPane.ERROR_MESSAGE);
				break;
			}
			// afisam toate produsele cu acest nume
			String textProduse = "";
			for(String tara : Gestiune.getInstanta().getTari()) {
				produs = Gestiune.getInstanta().cautaProdus(
						textCautare.getText(), tara);
				if(produs != null)
					textProduse += produs + "\n";
			}
			JOptionPane.showMessageDialog(null,
				    textProduse, "Rezultat cautare",
				    JOptionPane.INFORMATION_MESSAGE);
		default:
			break;
		}
	}

}
