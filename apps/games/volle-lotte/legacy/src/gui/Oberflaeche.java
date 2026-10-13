package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class Oberflaeche {

	public static JFrame frame;
	public static JPanel panel, panelRegeln;
	public static JLabel lblHome;
	public static JTextArea tarRegeln;
	public static JTextField txtRegeln;
	public static JMenuBar menubar;
	public static JMenu menuDatei, menuOptionen, menuHilfe;
	public static JMenuItem itmNeu, itmLaden, itmSchliessen, itmRegeln;

	// Neues Spielbrett erstellen
	public void newGame() {
		initialize();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private static void initialize() {
		// Neues Frame erstellen
		frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Volle Lotte Version 0.1");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		// Fenstergröße abfragen
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int screenWidth = (int) screen.getWidth();
		int screenHeight = (int) screen.getHeight();
		int gameWidth = 600;
		int gameHeight = 400;

		frame.setBounds(screenWidth / 2 - gameWidth / 2, screenHeight / 2
				- gameHeight / 2 - 100, 586, 400);

		// Neues Panel erstellen
		panel = new JPanel();
		panel.setBounds(0, 30, 594, 338);
		panel.setLayout(null);

		// Panel erstellen
		panelRegeln = new JPanel();
		panelRegeln.setBounds(0, 30, 594, 338);
		panelRegeln.setLayout(null);

		// Textfeld erstellen
		txtRegeln = new JTextField();
		txtRegeln.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegeln.setBounds(10, 10, 577, 24);
		txtRegeln.setFont(new Font("Arial", Font.BOLD, 15));
		txtRegeln.setBackground(SystemColor.control);
		txtRegeln.setText("Regeln des Spiels \"Volle Lotte\"");

		// Textarea erstellen
		tarRegeln = new JTextArea();
		tarRegeln.setBackground(SystemColor.control);
		tarRegeln.setFont(new Font("Arial", Font.PLAIN, 12));
		tarRegeln
				.setText("Spielziel: Wer zuerst die vorher festgelegte Punktzahl erreicht, hat gewonnen.\r\n\r\nSpielverlauf: ...");
		tarRegeln.setBounds(10, 47, 577, 240);

		// Elemente hinzufügen
		panelRegeln.add(txtRegeln);
		panelRegeln.add(tarRegeln);

		// Label erstellen
		lblHome = new JLabel("HINTERGRUNDBILD");
		lblHome.setIcon(new ImageIcon(
				"C:\\workspace\\VolleLotte\\img\\Background_Card.png"));
		lblHome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setBounds(0, 0, 594, 338);
		lblHome.setBackground(SystemColor.control);
		lblHome.setForeground(SystemColor.desktop);
		lblHome.setOpaque(true);

		// Menubar erstellen
		menubar = new JMenuBar();
		menubar.setBounds(0, 0, 594, 30);

		Font font = new Font("Arial", Font.PLAIN, 15);
		Font font1 = new Font("Arial", Font.BOLD, 18);

		// Menüs erstellen
		menuDatei = new JMenu("Datei");
		menuDatei.setFont(font);
		menuOptionen = new JMenu("Optionen");
		menuOptionen.setFont(font);
		menuHilfe = new JMenu("Hilfe");
		menuHilfe.setFont(font);

		// Menuitems erstellen
		itmSchliessen = new JMenuItem("Schließen");
		itmSchliessen.setFont(font);
		itmNeu = new JMenuItem("Neues Spiel");
		itmNeu.setFont(font);
		itmLaden = new JMenuItem("Laden");
		itmLaden.setFont(font);
		itmRegeln = new JMenuItem("Regelwerk");
		itmRegeln.setFont(font);

		frame.getContentPane().add(panel);

		menuDatei.add(itmNeu);
		menuDatei.add(itmLaden);
		menuDatei.add(itmSchliessen);
		menuHilfe.add(itmRegeln);
		// menuOptionen.add();

		menubar.add(menuDatei);
		menubar.add(menuHilfe);
		// menubar.add(menuOptionen);

		frame.getContentPane().add(menubar);
		panel.add(lblHome);

		frame.setVisible(true);

	}

	/* SETTER */
	public void setListenerSchliessen(MouseAdapter ma) {
		itmSchliessen.addMouseListener(ma);
	}

	public void setListenerNeuesSpiel(MouseAdapter ma) {
		itmNeu.addMouseListener(ma);
	}

	public void setListenerLaden(MouseAdapter ma) {
		itmLaden.addMouseListener(ma);
	}

	public void setListenerRegelwerk(MouseAdapter ma) {
		itmRegeln.addMouseListener(ma);
	}

	public void setRules() {
		frame.remove(panel);
		frame.getContentPane().add(panelRegeln);
		frame.repaint();
	}

	/* GETTER */
	public JFrame getFrame() {
		return this.frame;
	}

	public JPanel getPanel() {
		return this.panel;
	}

}