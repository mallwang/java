package dvdverleih.gui;

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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Dia_leihen extends JDialog {
	/**
	 * Dieser Dialog enthaelt eine Tabelle, in welcher man alle Objekte
	 * "sammelt", welche der Kunde ausleihen moechte.
	 */
	private static final long serialVersionUID = -8431849378042358335L;
	private JTable table;
	private JButton btnLeihen;
	private JButton btnSuche;
	private Object[][] inhalt;
	private JLabel lblKeineAuswahl;
	private ArrayList<ArrayList<String>> ergebnis = new ArrayList<ArrayList<String>>();

	public Dia_leihen(final String id) {
		getContentPane().setBackground(Color.DARK_GRAY);
		setBackground(Color.DARK_GRAY);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		setBounds(100, 100, 673, 436);
		getContentPane().repaint();
		getContentPane().setLayout(null);

		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBounds(0, 0, 644, 391);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setBounds(10, 348, 634, 33);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 634, 289);

		// Tabelle
		table = new JTable();
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.setForeground(new Color(245, 245, 245));
		table.setBackground(Color.DARK_GRAY);

		// Button
		btnSuche = new JButton("Suche");
		btnSuche.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSuche.setBounds(545, 11, 89, 23);

		btnLeihen = new JButton("Leihen");
		btnLeihen.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnLeihen.setActionCommand("OK");
		getRootPane().setDefaultButton(btnLeihen);

		// Hinzufuegen
		getContentPane().add(contentPanel);
		contentPanel.add(buttonPane);
		contentPanel.add(scrollPane);

		// Tabelle
		scrollPane.setViewportView(table);

		// Button
		contentPanel.add(btnSuche);

		// Label
		lblKeineAuswahl = new JLabel("Es wurde nichts ausgew\u00E4hlt!");
		lblKeineAuswahl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblKeineAuswahl.setForeground(Color.RED);
		lblKeineAuswahl.setVisible(false);

		// Label
		buttonPane.add(lblKeineAuswahl);
		buttonPane.add(btnLeihen);

	}

	public ArrayList<ArrayList<String>> auswahl() {
		int reihen = table.getRowCount();
		ArrayList<ArrayList<String>> rturn = new ArrayList<ArrayList<String>>();
		ArrayList<String> zw = new ArrayList<String>();
		for (int i = 0; i < reihen; i++) {

			if ((boolean) table.getModel().getValueAt(i, 5)) {
				zw = new ArrayList<String>();
				zw.add(String.valueOf(table.getModel().getValueAt(i, 3)));
				zw.add(String.valueOf(table.getModel().getValueAt(i, 4)));
				System.out.println((String.valueOf(table.getModel().getValueAt(
						i, 4))));
				rturn.add(zw);
				System.out.println(rturn.toString());
			}
		}
		return rturn;

	}

	public void setLblKeineAuswahl(boolean b) {
		lblKeineAuswahl.setVisible(b);
	}

	public void setListener_BtnLeihen(MouseAdapter ma) {
		btnLeihen.addMouseListener(ma);
	}

	public void setListener_BtnSuche(MouseAdapter ma) {
		btnSuche.addMouseListener(ma);
	}

	public void setNewRows(ArrayList<ArrayList<String>> rows) {
		System.out.println("neue reihen werden gesetzt");
		for (int i = 0; i < rows.size(); i++) {
			ArrayList<String> zw = new ArrayList<String>();
			zw.add(String.valueOf(ergebnis.size() + 1));
			for (int j = 0; j < rows.get(i).size(); j++) {
				zw.add(rows.get(i).get(j));
			}
			ergebnis.add(zw);
		}
	}

	public void updateInhalt() {

		inhalt = new Object[ergebnis.size()][6];
		for (int i = 0; i < ergebnis.size(); i++) {
			inhalt[i][1] = ergebnis.get(i).get(1);
			System.out.println(ergebnis.get(i).get(1));
			inhalt[i][2] = ergebnis.get(i).get(2);
			inhalt[i][3] = ergebnis.get(i).get(3);
			inhalt[i][4] = 1;
			inhalt[i][0] = i + 1;
			inhalt[i][5] = Boolean.TRUE;
		}
		System.out.println("inhalt wurde gesetzt");
	}

	public void updateTable() {
		table.setModel(new DefaultTableModel(inhalt, new String[] { "Nr.",
				"Artikelname", "Art", "ID", "Anzahl", "Auswahl" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 6428003706285907043L;
			Class[] columnTypes = new Class[] { Integer.class, String.class,
					String.class, Integer.class, Integer.class, Boolean.class };

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, true, true };

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
		table.getColumnModel().getColumn(0).setPreferredWidth(32);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(153);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(64);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(32);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(43);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
	}
}
