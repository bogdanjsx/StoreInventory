package storeinventory;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;

public class TabStart extends JPanel {
	private static final long serialVersionUID = 1L;

	// initializam panel-ul
	public TabStart() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		// setam textul si imaginea
		JTextPane textWelcome = new JTextPane();
		textWelcome.setText("Bine ati venit!\nAceasta este o "
				+ "aplicatie pentru gestiunea produselor.\n" +
				"Puteti naviga printre taburi facand click pe ele, " + 
				"cu ajutor sagetilor, sau apasand Alt + numarul tabului\n\n");
		textWelcome.insertIcon(new ImageIcon("fisiere/logostart.png"));
		textWelcome.setEditable(false);
		// setam stilul textului
		StyledDocument doc = textWelcome.getStyledDocument();
		SimpleAttributeSet stil = new SimpleAttributeSet();
		StyleConstants.setAlignment(stil, StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontSize(stil, 15);
		StyleConstants.setForeground(stil, Color.RED);
		Font font = new Font("Serif", Font.BOLD, 21);
        textWelcome.setFont(font);
		doc.setParagraphAttributes(0, doc.getLength(), stil, false);
		add(textWelcome);
	}

}
