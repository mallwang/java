package dvdverleih.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Hauptseite {

	public static void TabLoeschen(String name) {
		int index = tabbedPane.indexOfTab(name);
		if (index != -1) {
			tabbedPane.remove(index);
		}
	}

	public JFrame frame;
	private JLabel lblBestandskunde;
	private JLabel lblNeukunde;
	private JLabel lblKeinSuchbegriff;
	private JButton btnPreise;
	private JButton btnSuche;
	private JButton btnRegistrieren;
	private JButton btnProdukte;
	private static JTextField txtSuche;
	private static JTabbedPane tabbedPane;
	private JLabel lblKeinSuchtreffer;

	/**
	 * Die Hauptseite stellt das Startfenster der Anwendung dar, von wo aus
	 * Kunden oder Objekte verwaltet / gesucht werden koennen.
	 */
	public Hauptseite() {
		initialize();

		frame.setTitle("DVD-Verleih");
	}

	public String getTxtSuche() {
		if (txtSuche.getText().matches("[a-zA-Z0-9]*"))
			return txtSuche.getText();
		else
			return "";
	}

	private void initialize() {

		Image img = Toolkit.getDefaultToolkit().getImage(Hauptseite.class.getResource("/cd.png"));

		// Frame
		frame = new JFrame();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 619, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TabbedPane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 13));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(12, 26, 569, 341);

		// Starttab
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Start", null, panel, null);

		// Label
		lblBestandskunde = new JLabel("Bestandskunde");
		lblBestandskunde.setFont(new Font("Segoe UI", Font.BOLD, 19));
		lblBestandskunde.setForeground(new Color(245, 245, 245));
		lblBestandskunde.setBounds(70, 47, 157, 25);

		lblNeukunde = new JLabel("Neukunde");
		lblNeukunde.setFont(new Font("Segoe UI", Font.BOLD, 19));
		lblNeukunde.setForeground(new Color(245, 245, 245));
		lblNeukunde.setBounds(346, 93, 106, 25);

		lblKeinSuchbegriff = new JLabel(
				"Es wurde kein Suchbegriff (Name / ID) eingegeben!");
		lblKeinSuchbegriff.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblKeinSuchbegriff.setForeground(Color.RED);
		lblKeinSuchbegriff.setVisible(false);
		lblKeinSuchbegriff.setBounds(10, 284, 285, 14);

		lblKeinSuchtreffer = new JLabel("Die Suche ergab keinen Treffer.");
		lblKeinSuchtreffer.setVisible(false);
		lblKeinSuchtreffer.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblKeinSuchtreffer.setForeground(Color.RED);
		lblKeinSuchtreffer.setBounds(10, 285, 291, 14);

		// Button
		btnSuche = new JButton("Suche");
		btnSuche.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSuche.setBackground(UIManager.getColor("Button.background"));
		btnSuche.setActionCommand("OK");

		btnSuche.setBounds(70, 131, 135, 89);

		btnRegistrieren = new JButton("Registrieren");
		btnRegistrieren.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnRegistrieren.setBounds(330, 131, 141, 89);

		btnPreise = new JButton("Preise");
		btnPreise.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnPreise.setBounds(12, 378, 210, 50);

		// Text flied
		txtSuche = new JTextField();
		txtSuche.setBounds(68, 96, 137, 22);

		txtSuche.setColumns(10);

		// Hauptseite fuellen
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(tabbedPane);
		frame.getContentPane().add(btnPreise);
		panel.setLayout(null);
		panel.add(lblBestandskunde);
		panel.add(lblNeukunde);
		panel.add(lblKeinSuchbegriff);
		panel.add(lblKeinSuchtreffer);
		panel.add(btnSuche);
		panel.add(btnRegistrieren);
		panel.add(txtSuche);
		tabbedPane.getRootPane().setDefaultButton(btnSuche);

		btnProdukte = new JButton("Produktsuche / neu anlegen");
		btnProdukte.setBounds(342, 378, 239, 50);
		frame.getContentPane().add(btnProdukte);
		btnProdukte.setFont(new Font("Segoe UI", Font.BOLD, 15));

	}

	public void setLblKeinSuchbegriff(boolean b) {
		lblKeinSuchbegriff.setVisible(b);
	}

	public void setLblKeinSuchtreffer(boolean b) {
		lblKeinSuchtreffer.setVisible(b);
	}

	public void setListener_BtnPreise(MouseAdapter ma) {
		btnPreise.addMouseListener(ma);
	}

	public void setListener_BtnProdukte(MouseAdapter ma) {
		btnProdukte.addMouseListener(ma);
	}

	public void setListener_BtnRegistrieren(MouseAdapter ma) {
		btnRegistrieren.addMouseListener(ma);
	}

	public void setListener_BtnSuche(MouseAdapter ma) {
		btnSuche.addMouseListener(ma);
	}

	public void setListener_Schliessen(WindowAdapter wa) {
		frame.addWindowListener(wa);
	}

	public void TabSetzen(JPanel Panel, String id) {
		TabSetzen(Panel, id, true);
	}

	public void TabSetzen(JPanel Panel, String id, boolean b) {
		String name;
		if (!b) {
			name = id;
		} else {
			dvdverleih.funktionen.KKonto kk = new dvdverleih.funktionen.KKonto();
			name = kk.id2name(id);
		}
		int count = tabbedPane.indexOfTab(name);
		if (count == -1) {
			tabbedPane.addTab(name, null, Panel, null);
			Panel.setLayout(null);
		}
		count = tabbedPane.indexOfTab(name);
		tabbedPane.setSelectedIndex(count);
	}
}