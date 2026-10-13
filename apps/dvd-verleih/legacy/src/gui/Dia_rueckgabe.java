package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Dia_rueckgabe extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3093781415488428669L;
	/**
	 * 
	 */

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JLabel knr;
	private JLabel summe;
	private JLabel lblKeineAuswahl;
	private Object[][] inhalt;

	private JButton btnAnders_bezahlen;

	/**
	 * In diesem Dialog werden alle vom jeweiligen Benutzer ausgeliehenen
	 * Objekte aufgelistet.
	 */
	public Dia_rueckgabe() {
		setBounds(100, 100, 633, 366);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// Label
		JLabel lblKnr = new JLabel("Kundennummer:");
		lblKnr.setForeground(new Color(245, 245, 245));
		lblKnr.setBounds(66, 31, 111, 16);
		lblKnr.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		JLabel lblSumme = new JLabel("Summe");
		lblSumme.setForeground(new Color(245, 245, 245));
		lblSumme.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSumme.setBounds(372, 195, 56, 16);

		summe = new JLabel();
		summe.setForeground(new Color(245, 245, 245));
		summe.setBounds(440, 195, 68, 16);
		summe.setHorizontalAlignment(SwingConstants.RIGHT);
		summe.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		knr = new JLabel();
		knr.setForeground(new Color(245, 245, 245));
		knr.setBounds(230, 31, 56, 16);
		knr.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		lblKeineAuswahl = new JLabel("Es wurde nichts ausgew\u00E4hlt!");
		lblKeineAuswahl.setVisible(false);
		lblKeineAuswahl.setForeground(Color.RED);

		// Seperator
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 60, 251, 2);

		// Button
		btnAnders_bezahlen = new JButton("Bezahlen");
		btnAnders_bezahlen.setActionCommand("OK");
		getRootPane().setDefaultButton(btnAnders_bezahlen);

		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 75, 597, 107);

		// Table
		table = new JTable();

		// Hinzufuegen
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// Label
		contentPanel.add(lblKnr);
		contentPanel.add(lblSumme);
		contentPanel.add(summe);
		contentPanel.add(knr);
		buttonPane.add(lblKeineAuswahl);

		// Seperator
		contentPanel.add(separator);

		// Button
		buttonPane.add(btnAnders_bezahlen);

		// ScrollPane
		contentPanel.add(scrollPane);

		// Table
		scrollPane.setViewportView(table);

	}

	public ArrayList<ArrayList<String>> auswahl() {
		int reihen = table.getRowCount();
		ArrayList<ArrayList<String>> rturn = new ArrayList<ArrayList<String>>();
		ArrayList<String> zw = new ArrayList<String>();
		for (int i = 0; i < reihen; i++) {

			if ((boolean) table.getModel().getValueAt(i, 5)) {
				zw = new ArrayList<String>();
				zw.add(String.valueOf(table.getModel().getValueAt(i, 6)));// ID
				zw.add(String.valueOf(table.getModel().getValueAt(i, 4)));// Gesamtpreis
				rturn.add(zw);
			}
		}
		return rturn;
	}

	public Float getSumme() {
		String s = summe.getText();
		s = s.substring(0, s.length() - 1);
		System.out.println("SUMME: " + s);
		return Float.valueOf(s);
	}

	public void setInhalt(String[][] ergebnis) {
		System.out.println("SET INHALT");
		inhalt = new Object[ergebnis.length][7];
		for (int i = 0; i < ergebnis.length; i++) {
			inhalt[i][0] = ergebnis[i][0]; // Name
			inhalt[i][1] = ergebnis[i][1]; // Anzahl
			inhalt[i][2] = ergebnis[i][2]; // Tage
			inhalt[i][3] = ergebnis[i][3];// Grundpreis
			inhalt[i][4] = Integer.valueOf(ergebnis[i][1])
					* Integer.valueOf(ergebnis[i][2])
					* Float.valueOf(ergebnis[i][3]); // Endpreis
			inhalt[i][5] = Boolean.FALSE; // Auswahl
			inhalt[i][6] = ergebnis[i][4]; // ID
		}
	}

	public void setKnr(String knr) {
		this.knr.setText(knr);
	}

	public void setLblKeineAuswahl(boolean b) {
		lblKeineAuswahl.setVisible(b);
	}

	public void setListener_BtnAnders_bezahlen(MouseAdapter ma) {
		btnAnders_bezahlen.addMouseListener(ma);
	}

	public void setListener_table(TableModelListener tml) {
		table.getModel().addTableModelListener(tml);
	}

	public void setSumme(String summe) {
		this.summe.setText(summe + "\u20AC");
	}

	public void updateTable() {
		System.out.println("UPDATE TABLE");
		table.setModel(new DefaultTableModel(inhalt, new String[] { "Name",
				"Anzahl", "Tage", "Grundpreis", "Endpreis", "Auswahl", "ID" }) {

			private static final long serialVersionUID = 2419519718993811497L;
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class, Boolean.class,
					String.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, true, false };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(124);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(63);
		table.getColumnModel().getColumn(6).setPreferredWidth(26);
	}
}
