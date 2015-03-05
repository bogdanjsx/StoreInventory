package storeinventory;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogAdaugareProdus extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNume;
	private JTextField textFieldCategorie;
	private JTextField textFieldTaraOrigine;
	private JTextField textFieldPret;
	public Produs produs;

	public DialogAdaugareProdus() {
	}
	
	public DialogAdaugareProdus(JFrame frame, String title, Produs produs) {
		super(frame, title);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 160, 300);
		//setTitle("Produs nou");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNume = new JLabel("Nume");
			contentPanel.add(lblNume);
		}
		{
			textFieldNume = new JTextField();
			if(produs != null)
				textFieldNume.setText(produs.getNume());
			contentPanel.add(textFieldNume);
			textFieldNume.setColumns(10);
		}
		{
			JLabel lblCategorie = new JLabel("Categorie");
			contentPanel.add(lblCategorie);
		}
		{
			textFieldCategorie = new JTextField();
			if(produs != null)
				textFieldCategorie.setText(produs.getCategorie());
			contentPanel.add(textFieldCategorie);
			textFieldCategorie.setColumns(10);
		}
		{
			JLabel lblTaraOrigine = new JLabel("Tara Origine");
			contentPanel.add(lblTaraOrigine);
		}
		{
			textFieldTaraOrigine = new JTextField();
			if(produs != null)
				textFieldTaraOrigine.setText(produs.getTaraOrigine());
			contentPanel.add(textFieldTaraOrigine);
			textFieldTaraOrigine.setColumns(10);
		}
		{
			JLabel lblPret = new JLabel("Pret");
			contentPanel.add(lblPret);
		}
		{
			textFieldPret = new JTextField();
			if(produs != null)
				textFieldPret.setText("" + produs.getPret());
			contentPanel.add(textFieldPret);
			textFieldPret.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
				
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("OK")) {
			String nume = textFieldNume.getText();
			String categorie = textFieldCategorie.getText();
			String taraOrigine = textFieldTaraOrigine.getText();
			Double pret = 0.0;
			try {
			pret = Double.parseDouble(textFieldPret.getText());
			}
			catch(NumberFormatException exceptie) {
				JOptionPane.showMessageDialog(null,
					    "Pretul nu este un numar valid!", "Eroare",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}

			if(nume.isEmpty() == true || categorie.isEmpty() == true
					|| taraOrigine.isEmpty() == true) {
				JOptionPane.showMessageDialog(null,
					    "Nu ati completat toate campurile!", "Eroare",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			produs = new Produs(nume);
			produs.setCategorie(categorie);
			produs.setTaraOrigine(taraOrigine);
			produs.setPret(pret);
			setVisible(false);
			dispose();
		}
		else {
			produs = null;
			dispose();
		}

	}
}
