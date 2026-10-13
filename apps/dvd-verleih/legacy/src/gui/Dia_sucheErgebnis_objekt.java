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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Dia_sucheErgebnis_objekt extends JDialog {

	/**
	 * Beim Neuanlegen eines Objektes kann nach schon in der Datenbank
	 * angelegten Regisseuren gesucht werden, um sie dem neuen Film zuzuordnen.
	 */
	private static final long serialVersionUID = -6047025020729409990L;
	private final JPanel contentPanel = new JPanel();
	private JTable table_1;
	private JButton btnAusgewaehlte_anzeigen;
	private Object[][] inhalt;
	private JLabel lblKeineAuswahl;

	public Dia_sucheErgebnis_objekt() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 785, 364);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 11, 747, 273);

		// Table
		table_1 = new JTable();
		table_1.setForeground(new Color(245, 245, 245));
		table_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table_1.setBackground(Color.DARK_GRAY);

		// Label
		lblKeineAuswahl = new JLabel("Es wurde nichts ausgew\u00E4hlt!");
		lblKeineAuswahl.setForeground(Color.RED);
		lblKeineAuswahl.setVisible(false);

		// Button
		btnAusgewaehlte_anzeigen = new JButton("Ausgew\u00E4hlte anzeigen");
		btnAusgewaehlte_anzeigen.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAusgewaehlte_anzeigen.setActionCommand("OK");
		getRootPane().setDefaultButton(btnAusgewaehlte_anzeigen);

		// Hinzufuegen
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// ScrollPane
		contentPanel.add(scrollPane);

		// Table
		scrollPane.setViewportView(table_1);

		// Label
		buttonPane.add(lblKeineAuswahl);

		// Button
		buttonPane.add(btnAusgewaehlte_anzeigen);

	}

	public ArrayList<ArrayList<String>> auswahl() {
		System.out.println("es wird ausgewaehlt");
		ArrayList<ArrayList<String>> rturn = new ArrayList<ArrayList<String>>();
		ArrayList<String> werte = new ArrayList<String>();
		for (int i = 0; i < inhalt.length; i++) {
			if ((boolean) table_1.getModel().getValueAt(i, 7)) {
				werte = new ArrayList<String>();
				werte.add((String) table_1.getModel().getValueAt(i, 1));
				werte.add((String) table_1.getModel().getValueAt(i, 0));
				werte.add((String) table_1.getModel().getValueAt(i, 8));
				rturn.add(werte);
			}
		}
		return rturn;

	}

	public void setBtnAusgewaehlte_anzeigen(boolean b) {
		btnAusgewaehlte_anzeigen.setEnabled(b);
	}

	public void setInhalt(String[][] ergebnis) {
		inhalt = new Object[ergebnis.length][9];
		for (int i = 0; i < ergebnis.length; i++) {
			inhalt[i][0] = ergebnis[i][0]; // Art
			inhalt[i][1] = ergebnis[i][1]; // Titel
			inhalt[i][2] = ergebnis[i][2]; // Originaltitel
			if (ergebnis[i].length > 7) {
				inhalt[i][3] = ergebnis[i][7] + " " + ergebnis[i][8];
			} else {
				inhalt[i][3] = "";
			}// Regie bzw. Hersteller
			inhalt[i][4] = ergebnis[i][3]; // FSK
			inhalt[i][5] = ergebnis[i][4]; // Jahr
			inhalt[i][6] = ergebnis[i][5]; // Format
			inhalt[i][7] = Boolean.FALSE; // Auswahl
			inhalt[i][8] = ergebnis[i][6]; // ID
		}
	}

	public void setLblKeineAuswahl(boolean b) {
		lblKeineAuswahl.setVisible(b);
	}

	public void setListener_BtnAusgewaehlte_anzeigen(MouseAdapter ma) {
		btnAusgewaehlte_anzeigen.addMouseListener(ma);
	}

	public void updateTable(String regie_herst) {
		table_1.setModel(new DefaultTableModel(inhalt, new String[] { "Art",
				"Titel", "Originaltitel", regie_herst, "FSK", "Jahr", "Format",
				"Auswahl", "ID" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 7451062358100388008L;

			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, Integer.class, Integer.class,
					String.class, Boolean.class, Integer.class };

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, true, false };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(1).setResizable(true);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(120);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(33);
		table_1.getColumnModel().getColumn(7).setResizable(false);
		table_1.getColumnModel().getColumn(7).setPreferredWidth(35);
		table_1.getColumnModel().getColumn(8).setResizable(true);
		table_1.getColumnModel().getColumn(8).setPreferredWidth(1);
	}
}
