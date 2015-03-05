package storeinventory;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class TabIncarcare extends JPanel {
	private static final long serialVersionUID = 1L;

	// initializam panel-ul
	public TabIncarcare() {
		setLayout(null);
		
		
		// textul descriptiv
		JLabel labelText = new JLabel("Apasati pe buton pentru a incarca fisierele!");
		labelText.setBounds(159, 66, 333, 15);
		add(labelText);
		
		// butonul de incarcare
		JButton butonIncarcare = new JButton("Incarcare");
		// la apasare, se instiinteaza utilizatorul ca datele au fost incarcate
		butonIncarcare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
					    "Fisierele au fost incarcate!", "Informatie",
					    JOptionPane.INFORMATION_MESSAGE);
				try {
					StartFaraGrafica.main(null);
				} catch (IOException e) {
					System.out.println("Exceptie");
					e.printStackTrace();
				}
			}
		});
		butonIncarcare.setMnemonic(KeyEvent.VK_I);
		butonIncarcare.setToolTipText("Incarca informatiile din fisiere");
		butonIncarcare.setBounds(259, 93, 117, 25);
		add(butonIncarcare);
	}
}
