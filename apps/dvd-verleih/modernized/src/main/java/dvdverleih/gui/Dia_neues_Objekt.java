package dvdverleih.gui;

import dvdverleih.gui.Enums.Formate_Film_aendern;
import dvdverleih.gui.Enums.Formate_Spiel_aendern;
import dvdverleih.gui.Enums.Formate_Zubehoer_aendern;
import dvdverleih.gui.Enums.Genre_Film;
import dvdverleih.gui.Enums.Genre_Spiel;
import dvdverleih.gui.Enums.Genre_Zubehoer;
import dvdverleih.gui.Enums.Kategorie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Dia_neues_Objekt extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6511274830154042023L;
	private JButton btnObjekt_anlegen;
	private JButton btnZuruecksetzen;
	private JButton btnNeuDarsteller;
	private JButton btnSucheDarsteller;
	private JButton btnNeuRegie;
	private JButton btnSucheRegie;
	private JTextField txtTitel;
	private JTextField txtHersteller;
	private JTextField txtJahr;
	private JTextField txtFsk;
	private JTextField txtOriginaltitel;
	private JTextField txtTags;
	private JTextField txtPreis;
	private JTextField txtAnzahl;
	private JLabel lblArt;
	private JLabel lblGenre;
	private JLabel lblFormat;
	private JLabel lblTitel;
	private JLabel lblHersteller;
	private JLabel lblProduktionsjahr;
	private JLabel lblFsk;
	private JLabel lblOriginaltitel;
	private JLabel lblTags;
	private JLabel lblPreis;
	private JLabel lblAnzahl;
	private JLabel lblBezeichnung;
	private JLabel lblDarsteller;
	private JLabel lblRegie;
	private JLabel lblNichtKorrekt;
	private JComboBox dropdownKategorie;
	private JComboBox dropdownGenre;
	private JComboBox dropdownFormat;
	private JTextPane txtBeschreibung;
	private JPanel buttonPane;
	private JTable tableRegie;
	private JTable tableDarsteller;
	private JTextField txtDarsteller;
	private JTextField txtRegie;
	private Object[][] inhalt_darsteller;
	private ArrayList<ArrayList<String>> ergebnis_darsteller = new ArrayList<ArrayList<String>>();
	private Object[][] inhalt_regie;
	private ArrayList<ArrayList<String>> ergebnis_regie = new ArrayList<ArrayList<String>>();
	private JScrollPane scrollPaneDarsteller;
	private JScrollPane scrollPaneRegie;
	private JLabel lblKeinSuchbegriffRegie;
	private JLabel lblKeinSuchbegriffDarsteller;
	private JLabel lblKeinSuchtrefferDarsteller;
	private JLabel lblKeinSuchtrefferRegie;

	/**
	 * Dem Personal der DVD-Verleihs ist es durch diese Maske moeglich neue
	 * Objekte in der Datenbank anzulegen.
	 */
	public Dia_neues_Objekt() {
		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 1011, 520);
		getContentPane().setLayout(new BorderLayout());
		final JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// Buttons
		btnNeuRegie = new JButton("Neu");
		btnNeuRegie.setBounds(884, 248, 97, 25);

		btnSucheRegie = new JButton("Suche");
		btnSucheRegie.setBounds(775, 248, 97, 25);

		btnNeuDarsteller = new JButton("Neu");
		btnNeuDarsteller.setBounds(884, 38, 97, 25);

		btnSucheDarsteller = new JButton("Suche");
		btnSucheDarsteller.setBounds(775, 38, 97, 25);

		// Labels
		lblArt = new JLabel("Art");
		lblArt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblArt.setForeground(new Color(245, 245, 245));
		lblArt.setBounds(67, 38, 56, 22);

		lblGenre = new JLabel("Genre");
		lblGenre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGenre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblGenre.setForeground(new Color(245, 245, 245));
		lblGenre.setBounds(291, 38, 56, 22);

		lblFormat = new JLabel("Format");
		lblFormat.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFormat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFormat.setForeground(new Color(245, 245, 245));
		lblFormat.setBounds(67, 73, 56, 22);

		lblTitel = new JLabel("Titel");
		lblTitel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTitel.setForeground(new Color(245, 245, 245));
		lblTitel.setBounds(47, 110, 76, 20);

		lblHersteller = new JLabel("Hersteller");
		lblHersteller.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHersteller.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHersteller.setForeground(new Color(245, 245, 245));
		lblHersteller.setBounds(47, 137, 76, 22);

		lblProduktionsjahr = new JLabel("Produktionsjahr");
		lblProduktionsjahr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProduktionsjahr.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProduktionsjahr.setForeground(new Color(245, 245, 245));
		lblProduktionsjahr.setBounds(14, 165, 109, 23);

		lblFsk = new JLabel("Fsk");
		lblFsk.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFsk.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFsk.setForeground(new Color(245, 245, 245));
		lblFsk.setBounds(396, 165, 56, 23);

		lblOriginaltitel = new JLabel("Originaltitel");
		lblOriginaltitel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOriginaltitel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblOriginaltitel.setForeground(new Color(245, 245, 245));
		lblOriginaltitel.setBounds(24, 198, 99, 22);

		lblTags = new JLabel("Tags");
		lblTags.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTags.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTags.setForeground(new Color(245, 245, 245));
		lblTags.setBounds(64, 227, 56, 22);

		lblPreis = new JLabel("Preis");
		lblPreis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreis.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPreis.setForeground(new Color(245, 245, 245));
		lblPreis.setBounds(396, 73, 56, 22);

		lblAnzahl = new JLabel("Anzahl");
		lblAnzahl.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnzahl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAnzahl.setForeground(new Color(245, 245, 245));
		lblAnzahl.setBounds(253, 73, 56, 22);

		lblBezeichnung = new JLabel("Beschreibung");
		lblBezeichnung.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBezeichnung.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblBezeichnung.setForeground(new Color(245, 245, 245));
		lblBezeichnung.setBounds(31, 262, 92, 26);

		lblDarsteller = new JLabel("Darsteller");
		lblDarsteller.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDarsteller.setForeground(new Color(245, 245, 245));
		lblDarsteller.setBounds(553, 42, 92, 21);

		lblRegie = new JLabel("Regie");
		lblRegie.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblRegie.setForeground(new Color(245, 245, 245));
		lblRegie.setBounds(553, 249, 92, 20);

		lblKeinSuchbegriffRegie = new JLabel("Bitte Suchbegriff eingeben!");
		lblKeinSuchbegriffRegie.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblKeinSuchbegriffRegie.setForeground(Color.RED);
		lblKeinSuchbegriffRegie.setVisible(false);
		lblKeinSuchbegriffRegie.setBounds(621, 269, 142, 14);

		lblKeinSuchbegriffDarsteller = new JLabel("Bitte Suchbegriff eingeben!");
		lblKeinSuchbegriffDarsteller.setVisible(false);
		lblKeinSuchbegriffDarsteller.setForeground(Color.RED);
		lblKeinSuchbegriffDarsteller.setFont(new Font("Segoe UI", Font.PLAIN,
				11));
		lblKeinSuchbegriffDarsteller.setBounds(621, 61, 142, 14);

		lblKeinSuchtrefferDarsteller = new JLabel(
				"Es wurde kein Suchtreffer erzielt.");
		lblKeinSuchtrefferDarsteller.setVisible(false);
		lblKeinSuchtrefferDarsteller.setFont(new Font("Segoe UI", Font.PLAIN,
				11));
		lblKeinSuchtrefferDarsteller.setForeground(Color.RED);
		lblKeinSuchtrefferDarsteller.setBounds(621, 62, 191, 14);

		lblKeinSuchtrefferRegie = new JLabel(
				"Es wurde kein Suchtreffer erzielt.");
		lblKeinSuchtrefferRegie.setVisible(false);
		lblKeinSuchtrefferRegie.setForeground(Color.RED);
		lblKeinSuchtrefferRegie.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblKeinSuchtrefferRegie.setBounds(621, 270, 191, 14);

		// ComboBoxen
		dropdownKategorie = new JComboBox();
		dropdownKategorie.setBounds(135, 39, 92, 22);

		dropdownGenre = new JComboBox();
		dropdownGenre.setBounds(358, 39, 162, 22);

		dropdownFormat = new JComboBox();
		dropdownFormat.setBounds(135, 74, 92, 22);

		// TextFields
		txtTitel = new JTextField();
		txtTitel.setBounds(135, 108, 385, 22);
		txtTitel.setColumns(10);

		txtHersteller = new JTextField();
		txtHersteller.setBounds(135, 137, 385, 22);
		txtHersteller.setColumns(10);

		txtJahr = new JTextField();
		txtJahr.setBounds(135, 166, 76, 22);
		txtJahr.setColumns(10);

		txtFsk = new JTextField();
		txtFsk.setBounds(464, 166, 56, 22);
		txtFsk.setColumns(10);

		txtOriginaltitel = new JTextField();
		txtOriginaltitel.setBounds(135, 198, 385, 22);
		txtOriginaltitel.setColumns(10);

		txtTags = new JTextField();
		txtTags.setBounds(135, 227, 385, 22);
		txtTags.setColumns(10);

		txtPreis = new JTextField();
		txtPreis.setBounds(464, 73, 56, 22);
		txtPreis.setColumns(10);

		txtAnzahl = new JTextField();
		txtAnzahl.setBounds(321, 73, 46, 22);
		txtAnzahl.setColumns(10);

		txtDarsteller = new JTextField();
		txtDarsteller.setBounds(621, 39, 142, 22);
		txtDarsteller.setColumns(10);

		txtRegie = new JTextField();
		txtRegie.setBounds(621, 248, 142, 22);
		txtRegie.setColumns(10);

		// TextPane
		txtBeschreibung = new JTextPane();
		txtBeschreibung.setBounds(135, 262, 385, 147);

		// ScrollPane
		scrollPaneDarsteller = new JScrollPane();
		scrollPaneDarsteller.setBounds(553, 76, 428, 159);

		scrollPaneRegie = new JScrollPane();
		scrollPaneRegie.setBounds(553, 284, 428, 125);

		// Tabellen
		tableDarsteller = new JTable();
		tableRegie = new JTable();

		// Elemente dem contentPanel hinzufuegen
		contentPanel.add(btnNeuDarsteller);
		contentPanel.add(btnSucheDarsteller);
		contentPanel.add(btnNeuRegie);
		contentPanel.add(btnSucheRegie);

		contentPanel.add(lblTitel);
		contentPanel.add(lblPreis);
		contentPanel.add(lblBezeichnung);
		contentPanel.add(lblAnzahl);
		contentPanel.add(lblFsk);
		contentPanel.add(lblTags);
		contentPanel.add(lblOriginaltitel);
		contentPanel.add(lblHersteller);
		contentPanel.add(lblProduktionsjahr);
		contentPanel.add(lblFormat);
		contentPanel.add(lblGenre);
		contentPanel.add(lblArt);
		contentPanel.add(lblDarsteller);
		contentPanel.add(lblRegie);
		contentPanel.add(lblKeinSuchbegriffRegie);
		contentPanel.add(lblKeinSuchbegriffDarsteller);
		contentPanel.add(lblKeinSuchtrefferDarsteller);
		contentPanel.add(lblKeinSuchtrefferRegie);

		contentPanel.add(dropdownKategorie);
		contentPanel.add(dropdownFormat);
		contentPanel.add(dropdownGenre);

		contentPanel.add(txtBeschreibung);
		contentPanel.add(txtAnzahl);
		contentPanel.add(txtPreis);
		contentPanel.add(txtTags);
		contentPanel.add(txtOriginaltitel);
		contentPanel.add(txtFsk);
		contentPanel.add(txtJahr);
		contentPanel.add(txtHersteller);
		contentPanel.add(txtTitel);
		contentPanel.add(txtDarsteller);
		contentPanel.add(txtRegie);

		contentPanel.add(scrollPaneDarsteller);
		contentPanel.add(scrollPaneRegie);

		scrollPaneDarsteller.setViewportView(tableDarsteller);
		scrollPaneRegie.setViewportView(tableRegie);

		// ButtonPane
		buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		lblNichtKorrekt = new JLabel(
				"Bitte alle Felder korrekt ausf\u00FCllen!");
		lblNichtKorrekt.setVisible(false);
		lblNichtKorrekt.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNichtKorrekt.setForeground(Color.RED);
		buttonPane.add(lblNichtKorrekt);

		// Buttons
		btnObjekt_anlegen = new JButton("Objekt anlegen");
		btnObjekt_anlegen.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnObjekt_anlegen.setActionCommand("OK");
		buttonPane.add(btnObjekt_anlegen);
		getRootPane().setDefaultButton(btnObjekt_anlegen);

		btnZuruecksetzen = new JButton("Zur\u00FCcksetzen");
		btnZuruecksetzen.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnZuruecksetzen.setActionCommand("Cancel");
		buttonPane.add(btnZuruecksetzen);

	}

	public void clearTable(boolean darsteller) {
		if (darsteller) {
			ergebnis_darsteller = new ArrayList<ArrayList<String>>();
		} else {
			ergebnis_regie = new ArrayList<ArrayList<String>>();
		}

	}

	public void clearTableDarsteller() {
		tableDarsteller.removeAll();
	}

	public void clearTableRegie() {
		tableRegie.removeAll();
	}

	public String getcbArt() {
		return String.valueOf(dropdownKategorie.getSelectedItem());
	}

	public String getcbFormat() {
		return String.valueOf(dropdownFormat.getSelectedItem());
	}

	public ArrayList<String> getDarsteller() {
		ArrayList<String> darsteller = new ArrayList<String>();
		int anzahl = tableDarsteller.getRowCount();
		for (int i = 0; i < anzahl; i++) {
			if ((boolean) tableDarsteller.getModel().getValueAt(i, 3)) {
				darsteller.add(String.valueOf(tableDarsteller.getModel()
						.getValueAt(i, 2)));
			}

		}
		return darsteller;
	}

	public String getDropdownFormat() {
		return String.valueOf(dropdownFormat.getSelectedItem());
	}

	public String getDropdownGenre() {
		return String.valueOf(dropdownGenre.getSelectedItem());
	}

	public String getDropdownKategorie() {
		return String.valueOf(dropdownKategorie.getSelectedItem());
	}

	public ArrayList<String> getRegie() {
		ArrayList<String> regie = new ArrayList<String>();
		int anzahl = tableRegie.getRowCount();
		for (int i = 0; i < anzahl; i++) {
			if ((boolean) tableRegie.getModel().getValueAt(i, 3)) {
				regie.add(String
						.valueOf(tableRegie.getModel().getValueAt(i, 2)));
			}

		}
		return regie;
	}

	public String getTxtAnzahl() {
		if (txtAnzahl.getText().matches("[0-9]*")
				&& (txtAnzahl.getText().length() <= 2))
			return txtAnzahl.getText();
		else
			return "";
	}

	public String getTxtBeschreibung() {
		return txtBeschreibung.getText();
	}

	public String getTxtDarsteller() {
		if (txtDarsteller.getText().matches("[a-zA-Z]*"))
			return txtDarsteller.getText();
		else
			return "";
	}

	public String getTxtFsk() {
		if (txtFsk.getText().matches("[0-9]*")
				&& (txtFsk.getText().length() <= 2))
			return txtFsk.getText();
		else
			return "";
	}

	public String getTxtHersteller() {
		if (txtHersteller.getText().matches("[a-zA-Z0-9]*"))
			return txtHersteller.getText();
		else
			return "";
	}

	public String getTxtJahr() {
		if (txtJahr.getText().matches("[0-9]*")
				&& (txtJahr.getText().length() == 4))
			return txtJahr.getText();
		else
			return "";
	}

	public String getTxtOriginaltitel() {
		return txtOriginaltitel.getText();
	}

	public String getTxtPreis() {
		return txtPreis.getText();
	}

	public String getTxtRegie() {
		if (txtRegie.getText().matches("[a-zA-Z]*"))
			return txtRegie.getText();
		else
			return "";
	}

	public String getTxtTags() {
		return txtTags.getText();
	}

	// Getter
	public String getTxtTitel() {
		return txtTitel.getText();
	}

	public void setDropdownFormat(String kategorie) {
		if (kategorie.equals("Film")) {
			dropdownFormat.setModel(new DefaultComboBoxModel(
					Formate_Film_aendern.values()));
		} else if (kategorie.equals("Spiel")) {
			dropdownFormat.setModel(new DefaultComboBoxModel(
					Formate_Spiel_aendern.values()));
		} else if (kategorie.equals("Zubehör")) {
			dropdownFormat.setModel(new DefaultComboBoxModel(
					Formate_Zubehoer_aendern.values()));
		}
	}

	public void setDropdownGenre(boolean b) {
		dropdownGenre.setEnabled(b);
	}

	public void setDropdownGenre(String kategorie) {
		if (kategorie.equals("Film")) {
			dropdownGenre
					.setModel(new DefaultComboBoxModel(Genre_Film.values()));
		} else if (kategorie.equals("Spiel")) {
			dropdownGenre.setModel(new DefaultComboBoxModel(Genre_Spiel
					.values()));
		} else if (kategorie.equals("Zubehör")) {
			dropdownGenre.setModel(new DefaultComboBoxModel(Genre_Zubehoer
					.values()));
		}
	}

	public void setDropdownKategorie(String kategorie) {
		dropdownKategorie
				.setModel(new DefaultComboBoxModel(Kategorie.values()));
	}

	public void setFilmDaten(boolean b) {
		tableDarsteller.setVisible(b);
		scrollPaneDarsteller.setVisible(b);
		tableRegie.setVisible(b);
		scrollPaneRegie.setVisible(b);
		btnSucheDarsteller.setVisible(b);
		btnSucheRegie.setVisible(b);
		btnNeuDarsteller.setVisible(b);
		btnNeuRegie.setVisible(b);
		lblDarsteller.setVisible(b);
		txtDarsteller.setVisible(b);
		lblRegie.setVisible(b);
		txtRegie.setVisible(b);
	}

	public void setLblKeinSuchbegriffDarsteller(boolean b) {
		lblKeinSuchbegriffDarsteller.setVisible(b);
	}

	public void setLblKeinSuchbegriffRegie(boolean b) {
		lblKeinSuchbegriffRegie.setVisible(b);
	}

	public void setLblKeinSuchtrefferDarsteller(boolean b) {
		lblKeinSuchtrefferDarsteller.setVisible(b);
	}

	public void setLblKeinSuchtrefferRegie(boolean b) {
		lblKeinSuchtrefferRegie.setVisible(b);
	}

	public void setLblNichtKorrekt(boolean b) {
		lblNichtKorrekt.setVisible(b);
	}

	// Setter
	public void setLblTitel(String lblTitel) {
		this.lblTitel.setText(lblTitel);
	}

	public void setListener_BtnNeuDarsteller(MouseAdapter ma) {
		btnNeuDarsteller.addMouseListener(ma);
	}

	public void setListener_BtnNeuRegie(MouseAdapter ma) {
		btnNeuRegie.addMouseListener(ma);
	}

	// Listener
	public void setListener_BtnObjektAnlegen(MouseAdapter ma) {
		btnObjekt_anlegen.addMouseListener(ma);
	}

	public void setListener_BtnSucheDarsteller(MouseAdapter ma) {
		btnSucheDarsteller.addMouseListener(ma);
	}

	public void setListener_BtnSucheRegie(MouseAdapter ma) {
		btnSucheRegie.addMouseListener(ma);
	}

	public void setListener_BtnZuruecksetzen(MouseAdapter ma) {
		btnZuruecksetzen.addMouseListener(ma);
	}

	public void setListener_CbArt(ActionListener al) {
		dropdownKategorie.addActionListener(al);
	}

	public void setListener_CbFormat(ActionListener al) {
		dropdownFormat.addActionListener(al);
	}

	public void setNewRows(ArrayList<ArrayList<String>> rows, boolean darsteller) {
		System.out.println("neue reihen werden gesetzt");
		System.out.println(rows.toString());
		for (int i = 0; i < rows.size(); i++) {
			ArrayList<String> zw = new ArrayList<String>();
			// if (darsteller){
			// System.out.println(String.valueOf(ergebnis_darsteller.size()+1));
			// zw.add(String.valueOf(ergebnis_darsteller.size()+1));
			// }
			// else{
			// zw.add(String.valueOf(ergebnis_regie.size()+1));
			// }
			for (int j = 0; j < rows.get(i).size(); j++) {
				// System.out.println(rows.get(i).get(j));
				zw.add(rows.get(i).get(j));
			}
			if (darsteller) {
				ergebnis_darsteller.add(zw);
			} else {
				ergebnis_regie.add(zw);
			}

		}

	}

	public void setTxtAnzahl(String txtAnzahl) {
		this.txtAnzahl.setText(txtAnzahl);
	}

	// Setter

	public void setTxtBeschreibung(String txtBeschreibung) {
		this.txtBeschreibung.setText(txtBeschreibung);
	}

	public void setTxtDarsteller(String txtDarsteller) {
		this.txtDarsteller.setText(txtDarsteller);
	}

	public void setTxtFsk(String txtFsk) {
		this.txtFsk.setText(txtFsk);
	}

	public void setTxtHersteller(String txtHersteller) {
		this.txtHersteller.setText(txtHersteller);
	}

	public void setTxtJahr(String txtJahr) {
		this.txtJahr.setText(txtJahr);
	}

	public void setTxtOriginaltitel(String txtOriginaltitel) {
		this.txtOriginaltitel.setText(txtOriginaltitel);
	}

	public void setTxtPreis(String txtPreis) {
		this.txtPreis.setText(txtPreis);
	}

	public void setTxtRegie(String txtRegie) {
		this.txtRegie.setText(txtRegie);
	}

	public void setTxtTags(String txtTags) {
		this.txtTags.setText(txtTags);
	}

	public void setTxtTitel(String txtTitel) {
		this.txtTitel.setText(txtTitel);
	}

	public void updateInhalt(boolean darsteller) {
		ArrayList<ArrayList<String>> zwergebnis;
		if (darsteller) {
			zwergebnis = ergebnis_darsteller;
		} else {
			zwergebnis = ergebnis_regie;
		}
		Object[][] zwinhalt = new Object[zwergebnis.size()][4];
		for (int i = 0; i < zwergebnis.size(); i++) {
			// System.out.println(ergebnis.get(i).get(1));
			zwinhalt[i][0] = zwergebnis.get(i).get(0);
			zwinhalt[i][1] = zwergebnis.get(i).get(1);
			zwinhalt[i][2] = zwergebnis.get(i).get(2);
			zwinhalt[i][3] = Boolean.TRUE;
		}
		if (darsteller) {
			inhalt_darsteller = zwinhalt;
		} else {
			inhalt_regie = zwinhalt;
		}
		System.out.println("inhalt wurde gesetzt");
	}

	public void updateTable(boolean darsteller) {
		if (darsteller) {
			tableDarsteller.setModel(new DefaultTableModel(inhalt_darsteller,
					new String[] { "Vorname", "Nachname", "ID", "Auswahl" }) {
				/**
						 * 
						 */
				private static final long serialVersionUID = -269815045580809523L;

				Class[] columnTypes = new Class[] { String.class, String.class,
						Integer.class, Boolean.class };

				boolean[] columnEditables = new boolean[] { false, false,
						false, true };

				@Override
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}

				@Override
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		} else {
			tableRegie.setModel(new DefaultTableModel(inhalt_regie,
					new String[] { "Vorname", "Nachname", "ID", "Auswahl" }) {
				/**
						 * 
						 */
				private static final long serialVersionUID = -7307282465091118627L;

				Class[] columnTypes = new Class[] { String.class, String.class,
						Integer.class, Boolean.class };

				boolean[] columnEditables = new boolean[] { false, false,
						false, true };

				@Override
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}

				@Override
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		}

	}
}