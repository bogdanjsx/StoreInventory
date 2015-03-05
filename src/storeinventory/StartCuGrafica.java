package storeinventory;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StartCuGrafica extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;

	// pornim aplicatia
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartCuGrafica frame = new StartCuGrafica();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("Exceptie!");
					e.printStackTrace();
				}
			}
		});
	}

	// in constructor initializam fereastra
	public StartCuGrafica() throws ClassNotFoundException, InstantiationException,
	IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Gestiune facturi fiscale");
		
		ImageIcon icon = new ImageIcon("fisiere/logostart.png");
		setIconImage(icon.getImage());
		
		// panel-ul principal
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);
		
		// panel-ul cu taburi
		JTabbedPane panelTaburi = new JTabbedPane(JTabbedPane.TOP);
		// tab pagina de start
		panelTaburi.addTab("Start", null, new TabStart(), "Pagina de start a aplicatiei");
		panelTaburi.setMnemonicAt(0, KeyEvent.VK_1);
		// tab pagina incarcare fisiere
		panelTaburi.addTab("Incarcare fisiere", null, new TabIncarcare(),
				"Incarcarea fisierelor cu produse si magazine");
		panelTaburi.setMnemonicAt(1, KeyEvent.VK_2);
		// tab pagina gestiune produse
		panelTaburi.addTab("Gestiune produse", null, new TabGestiune(), 
				"Adaugarea si stergerea produselor");
		panelTaburi.setMnemonicAt(2, KeyEvent.VK_3);
		// tab pagina statistici
		panelTaburi.addTab("Statistici", null, new TabStatistici(), 
				"Statistici despre vanzarile magazinelor");
		panelTaburi.setMnemonicAt(3, KeyEvent.VK_4);
		// adaugam panel-ul cu taburi la cel principal
		panelPrincipal.add(panelTaburi, BorderLayout.CENTER);
	}
}
